package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.CompositeSpreadsheetController;
import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CompositeFrame extends JFrame {
  private Map<Coord, Cell> curr;
  private GridPanel gridPanel;
  private JPanel editOptions;
  private JFrame frame=new JFrame(); //creates frame
  private JLabel[][] grid; //names the grid of buttons
  private JTextField rawContents;
  private JButton confirm;
  private JButton cancel;
  private Spreadsheet model;

  /**
   *
   * @param curr
   * @param width
   * @param height
   */
  public CompositeFrame(Map<Coord, Cell> curr,
      int width, int height, Spreadsheet model, IView view) {

    super();
    this.model = model;
    this.setPreferredSize(new Dimension(width,  height));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    this.setLayout(new BorderLayout());





    //add options panel
    editOptions = new JPanel(new GridLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;


    //add buttons to the frame
    cancel = new JButton("Cancel");
    cancel.setSize(50,50);
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 10;
    c.ipady = 30;

    //Cancel Button Action, to clear the text
    cancel.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            rawContents.setText("");

          }
        });
    editOptions.add(cancel, c);

    confirm = new JButton("Confirm");
    confirm.setPreferredSize(new Dimension(10, 30));
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 10;
    c.ipady = 30;
    editOptions.add(confirm,c);


    //textfield
    rawContents = new JTextField();
    rawContents.setPreferredSize(new Dimension(10, 30));
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 10;
    c.ipady = 30;
    editOptions.add(rawContents, c);



    this.add(editOptions, BorderLayout.NORTH);



    //add the grid of cells
    gridPanel = new GridPanel(width, height, curr);
    JScrollPane scrollBar=new JScrollPane(gridPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar, BorderLayout.CENTER);



    this.gridPanel.addMouseListener(new CompositeSpreadsheetController(model, width, height, rawContents, confirm, view));

    //graphicsPanel.setcurrState(curr);
    this.pack();
    this.setSize(800, 500);
  }

  /**
   *
   * @param curr
   */
  public void updatecurrState(Map<Coord, Cell> curr) {
    gridPanel.setcurrState(curr);
  }

  /**
   *
   */
  public void display() {
    this.setVisible(true);
  }

  public GridPanel getGridPanel(){
    return this.gridPanel;
  }
}