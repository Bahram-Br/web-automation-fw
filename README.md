# Web Automation Framework

Java UI tests for the public [OrangeHRM demo](https://opensource-demo.orangehrmlive.com/). The project uses Selenium WebDriver, Cucumber, and TestNG with the Page Object Model.

## Tech stack

- Java 8
- Selenium WebDriver (Chrome and Edge)
- Cucumber
- TestNG
- Maven

## Project structure

```
src/test/java/
  constants/          file paths and browser names
  driver/             starts Chrome or Edge
  hooks/              opens and closes the browser
  pages/              login page objects
  runners/            TestNG + Cucumber runner
  stepdefinations/    login steps
  utils/              reads property files
src/test/resources/
  config/             browser, environment, and site URL
  features/           LoginOrange.feature
```

`config.properties` sets `browser` (`Chrome` or `Edge`) and `environment` (`QA` or `Prod`). Use `QA` for the demo site.

## Scenario

`LoginOrange.feature` has one scenario: log in with the public demo user `Admin` / `admin123`, then check that the URL is the demo dashboard.

## How to run

You need JDK 8 or newer, Maven, and Chrome or Edge.

```bash
git clone https://github.com/Bahram-Br/web-automation-fw.git
cd web-automation-fw
```

In `src/test/resources/config/config.properties`:

```properties
browser=Chrome
environment=QA
```

From the project root, run:

```bash
mvn test -Dtest=MyTestNGRunner
```

You can also run `runners.MyTestNGRunner` as a TestNG test in Eclipse or IntelliJ.
