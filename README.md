# Web Automation Framework

A small Java UI automation project for the public [OrangeHRM demo](https://opensource-demo.orangehrmlive.com/). It uses Selenium WebDriver, Cucumber, and TestNG, with the Page Object Model so locators stay out of the test steps.

## Tech stack

| Piece | In this repo |
| --- | --- |
| Language | Java 8 (Eclipse source and target are `1.8`) |
| Browser automation | Selenium WebDriver |
| Browsers | Google Chrome and Microsoft Edge |
| Scenarios | Cucumber (Gherkin feature file and step definitions) |
| Runner and checks | TestNG (`AbstractTestNGCucumberTests`) |
| Build | Maven |

Versions of Selenium, Cucumber, and TestNG belong in `pom.xml`. That file is not in Git yet. See [Known limitation](#known-limitation).

## Project structure

```
src/test/java/
  constants/FrameworkConstants.java   # file paths, browser names, environment
  driver/DriverFactory.java           # creates Chrome or Edge
  hooks/Hooks.java                    # opens and closes the browser
  pages/BasePage.java                 # waits and shared page actions
  pages/LoginPage.java                # login locators and actions
  runners/MyTestNGRunner.java         # Cucumber + TestNG runner
  stepdefinations/LoginSteps.java     # steps for the login feature
  utils/PropertyUtils.java            # reads .properties files
  utils/TestDataLoader.java           # loads the URL for the active environment
src/test/resources/
  config/config.properties            # browser and environment
  config/qa_test_data.properties      # demo site URL
  config/prod_test_data.properties    # prod URL
  features/LoginOrange.feature        # login scenario
```

Run commands from the project root. The Java code loads property files with paths such as `src/test/resources/config/config.properties`.

## Design

### Page Object Model

`LoginPage` extends `BasePage`. Locators use `@FindBy`, and `PageFactory` connects them to the driver. `BasePage` waits up to 10 seconds, then clicks or types. `LoginPage` exposes three actions: `enterUserName`, `enterPassword`, and `clickLogin`.

### Driver handling

`DriverFactory` reads the `browser` value and starts a driver:

- `Chrome` starts Google Chrome
- `Edge` starts Microsoft Edge

Any other value throws `IllegalStateException` (`Invalid browser name`). The names are case-sensitive.

`Hooks` runs once for the suite:

- `@BeforeAll` reads config, starts the driver, and opens the application URL
- `@AfterAll` calls `driver.quit()`

Step definitions share that driver through `Hooks.driver`.

### Configuration

`FrameworkConstants` stores the property-file paths and the browser names `Chrome` and `Edge`.

`config.properties` sets the browser and the environment (`QA` or `Prod`). `TestDataLoader` is a singleton. It loads `qa_test_data.properties` or `prod_test_data.properties` and returns `appurl`.

| Environment | `appurl` |
| --- | --- |
| QA | `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login` |
| Prod | `https://orangehrm.com/` |

Use `environment=QA` for the current scenario. The QA file is the public OrangeHRM demo login page, and the test expects the demo dashboard URL.

`PropertyUtils` reads a properties file from disk.

## Scenario

`LoginOrange.feature` has one scenario, **Login with valid username and password**:

1. Enter a valid username
2. Enter a valid password
3. Click the login button
4. Check that the URL is `https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index`

The username `Admin` and the password `admin123` are the public OrangeHRM demo credentials. They are written in `LoginSteps`.

The runner `MyTestNGRunner` points Cucumber at `LoginOrange.feature` and glues the packages `stepdefinations` and `hooks`. The console output uses the `pretty` plugin. Cucumber also writes an HTML file to `target/cucumber.hrml`.

## How to run

### What you need

- JDK 8 or newer
- Maven
- Google Chrome or Microsoft Edge, matching the `browser` value

Install the browser you select. `DriverFactory` starts `ChromeDriver` or `EdgeDriver` directly.

### Clone

```bash
git clone https://github.com/Bahram-Br/web-automation-fw.git
cd web-automation-fw
```

### Known limitation

`pom.xml` is not in this repository. `.gitignore` ignores every `*.xml` file, so Git does not store the Maven build file. A fresh clone has the tests, and Maven can build them only after you add a `pom.xml`.

That file needs the libraries this code imports: Selenium (`selenium-java`), Cucumber (`cucumber-java` and `cucumber-testng`), and TestNG.

The planned fix is to change `.gitignore` so `pom.xml` is tracked, then commit the file.

### Choose the browser and environment

Edit `src/test/resources/config/config.properties`:

```properties
browser=Chrome
environment=QA
```

The code reads `browser` and `environment`. Use `Chrome` or `Edge`, and `QA` or `Prod`.

### Run the test

In Eclipse or IntelliJ, run `runners.MyTestNGRunner` as a TestNG test.

From the command line, after `pom.xml` is in the project:

```bash
mvn test -Dtest=MyTestNGRunner
```

Pass `-Dtest=MyTestNGRunner` so Surefire runs this class. Surefire's default name filter picks up classes that end with `Test`.

## Planned improvements

- Commit `pom.xml`, and stop ignoring every `*.xml` file in `.gitignore`.
- Move `Admin` and `admin123` into the test data property files.
- Add scenarios for a wrong password, empty fields, and logout.
- Save the Cucumber HTML report as `target/cucumber.html` (the runner currently writes `target/cucumber.hrml`).
- Rename the package `stepdefinations` to `stepdefinitions`.
- Use the wait value from `FrameworkConstants` in `BasePage` (the 10 second wait is written in the page class today).
- Remove the unused `brower` line from `config.properties`.
- Start a driver per scenario so the suite can run in parallel. Today one static driver is shared for the whole suite.
- Save a screenshot when a scenario fails.
- Point the Prod URL at a real login page. Today it is `https://orangehrm.com/`.
