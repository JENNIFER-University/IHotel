#!/bin/sh

echo "Stopping the stress"
cd /home/centos/acme.stress
while read line
do kill -9 $line
done < PID
rm PID

echo "Stress toll should be stopped in a few second, please confirm manually"
