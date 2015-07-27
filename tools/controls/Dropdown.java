package com.softserveinc.ita.volleymanagementtests.tools.controls;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapperList;
import com.softserveinc.ita.volleymanagementtests.tools.SelectWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IDropdown;

/**
 * This class makes a wrapper for dropdown.
 *
 * @author Dp-076 ATQC
 */
public final class Dropdown implements IDropdown {
    /**
    * Object of SelectWrapper class.
    */
    private SelectWrapper select;
    /**
     * Constructor.
     *
     * @param theSelect
     *            - object of SelectWrapper class.
     */
    private Dropdown(final SelectWrapper theSelect) {
        select = theSelect;
    }
    @Override
    public void selectByIndex(final int index) {
        select.selectByIndex(index);

    }

    @Override
    public void selectByValue(final String value) {
        select.selectByValue(value);

    }

    @Override
    public void selectByVisibleText(final String text) {
        select.selectByVisibleText(text);

    }

    @Override
    public void diselect() {
        select.deselectAll();

    }
    /**
     * This method get all dropdown categories in a list.
     * @return list of categories
     */
    public List<String> getValuesList() {
        List<String> dropdownValuesList = new ArrayList<String>();
        for (ControlWrapper item : ControlWrapperList.getList(select
                .getOptions())) {
            dropdownValuesList.add(item.getText());
        }
        return dropdownValuesList;

    }
    /**
     * This method get all selected dropdown categories in a list.
     * @return list of selected categories
     */
    public List<String> getSelectedValuesList() {
        List<String> dropdownSelectedValuesList = new ArrayList<String>();
        for (ControlWrapper item : ControlWrapperList.getList(select
                .getAllSelectedOptions())) {
            dropdownSelectedValuesList.add(item.getText());
        }
        return dropdownSelectedValuesList;

    }
    /**
     * Method finds dropdown by id.
     * @param id locator
     * @return get dropdown by id
     */
    public static IDropdown getById(final String id) {
        return get(ControlLocation.getById(id));
    }
    /**
     * Method finds dropdown by name.
     * @param searchedName locator
     * @return get dropdown by name
     */
    public static IDropdown getByName(final String searchedName) {
        return get(ControlLocation.getByName(searchedName));
    }
    /**
     * Method finds dropdown by xpath.
     * @param xpathExpression locator
     * @return get dropdown by xpath
     */
    public static IDropdown getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }
    /**
     * Method finds dropdown by css.
     * @param cssPathExpression locator.
     * @return get dropdown by css
     */
    public static IDropdown getByCss(final String cssPathExpression) {
        return get(ControlLocation.getByCss(cssPathExpression));
    }
    /**
     * Method returns dropdown as WebElement.
     * @param controlLocation locator
     * @return get dropdown as WebElement
     */
    public static IDropdown get(final ControlLocation controlLocation) {
        return new Dropdown(new SelectWrapper(ContextVisible.get().get(
                controlLocation)));
    }
}
