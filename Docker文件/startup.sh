APPNAME=dockerdemo
PORT=8080
docker build -t $APPNAME .
docker run -d -h 127.0.0.1  --name $APPNAME -p $PORT:$PORT $APPNAME

