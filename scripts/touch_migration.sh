#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Add migration name as an argument"
  exit 1
fi

file_name="$(echo "$1" | tr '[:upper:]' '[:lower:]' | sed -E 's/[[:space:]]+/_/g')"

scripts_path=$(cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P)
cd "$scripts_path" || exit 2

# Ensure dir exists
mkdir -p "../src/main/resources/db/migration"

touch "../src/main/resources/db/migration/V$(date +"%Y%m%d%H%M%S")__${file_name}.sql"