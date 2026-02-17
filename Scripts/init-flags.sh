#!/bin/bash
# Init Unleash feature flags

# Make sure ADMIN_TOKEN is set
if [ -z "$ADMIN_TOKEN" ]; then
  echo "Please set ADMIN_TOKEN"
  exit 1
fi

UNLEASH_URL=${UNLEASH_API_URL:-http://localhost:4242/api}

# Array of flags: name|description
flags=(
  "order-notifications|Enable order notifications feature"
  "bulk-order-discount|Enable bulk order discount feature"
  "premium-pricing|Enable premium pricing discounts"
)

for flag in "${flags[@]}"; do
  name="${flag%%|*}"
  description="${flag##*|}"

  echo "Creating feature flag: $name"

  curl -s -X POST "$UNLEASH_URL/admin/projects/default/features" \
    -H "Authorization: Bearer $ADMIN_TOKEN" \
    -H "Content-Type: application/json" \
    -d "{
          \"name\": \"$name\",
          \"description\": \"$description\",
          \"type\": \"release\"
        }" \
    || echo "Failed or already exists: $name"
done
