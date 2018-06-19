``` bash
##############################################################################
#   ____       _                   __        __         _   _                #
#  / ___| __ _| | __ ___  ___   _  \ \      / /__  __ _| |_| |__   ___ _ __  #
# | |  _ / _` | |/ _` \ \/ / | | |  \ \ /\ / / _ \/ _` | __| '_ \ / _ \ '__| #
# | |_| | (_| | | (_| |>  <| |_| |   \ V  V /  __/ (_| | |_| | | |  __/ |    #
#  \____|\__,_|_|\__,_/_/\_\\__, |    \_/\_/ \___|\__,_|\__|_| |_|\___|_|    #
#                           |___/                                            #
#  _____                            _                                        #
# |  ___|__  _ __ ___  ___ __ _ ___| |_ ___ _ __                             #
# | |_ / _ \| '__/ _ \/ __/ _` / __| __/ _ \ '__|                            #
# |  _| (_) | | |  __/ (_| (_| \__ \ ||  __/ |                               #
# |_|  \___/|_|  \___|\___\__,_|___/\__\___|_|                               #
#                                                                            #
##############################################################################
```

## Pre-requisites
* Java 8
* Gradle
* MongoDB
* Git 

## Setup

### Install dependencies

### Java 8
Go to Oracle Java page (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), download and install.

### Gradle
Go to Gradle main page (https://gradle.org/), download and install.

#### MongoDB
Go to MongoDB main page (https://www.mongodb.com/), download and install.

### Node.js
Go to Node.js main page (https://nodejs.org/en/), download and install.

### Git
Go to Git main page (https://git-scm.com/), download and install.

#### To get source code from parent repository
``` bash
> $ git clone https://github.com/nanielito/GalaxyWeatherForecaster.git
```

### To build project
``` bash
> $ gradle build
```

### Docker (Alternative)
Docker can be used as an alternative to create instances for database and application core.

Go to Docker main page (https://www.docker.com), install docker engine and docker compose. 

## Development

### To start MongoDB server:         
``` bash
> $ mongod
```

### To create the MongoDB docker container (Alternative):
``` bash
> $ cd docker
> $ docker-compose up -d gwf-db
```

### To build the application docker image (Alternative):
``` bash
> $ cd docker
> $ bash build.sh
```

### To create the application docker container (Alternative):
``` bash
> $ cd docker
> $ docker-compose up -d gwf-app
```

### To create the application docker container for a development environment (Alternative):
``` bash
> $ cd docker
> $ docker-compose up -d gwf-app-dev
```

### To start, restart and stop docker containers:
``` bash
> $ docker-compose start CONTAINER
> $ docker-compose restart CONTAINER
> $ docker-compose stop CONTAINER
```
Go to Docker Compose page documentation (https://docs.docker.com/compose) to view more details.

* ##### You can use [DockerUtils](https://nanielito.github.io/DockerUtils/) library as a middleware to handle docker-compose commands.

### To start the application server: 
``` bash
> $ gradle bootRun
```

## Git Rules

### Commit messages:
Use the following prefix to classified the actions made:
* #N for adding new features.
* #M for updating features.
* #R for removing features or some piece of code.
* #F for fixing some bug on features.

### Branches
Use the following prefix to create branches:
* feature/FUNCTIONALITY-NAME for normal development.
* fix/ISSUE-DESCRIPTION for bug's fix.

## RESTful API resources:

### POST:

* Weather forecasts initializer:    
  Calculates the weather forecasts during the following 10 years and stores them into a MongoDB collection.
  ``` bash
  @ CALL
    URL:PORT/weatherForecasts 
  @ RETURN
    A JSON string:
      {
        "status": "STATUS",
        "message": "MESSAGE"
      }
  ```

### GET:

* Weather forecast retriever:
  Gets the weather forecast information for a specific day.
  ``` bash
  @ CALL
    URL:PORT/weatherForecasts?day=DAY
  @ PARAM
    - Name: day
      - Parameter type: Query parameter
      - Value type: Integer 
  @ RETURN
    A JSON string:
      {
        "day": DAY,
        "weatherForecastType": "WEATHER_FORECAST_TYPE"
      }
  ```
* Weather forecast counter:
  Counts the weather forecasts related to a weather forecast type.
  ``` bash
  @ CALL
    URL:PORT/weatherForecasts/{weatherType}/count
  @ RETURN
    An integer
  ```
  A _weatherType_ must be one of the following values:
  * DROUGHT
  * NORMAL
  * OPTIMAL
  * RAIN
* Weather forecast retriever for the maximum rainy day.
  Gets the weather forecast information related to the day with maximum rain.
  ``` bash
  @ CALL
    URL:PORT/weatherForecasts/RAIN/maximum
  @ RETURN
    A JSON string:
      {
        "day": DAY,
        "weatherForecastType": "WEATHER_FORECAST_TYPE"
      }
  ```
  
## Version
v0.1.0-SNAPSHOT

## Author
* **DER** - *Initial work* - [nanielito](https://github.com/nanielito)

## Licence
ISC