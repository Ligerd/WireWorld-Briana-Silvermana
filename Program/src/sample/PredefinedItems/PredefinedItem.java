package sample.PredefinedItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;




public class PredefinedItem {
    protected String name;
    protected Rectangle[][] definitionOfItem;
    protected Rectangle[][] temporaryRectangles;
    protected int width;
    protected int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getItemCell (int x, int y)
    {
        return (Color)definitionOfItem[x][y].getFill();
    }

    public void setTemporaryRectangles(int x, int y, Color color) {
        temporaryRectangles[x][y].setFill(color);
    }

    public Color getTemporaryRectangles (int x, int y)
    {
        return (Color)temporaryRectangles[x][y].getFill();
    }
}
