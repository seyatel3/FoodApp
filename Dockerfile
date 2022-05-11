FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME = test_krista_food_bot
ENV BOT_TOKEN = 5386295105:AAFS5j_2z8Rw0NOnO9ExgSiDrzTX4bUl33w
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]