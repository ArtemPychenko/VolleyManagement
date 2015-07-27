package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;

/**
 * @author Dp-076 ATQC
 * This class gives the map of the edit player page.
 */
public class EditPlayerPageUIMap {
    /**
     * @param cancelButton holds location of cancel button.
     */
    private IButton cancelButton;
    /**
     * @param saveButton holds location of cancel button.
     */
    private IButton saveButton;
    /**
     * constructor.
     */
    public EditPlayerPageUIMap() {
    }
      /**
     * @return cancel button.
     */
    public final IButton getCancelButton() {
          if (this.cancelButton == null) {
              this.cancelButton = Button.getByCss(
                      ".btn.btn-warning.cancel");
          }
          return cancelButton;
      }
    /**
     * @return cancel button.
     */
    public final IButton getSaveButton() {
          if (this.saveButton == null) {
              this.saveButton = Button.getByCss(
                      ".btn.btn-success.save.pull-right");
          }
          return saveButton;
      }
}