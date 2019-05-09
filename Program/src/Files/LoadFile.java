package Files;


import javafx.scene.paint.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class LoadFile {
    private File file;
    private Image image;

    public LoadFile(File file) throws MalformedURLException {
        this.file = file;
        URL url = file.toURI().toURL();
        image = new Image(String.valueOf(url));
    }

    public int getwidthimg() {
        return (int) image.getWidth();
    }

    public int getheightimg() {
        return (int) image.getHeight();
    }

    public Color getColor(int width, int height) {
        PixelReader pixelReader = image.getPixelReader();
        Color color = pixelReader.getColor(width, height);

        if (color.equals(Color.rgb(0, 0, 0)))
            return Color.BLACK;

        if (color.equals(Color.rgb(255, 255, 0)))
            return Color.YELLOW;

        if (color.equals(Color.rgb(0, 0, 255)))
            return Color.BLUE;

        if (color.equals(Color.rgb(255, 0, 0)))
            return Color.RED;

        return Color.BLACK;
    }
}