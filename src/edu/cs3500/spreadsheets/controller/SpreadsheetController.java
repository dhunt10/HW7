package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.BasicWorksheet.Builder;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.io.File;

public interface SpreadsheetController {

  void updateProgram(String coordinate, String inString, Spreadsheet s);

  /**
   * Used for testing purposes only.
   * @param x the manually set x.
   */
  void setX(int x);

  /**
   * used for testing purposes only.
   * @param y the manually set y.
   */
  void setY(int y);
}