import { Page, Locator, expect } from '@playwright/test';

export class CDROPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  //#region "Locators as getters"
  get header(): Locator {
    return this.page.locator('[data-test="hero.header.content.featuredTitle"]');
  }

  get costSavingsTitle(): Locator {
    return this.page.locator('[data-test="stat"] [data-test="stat.preTitle"]').first();
  }

  get costSavingsValue(): Locator {
    return this.page.locator('[data-test="stat"] [data-test="stat.primary"]').first();
  }

  get auditorsSecurityBtn(): Locator {
    return this.page.getByRole('button', { name: 'Auditors / Security' });
  }

  get auditFeaturePreTitle(): Locator {
    return this.page.locator('[data-test="container.tabSaaS"] [data-test="headerContent.preTitle"]').last();
  }

  get auditFeatureTitle(): Locator {
    return this.page.locator('[data-test="container.tabSaaS"] [data-test="headerContent.title"]').last();
  }
  //#endregion "Locators as getters"

  //#region "Page actions"
  
  async clickAuditorsSecurityTab() {
    await this.auditorsSecurityBtn.click();
  }
  //#endregion "Page actions"

  //#region "Page Assertion"
  async verifyUserIsOnCDROPage() {
    await expect(this.header).toContainText('CloudBees CD/RO');
  }

  async verifyCostSavingsStat(title: string, value: string) {
    await expect(this.costSavingsTitle).toContainText(title);
    await expect(this.costSavingsValue).toContainText(value);
  }

  async verifyAuditFeatureSection(title: string) {
    await this.auditFeaturePreTitle.scrollIntoViewIfNeeded();
    await expect(this.auditFeaturePreTitle).toBeVisible();
    await expect(this.auditFeatureTitle).toContainText(title);
  }
  //#endregion "Page Assertion"
}