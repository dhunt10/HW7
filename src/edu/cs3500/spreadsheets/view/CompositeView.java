package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.util.Map;

public class CompositeView implements IView{

  GraphicsFrame frame;
  Map<Coord, Cell> sheet;
  int width;
  int height;
  int x;
  int y;
  Spreadsheet spreadsheet;

  /**
   *
   * @param sheet
   * @param width
   * @param height
   */
  public CompositeView(Spreadsheet spreadsheet, int width, int height) {
    this.sheet = spreadsheet.getCurrSpreadSheet();
    this.width = width;
    this.height = height;
    this.frame = new GraphicsFrame(sheet, width, height);
    this.spreadsheet = spreadsheet;
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
