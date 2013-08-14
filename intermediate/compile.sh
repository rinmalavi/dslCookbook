#!/bin/sh

dir="$( dirname "$(readlink -f "$0")" )"
generated="$dir/src/generated"

java \
  -jar "$dir/../dsl-clc.jar" 0.7.12 \
  --project-ini-path= "~/.config/dslcookbook/intermediate.ini" \
  --dsl-path="$dir/src/main/resources/dsl" \
  --namespace=com.dslplatform.examples.intermediate \
  --output-path="$generated" \
  "$@"

