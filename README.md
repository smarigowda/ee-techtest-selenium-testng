# EE Tech Test Solution
## Approach TDD

### Prerequisites

- JDK 1.8 should be installed
- JDK should be available on system path (set JAVA_HOME)
- Maven. Maven should be installed, it should be available on system path.

### Steps to install the dependencies and run the test

- Clone the github repository
`git clone git@github.com:smarigowda/ee-techtest-selenium-testng.git`
- Get into the project folder
`cd ee-techtest-selenium-testng`
- Install all the dependencies. It also runs the tests using TestNG test runner.
`mvn clean install` 
- Run the test
`mvn test -Dtest=AppTest`
- Test should run successfully.
- I have not implemented any reporting for this solution
- I have implemented the HTML reporting in my BDD Approach solution