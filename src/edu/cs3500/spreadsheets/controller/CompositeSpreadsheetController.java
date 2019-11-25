package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.view.CompositeViewAction;
import java.awt.event.MouseEvent;

public class CompositeSpreadsheetController implements SpreadsheetController, CompositeViewAction {

  @Override
  public void mouseClicked(MouseEvent e) {
      //gotta somehow get these coordinates then convert them to cell Coords
      int x = e.getX();
      int y = e.getY();
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
