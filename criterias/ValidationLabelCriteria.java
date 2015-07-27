package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;

public class ValidationLabelCriteria implements ISpecification {
    private IValidationLabel validationLabel;
    private Specification specification;
    

    private ValidationLabelCriteria(IValidationLabel validationLabel,
            Specification specification) {
        this.validationLabel = validationLabel;
        this.specification = specification;
    }
        
    private boolean isElementPresent (String expectedResult) {
        boolean elementPresent = this.validationLabel.isPresent();
        specification.add(elementPresent, 
                "Validation label doesn't present on the page. Expect: " 
                        + expectedResult);
        return elementPresent;
    }
        
    private boolean isElementPresent () {
        boolean elementPresent = this.validationLabel.isPresent();
        specification.add(elementPresent, 
                "Validation label doesn't present on the page.");
        return elementPresent;
    }
  
    public static ValidationLabelCriteria get
    (IValidationLabel validationLabel, Specification specification) {
        return new ValidationLabelCriteria(validationLabel, specification);
    }

    public ValidationLabelCriteria valueMatch(String expectedResult) {
        if(isElementPresent(expectedResult)){
            this.specification.add
            (this.validationLabel.getText().equals(expectedResult),
                "Text of validation label is not valid. Expect: " 
            + expectedResult + " Actual: " + this.validationLabel.getText());
        }
        return this;
    }
    
    public ValidationLabelCriteria isVisible() {
        if(isElementPresent()){
            this.specification.add(this.validationLabel.isDisplayed()
                    , "Validation label is not visible.");
        }
        return this;
    }
    
    public ValidationLabelCriteria isPresentFor( String element) {
    	if (!isElementPresent()) {
    	this.specification.add(false, "Element dont present in page for: "+element);
    	}
    	return this;
    }

    public Specification next() {
        return this.specification;
    }
}
