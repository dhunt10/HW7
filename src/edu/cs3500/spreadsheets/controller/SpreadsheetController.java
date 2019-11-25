package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.BasicWorksheet.Builder;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.awt.event.MouseEvent;
import java.io.File;

public interface SpreadsheetController {

  void updateProgram(String coordinate, String inString, Spreadsheet s);

  void mouseClicked(MouseEvent e);

  void mousePressed(MouseEvent e);

  void mouseReleased(MouseEvent e);

  void mouseEntered(MouseEvent e);

  void mouseExited(MouseEvent e);

}
