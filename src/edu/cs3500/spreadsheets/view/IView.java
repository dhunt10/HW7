package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import java.util.Map;

/**
 *
 */
public interface IView {


  /**
   *
   * @param filePath
   */
  void saveTo(String filePath);

  /**
   *
   */
  void display();

  /**
   *
   * @return
   */
  String buildTextView();
  GridPanel getCells();
  void newState(Map<Coord, Cell> newSheet);
}
