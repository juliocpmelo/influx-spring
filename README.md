# Influx and Prometheus tests

Simple project to test behavior of spring when publishing data on Influx and Prometheus.

## Install Influx

To install influx using docker use the command below. Note that I'm using a Time zone (TZ), change it if you need.

```docker
docker run \
    --name influxdb2 \
    -v "./influx-data:/var/lib/influxdb2" \
    -v "./influx-config:/etc/influxdb2" \
    -p 8086:8086 \
    -e DOCKER_INFLUXDB_INIT_MODE=setup \
    -e DOCKER_INFLUXDB_INIT_USERNAME=admin_name \
    -e DOCKER_INFLUXDB_INIT_PASSWORD=admin_pass \
    -e DOCKER_INFLUXDB_INIT_ORG=org_name \
    -e DOCKER_INFLUXDB_INIT_BUCKET=bucket_name \
    -e DOCKER_INFLUXDB_INIT_ADMIN_TOKEN=test-token \
    -e TZ=America/Fortaleza \
    influxdb:2
```

After running, influx will be available at http://localhost:8086/. Access it with the password you configured. Influx is able
to create api tokens that are used by applications to push data, here we used a hard coded token "test-token".

## Install Prometheus

To install prometheus I suggest using the Dokerfile I wrote on my [Pometheus Example](https://github.com/juliocpmelo/prometheus_examples)
repository.

## Run the app on this repository

This is a spring application, to run it just use the usual spring toolkits to build the jar or run
from source using any IDE.

The application has a scheduler that runs every 5 seconds to create a list containing random strings and run 
a sort algorithm on it. The application will register on influx and also publish prometheus metrics containing 
some information on the process, you can check the class [TaskScheduler](src/main/java/br/metrics/schedule/TaskScheduler.java)
to see which metrics are exported.
