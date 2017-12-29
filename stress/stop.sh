#!/bin/sh

# ACME Stress. Applying Stress to ACME Demo System


echo "Stopping the stress"
APP_HOME="$PWD"
cd $APP_HOME
while read line
do kill -9 $line
done < PID
rm PID

echo "Stress toll should be stopped in a few second, please confirm manually"
