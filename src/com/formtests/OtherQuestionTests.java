package com.formtests;

public class OtherQuestionTests
{
	public static void selectRandomAndLAThirdQuestion(FormPage page)
	{
		// Select a random checkbox from the set on the page
		String labelToSelect = Randomizer.getRandomElementFromArray(FormPage.THIRD_QUESTION_LABELS);
		String errorMessage = "";

		page.clickThirdQuestionCheckbox(labelToSelect, true);
		page.clickThirdQuestionCheckbox(FormPage.THIRD_QUESTION_LA_LABEL, true);
		for (String label : FormPage.THIRD_QUESTION_LABELS)
		{
			if ((label.equals(labelToSelect) || label.equals(FormPage.THIRD_QUESTION_LA_LABEL))
					&& !page.isThirdQuestionLabelledCheckboxSelected(label))
			{
				errorMessage = errorMessage + "Checkbox with label " + label
						+ " should be selected after clicking on it. ";
			}
			else if (!(label.equals(labelToSelect) || label.equals(FormPage.THIRD_QUESTION_LA_LABEL))
					&& page.isFirstQuestionLabelledRadioButtonSelected(label))
			{
				errorMessage = errorMessage + label + " should not be selected because we did not click on it. ";
			}
		}

		if (!errorMessage.isBlank())
		{
			System.out.println(
					"Error! Selecting a random element and LA from the Third Question is not working as expected. "
							+ errorMessage);
		}
	}

}
