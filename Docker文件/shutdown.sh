APPNAME=dockerdemo
docker ps -a | grep "$APPNAME" | awk '{print $1 }'|xargs docker stop
docker ps -a | grep "$APPNAME" | awk '{print $1 }'|xargs docker rm
docker images|grep "$APPNAME"|awk '{print $3 }'|xargs docker rmi
