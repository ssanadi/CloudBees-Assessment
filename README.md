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

**Description:**  
Automates the following scenarios on [https://www.cloudbees.com/](https://www.cloudbees.com/):

1. **Products > CloudBees CD/RO > Cost Savings > Auditors / Security > Release Governance**
   - Open the application
   - Click the link Products on top > Click CloudBees CD/RO under Other Products
   - Verify that Cost Savings has a value of $2m
   - Scroll down, click Auditors / Security
   - Verify the text under Release Governance (Generate single-click audit reports)

2. **Resources > Documentation > Search > Pagination**
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

# Run a specific test file
npx playwright test tests/cloudbees-first-test-case.spec.ts

# Generate and open Allure report
npm run report
```

---

## Task 2: Git Automation with Java

**Location:**  
- Source: `java-git-automation/src/GitRepoActions.java`

**Description:**  
Automates the following git operations using Java (no external git libraries, only CLI via `ProcessBuilder`):

1. **Clone and Add File**
   - Clone a given git repo
   - Add a new file with provided content (file name and content are inputs)
   - Commit the change

2. **Clone and Append to File**
   - Clone a given git repo
   - Append content to an existing file (file name and content are inputs)
   - Commit the change

**How to Run:**

```sh
# Compile the Java file
cd java-git-automation
javac src/GitRepoActions.java

# Run the Java program
java -cp src GitRepoActions
```

You can modify the `main` method in `GitRepoActions.java` to provide different repo URLs, file names, and content as needed.

---

## Assumptions

- For UI automation:
  - The system has latest node version installed.

- For git automation, it is assumed that:
  - The system has `git` and `bash` available in the environment path.
  - The user has network access to clone public repositories.
  - The Java program is run on a Unix-like environment (for `bash -c` commands).
  - The git user identity is set programmatically in the repo for commit operations.
- Allure reporting is integrated for Playwright tests only.

---

## Reporting

- **Playwright + Allure:**  
  Allure reports are generated for UI automation.  
  To generate and view the report:
  ```sh
  npm run report
  ```

---

## CI/CD with GitHub Actions

- This repository includes a GitHub Actions workflow (`.github/workflows/playwright.yml`) that automatically runs Playwright tests and generates an Allure report on every push and pull request to the `main` or `master` branches.
- The workflow installs dependencies, runs the tests, generates the Allure report, and uploads the report as an artifact for easy access and review.

---

## License

- This project is licensed under the GNU General Public License v3.0.  
  See the [LICENSE](LICENSE) file for details.

---

## Contact

For any questions, please contact [ssanadi](mailto:sanadi.saifali.7@gmail.com).
