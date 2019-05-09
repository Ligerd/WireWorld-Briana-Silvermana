package sample.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import CA.Cell;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


//import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private static Cell test_cell1= new Cell();
    private static Cell test_cell2= new Cell();
    private static Cell test_cell3= new Cell();
    private static Cell test_cell4= new Cell();

    @BeforeAll
    static void setUp(){
        test_cell2.setState(Cell.State.ELECTRONHEAD);
        test_cell3.setState(Cell.State.ELECTRONTAIL);
        test_cell4.setState(Cell.State.CONDUCTOR);
        test_cell4.setAmoutOfElectronHeadNeighbours(1);
    }

    @Test
    void test_evolve(){
        test_cell1.evolve();
        assertEquals(Cell.State.EMPTY,test_cell1.getState(),"Test for EMPTY");
        test_cell2.evolve();
        assertEquals(Cell.State.ELECTRONTAIL,test_cell2.getState(),"Test for ELECTRONHEAD");
        test_cell3.evolve();
        assertEquals(Cell.State.CONDUCTOR,test_cell3.getState(),"Test for ELECTRONTAIL");
        test_cell4.evolve();
        assertEquals(Cell.State.ELECTRONHEAD,test_cell4.getState(),"Test for CONDUCTOR");
    }

    @Test
    void test_isEmpty(){

        assertTrue(test_cell1.isEmpty(),"Metoda test_isEmpy. Test for EMPTY");
        assertFalse(test_cell2.isEmpty(),"Metoda test_isEmpy. Test for ELECTRONHEAD");
        assertFalse(test_cell3.isEmpty(),"Metoda test_isEmpy. Test for ELECTRONTAIL");
        assertFalse(test_cell4.isEmpty(),"Metoda test_isEmpy. Test for CONDUCTOR");
    }

    @Test
    void test_isElectronHead(){
        assertFalse(test_cell1.isElectronHead(),"Metoda test_isElectronHead(). Test for EMPTY");
        assertTrue(test_cell2.isElectronHead(),"Metoda test_isElectronHead(). Test for ELECTRONHEAD");
        assertFalse(test_cell3.isElectronHead(),"Metoda test_isElectronHead(). Test for ELECTRONTAIL");
        assertFalse(test_cell4.isElectronHead(),"Metoda test_isElectronHead(). Test for CONDUCTOR");
    }



    @Test
    void  test_isElectronTail(){
        assertFalse(test_cell1.isElectronTail(),"Metoda test_isElectronTail(). Test for EMPTY");
        assertFalse(test_cell2.isElectronTail(),"Metoda test_isElectronTail(). Test for ELECTRONHEAD");
        assertTrue(test_cell3.isElectronTail(),"Metoda test_isElectronTail(). Test for ELECTRONTAIL");
        assertFalse(test_cell4.isElectronTail(),"Metoda test_isElectronTail(). Test for CONDUCTOR");
    }

    @Test
    void  test_isConductor(){
        setUp();
        assertFalse(test_cell1.isConductor(),"Metoda test_isConductor(). Test for EMPTY");
        assertFalse(test_cell2.isConductor(),"Metoda test_isConductor(). Test for ELECTRONHEAD");
        assertFalse(test_cell3.isConductor(),"Metoda test_isConductor(). Test for ELECTRONTAIL");
        assertTrue(test_cell4.isConductor(),"Metoda test_isConductor(). Test for CONDUCTOR");
    }
}