package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.controller.CompositeSpreadsheetController;
import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.BasicWorksheet.Builder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.CompositeView;
import edu.cs3500.spreadsheets.view.GraphicsView;
import edu.cs3500.spreadsheets.view.IView;
import edu.cs3500.spreadsheets.view.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The main class for our program.
 */
public class BeyondGood {

  /**
   * Static void main.
   * @param args any command-line arguments.
   */
  public static void main(String[] args) throws FileNotFoundException {
    File infile = null;
    File outfile = null;
    String incell = null;
    String view = "composite";
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case("-in"):
          if (i == args.length - 1) {
            throw new IllegalArgumentException("You need to give me an input file you dumb-dumb");
          }
          infile = new File(args[i + 1]);
          i++;
          break;
        case("-eval"):
          if (i == args.length - 1) {
            throw new IllegalArgumentException("You need to give me a cell you silly nugget");
          }
          incell = args[i + 1];
          i++;
          break;
        case("-save"):
          if(i == args.length - 1){
            throw new IllegalArgumentException("Need a file name to save to");
          }
          outfile = new File(args[i+1]);
          view = "text";
          i++;
          break;
        case("-gui"):
          view = "graphic";
          break;
        case("-edit"):
          view = "composite";
          break;
        default:
          throw new IllegalArgumentException("This is not how you use our application tough guy");
      }
    }

    createSpreadSheet(infile, incell, view, outfile);
  }

    /**
   * Function that creates a spreadsheet by taking in a file.
   * @param file the name of the file.
   * @param cell cell.
   * @throws FileNotFoundException
   */
  private static void createSpreadSheet(File file, String cell, String type, File saveTo) throws FileNotFoundException {
    Builder b = new Builder();
    BufferedReader reader;
    Spreadsheet spreadsheet;

    if (file == null && type == "text") {
      throw new IllegalArgumentException("text needs a file");
    }

    if (file == null && type == "graphic" || file == null && type == "composite") {
      IView v = createView(type, saveTo, b.createWorksheet());
      v.display();

      //updateCurrentView(new String("A1"), "2", b.createWorksheet());
      //CompositeSpreadsheetController compositeSpreadsheetController = new CompositeSpreadsheetController();
      //compositeSpreadsheetController.updateProgram(new String("A1"), "5", b.createWorksheet());

      v = createView(type, saveTo, b.createWorksheet());
      v.display();

      //BasicWorksheet.getEvaluatedCells(b.getCurrSpreadSheet(), b.getCurrList());
      //TODO keeping ^ in here to remember what i was going for earlier. now update is auto?

      v = createView(type, saveTo, b.createWorksheet());
      v.display();
    }
    else {
      try {
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
          String[] phrase = line.split(" ", 2);
          String coordinate = phrase[0];
          String formula = phrase[1];
          String[] coord1 = coordinate.split("(?<=\\D)(?=\\d)", 2);

          int col = Coord.colNameToIndex(coord1[0]);
          int row = Integer.parseInt(coord1[1]);
          b.createCell(col, row, formula);
          line = reader.readLine();
        }
        reader.close();
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
      FileReader fileReader = new FileReader(file);
      Spreadsheet s = WorksheetReader.read(BasicWorksheet.defaultBuilder(), fileReader);

      b.createWorksheet();

      IView v = createView(type, saveTo, s);
      v.display();
    }

  }



  public static void updateCurrentView(String coord, String value, Spreadsheet s) {
    String[] coord1 = coord.split("(?<=\\D)(?=\\d)", 2);
    int col;
    int row;
    try {
      col = Coord.colNameToIndex(String.valueOf(coord1[0]));
      row = Integer.parseInt(coord1[1]);
      s.getCurrSpreadSheet().get(new Coord(col, row))
          .setEvaluatedData(BasicWorksheet.getEvaluatedSingleCell(s, value));
      s.getCurrSpreadSheet().get(new Coord(col, row)).setRawString(value);
      s.getCurrSpreadSheet().get(new Coord(col, row)).setContents(value);
    }
    catch (NumberFormatException e) {

    }
  }

  public static IView createView(String type, File saveTo, Spreadsheet s) {
    switch (type) {
      case("text"):
        TextView createView = new TextView(s.getCurrSpreadSheet(), 5, 5);
        createView.saveTo(saveTo.getPath());
        return createView;
      case("graphic"): return new GraphicsView(s,  50, 50);
      case("composite"):
        return new CompositeView(s, 50, 50);
      default: throw new IllegalArgumentException("This type of view is not supported");
    }
  }
}
