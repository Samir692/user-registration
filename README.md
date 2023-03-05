# User Registration System Java SpringBoot

- Installation with docker
  - Clone the repository down to your local machine
  - Install docker-compose
  - Run ``` docker-compose up ```
  

- Local build
  - Build Mysql container
    - ```docker build -f DockerfileMysql -t findme692/user-registration-mysql .```
  - Build SpringBoot container
    - ```mvn clean install```
    - create environment variables for spring.datasource.url, spring.datasource.username, spring.datasource.password with the help of docker-compose.yml file
    - spin up mysql db and update the spring.datasource.url accordingly
    - ```docker build -t findme692/user-registration-spring-boot-app . ```
  - Run docker-compose
    - ``` docker-compose up ```