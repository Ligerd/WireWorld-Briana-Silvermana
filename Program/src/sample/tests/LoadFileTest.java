package sample.tests;

import Files.LoadFile;
import javafx.scene.paint.Color;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class LoadFileTest {
    private static LoadFile loadFile;
    @BeforeAll
    static void setUp() throws MalformedURLException {
        JFXPanel jfxPanel = new JFXPanel();
        File file = new File("src/sample/tests/loadfile_test.png");
        loadFile=new LoadFile(file);
    }
    @Test
    void getwidthimg(){
        assertEquals(5,loadFile.getwidthimg(),"Test getwidthimg");
    }
    @Test
    void getheightimg(){
    assertEquals(5,loadFile.getheightimg(),"Test getheightimg");
    }
    @Test
    void getColor() {
        assertTrue(loadFile.getColor(0,0).equals(Color.BLUE));
        assertTrue(loadFile.getColor(1,0).equals(Color.YELLOW));
        assertTrue(loadFile.getColor(2,0).equals(Color.BLACK));
        assertTrue(loadFile.getColor(3,0).equals(Color.BLACK));
        assertTrue(loadFile.getColor(4,0).equals(Color.RED));
        assertTrue(loadFile.getColor(0,1).equals(Color.YELLOW));
        assertTrue(loadFile.getColor(1,1).equals(Color.RED));
        assertTrue(loadFile.getColor(2,1).equals(Color.BLUE));
        assertTrue(loadFile.getColor(3,1).equals(Color.BLUE));
        assertTrue(loadFile.getColor(4,1).equals(Color.BLUE));
        assertTrue(loadFile.getColor(0,2).equals(Color.RED));
        assertTrue(loadFile.getColor(1,2).equals(Color.BLACK));
        assertTrue(loadFile.getColor(2,2).equals(Color.BLACK));
        assertTrue(loadFile.getColor(3,2).equals(Color.RED));
        assertTrue(loadFile.getColor(4,2).equals(Color.BLACK));
        assertTrue(loadFile.getColor(0,3).equals(Color.YELLOW));
        assertTrue(loadFile.getColor(1,3).equals(Color.RED));
        assertTrue(loadFile.getColor(2,3).equals(Color.BLUE));
        assertTrue(loadFile.getColor(3,3).equals(Color.BLUE));
        assertTrue(loadFile.getColor(4,3).equals(Color.BLUE));
        assertTrue(loadFile.getColor(0,4).equals(Color.RED));
        assertTrue(loadFile.getColor(1,4).equals(Color.BLACK));
        assertTrue(loadFile.getColor(2,4).equals(Color.YELLOW));
        assertTrue(loadFile.getColor(3,4).equals(Color.BLACK));
        assertTrue(loadFile.getColor(4,4).equals(Color.BLACK));
    }

}