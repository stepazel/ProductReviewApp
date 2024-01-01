# ACME Application Architecture Iteration Report

## Executive Summary
The ACME application's transition to a microservices architecture marks a significant step in enhancing its operational efficiency and scalability. This architectural evolution addresses key non-functional requirements like performance under high demand, efficient hardware usage, increased release frequency, API stability, and adherence to SOA strategy and API-led connectivity.

## Introduction
Initially structured as a monolithic system, the ACME application faced challenges in scalability and agility. The project's second iteration focused on restructuring the application into a microservices architecture to address these challenges and improve overall system performance.

## Architectural Strategy
### High-Level Architectural Goals
The primary goal was to create an architecture that could handle increased loads (performance in high demand), use hardware resources efficiently (parsimonious hardware usage), and allow for more frequent and reliable releases (increased releasability frequency).

### Addressing Functional Requirements
The architecture was designed to meet specific functional needs:
- **Product Publishing:** Facilitating the publication of products post-approval by two other product managers.
- **Review Publishing:** Ensuring reviews are published only after receiving necessary endorsements.
- **Vote and Review Process:** Enabling the creation of votes and reviews in a unified process for certain user groups.

## Microservices Architecture
### Overview of Microservices
The adoption of microservices architecture was aimed at providing isolated, independently scalable services, each with its dedicated database, thus ensuring optimal performance and resource utilization.

## Service-Specific Design
### Individual Service Design
Each service within the ACME application, including Product, Review, and Vote services, was tailored to address specific functionalities, ensuring a high degree of modularity and independence.

### API Gateway
#### Centralized Request Handling
The API Gateway serves as the central point for all incoming requests, providing effective load management and ensuring API stability for client applications.

#### Authentication Management
Centralizing authentication at the API Gateway simplifies the security mechanism across services, enhancing overall system security.

## Deployment and Infrastructure
### Deployment Model
The deployment model was designed to ensure high availability and robustness, with services deployed across multiple nodes for load balancing and fault tolerance.

### Database Configuration
A polyglot persistence approach was adopted, with each service utilizing the most suitable database (MongoDB, Neo4j, H2) for its specific needs.

## Conclusion
The transition to a microservices architecture in the ACME application marks a significant improvement in meeting both functional and non-functional requirements. The new architecture enhances scalability, performance, and maintainability, aligning with the strategic objectives of the project.

