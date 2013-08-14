#!/bin/sh

dir="$( dirname "$(readlink -f "$0")" )"
generated="$dir/src/generated"


java \
  -jar "$dir/../dsl-clc.jar" 0.7.11-localhost-SNAPSHOT \
  --project-ini-path= "~/.config/dslcookbook/beginner1.ini" \
  --dsl-path="$dir/src/main/resources/dsl" \
  --namespace=com.dslplatform.examples.beginner \
  --output-path="$generated" \
  "$@"

