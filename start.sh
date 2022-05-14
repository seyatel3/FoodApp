#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME=$1
export BOT_TOK  EN=$2
export BOT_DB_USERNAME='dev_kristafoodbot_user'
export BOT_DB_PASSWORD='dev_kristafoodbot_password'

# Start new deployment
docker-compose up --build -d