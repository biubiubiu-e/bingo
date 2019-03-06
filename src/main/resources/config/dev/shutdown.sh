#!/bin/sh
echo "开始脚本"
PID=$(ps -ef | grep bingo-1.0-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
echo "start server"
/usr/bin/nohup /apps/product/jdk1.8.0_60/bin/java -jar -Dspring.profiles.active=test /apps/product/bingo/bingo-1.0-SNAPSHOT.jar >/apps/product/bingo/con.log 2>&1 &
echo "启动成功"