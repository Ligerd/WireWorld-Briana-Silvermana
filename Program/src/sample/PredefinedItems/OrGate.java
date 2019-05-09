package sample.PredefinedItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OrGate extends PredefinedItem {
    public OrGate()
    {
        width = 4;
        height = 5;
        definitionOfItem = new Rectangle[height][width];
        temporaryRectangles = new Rectangle[height][width];
        for (int i = 0; i < height; ++i)
            for (int j = 0; j < width; ++j)
            {
                temporaryRectangles[i][j] = new Rectangle();
                definitionOfItem[i][j] = new Rectangle();
                definitionOfItem[i][j].setFill(Color.BLACK);
            }
        definitionOfItem[0][0].setFill(Color.YELLOW);
        definitionOfItem[0][1].setFill(Color.YELLOW);
        definitionOfItem[1][2].setFill(Color.YELLOW);
        definitionOfItem[2][1].setFill(Color.YELLOW);
        definitionOfItem[2][2].setFill(Color.YELLOW);
        definitionOfItem[2][3].setFill(Color.YELLOW);
        definitionOfItem[3][2].setFill(Color.YELLOW);
        definitionOfItem[4][0].setFill(Color.YELLOW);
        definitionOfItem[4][1].setFill(Color.YELLOW);

    }
}
