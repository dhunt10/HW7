package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Spreadsheet;

public class CompositeSpreadsheetController implements SpreadsheetController {

  public CompositeSpreadsheetController() {

  }

  @Override
  public void updateProgram(String coordinate, String inString, Spreadsheet s) {
    BeyondGood.updateCurrentView(coordinate, inString, s);
  }

}
