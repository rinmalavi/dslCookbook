#!/bin/bash

rm -rf project
rm -rf Intermediate/src/main/resources/codesnippets

cp eclipse/.* intermediate/
sed -i -e 's/BlackHawk/DSL-Platform-CookBook-Beginner/g' beginner/.project
cp eclipse/.* beginner/
sed -i -e 's/BlackHawk/DSL-Platform-CookBook-Intermediate/g' intermediate/.project
rm -rf eclipse
rm .gitignore

find . -maxdepth 1 -name  "*.bat" -delete
find . -maxdepth 1 -name  "*.sh" -delete
find . -type d  -name  "target" | xargs rm -rf
find . -type d  -name  ".settings" | xargs rm -rf
