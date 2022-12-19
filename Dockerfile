FROM openjdk:11
COPY build/libs/user-info-0.0.1-SNAPSHOT.jar /user-info.jar
EXPOSE 8080
CMD ["java", "-XX:+UnlockExperimentalVMOptions",  "-XX:MaxRAMFraction=2", "-jar","/user-info.jar"]