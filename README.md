# term-frequency
Tf/idf (term frequency / inverse document frequency) is an statistic that reflects the importance of a term T in a document D (or the relevance of a document for a searched term) relative to a document set S

## How to execute this program
For execute this program there are two options:

### Execute using spring-boot mvn plugin
```shell script
mvn spring-boot:run -Dspring-boot.run.arguments="[ARGUMENTS]"
```
### Compile and execute with java
```shell script
mvn clean install
java -jar target/term-frequency-0.0.1-SNAPSHOT.jar [ARGUMENTS] 
```
### Available arguments

You can use two notations for pass params:
 - Spring boot notation: "--<spring-boot-param-name>=<value>"
 - Shell notation: "-<shell-param> <value>"

There are 4 arguments for this application:

| Spring notation | Shell notation | Description | Default value |
| --------------- | -------------- | ----------- | ------------- |
| term-frequency.path | D | The directory where the documents will be written. | ./documents |
| term-frequency.terms | TT | The terms to be analyzed. | |
| term-frequency.resultsToShow | N | The count of top results to show. | 5 |
| term-frequency.interval | P | The period to report the top results. | 1s |

For set period use this format: [value][unit]. The supported units are:
 - ns: nanoseconds 
 - us: microseconds
 - ms: milliseconds
 - s: seconds
 - m: minutes
 - h: hours
 - d: days