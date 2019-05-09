package sample.tests;

import CA.Cell;
import CA.MatrixOfCells;
import GUI.GraphicalMatrixOfCells;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphicalMatrixOfCellsTest {
    private static GraphicalMatrixOfCells graphicalMatrixOfCells_test1;
    private static MatrixOfCells matrixOfCells_for_test1;
    private static MatrixOfCells matrixOfCells_for_test2;
    @BeforeAll
    private static void setUp(){
        matrixOfCells_for_test1=new MatrixOfCells(3,3);
        matrixOfCells_for_test1.getCell(0,0).setState(Cell.State.ELECTRONHEAD);
        matrixOfCells_for_test1.getCell(0,1).setState(Cell.State.CONDUCTOR);
        matrixOfCells_for_test1.getCell(0,2).setState(Cell.State.EMPTY);
        matrixOfCells_for_test1.getCell(1,0).setState(Cell.State.CONDUCTOR);
        matrixOfCells_for_test1.getCell(1,1).setState(Cell.State.ELECTRONTAIL);
        matrixOfCells_for_test1.getCell(1,2).setState(Cell.State.ELECTRONHEAD);
        matrixOfCells_for_test1.getCell(2,0).setState(Cell.State.ELECTRONTAIL);
        matrixOfCells_for_test1.getCell(2,1).setState(Cell.State.EMPTY);
        matrixOfCells_for_test1.getCell(2,2).setState(Cell.State.EMPTY);
        graphicalMatrixOfCells_test1 = new GraphicalMatrixOfCells(3,3,10);
        matrixOfCells_for_test2 = new MatrixOfCells(3,3);

    }

    @Test
    void setGraphicalMatrixOfCells() {
        graphicalMatrixOfCells_test1.setGraphicalMatrixOfCells(matrixOfCells_for_test1.getMatrixOfcells_for_test());
        assertTrue(graphicalMatrixOfCells_test1.getColor(0,0).equals(Color.BLUE));
        assertTrue(graphicalMatrixOfCells_test1.getColor(0,1).equals(Color.YELLOW));
        assertTrue(graphicalMatrixOfCells_test1.getColor(0,2).equals(Color.BLACK));
        assertTrue(graphicalMatrixOfCells_test1.getColor(1,0).equals(Color.YELLOW));
        assertTrue(graphicalMatrixOfCells_test1.getColor(1,1).equals(Color.RED));
        assertTrue(graphicalMatrixOfCells_test1.getColor(1,2).equals(Color.BLUE));
        assertTrue(graphicalMatrixOfCells_test1.getColor(2,0).equals(Color.RED));
        assertTrue(graphicalMatrixOfCells_test1.getColor(2,1).equals(Color.BLACK));
        assertTrue(graphicalMatrixOfCells_test1.getColor(2,2).equals(Color.BLACK));

    }

   @Test
   void readGraphicalMatrixofCells() {
        graphicalMatrixOfCells_test1.readGraphicalMatrixofCells(matrixOfCells_for_test2);
        assertEquals(Cell.State.ELECTRONHEAD,matrixOfCells_for_test1.getCell(0,0).getState());
        assertEquals(Cell.State.CONDUCTOR,matrixOfCells_for_test1.getCell(0,1).getState());
        assertEquals(Cell.State.EMPTY,matrixOfCells_for_test1.getCell(0,2).getState());
        assertEquals(Cell.State.CONDUCTOR,matrixOfCells_for_test1.getCell(1,0).getState());
        assertEquals(Cell.State.ELECTRONTAIL,matrixOfCells_for_test1.getCell(1,1).getState());
        assertEquals(Cell.State.ELECTRONHEAD,matrixOfCells_for_test1.getCell(1,2).getState());
        assertEquals(Cell.State.ELECTRONTAIL,matrixOfCells_for_test1.getCell(2,0).getState());
        assertEquals(Cell.State.EMPTY,matrixOfCells_for_test1.getCell(2,1).getState());
        assertEquals(Cell.State.EMPTY,matrixOfCells_for_test1.getCell(2,2).getState());
   }
}