package GUI;


import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import CA.Cell;
import CA.MatrixOfCells;
import sample.PredefinedItems.PredefinedItem;

public class GraphicalMatrixOfCells {

    private Rectangle[][] graphicalMatrixOfCells;
    private Group group = new Group();
    private int height;
    private int width;
    private int lastItemPositionX = -1;
    private int lastItemPositionY = -1;

    private final double cellSize;



    public GraphicalMatrixOfCells (int height, int width, double cellSize)
    {
        graphicalMatrixOfCells = new Rectangle[height][width];
        this.height = height;
        this.width = width;
        this.cellSize = cellSize;
        for (int i = 0; i < height; ++i)
            for (int j = 0; j < width; ++j)
            {
                graphicalMatrixOfCells[i][j] = new Rectangle();
                graphicalMatrixOfCells[i][j].setX(12 + j * (cellSize + 1));
                graphicalMatrixOfCells[i][j].setY(10 + i * (cellSize + 1));
                graphicalMatrixOfCells[i][j].setWidth(cellSize);
                graphicalMatrixOfCells[i][j].setHeight(cellSize);
                group.getChildren().add(graphicalMatrixOfCells[i][j]);
            }

    }

    private int getRectCoordinateX(double value)
    {
        Double d = (value - 12) /(cellSize + 1.0);
        if (d%(cellSize + 1.0) == 0)
        {
            return -1;
        }
        return d.intValue();
    }

    private int getRectCoordinateY(double value)
    {
        Double d = (value - 10) /(cellSize + 1.0);
        if (d%(cellSize + 1.0) == 0)
        {
            return -1;
        }
        return d.intValue();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setColorByCoordinate(double y, double x, Color color)
    {
        if( getRectCoordinateY(y) < height && getRectCoordinateY(y) >= 0 &&
                getRectCoordinateX(x) < width && getRectCoordinateX(x) >= 0 )
        graphicalMatrixOfCells[getRectCoordinateY(y)][getRectCoordinateX(x)].setFill(color);
    }

    public void setColor(int x, int y, Color color)
    {
        graphicalMatrixOfCells[x][y].setFill(color);
    }

    public Color getColor(int x, int y)
    {
        return (Color)graphicalMatrixOfCells[x][y].getFill();
    }


    public void show( Pane pane )
    {
        pane.getChildren().add(group);
    }





    public void setGraphicalMatrixOfCells( Cell[][] matrixOfCells )
    {
        for (int i = 0; i < matrixOfCells.length; ++i)
            for (int j = 0; j < matrixOfCells[i].length; ++j)
            {
                if ( matrixOfCells[i][j].isEmpty() )
                    graphicalMatrixOfCells[i][j].setFill(Color.BLACK);
                else if ( matrixOfCells[i][j].isConductor() )
                    graphicalMatrixOfCells[i][j].setFill(Color.YELLOW);
                else if ( matrixOfCells[i][j].isElectronHead() )
                    graphicalMatrixOfCells[i][j].setFill(Color.BLUE);
                else if ( matrixOfCells[i][j].isElectronTail() )
                    graphicalMatrixOfCells[i][j].setFill(Color.RED);
            }


    }

    public void readGraphicalMatrixofCells(MatrixOfCells matrixOfCells){
        for (int i=0;i<graphicalMatrixOfCells.length;i++)
            for(int j=0;j<graphicalMatrixOfCells[i].length;j++){

                if(graphicalMatrixOfCells[i][j].getFill().equals(javafx.scene.paint.Color.rgb(0,0,0)))
                    matrixOfCells.getCell(i, j).setState(Cell.State.EMPTY);
                if (graphicalMatrixOfCells[i][j].getFill().equals(javafx.scene.paint.Color.rgb(255,255,0)))
                    matrixOfCells.getCell(i, j).setState(Cell.State.CONDUCTOR);

                if(graphicalMatrixOfCells[i][j].getFill().equals(javafx.scene.paint.Color.rgb(0,0,255)))
                    matrixOfCells.getCell(i, j).setState(Cell.State.ELECTRONHEAD);
                if (graphicalMatrixOfCells[i][j].getFill().equals(Color.rgb(255,0,0)))
                    matrixOfCells.getCell(i, j).setState(Cell.State.ELECTRONTAIL);
            }
    }

    public void addPredefinedItem (double y, double x, PredefinedItem item)
    {
        int borderX = 0;
        int borderY = 0;
        int coordinateX = getRectCoordinateX(x);
        int coordinateY = getRectCoordinateY(y);
        if ( coordinateX != lastItemPositionX || coordinateY != lastItemPositionY)
        {
            removePredefinedItem(lastItemPositionY, lastItemPositionX, item);
            lastItemPositionY = coordinateY;
            lastItemPositionX = coordinateX;
            if( coordinateX < height && coordinateY >= 0 &&
                    coordinateY < width && coordinateX >= 0 )
            {
                if( coordinateX + item.getWidth() < width  )
                    borderX = item.getWidth();
                else
                    borderX = width - coordinateX;

                if( coordinateY + item.getHeight() < height  )
                    borderY = item.getHeight();
                else
                    borderY = height - coordinateY;


                for (int i = 0; i < borderY; ++i)
                    for (int j = 0; j < borderX; ++j) {

                    }
                for (int i = 0; i < borderY; ++i)
                    for (int j = 0; j < borderX; ++j)
                    {
                        item.setTemporaryRectangles(i, j, (Color) graphicalMatrixOfCells[coordinateY + i][coordinateX + j].getFill());
                        graphicalMatrixOfCells[coordinateY + i][coordinateX + j].setFill(item.getItemCell(i, j));
                    }
            }

        }

    }

    public void removePredefinedItem (int coordinateY, int coordinateX, PredefinedItem item)
    {
        int borderX = 0;
        int borderY = 0;


        if( coordinateX < height && coordinateY >= 0 &&
                coordinateY < width && coordinateX >= 0 ) {
            if (coordinateX + item.getWidth() < width)
                borderX = item.getWidth();
            else
                borderX = width - coordinateX;

            if (coordinateY + item.getHeight() < height)
                borderY = item.getHeight();
            else
                borderY = height - coordinateY;

            for (int i = 0; i < borderY; ++i)
                for (int j = 0; j < borderX; ++j) {
                    graphicalMatrixOfCells[coordinateY + i][coordinateX + j].setFill(item.getTemporaryRectangles(i, j));
                }
        }
    }

    public void clearLastItemPosition()
    {
        lastItemPositionX = -1;
        lastItemPositionY = -1;
    }

    public void clearLastInsertedItem( PredefinedItem item )
    {
        removePredefinedItem(lastItemPositionY, lastItemPositionX, item);
    }


}
