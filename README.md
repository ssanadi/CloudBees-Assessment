# CloudBees-Assessment

This repository demonstrates my automation skills for the Senior Software Engineer QA role at CloudBees.  
It contains two main tasks as per the assessment requirements:

---

## Table of Contents

- [Task 1: UI Automation with Playwright + TypeScript](#task-1-ui-automation-with-playwright--typescript)
- [Task 2: Git Automation with Java](#task-2-git-automation-with-java)
- [Assumptions](#assumptions)
- [Reporting](#reporting)
- [CI/CD with GitHub Actions](#cicd-with-github-actions)
- [License](#license)

---

## Task 1: UI Automation with Playwright + TypeScript

**Location:**  
- Source: `pages/` (Page Objects)
- Tests: `tests/cloudbees-first-test-case.spec.ts`
- Config: `playwright.config.ts`

**Test Cases Covered:**  
Automates the following scenarios on [https://www.cloudbees.com/](https://www.cloudbees.com/):

1. **Navigation CloudBees CD/RO and verify cost savings stat**
   - Open the application
   - Click the link Products on top > Click CloudBees CD/RO under Other Products
   - Verify that Cost Savings has a value of $2m
2. **Navigation Auditors / Security on CloudBees CD/RO page and verify one of its featur title and header content**
   - Scroll down, click Auditors / Security
   - Verify the text under Release Governance (Generate single-click audit reports)

2. **Navigation CloudBees Documentation page and verify pagination displayed**
   - Click the link Resources on top > Click Documentation
   - Verify that it opens a new tab
   - Click in the text field "Search all CloudBees Resources"
   - Verify that a new page is opened in this tab
   - Search for the word "Installation"
   - Verify that we have pagination options at bottom

**How to Run:**

```sh
# Install dependencies
npm install

# Run all Playwright tests
npx playwright test

# Run test suite
npm run test 

# Generate and open Allure report
npm run report
```

---

## Task 2: Git Automation with Java

**Location:**  
- Source: `java-git-automation/src/test/java/GitHelperTest.java`

**Description:**  
Automates the following git operations using Java (no external git libraries, only CLI via `ProcessBuilder`):

1. GitHelperTest.java: TestNG test class that:
- Clones a repository
- Adds a new file and commits
- Appends to the file and commits
- Asserts file existence and content

**How to Run:**

```sh
# Run tests
mvn clean test

# Generate and View Allure Report
mvn allure:report
mvn allure:serve
```

You can modify `GitHelperTest.java` to provide different repo URLs, file names, and content as needed.

---

## Assumptions

- For UI automation:
  - The system has latest node version installed.

- For git automation test, it is assumed that:
  - The system has `git` and `bash` available in the environment path.
  - The user has network access to clone public repositories.
- Allure reporting is integrated for both tasks.

---

## Reporting

- **Playwright + Allure:**  
  Allure reports are generated for UI automation.  
  To generate and view the report:
  ```sh
  npm run report
  ```
- **Git Automation test with TestNG + Allure:**
  Generate Allure Report
  ```sh
  mvn allure:report
  ```

---

## CI/CD with GitHub Actions

- Playwright test GitHub Actions workflow (`.github/workflows/playwright.yml`)
- TestNG test GitHub Actions workflow (`.github/workflows/java-git-automation.yml`)
- The workflow installs dependencies, runs the tests, generates the Allure report, and uploads the report as an artifact for easy access and review.

---

## License

- This project is licensed under the GNU General Public License v3.0.  
  See the [LICENSE](LICENSE) file for details.

---

## Contact

For any questions, please contact [ssanadi](mailto:sanadi.saifali.7@gmail.com).
