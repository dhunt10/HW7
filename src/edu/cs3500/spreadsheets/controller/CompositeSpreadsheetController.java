package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.CompositeFrame;
import edu.cs3500.spreadsheets.view.CompositeViewButtonActions;
import edu.cs3500.spreadsheets.view.CompositeViewMouseActions;
import edu.cs3500.spreadsheets.view.GridPanel;
import edu.cs3500.spreadsheets.view.IView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class CompositeSpreadsheetController implements SpreadsheetController,
    CompositeViewMouseActions, CompositeViewButtonActions{

  private Spreadsheet model;
  private IView view;
  private int maxCols;
  private int maxRows;
  private JTextField textField;
  private int x;
  private int y;
  private JButton accept;
  private boolean high = false;
  private Coord highLight;

  public CompositeSpreadsheetController(Spreadsheet model, int maxCols, int maxRows, JTextField textfield, JButton accept, IView view) {
    this.model = model;
    this.view = view;
    this.maxCols = maxCols;
    this.maxRows = maxRows;
    this.textField = textfield;
    this.accept = accept;
    accept.addActionListener(this::actionPerformed);
  }

  //I know i am not using a controller correctly because this does not pass in a model and does
  //not change the view based on the model that it inputs for the mouse things.

  //Also another issue is that i hardcoded the goddamn for loops with a number, idk what
  //to do about htis, should this be based on the model???




  @Override
  public void mouseClicked(MouseEvent e) {
    //highlightCell
    Component c = e.getComponent();
    ArrayList<Component> headerCells = new ArrayList<>();
    if (c instanceof GridPanel) {


      Cell highlightCell = (Cell) ((GridPanel) c).getcellScreenLocation()
          .get(e.getPoint());
      //highlightCell.highlightSelf();
      JPanel test = (JPanel) c.getComponentAt(e.getPoint());


      for(Component cell : ((GridPanel) c).getComponents()){
        cell.setBackground(new Color(196, 198, 255));
      }

      for(int width = 1; width <= 1 + maxCols*80; width += 80){
        c.getComponentAt(width, 1).setBackground(new Color(74, 77, 145));
        headerCells.add(c.getComponentAt(width, 1));

      }

      for(int height = 1; height <= 1 + maxRows*30; height += 30){
        c.getComponentAt(1, height).setBackground(new Color(74, 77, 145));
        headerCells.add(c.getComponentAt(1, height));
      }

      if(!headerCells.contains(test)) {


        if (highLight == null || !highLight.equals(new Coord(e.getX(), e.getY()))) {
          highLight = new Coord(e.getX(), e.getY());
          this.x = e.getX() / 80;
          this.y = e.getY() / 30;
          test.setBackground(Color.PINK);
          //System.out.println(c.getComponentAt(e.getPoint()));
          StringBuilder sb = new StringBuilder();
          sb.append(Coord.colIndexToName(x)).append(y);
          System.out.println(sb);
          this.textField.setText(model.getCellAt(new Coord(x, y)).getRawString());
        }
        else {
          this.x = -1;
          this.y = -1;
          test.setBackground(new Color(196,198,255));
          this.high = false;
          highLight = null;
        }
        /*if (!this.high) {
          this.x = e.getX() / 80;
          this.y = e.getY() / 30;
          test.setBackground(Color.PINK);
          //System.out.println(c.getComponentAt(e.getPoint()));
          StringBuilder sb = new StringBuilder();
          sb.append(Coord.colIndexToName(x)).append(y);
          System.out.println(sb);
          this.textField.setText(model.getCellAt(new Coord(x, y)).getRawString());
          this.high = true;
        }
        else if (this.high) {
          this.x = -1;
          this.y = -1;
          test.setBackground(new Color(196,198,255));
          this.high = false;
        }*/
      }
    }
  }




  @Override
  public void mousePressed(MouseEvent e) {
    Component c= e.getComponent();
    Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
    Border redBorder = BorderFactory.createLineBorder(Color.magenta,5);
    JPanel test = (JPanel) c.getComponentAt(e.getPoint());

    test.setBorder(redBorder);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Component c= e.getComponent();
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
    StringBuilder sb = new StringBuilder();
    sb.append(Coord.colIndexToName(x)).append(y);
    try {
      CompositeSpreadsheetController.updateProgram(sb.toString(), textField.getText(), model);
    }
    catch (ArrayIndexOutOfBoundsException r) {
      System.out.println("No Cell Selected");
    }
    IView v = BeyondGood.createView("composite", null, model);
    v.display();

  }

  public static void updateProgram(String coordinate, String inString, Spreadsheet s) {
    BeyondGood.updateCurrentView(coordinate, inString, s);
  }
}