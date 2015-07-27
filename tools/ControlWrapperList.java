package com.softserveinc.ita.volleymanagementtests.tools;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author Dp-076 ATQC
 * This class describes ControlWrapperList, which is wrapper
 * for WebElements list.
 */
public class ControlWrapperList {
    /**
     * List of WebElements to wrap.
     */
    protected List<WebElement> webElementList;
    /**
     * List-wrapper for WebElement list.
     */
    protected List<ControlWrapper> controlWraperList;

    /**
     * This method wrap the list of WebElement to list of ControlWrapper.
     * @param webElements - list to wrap.
     * @return wrapped list.
     */
    public static List<ControlWrapper> getList(
            final List<WebElement> webElements) {
        List<ControlWrapper> controlWraperList =
                new ArrayList<ControlWrapper>();
      for (WebElement w: webElements) {
          controlWraperList.add(new ControlWrapper(w));
      }
      return controlWraperList;
    }
}
