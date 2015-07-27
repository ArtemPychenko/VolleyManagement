package com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces;

import java.util.List;
/**
 * This interface is a layer between controls level and higher levels.
 *
 * @author Dp-076 ATQC
 */
public interface IDropdown {
    /**
     * This method diselected all selected categories in dropdown.
     */
    void diselect();
    /**
     * This method selected categories in dropdown by index.
     * @param index of category
     */
    void selectByIndex(int index);
    /**
     * This method selected categories in dropdown by value.
     * @param value of category
     */
    void selectByValue(String value);
    /**
     * This method selected categories in dropdown by visible text.
     * @param text of category
     */
    void selectByVisibleText(String text);
    /**
     * This method get all selected dropdown categories in a list.
     * @return list of selected categories
     */
    List<String> getSelectedValuesList();
    /**
     * This method get all dropdown categories in a list.
     * @return list of categories
     */
    List<String> getValuesList();
}
