#!/bin/sh
set -e

# -------------------------
# Configuration (env vars)
# -------------------------
UNLEASH_URL="${UNLEASH_URL:-http://unleash-server:4242}"
ADMIN_USER="${UNLEASH_ADMIN_USER:-admin}"
ADMIN_PASSWORD="${UNLEASH_ADMIN_PASSWORD:-unleash4all}"
ADMIN_TOKEN="${UNLEASH_ADMIN_TOKEN}"
PROJECT="${UNLEASH_PROJECT:-default}"
ENVIRONMENT="${UNLEASH_ENVIRONMENT:-development}"
FEATURES="${FEATURES:-premium-pricing order-notifications bulk-order-discount}"

# -------------------------
# Wait for Unleash API
# -------------------------
sleep 5
echo "Waiting for Unleash API to be ready..."
until curl -s "$UNLEASH_URL/health" | grep -q '"health":"GOOD"'; do
    echo "Unleash not ready yet..."
    sleep 2
done
echo "Unleash is ready!"

# -------------------------
# Get or create admin PAT
# -------------------------
# echo "Checking for existing admin token..."
# ADMIN_TOKEN=$(curl -s -u "$ADMIN_USER:$ADMIN_PASSWORD" \
#     "$UNLEASH_URL/api/admin/user/tokens" | jq -r '.[] | select(.description=="init-flags token") | .token')

# if [ -z "$ADMIN_TOKEN" ] || [ "$ADMIN_TOKEN" = "null" ]; then
#     echo "No existing token found. Creating new admin token..."
#     ADMIN_TOKEN=$(curl -s -X POST "$UNLEASH_URL/api/admin/user/tokens" \
#         -u "$ADMIN_USER:$ADMIN_PASSWORD" \
#         -H "Content-Type: application/json" \
#         -d '{"description": "init-flags token"}' | jq -r '.token')
# fi

# if [ -z "$ADMIN_TOKEN" ] || [ "$ADMIN_TOKEN" = "null" ]; then
#     echo "Failed to create or retrieve admin token!"
#     exit 1
# fi

echo "Admin token ready."

# -------------------------
# Loop over features
# -------------------------
for FEATURE_NAME in $FEATURES; do
    echo "Processing feature '$FEATURE_NAME'..."

    # Check if feature exists
    EXISTS=$(curl -s -H "Authorization: $ADMIN_TOKEN" \
        "$UNLEASH_URL/api/admin/projects/$PROJECT/features" | jq -r ".features[] | select(.name==\"$FEATURE_NAME\") | .name")

    if [ "$EXISTS" = "$FEATURE_NAME" ]; then
        echo "Feature '$FEATURE_NAME' already exists."
    else
        echo "Creating feature '$FEATURE_NAME'..."
        curl -s -X POST "$UNLEASH_URL/api/admin/projects/$PROJECT/features" \
            -H "Authorization: $ADMIN_TOKEN" \
            -H "Content-Type: application/json" \
            -d "{
                \"name\": \"$FEATURE_NAME\",
                \"project\": \"$PROJECT\",
                \"description\": \"Auto-created feature flag\",
                \"type\": \"release\",
                \"stale\": false,
                \"enabled\": false
            }"
    fi

    # Check if strategy exists
    STRATEGY_EXISTS=$(curl -s -H "Authorization: $ADMIN_TOKEN" \
        "$UNLEASH_URL/api/admin/projects/$PROJECT/features/$FEATURE_NAME/environments/$ENVIRONMENT/strategies" | jq -r ".[] | select(.name==\"default\") | .name")

    if [ "$STRATEGY_EXISTS" = "default" ]; then
        echo "Strategy for '$FEATURE_NAME' in '$ENVIRONMENT' already exists."
    else
        echo "Adding 100% rollout strategy for '$FEATURE_NAME' in '$ENVIRONMENT'..."
        curl -s -X POST "$UNLEASH_URL/api/admin/projects/$PROJECT/features/$FEATURE_NAME/environments/$ENVIRONMENT/strategies" \
            -H "Authorization: $ADMIN_TOKEN" \
            -H "Content-Type: application/json" \
            -d "{
                \"name\": \"default\",
                \"parameters\": {\"percentage\":\"100\"}
            }"
    fi
done

echo "All features and strategies are set up. ✅"