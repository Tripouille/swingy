#!/bin/sh
sudo docker build -t mi .
sudo docker rm -f mc
sudo docker run -d --name mc -p 80:80 -p 5432:5432 mi