#!/bin/bash

eval "mvn test -Pprod -DisRemote=true -Dbrowser=$BROWSER -Dbrowser.version=$BROWSER_VER -DremoteURL=$REMOTE_URL"
