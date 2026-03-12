Commands to Run for Assignment 3
Follow these commands in sequence for each part. Replace assignment3 with assignment3 (or your chosen namespace name). Ensure Minikube is installed and kubectl is configured. Run these in your terminal (e.g., VSCode integrated terminal). Capture screenshots/outputs as required for evidence.

Prerequisites
Build and push your Docker images (e.g., docker build -t product-service:latest ./ProductService and docker build -t order-service:latest ./OrderService).
Start Minikube: minikube start.
Part 1: Kubernetes Deployment Manifests
Validate YAML syntax (dry-run):

kubectl apply --dry-run=client -f k8s/namespace.yaml
kubectl apply --dry-run=client -f k8s/configmap.yaml
kubectl apply --dry-run=client -f k8s/product-deployment.yaml
kubectl apply --dry-run=client -f k8s/product-service.yaml
kubectl apply --dry-run=client -f k8s/order-deployment.yaml
kubectl apply --dry-run=client -f k8s/order-service.yaml
Apply all manifests:

kubectl apply -f k8s/

load docker images: minikube image load order-service:latest && minikube image load product-service:latest

Part 2: ReplicaSets, Scaling, and Self-Healing
Verify deployments: kubectl get deployments -n assignment3
Verify ReplicaSets: kubectl get replicasets -n assignment3
Verify Pods: kubectl get pods -n assignment3
Scale product-service: kubectl scale deployment product-service --replicas=4 -n assignment3
Verify scaled Pods: kubectl get pods -n assignment3
Delete a Pod (replace <pod-name> with an actual Pod name): kubectl delete pod <pod-name> -n assignment3
Watch Pod replacement: kubectl get pods -n assignment3 -w
Part 3: Rolling Update Demonstration
Check initial rollout: kubectl rollout status deployment/product-service -n assignment3
Modify k8s/product-deployment.yaml (e.g., change image tag to product-service:v2 or update ConfigMap).
Apply update: kubectl apply -f k8s/product-deployment.yaml
Check rollout status: kubectl rollout status deployment/product-service -n assignment3
Check rollout history: kubectl rollout history deployment/product-service -n assignment3
(Bonus) Rollback: kubectl rollout undo deployment/product-service -n assignment3
Part 4: Video Presentation
No specific commands; record the video demonstrating the above, including minikube service order-service -n assignment3 to access the NodePort service externally.
Cleanup (Optional)
Delete all resources: kubectl delete -f k8s/
Stop Minikube: minikube stop
