package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class GridPanel extends JPanel {
  private Map<Coord, Cell> curr;

  //add this list so that you will be able to access cells based on coordinates
  private ArrayList<Cell> cellsOnScreen;
  private Map<Point, Cell> cellScreenLocations;


  /**
   * Make a GridPanel.
   */
  public GridPanel(int col, int row, Map<Coord, Cell> curr) {
    super();
    this.curr = curr;
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    cellScreenLocations = new HashMap<>();
    cellsOnScreen = new ArrayList<>();

    //first loop to create empty sheet
    setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    for (int i = 0; i < col; i++) {
      for (int j = 0; j < row; j++) {
        JLabel field = new JLabel("     ");
        Cell template = new Cell(new Coord(i + 1, j + 1));
        cellsOnScreen.add(template);
        JPanel cell = template.drawSelf();
        cell.add(field);
        cell.setBackground(new Color(196, 198, 255));
        c.gridx = i;
        c.gridy = j;
        c.ipadx = 30;
        c.ipady = 10;
        cell.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        if (j == 0 && i == 0) {
          cell.setBackground(new Color(74, 77, 145));
        }
        if (j == 0 && i >= 1) {
          JLabel field2 = new JLabel(Coord.colIndexToName(i));
          cell.setBackground(new Color(74, 77, 145));
          cell.add(field2, c);
        }
        if (i == 0 && j >= 1) {
          Coord coord = new Coord(i + 1, j );
          JLabel field3 = new JLabel(String.valueOf(coord.row));
          cell.setBackground(new Color(74, 77, 145));
          cell.add(field3, c);
        }

        //this is for the headers, needs to be modified.
        for (Coord coord: curr.keySet()) {
          if(coord.row == j && coord.col == i)
            add(curr.get(coord).drawSelf(), c); //adds labels to grid
        }
        add(cell, c);
      }
    }

  }

  /**
   * Will need this I assume next assignment when we can interact with the spreadsheet.
   * @param curr is the current list of cells
   */
  public void setcurrState(Map<Coord, Cell> curr) {
    this.curr = curr;
  }

  public Map getcellScreenLocation(){
    return this.cellScreenLocations;
  }

  public ArrayList<Cell> getCellsOnScreen(){
    return this.cellsOnScreen;
  }

}

