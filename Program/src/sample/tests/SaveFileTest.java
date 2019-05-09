package sample.tests;

import Files.SaveFile;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileTest {
    private static File file= new File("src/sample/tests/writefile_test.png");
    private static SaveFile saveFile;
    @BeforeAll
    static void setUp(){
        saveFile=new SaveFile(5,5,file);
    }

    @Test
    void writeImage() {
        saveFile.writePixel(0,0,Color.BLUE);
        saveFile.writePixel(1,0,Color.YELLOW);
        saveFile.writePixel(2,0,Color.BLACK);
        saveFile.writePixel(3,0,Color.BLACK);
        saveFile.writePixel(4,0,Color.RED);
        saveFile.writePixel(0,1,Color.YELLOW);
        saveFile.writePixel(1,1,Color.RED);
        saveFile.writePixel(2,1,Color.BLUE);
        saveFile.writePixel(3,1,Color.BLUE);
        saveFile.writePixel(4,1,Color.BLUE);
        saveFile.writePixel(0,2,Color.RED);
        saveFile.writePixel(1,2,Color.BLACK);
        saveFile.writePixel(2,2,Color.BLACK);
        saveFile.writePixel(3,2,Color.RED);
        saveFile.writePixel(4,2,Color.BLACK);
        saveFile.writePixel(0,3,Color.YELLOW);
        saveFile.writePixel(1,3,Color.RED);
        saveFile.writePixel(2,3,Color.BLUE);
        saveFile.writePixel(3,3,Color.BLUE);
        saveFile.writePixel(4,3,Color.BLUE);
        saveFile.writePixel(0,4,Color.RED);
        saveFile.writePixel(1,4,Color.BLACK);
        saveFile.writePixel(2,4,Color.YELLOW);
        saveFile.writePixel(3,4,Color.BLACK);
        saveFile.writePixel(4,4,Color.BLACK);

        try{
            assertTrue(saveFile.writeImage());
        }catch (IOException e){
            System.out.println("Test ma wyjątek");
        }
    }
}