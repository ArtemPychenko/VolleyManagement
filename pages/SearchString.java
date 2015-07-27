package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.TextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;

/**
 * @author Dp-076 ATQC
 * This class allows to create the Search String page object.
 */
public class SearchString extends Page {


    /**
     * The constructor of the class.
     */
    public SearchString() {
    }

    /**
     * @return the "Clear" button of this Search string.
     */
    public final IButton getClearButton() {
        return Button.getByCss(".btn.btn-info");
    }
    /**
     * @return the "TextInput" field of this Search string.
     */
    public final ITextInput getTextInput() {
        return TextInput.getByCss(".form-control.searchField");
    }
    
    /**
     * @return NavigationPage page after clicking "Clear" button.
     */
    public final NavigationPage clickClear() {
        Button.getByCss(".btn.btn-info").click();
        return new NavigationPage();
    }
    
    /**
     * param text - Text for search.
     * @return NavigationPage page after input text in SearchString field.
     */
    public final NavigationPage inputText(String text) {
        TextInput.getByCss(".form-control.searchField").type(text);
        return new NavigationPage();
    }
    
    /**
     * @return NavigationPage page after clear text field in SearchString.
     */
    public final NavigationPage clearTextField() {
        TextInput.getByCss(".form-control.searchField").clear();
        return new NavigationPage();
    }
    
}
