package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.CompositeViewButtonActions;
import edu.cs3500.spreadsheets.view.CompositeViewMouseActions;
import edu.cs3500.spreadsheets.view.GridPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class CompositeSpreadsheetController implements SpreadsheetController,
    CompositeViewMouseActions, CompositeViewButtonActions {

  //I know i am not using a controller correctly because this does not pass in a model and does
  //not change the view based on the model that it inputs for the mouse things.

  //Also another issue is that i hardcoded the goddamn for loops with a hard number, idk what
  //to do about htis, should this be based on the model???

  JTextField jTextField = new JTextField();

  public CompositeSpreadsheetController(JTextField textField) {
    this.jTextField = textField;
  }

  @Override
  public void mouseClicked(MouseEvent e) {


    //highlightCell
    Component c= e.getComponent();
    ArrayList<Component> headerCells = new ArrayList<>();
    if (c instanceof GridPanel) {
      Cell highlightCell = (Cell) ((GridPanel) c).getcellScreenLocation()
          .get(e.getPoint());
      //highlightCell.highlightSelf();
      JPanel test = (JPanel) c.getComponentAt(e.getPoint());


      for(Component cell : ((GridPanel) c).getComponents()){
        cell.setBackground(new Color(196, 198, 255));
      }

      for(int width = 1; width <= 3921; width += 80){
        c.getComponentAt(width, 1).setBackground(new Color(74, 77, 145));
        headerCells.add(c.getComponentAt(width, 1));

      }

      for(int height = 1; height <= 1501; height += 30){
        c.getComponentAt(1, height).setBackground(new Color(74, 77, 145));
        headerCells.add(c.getComponentAt(1, height));
      }

      if(!headerCells.contains(test)) {
        test.setBackground(Color.PINK);
      }
      System.out.println(c.getComponentAt(e.getPoint()));
    }
  }



  @Override
  public void mousePressed(MouseEvent e) {
    Component c = e.getComponent();
    Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
    Border redBorder = BorderFactory.createLineBorder(Color.magenta,5);
    JPanel test = (JPanel) c.getComponentAt(e.getPoint());

    test.setBorder(redBorder);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Component c = e.getComponent();
    Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
    Border redBorder = BorderFactory.createLineBorder(Color.magenta,5);
    JPanel test = (JPanel) c.getComponentAt(e.getPoint());

    test.setBorder(whiteBorder);
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {


  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void updateProgram(String coordinate, String inString, Spreadsheet s) {
    BeyondGood.updateCurrentView(coordinate, inString, s);
  }
}