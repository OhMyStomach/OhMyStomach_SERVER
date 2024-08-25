#!/bin/bash

cd /home/ec2-user/OhMyStomach_SERVER

git pull origin main

./gradlew build

sudo systemctl stop OhMyStomach.service

sudo systemctl start OhMyStomach.service