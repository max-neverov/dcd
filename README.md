## Docker CPU detection

This project shows several approaches how to detect CPU limitations in Docker from inside Java.
One can limit the number of CPU cores available for a container via `--cpuset-cpus="0"` command or `cpuset: "0"` (see [docker-compose](docker-compose.yml)). 

Until version 8u131 Java could not detect this limitation with standard approaches like 
```Runtime.getRuntime().availableProcessors()```.

Most reliable way is to use `nproc` (see [RestController](src/main/java/dcd/RestController.java)).

The project is a simple spring-boot application, which exposes the metric found using different methods on `localhost:8080/cpu`  

## Tasks  
- `./gradlew startDcd`: build the service and start container
- `./gradlew downDcd`: shutdown service

## Links
Announce from Oracle [Java SE support for Docker CPU and memory limits](https://blogs.oracle.com/java-platform-group/java-se-support-for-docker-cpu-and-memory-limits)

Not related directly, but for better understanding [Running a JVM in a Container Without Getting Killed](https://blog.csanchez.org/2017/05/31/running-a-jvm-in-a-container-without-getting-killed/) 