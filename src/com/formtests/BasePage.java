package com.formtests;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage
{
	private WebDriver driver;
	private FluentWait<WebDriver> wait;

	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		wait = new FluentWait<>(driver).withTimeout(Duration.of(5, ChronoUnit.SECONDS));
	}

	protected WebDriver getDriver()
	{
		return driver;
	}

	protected FluentWait<WebDriver> getWait()
	{
		return wait;
	}
}
