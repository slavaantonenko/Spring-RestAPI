# Spring RestAPI

## Overview
This is a RestAPI application running Spring boot and uses MySQL DB and JPA

## Prerequisites
Following environment variables has to be configured

MYSQL_DB_HOST\
MYSQL_DB_PORT\
MYSQL_DB_NAME\
MYSQL_DB_USERNAME\
MYSQL_DB_PASSWORD

Note: If you are you using Intellij to execute this project, you need go to Edit Configurations -> Environment variables

## DB
The database has the follwoing structure

machines table\
id (int) | name (varchar)

teams table\
id (int) | quota (int)

assigned_machines table\
id (int) | team_id (int) | machine_id (int)

## RestAPI Operations
This API can perform the following operations

#### GET
Reads records from DB

/machines\
/machine/{id}\
/teams\
/team/{id}

#### POST
Creates a record in DB\
Request body should be passed with machine name or team's quota

/machine\
{"name": "test"}

/team\
{"quota": 5}

You can also assign machine to a team, in this case you should pass team and machine id\
/assign\
{"team": 3, "machine": 5}

#### PUT
Updates machine's name or team's quota in DB\
Request body should be passed with new machine name or new team's quota

/machine/{id}\
{"name": "test2"}

/team/{id}\
{"quota": 10}

#### DELETE
Deletes a record from DB

/machine/{id}\
/team/{id}