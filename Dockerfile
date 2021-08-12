FROM ubuntu
RUN apt update && apt upgrade -y
RUN apt install openjdk-11-jdk -y
RUN apt install maven -y
WORKDIR /opt/server
COPY pom.xml .
#RUN mvn install
COPY . .
RUN mvn package
ENTRYPOINT java -jar /opt/server/target/android-project-server-1.0-SNAPSHOT.jar
