# open jdk 17
FROM eclipse-temurin:17
WORKDIR /app
COPY ./build/libs/jaksim-0.0.1-SNAPSHOT.jar /app/jaksim.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=prod", "jaksim.jar"]

