#!/bin/bash

set -e
set -u

function create_user_and_database() {
    local database=$1
    echo "Creation de la  database '$database'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
        CREATE DATABASE $database;
        GRANT ALL PRIVILEGES ON DATABASE $database TO $POSTGRES_USER;
EOSQL
}

if [ -n "$POSTGRES_DB" ]; then
    echo "Database $POSTGRES_DB existe deja"
else
    echo "Creating database $POSTGRES_DB"
fi

# Create additional databases
for db in "product" "order" "finances"; do
    create_user_and_database $db
done