package Files;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SaveFile {
    private WritableImage writableImage;
    private File file;
    public SaveFile(int height, int widht, File file){
        writableImage= new WritableImage(widht,height);
        this.file=file;
    }
    public void writePixel(int x, int y,Color color){
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        if(color.equals(Color.BLACK))
            pixelWriter.setColor(x,y,Color.BLACK);
        if(color.equals(Color.YELLOW))
            pixelWriter.setColor(x,y,Color.YELLOW);
        if(color.equals(Color.BLUE))
            pixelWriter.setColor(x,y,Color.BLUE);
        if(color.equals(Color.RED))
            pixelWriter.setColor(x,y,Color.RED);
    }
    public boolean writeImage() throws IOException {
        if(ImageIO.write(SwingFXUtils.fromFXImage(writableImage,null),"png",file))
            return true;
        else
            return false;
    }
}
