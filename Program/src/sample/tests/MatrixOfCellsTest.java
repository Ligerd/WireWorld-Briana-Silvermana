package sample.tests;

import CA.Cell;
import CA.MatrixOfCells;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOfCellsTest {
    private static MatrixOfCells matrixOfCells_test1;
    private static MatrixOfCells matrixOfCells_test2;
    private static MatrixOfCells correct_matrixOfCells;
    @BeforeAll
    static void setUp(){
        matrixOfCells_test1=new MatrixOfCells(3,3);
        matrixOfCells_test1.getCell(0,0).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test1.getCell(0,1).setState(Cell.State.ELECTRONHEAD);
        matrixOfCells_test1.getCell(0,2).setState(Cell.State.ELECTRONTAIL);
        matrixOfCells_test1.getCell(1,0).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test1.getCell(1,1).setState(Cell.State.ELECTRONTAIL);
        matrixOfCells_test1.getCell(1,2).setState(Cell.State.EMPTY);
        matrixOfCells_test1.getCell(2,0).setState(Cell.State.EMPTY);
        matrixOfCells_test1.getCell(2,1).setState(Cell.State.ELECTRONHEAD);
        matrixOfCells_test1.getCell(2,2).setState(Cell.State.EMPTY);

        matrixOfCells_test2=new MatrixOfCells(4,9);
        matrixOfCells_test2.getCell(1,4).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(1,5).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,0).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,1).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,2).setState(Cell.State.ELECTRONTAIL);
        matrixOfCells_test2.getCell(2,3).setState(Cell.State.ELECTRONHEAD);
        matrixOfCells_test2.getCell(2,4).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,6).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,7).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(2,8).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(3,4).setState(Cell.State.CONDUCTOR);
        matrixOfCells_test2.getCell(3,5).setState(Cell.State.CONDUCTOR);

        correct_matrixOfCells=new MatrixOfCells(4,9);
        correct_matrixOfCells.getCell(1,4).setState(Cell.State.ELECTRONHEAD);
        correct_matrixOfCells.getCell(1,5).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,0).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,1).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,2).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,3).setState(Cell.State.ELECTRONTAIL);
        correct_matrixOfCells.getCell(2,4).setState(Cell.State.ELECTRONHEAD);
        correct_matrixOfCells.getCell(2,6).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,7).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(2,8).setState(Cell.State.CONDUCTOR);
        correct_matrixOfCells.getCell(3,4).setState(Cell.State.ELECTRONHEAD);
        correct_matrixOfCells.getCell(3,5).setState(Cell.State.CONDUCTOR);
    }
    @Test
    void countElectronHeadNeighbours() {
        matrixOfCells_test1.countElectronHeadNeighbours();
        assertEquals(1,matrixOfCells_test1.getCell(0,0).getAmoutOfElectronHeadNeighbours_for_test(),"Test komurki '0,0'");
        assertEquals(2,matrixOfCells_test1.getCell(1,0).getAmoutOfElectronHeadNeighbours_for_test(),"Test komurki '1,0");
    }

    @Test
    void nextGeneration() {
        matrixOfCells_test2.nextGeneration();
        for (int i=0;i<4;i++)
            for(int j=0;j<9;j++){
            assertEquals(correct_matrixOfCells.getCell(i,j).getState(),matrixOfCells_test2.getCell(i,j).getState(),"Test nextGeneration");
            }

    }


}