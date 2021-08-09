FROM ubuntu
RUN apt update && apt upgrade -y
RUN apt install openjdk-11-jdk -y
RUN apt install maven -y
COPY . /opt/server
WORKDIR /opt/server
RUN mvn clean package
ENTRYPOINT java -jar /opt/server/target/android-project-server-1.0-SNAPSHOT.jar
