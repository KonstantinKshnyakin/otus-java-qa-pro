#!/bin/bash

eval "mvn test -Pprod -DisRemote=true -Dbrowser=$BROWSER -DbrowserVersion=$BROWSER_VER -DremoteURL=$REMOTE_URL"
docker run --rm -e BROWSER=chrome -e BROWSER_VER=127.0 -e REMOTE_URL=http://192.168.1.124/wd/hub localhost:5000/ui-test:1.0.0

