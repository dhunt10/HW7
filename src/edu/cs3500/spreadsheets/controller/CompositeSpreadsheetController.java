package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.CompositeViewAction;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CompositeSpreadsheetController implements SpreadsheetController, CompositeViewAction,
    MouseListener {
  public int row;
  public int col;
  public String coord;

  @Override
  public void updateProgram(String coordinate, String inString, Spreadsheet s) {
    BeyondGood.updateCurrentView(coordinate, inString, s);

    //s.getCurrSpreadSheet().get("A1").getRawString();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
      //gotta somehow get these coordinates then convert them to cell Coords
      int x = e.getX();
      this.col = x / 30;
      int y = e.getY();
      this.row = y / 10;

      StringBuilder sb = new StringBuilder();
      sb.append(Coord.colIndexToName(this.col));
      sb.append(this.row);
      this.coord = sb.toString();
      System.out.print("cenjcek");


  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
