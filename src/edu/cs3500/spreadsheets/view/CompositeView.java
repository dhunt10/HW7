package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import java.util.Map;

public class CompositeView implements IView{

  GraphicsFrame frame;
  Map<Coord, Cell> sheet;
  int width;
  int height;
  int x;
  int y;

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
    this.frame = new GraphicsFrame(sheet, width, height);
  }
  @Override
  public void saveTo(String filePath) {

  }

  @Override
  public void display() {

  }

  @Override
  public String buildTextView() {
    return null;
  }
}
