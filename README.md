# GitMiner project

## Description

Set of microservices that read and store data from different git cloud providers.

## Setup

Launch all 3 services using your preferred method(in your IDE or by using `mvn spring-boot:run`)

### GitLabMiner

- Under src/main/resources:
  - Copy `secrets.properties.template` to `secrets.properties`
  - Fill in the properties with your GitLab token.
- Runs on port 8081

### GitHubMiner

- Under src/main/resources:
  - Copy `secrets.properties.template` to `secrets.properties`
  - Fill in the properties with your GitHub token.
- Runs on port 8082

### GitMiner

- Runs on port 8080
