package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.NoSuchElementException;

import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Dp-076 ATQC
 * This class allows to create page objects.
 */
public class Page {
    /**
     * The constructor of the class.
     */
    public Page() {
    }
    /**
     * This method allows to verify the part page URL.
     * @param theUrl - URL you need to verify.
     * @param anErrorMessage - message, if the verifying url is not passed.
     */
    public final void verifyUrlStartWith(final String theUrl,
            final String anErrorMessage) {
        if (!WebDriverUtils.getCurrentUrl().startsWith(theUrl)) {
            throw new NoSuchElementException(anErrorMessage);
        }
    }
    
    /**
     * This method allows to verify the part page URL.
     * @param UrlStart - URL start with you need to verify.
     * @param UrlContains - URL contains part you need to verify.
     * @param anErrorMessage - message, if the verifying URL is not passed.
     */
    public final void verifyUrlStartAndContain(final String UrlStart,
            final String UrlConain, final String anErrorMessage) {
            String currentURL = WebDriverUtils.getCurrentUrl(); 
        if (!(currentURL.startsWith(UrlStart) && currentURL
                .contains(UrlConain))) {
            throw new NoSuchElementException(anErrorMessage);
        }
    }
    
    /**
     * This method allows to verify the part page URL.
     * @param theUrl - URL you need to verify.
     * @param anErrorMessage - message, if the verifying url is not passed.
     */
    public final void verifyUrlContains(final String theUrl,
            final String anErrorMessage) {
        if (!WebDriverUtils.getCurrentUrl().contains(theUrl)) {
            throw new NoSuchElementException(anErrorMessage);
        }
    }
    /**
     * This method allows to verify the all page URL.
     * @param theUrl - URL you need to verify.
     * @param anErrorMessage - message, if the verifying url is not passed.
     */
    public final void verifyUrl(final String theUrl,
            final String anErrorMessage) {
        if (!WebDriverUtils.getCurrentUrl().equals(theUrl)) {
            throw new NoSuchElementException(anErrorMessage);
        }
    }
    /**
     * This method allows to verify the title page URL.
     * @param theTitle - title you need to verify.
     * @param anErrorMessage - message, if the verifying title is not passed.
     */
    public final void verifyTitle(final String theTitle,
            final String anErrorMessage) {
        if (!WebDriverUtils.get().getTitle().equals(theTitle)) {
            throw new NoSuchElementException(anErrorMessage);
        }
    }
}
