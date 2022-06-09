FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME = test_krista_food_bot
ENV BOT_TOKEN = 5386295105:AAFS5j_2z8Rw0NOnO9ExgSiDrzTX4bUl33w
ENV BOT_DB_USERNAME=dev_kristafoodbot_user
ENV BOT_DB_PASSWORD=dev_kristafoodbot_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]