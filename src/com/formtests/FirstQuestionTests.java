package com.formtests;

// These should be assertions with a proper TestNG or similar framework,
// but setting that up that kind of framework in Java would take a considerable portion of the alloted time for this,
// so just going with simple printouts of failures for now
public class FirstQuestionTests
{
	public static void validateDefault(FormPage page)
	{
		String errorMessage = "";
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " radio button should not be selected by default. ";
			}
		}
		if (!page.getFirstQuestionOtherTextFieldText().isBlank())
		{
			errorMessage = errorMessage + "Other text box should be empty by default ";
		}

		if (!errorMessage.isBlank())
		{
			System.out.println("Error! Default status of First Question is not as expected. " + errorMessage);
		}
	}

	public static void validateSelectOne(FormPage page)
	{
		String labelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		String errorMessage = "";

		page.clickFirstQuestionLabelledRadioButton(labelToSelect);
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(labelToSelect) && !page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + "Radio Button with label " + label
						+ " should be selected after clicking on it. ";
			}
			else if (!label.equals(labelToSelect) && page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting an element from the First Question is not working as expected. " + errorMessage);
		}
	}

	public static void validateSelectOneThenAnother(FormPage page)
	{
		String labelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		String secondLabelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		while (secondLabelToSelect.equals(labelToSelect))
		{
			secondLabelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		}
		String errorMessage = "";

		page.clickFirstQuestionLabelledRadioButton(labelToSelect);
		page.clickFirstQuestionLabelledRadioButton(secondLabelToSelect);
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(secondLabelToSelect) && !page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + "Radio Button with label " + label
						+ " should be selected after clicking on it. ";
			}
			else if (label.equals(labelToSelect) && page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should have been de-selected when we clicked on "
						+ secondLabelToSelect + " ";
			}
			else if (!label.equals(labelToSelect) && !label.equals(secondLabelToSelect)
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting an element from the First Question then selecting another is not working as expected. "
							+ errorMessage);
		}
	}

	public static void validateClearSelection(FormPage page)
	{
		String labelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		String errorMessage = "";

		page.clickFirstQuestionLabelledRadioButton(labelToSelect);
		page.clickFirstQuestionClearSelection();
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(labelToSelect) && page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label
						+ " should have been de-selected when we clicked on Clear Selection ";
			}
			else if (!label.equals(labelToSelect) && page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting an element from the First Question then clearing our selection is not working as expected. "
							+ errorMessage);
		}
	}

	public static void validateTypeInOther(FormPage page)
	{
		String labelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		while (labelToSelect.equals(FormPage.FIRST_QUESTION_OTHER_LABEL))
		{
			labelToSelect = Randomizer.getRandomElementFromArray(FormPage.FIRST_QUESTION_LABELS);
		}
		String textToEnter = Randomizer.getRandomASCIITextOfLength(5);
		String errorMessage = "";

		page.clickFirstQuestionLabelledRadioButton(labelToSelect);
		page.enterTextInFirstQuestionOtherTextField(textToEnter);
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(FormPage.FIRST_QUESTION_OTHER_LABEL)
					&& !page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage
						+ "Other Radio Button should be selected after typing in the Other text box. ";
			}
			else if (label.equals(labelToSelect) && page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label
						+ " should have been de-selected when we typed in the Other text box ";
			}
			else if (!label.equals(labelToSelect) && !label.equals(FormPage.FIRST_QUESTION_OTHER_LABEL)
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}
		if (!page.getFirstQuestionOtherTextFieldText().equals(textToEnter))
		{
			errorMessage = errorMessage + " Text we entered " + textToEnter
					+ " is not what is displayed in the Other text box " + page.getFirstQuestionOtherTextFieldText();
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting an element from the First Question then typing in the Other text box is not working as expected. "
							+ errorMessage);
		}
	}

	public static void validateTypeInOtherThenClear(FormPage page)
	{
		String textToEnter = Randomizer.getRandomASCIITextOfLength(5);
		String errorMessage = "";

		page.enterTextInFirstQuestionOtherTextField(textToEnter);
		page.clickFirstQuestionClearSelection();
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(FormPage.FIRST_QUESTION_OTHER_LABEL)
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should have been de-selected when we clicked Clear Selection ";
			}
			else if (!label.equals(FormPage.FIRST_QUESTION_OTHER_LABEL)
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}
		if (!page.getFirstQuestionOtherTextFieldText().equals(textToEnter))
		{
			errorMessage = errorMessage + " Text we entered " + textToEnter
					+ " is not what is displayed in the Other text box " + page.getFirstQuestionOtherTextFieldText();
		}

		if (!errorMessage.isBlank())
		{
			System.out.println("Error! Clear Selection should not clear text from the First Question Other text box. "
					+ errorMessage);
		}
	}

	public static void selectEnergy(FormPage page)
	{
		String errorMessage = "";

		page.clickFirstQuestionLabelledRadioButton(FormPage.FIRST_QUESTION_ENERGY_LABEL);
		for (String label : FormPage.FIRST_QUESTION_LABELS)
		{
			if (label.equals(FormPage.FIRST_QUESTION_ENERGY_LABEL)
					&& !page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + "Radio Button with label " + label
						+ " should be selected after clicking on it. ";
			}
			else if (!label.equals(FormPage.FIRST_QUESTION_ENERGY_LABEL)
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting Energy from the First Question is not working as expected. " + errorMessage);
		}
	}
}
