package sample.PredefinedItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AndGate extends PredefinedItem {
    public AndGate()
    {
        width = 13;
        height = 8;
        definitionOfItem = new Rectangle[height][width];
        temporaryRectangles = new Rectangle[height][width];
        for (int i = 0; i < height; ++i)
            for (int j = 0; j < width; ++j)
            {
                temporaryRectangles[i][j] = new Rectangle();
                definitionOfItem[i][j] = new Rectangle();
                definitionOfItem[i][j].setFill(Color.BLACK);
            }
        for (int i = 0; i < 8; ++i)
            definitionOfItem[0][i].setFill(Color.YELLOW);

        for (int i = 0; i < 3; ++i)
        definitionOfItem[1][8+i].setFill(Color.YELLOW);

        definitionOfItem[2][5].setFill(Color.YELLOW);
        definitionOfItem[2][7].setFill(Color.YELLOW);
        definitionOfItem[2][11].setFill(Color.YELLOW);

        definitionOfItem[3][0].setFill(Color.YELLOW);
        for (int i = 0; i < 3; ++i)
            definitionOfItem[3][4+i].setFill(Color.YELLOW);
        definitionOfItem[3][11].setFill(Color.YELLOW);

        definitionOfItem[4][1].setFill(Color.YELLOW);
        definitionOfItem[4][3].setFill(Color.YELLOW);
        definitionOfItem[4][5].setFill(Color.YELLOW);
        definitionOfItem[4][7].setFill(Color.YELLOW);
        definitionOfItem[4][9].setFill(Color.YELLOW);
        definitionOfItem[4][11].setFill(Color.YELLOW);

        definitionOfItem[5][1].setFill(Color.YELLOW);
        definitionOfItem[5][3].setFill(Color.YELLOW);
        for (int i = 0; i < 3; ++i)
            definitionOfItem[5][8+i].setFill(Color.YELLOW);

        definitionOfItem[6][1].setFill(Color.YELLOW);
        definitionOfItem[6][3].setFill(Color.YELLOW);
        definitionOfItem[6][9].setFill(Color.YELLOW);
        definitionOfItem[6][11].setFill(Color.YELLOW);
        definitionOfItem[6][12].setFill(Color.YELLOW);

        definitionOfItem[7][2].setFill(Color.YELLOW);




    }
}
