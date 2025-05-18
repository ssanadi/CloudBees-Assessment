import { Page, Locator, expect } from '@playwright/test';

export class DocumentationPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  // #region "Locators as getters"
  get searchInput(): Locator {
    return this.page.locator('[placeholder="Search all CloudBees Resources"]');
  }

  get searchInputField(): Locator {
    return this.page.locator('.search-page .search-input');
  }

  get resultTitles(): Locator {
    return this.page.locator('.result-title');
  }

  get pagination(): Locator {
    return this.page.locator('.pagination');
  }

  //#endregion "Locators as getters"

  //#region "Page actions"

  async searchFor(term: string) {
    await this.searchInput.click();
    await this.searchInputField.click();
    await this.searchInputField.fill(term);
    await this.searchInputField.press('Enter');
  }
  //#endregion "Page actions"

  //#region "Page Assertion"
  async verifyPageLoaded() {
    await expect(this.page).not.toBeNull();
    await expect(this.page).toHaveTitle('CloudBees Docs');
    await this.page.bringToFront();
    await this.page.waitForLoadState();
  }

  async verifySearchResultAtLeastHaveOneSearchRecord() {
    // await this.page.waitForSelector('.result-title');
    // this.resultTitles.first().waitFor({state:'visible'});
    await expect(this.resultTitles.first()).toBeVisible();
    const count = await this.resultTitles.count();
    expect(count).toBeGreaterThan(1);
  }

  async verifyPaginationDisplayed() {
    await this.pagination.scrollIntoViewIfNeeded();
    await expect(this.pagination).toHaveCount(1);
  }
  //#endregion "Page Assertion"
}