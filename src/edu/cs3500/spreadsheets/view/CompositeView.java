package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import java.util.Map;

public class CompositeView implements IView{

  CompositeFrame frame;
  Map<Coord, Cell> sheet;
  int width;
  int height;
  GridPanel cells;

  /**
   *
   * @param sheet
   * @param width
   * @param height
   */
  public CompositeView(Map<Coord, Cell> sheet, int width, int height) {
    this.sheet = sheet;
    this.width = width;
    this.height = height;
    this.frame = new CompositeFrame(sheet, width, height);
    this.cells = frame.getGridPanel();
  }
  @Override
  public void saveTo(String filePath) {
    throw new UnsupportedOperationException("Can't save a visual view");
  }

  @Override
  public void display() {
    frame.display();
  }

  @Override
  public String buildTextView() {
    throw new UnsupportedOperationException(
        "Can't display textual view of visual view");
   }

  @Override
  public GraphicsFrame getGraphicsFrame() {
    throw new UnsupportedOperationException(
        "No Graphics Frame in this view");
  }

  @Override
  public CompositeFrame getCompositeFrame() {
    return this.frame;
  }

  @Override
  public GridPanel getCells() {
    return this.cells;
  }
}
