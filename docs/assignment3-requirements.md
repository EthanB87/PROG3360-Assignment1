# Overview

In this assignment, you will deploy your Spring Boot microservices application to a local Kubernetes cluster using Minikube. Building upon the microservices created in earlier assignments, you will define Kubernetes manifests for your services, deploy them to the cluster, scale the application, and demonstrate how Kubernetes uses Deployments and ReplicaSets to maintain application availability.

**Only use the tech stack that we have reviewed in class.**

# Instructions

## Part 1: Kubernetes Deployment Manifests (25 Marks)

Using your existing containerized microservices system, deploy the following services to Kubernetes:

- product-service
- order-service

### Required Kubernetes Resources

You must create the following Kubernetes resources:

- One Namespace for the assignment
- One Deployment for product-service
- One Deployment for order-service
- One Service for product-service
- One Service for order-service
- One ConfigMap for application configuration

### Technical Requirements

**Namespace**

- Create a dedicated namespace for the application
- All Kubernetes resources must be deployed into this namespace

**Product Service**

- Deploy using a Deployment manifest
- Use 2 replicas
- Expose the service using a ClusterIP service
- The application must run on the correct internal container port

**Order Service**

- Deploy using a Deployment manifest
- Use 2 replicas
- Expose the service using a NodePort service
- The service must be reachable from outside the cluster through Minikube

**ConfigMap**

Use a ConfigMap to store configuration such as:

- application environment name
- downstream service URL
- version label or other simple configuration values

**Labels and Selectors**

- All Pods must use labels
- Services must use selectors that correctly target the matching Pods

### Required Files

- k8s/namespace.yaml
- k8s/product-deployment.yaml
- k8s/product-service.yaml
- k8s/order-deployment.yaml
- k8s/order-service.yaml
- k8s/configmap.yaml

## Part 2: ReplicaSets, Scaling, and Self-Healing (25 Marks)

Demonstrate how Kubernetes manages application availability using Deployments and ReplicaSets.

### Required Tasks

1. Deploy the application and verify all Pods are running
2. Show the ReplicaSets created by each Deployment
3. Scale one Deployment from 2 replicas to 4 replicas
4. Delete one running Pod manually
5. Show that Kubernetes automatically creates a replacement Pod

### Required Evidence

You must provide screenshots or terminal output showing:

- kubectl get deployments
- kubectl get replicasets
- kubectl get pods
- scaling command and updated Pod count
- Pod deletion and replacement

### Suggested Commands

kubectl scale deployment product-service --replicas=4 -n <namespace>

kubectl delete pod <pod-name> -n <namespace>

kubectl get rs -n <namespace>

kubectl get pods -n <namespace> -w

## Part 3: Rolling Update Demonstration (25 Marks)

Demonstrate a rolling update using Kubernetes.

### Requirements

- Start with version 1 of one service
- Update the Deployment to version 2
- Apply the updated Deployment manifest
- Verify the rollout succeeds
- Show that the new version is available

### Implementation Options

You may demonstrate the version change by:

- changing the Docker image tag, or
- changing a simple API response such as /version

### Required Evidence

Provide screenshots or terminal output showing:

- the original running version
- updated Deployment manifest or image tag
- rollout status
- application response after the update

### Suggested Commands

kubectl apply -f k8s/product-deployment.yaml

kubectl rollout status deployment/product-service -n <namespace>

kubectl rollout history deployment/product-service -n <namespace>

### Bonus

Rollback the Deployment to the previous version:

kubectl rollout undo deployment/product-service -n <namespace>

## Part 4: Video Presentation (25 Marks)

Create a short video presentation on YouTube or another streaming provider. You must include a short PowerPoint (or Google Slides) slide deck that includes a single slide to start your video.

### Slide Requirements

The first (and only) slide of your slide deck must include:

- a current image of you (no avatars allowed)
- your full name(s)
- student ID(s)
- course code
- course name
- assignment information
- a link to your video

### Video Content Requirements

Your video must demonstrate:

- Minikube cluster running
- all Kubernetes resources deployed
- Deployments, ReplicaSets, Pods, and Services
- scaling from 2 replicas to 4 replicas
- deletion of a Pod and automatic replacement
- rolling update from one version to another
- a brief explanation of labels, selectors, Deployments, and ReplicaSets

### Technical Requirements

- sound must be clear
- screen resolution must make code and terminal output readable
- video should run no more than 15 minutes
- video may be unlisted but must be accessible to the instructor

# Assessment

| **Criteria**                                       | **Not Quite (0–8 marks)**                                                                 | **Good Work (9–17 marks)**                                                               | **Awesome (18–25 marks)**                                                                                                          |
| -------------------------------------------------- | ----------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| **Kubernetes Manifests (25 Marks)**                | Missing manifests or significant YAML errors; labels or selectors do not match            | All manifests present, services connect correctly, basic labels and selectors configured | Full implementation with correct replicas, proper ClusterIP and NodePort services, namespace isolation, and ConfigMap integration  |
| **ReplicaSets, Scaling & Self-Healing (25 Marks)** | Scaling or self-healing not demonstrated; missing required screenshots or terminal output | Scaling and self-healing demonstrated with some supporting evidence                      | All five tasks completed with clear before-and-after evidence showing ReplicaSet creation, scaling, and automatic Pod replacement  |
| **Rolling Update (25 Marks)**                      | Rolling update not attempted or fails; no evidence provided                               | Rolling update demonstrated with at least two pieces of required evidence                | Full rolling update demonstrated with all four evidence items; bonus rollback completed                                            |
| **Video Presentation (25 Marks)**                  | Poor audio or video quality; incomplete demonstration; concepts not explained             | Clear presentation; most scenarios shown live; basic explanation provided                | Professional quality; all required demonstrations shown live; clear explanation of labels, selectors, Deployments, and ReplicaSets |

# Submission Details

Your submission should include:

1. A zip archive of your Project files (Mandatory)

- Format: assignment3\_{StudentLastName}.zip

1. 1 Slide Deck with a link to your Video Presentation
2. Submit your GitHub repository URL

- Ensure the repository is public or the instructor has been granted access

# Assignment Due Date

See Class Website

# Late Submission Policy

| **Days Late** | **Penalty**              |
| ------------- | ------------------------ |
| 1 day late    | -20% cumulative penalty  |
| 2 days late   | -40% cumulative penalty  |
| 3 days late   | -100% cumulative penalty |

# Assignment Weight

Assignment Three is worth 15% of your final grade.

Please submit your assignment online under Assignments > Assignment Three.

**⚠️  Important**

Please ensure that any work you submit is your own unique and independent work. Work submitted that is found to be not your own unique and independent work will be subjected to a grade of 0 and considered to be academic misconduct.

# Additional Resources

- [Kubernetes Documentation – Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
- [Kubernetes Documentation – Services](https://kubernetes.io/docs/concepts/services-networking/service/)
- [Kubernetes Documentation – ConfigMaps](https://kubernetes.io/docs/concepts/configuration/configmap/)
- [Minikube Getting Started](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl Cheat Sheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)
- [Kubernetes – Rolling Updates](https://kubernetes.io/docs/tutorials/kubernetes-basics/update/update-intro/)
