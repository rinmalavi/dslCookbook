#!/bin/bash

rm -rf project
rm -rf Intermediate/src/main/resources/codesnippets

cp eclipse/.* intermediate/
cp eclipse/.* beginner/
rm -rf eclipse

find . -maxdepth 1 -name  "*.bat" -delete
find . -maxdepth 1 -name  "*.sh" -delete
