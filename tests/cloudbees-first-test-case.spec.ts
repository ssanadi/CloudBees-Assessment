import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { CDROPage } from '../pages/CDROPage';
import { DocumentationPage } from '../pages/DocumentationPage';

let homePage: HomePage;
let cdroPage: CDROPage;
let docsPage: DocumentationPage;

test.beforeEach(async ({ page }) => {
  await page.goto('https://www.cloudbees.com/');
  homePage = new HomePage(page);
  cdroPage = new CDROPage(page);
});

test('Navigation CloudBees CD/RO and verify cost savings stat', async ({ page }) => {

  // Open 'Products' link in the top navigation bar
  await homePage.openProductMenu();

  // Click 'CloudBees CD/RO' under 'Other Products'
  await homePage.navigateToCDRO();

  // Verify user navigate to CloudBees CD/RO
  await cdroPage.verifyUserIsOnCDROPage();

  // Verify that stat section has values - Cost Savings of $2m
  await cdroPage.verifyCostSavingsStat('Cost Savings', '$2m');
})

test('Navigation Auditors / Security on CloudBees CD/RO page and verify one of its featur title and header content', async ({ page }) => {
  // Click the 'Products' link in the top navigation bar
  await homePage.openProductMenu();

  // Click 'CloudBees CD/RO' under 'Other Products'
  await homePage.navigateToCDRO();
  
  // Scroll down, click 'Auditors / Security'
  await cdroPage.clickAuditorsSecurityTab()

  // Verify the text under Release Governance
  await cdroPage.verifyAuditFeatureSection('Generate single-click audit reports');
})

test('Navigation CloudBees Documentation page and verify pagination displayed', async ({ page, context }) => {
  // Click the 'Resources' link in the top navigation bar
  await homePage.openResourcesMenu();

  // Click 'Documentation' under Support & Documentation
  let docsPage = await homePage.openDocumentationPage(context);
  docsPage = new DocumentationPage(docsPage);

  // Verify that it opens a new tab
  await docsPage.verifyPageLoaded();

  // Search for the word "Installation"
  await docsPage.searchFor('Installation');

  // Verify atleast one search result displayed
  await docsPage.verifySearchResultAtLeastHaveOneSearchRecord();

  // Verify that we have pagination options at bottom
  await docsPage.verifyPaginationDisplayed();
}); 