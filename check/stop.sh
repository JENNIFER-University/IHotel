#!/bin/sh

# Stop iCheck Instance


echo "Stopping credit check system"
APP_HOME="$PWD"
cd $APP_HOME
while read line
do kill -9 $line
done < PID
rm PID

echo "Credit Check application should stop momentarily"
