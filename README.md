## Project Overview

**What is tested:** Web application

**Tools used:**
* Selenium: browser automation

**Frameworks used:**
* TestNG: testing framework
* Log4j: logging
* Allure: reporting

**Design Patterns:**
* Page Objects: encapsulate page elements
* Factory: for object instances creation
* Builder: for object instances creation
* Singleton: ensures single browser instance

**Configuration:**
* Property file: stores environment variables, URLs

**Utilities:**
* DataGenerator for test data preparation

**Reporting:**
* Allure Reports: detailed test reports with graphs and logs
```
allure serve target/allure-results
```

**Continuous Integration:**
* Jenkins (Pipeline): automates build cycle

**Test Execution:**
* Local: run tests directly via IDE or command line
```
mvn clean test
```
* CI: automated execution in Jenkins pipeline:
[Script Link](https://github.com/ElenaD1402/finalproject/blob/24bc8d19c1f0b40d93092f16b528d8ec1c6015d1/FinalProject/Jenkinsfile)
