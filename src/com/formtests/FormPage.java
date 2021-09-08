package com.formtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormPage extends BasePage
{
	public static final String FIRST_QUESTION_ENERGY_LABEL = "Energy", FIRST_QUESTION_OTHER_LABEL = "Other:";
	public static final String[] FIRST_QUESTION_LABELS =
	{ "Carbon", "Water", FIRST_QUESTION_ENERGY_LABEL, "Waste", FIRST_QUESTION_OTHER_LABEL };
	public static final String THIRD_QUESTION_LA_LABEL = "Los Angeles";
	public static final String[] THIRD_QUESTION_LABELS =
	{ "San Diego", "New York", THIRD_QUESTION_LA_LABEL, "London", "Other:" };

	public static FormPage navToFormPage(WebDriver driver)
	{
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLScP_qaA9ZuP519Z4TZmXO79FA7dh7nxaBe4_xuX6E33Y3PMVA/viewform");
		return new FormPage(driver);
	}

	public FormPage(WebDriver driver)
	{
		super(driver);
	}

	private WebElement getFirstQuestionLabelledRadioButton(String label)
	{
		// 'Other:' has no aria-label element, so needs to be found a different way
		if (label.equals(FIRST_QUESTION_OTHER_LABEL))
		{
			return getDriver().findElement(By.xpath("//div[@data-value='__other_option__']"));
		}
		else
		{
			return getDriver().findElement(By.xpath("//div[@aria-label='" + label + "']"));
		}
	}

	public boolean isFirstQuestionLabelledRadioButtonSelected(String label)
	{
		return getFirstQuestionLabelledRadioButton(label).getAttribute("aria-checked").equals("true");
	}

	public void clickFirstQuestionLabelledRadioButton(String label)
	{
		WebElement element = getFirstQuestionLabelledRadioButton(label);
		getWait().until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	private WebElement getFirstQuestionOtherTextField()
	{
		// There are two of these on the page, we want the first, so findElement will
		// work for this
		return getDriver().findElement(By.xpath("//input[@aria-label='Other response']"));
	}

	public String getFirstQuestionOtherTextFieldText()
	{
		return getFirstQuestionOtherTextField().getAttribute("data-initial-value");
	}

	public void enterTextInFirstQuestionOtherTextField(String text)
	{
		getFirstQuestionOtherTextField().clear();
		getFirstQuestionOtherTextField().sendKeys(text);
	}

	public void clickFirstQuestionClearSelection()
	{
		// There are several of these on the page, we want the first, so findElement
		// will work for this
		WebElement clearSelectionText = getDriver().findElement(By.xpath("//span[text()='Clear selection']"));
		getWait().until(ExpectedConditions.visibilityOf(clearSelectionText));
		getWait().until(ExpectedConditions.elementToBeClickable(clearSelectionText));
		// This element does not like being interacted with quickly for some reason. A
		// half second sleep is an acceptable compromise
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		clearSelectionText.click();
		getWait().until(ExpectedConditions.invisibilityOf(clearSelectionText));
	}

	/**
	 * @param labelToSelect
	 *            The label of the checkbox to interact with
	 * @param flag
	 *            The status that the checkbox should be, true for selected, false
	 *            for unselected
	 */
	public void clickThirdQuestionCheckbox(String labelToSelect, boolean flag)
	{
		WebElement checkbox = getDriver()
				.findElement(By.xpath("//div[contains(@data-params, 'mandatory')]//span[text()='" + labelToSelect
						+ "']/../../../div[@role='checkbox']"));
		if (checkbox.getAttribute("aria-checked").equals("true") != flag)
		{
			checkbox.click();
		}
	}

	public boolean isThirdQuestionLabelledCheckboxSelected(String label)
	{
		return getDriver().findElement(By.xpath("//div[contains(@data-params, 'mandatory')]//span[text()='" + label
				+ "']/../../../div[@role='checkbox']")).getAttribute("aria-checked").equals("true");

	}
}
