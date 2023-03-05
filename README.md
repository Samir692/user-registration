# User Registration System Java SpringBoot

- Installation with docker
  - Clone the repository down to your local machine
  - Install docker-compose
  - Run ``` docker-compose up ```
  

- Local build
  - Build Mysql container
    - ```docker build -f DockerfileMysql -t findme692/user-registration-mysql .```
  - Build SpringBoot container
    - ```docker build -t findme692/user-registration-spring-boot-app . ```
  - Run docker-compose
    - ``` docker-compose up ```