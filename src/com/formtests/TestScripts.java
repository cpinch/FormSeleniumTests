package com.formtests;

import org.openqa.selenium.WebDriver;

public class TestScripts
{
	public void run()
	{
		WebDriver driver = DriverFactory.getChromeDriver();
		FormPage page = FormPage.navToFormPage(driver);

		runFirstQuestionTests(page);
		runOtherQuestionTests(page);

		driver.quit();
	}

	private void runFirstQuestionTests(FormPage page)
	{
		FirstQuestionTests.validateDefault(page);
		FirstQuestionTests.validateSelectOne(page);
		FirstQuestionTests.validateSelectOneThenAnother(page);
		FirstQuestionTests.validateClearSelection(page);
		FirstQuestionTests.validateTypeInOther(page);
		FirstQuestionTests.validateTypeInOtherThenClear(page);
		FirstQuestionTests.selectEnergy(page);
	}

	private void runOtherQuestionTests(FormPage page)
	{
		OtherQuestionTests.selectRandomAndLAThirdQuestion(page);
	}
}
