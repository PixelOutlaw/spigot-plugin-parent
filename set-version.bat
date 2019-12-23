@echo off

set version=$1

mvn versions:set -DgenerateBackupPoms=false -DnewVersion=%version%

git add --all

git commit -m "marking as release %version%"

git tag %version%

git push origin master --tags