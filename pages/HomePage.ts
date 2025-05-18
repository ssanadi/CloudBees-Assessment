import { Page, Locator } from '@playwright/test';

export class HomePage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }


  //#region Locators
  get productMenu() {
    return this.page.locator('nav[role="navigation"] button:has-text("Product")');
  }

  get resourcesMenu() {
    return this.page.locator('nav[role="navigation"] button:has-text("Resources")');
  }

  get cdRoLink() {
    return this.page.locator('[data-test="navbar.menuLink.products.otherProducts.cloudbeesCD"]');
  }

  get documentationLink() {
    return this.page.locator('[data-test="navbar.menuLink.resources.supportDocumentation.documentation"]');
  }
  //#endregion

  //#region Actions
  async openProductMenu() {
    await this.productMenu.click();
  }

  async openResourcesMenu() {
    await this.resourcesMenu.click();
  }

  async navigateToCDRO() {
    await this.cdRoLink.click();
  }

  async openDocumentationPage(context) {
    const [docsPage] = await Promise.all([
      context.waitForEvent('page'),
      this.documentationLink.click()
    ]);
    return docsPage;
  }
  //#endregion
}
