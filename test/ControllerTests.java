import static junit.framework.TestCase.assertEquals;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.controller.CompositeSpreadsheetController;
import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.view.CompositeView;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.junit.Test;

public class ControllerTests {


  @Test
  public void goodControllerTest() {
    List<Coord> coordList = new ArrayList<>();
    Map<Coord, Cell> cellMap = new HashMap<>();
    BasicWorksheet basicWorksheet = new BasicWorksheet(cellMap, coordList);
    CompositeView view = new CompositeView(cellMap, 50, 50, basicWorksheet);
    CompositeSpreadsheetController compositeSpreadsheetController = new
        CompositeSpreadsheetController(basicWorksheet, 50, 50, new JTextField(),
        new JButton(), view, new JButton());
    compositeSpreadsheetController.updateProgram("A1", "10", basicWorksheet);
    compositeSpreadsheetController.updateProgram("A2", "=(SUM 20 10)", basicWorksheet);
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 1)).getEvaluatedData().toString(), "10.0");
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 2)).getEvaluatedData().toString(), "30.0");
  }

  @Test
  public void failureControllerTest() {
    List<Coord> coordList = new ArrayList<>();
    Map<Coord, Cell> cellMap = new HashMap<>();
    BasicWorksheet basicWorksheet = new BasicWorksheet(cellMap, coordList);
    CompositeView view = new CompositeView(cellMap, 50, 50, basicWorksheet);
    CompositeSpreadsheetController compositeSpreadsheetController = new
        CompositeSpreadsheetController(basicWorksheet, 50, 50, new JTextField(),
        new JButton(), view, new JButton());
    compositeSpreadsheetController.updateProgram("A2", "=SUM 20", basicWorksheet);
    compositeSpreadsheetController.updateProgram("A1", "39e34nuri43if4nui", basicWorksheet);
    compositeSpreadsheetController.updateProgram("A3", "10+3", basicWorksheet);
    compositeSpreadsheetController.updateProgram("A4", "=10+3", basicWorksheet);
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 2)).getEvaluatedData().toString(), "SUM 20");
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 1)).getEvaluatedData().toString(), "NaN");
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 3)).getEvaluatedData().toString(), "NaN");
    assertEquals(basicWorksheet.getCellAt(new Coord(1, 4)).getEvaluatedData().toString(), "10+3");
  }


  @Test
  public void testClicks() throws AWTException {
    List<Coord> coordList = new ArrayList<>();
    Map<Coord, Cell> cellMap = new HashMap<>();
    BasicWorksheet basicWorksheet = new BasicWorksheet(cellMap, coordList);
    CompositeView view = new CompositeView(cellMap, 50, 50, basicWorksheet);
    CompositeSpreadsheetController compositeSpreadsheetController = new
        CompositeSpreadsheetController(basicWorksheet, 50, 50, new JTextField(),
        new JButton(), view, new JButton());
    Robot robot = new Robot();

    //System.out.println(compositeSpreadsheetController.getX());
  }


    /*Robot robot = new Robot();
    robot.mouseMove(120, 50);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    compositeSpreadsheetController.setX(1);
    compositeSpreadsheetController.setY(1);
    compositeSpreadsheetController.actionPerformed(new MouseEvent(InputEvent.));
    System.out.println(compositeSpreadsheetController.getX());
  }*/
}
