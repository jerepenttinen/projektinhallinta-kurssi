#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Add migration name as an argument"
  exit 1
fi

file_name="$(echo "$1" | tr '[:upper:]' '[:lower:]')"

scripts_path=$(cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P)
cd "$scripts_path" || exit 2

touch "../src/main/resources/db/migration/V$(date +"%Y%m%d%H%M%S")__${file_name}.sql"