package CA;


import sample.Observer.Observer;
import sample.Observer.Publisher;
import java.util.ArrayList;

public class MatrixOfCells  {
    private Cell[][] matrixOfcells;
    private int height;
    private int width;
    private int numberOfGenerations;
    private SimulationThread simulationThread;
    private Boolean canStartNewSimulation = true;


    private Double timeGap;

    public void setTimeGap(double speedValue) {

        this.timeGap = 2/speedValue*1000;
    }



    public MatrixOfCells(int height, int width){
        matrixOfcells= new Cell[height][width];
        for(int i=0;i<height;i++)
            for(int j = 0; j< width; j++)
                matrixOfcells[i][j] = new Cell();
        this.height = height;
        this.width = width;
        timeGap = Double.valueOf(2000);
    }

    public Cell [][]  getMatrixOfcells_for_test(){
        return matrixOfcells;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }

    public Cell getCell(int height, int width)
    {
       return matrixOfcells[height][width];
    }

    public void countElectronHeadNeighbours(){
        int neighbours=0;

        for(int x=0;x<matrixOfcells.length;x++){
            for(int y=0;y<matrixOfcells[x].length;y++)
                if(matrixOfcells[x][y].getState()==Cell.State.CONDUCTOR){
                    if(x>0){
                        if(matrixOfcells[x-1][y].isElectronHead())
                            neighbours++;
                        if (y>0)
                            if(matrixOfcells[x-1][y-1].isElectronHead())
                                neighbours++;
                        if(y< width -1)
                            if(matrixOfcells[x-1][y+1].isElectronHead())
                                neighbours++;
                    }
                    if(x<height-1){
                        if(matrixOfcells[x+1][y].isElectronHead())
                            neighbours++;
                        if(y>0)
                            if(matrixOfcells[x+1][y-1].isElectronHead())
                                neighbours++;
                        if(y< width -1)
                            if(matrixOfcells[x+1][y+1].isElectronHead())
                                neighbours++;
                    }
                    if(y< width -1)
                        if(matrixOfcells[x][y+1].isElectronHead())
                            neighbours++;
                    if(y>0)
                        if(matrixOfcells[x][y-1].isElectronHead())
                            neighbours++;


                    matrixOfcells[x][y].setAmoutOfElectronHeadNeighbours(neighbours);
                    neighbours = 0;
                }

        }
    }

    public  void nextGeneration(){
        countElectronHeadNeighbours();
        for(int i=0;i<height;i++){
            for(int j = 0; j< width; j++)
                matrixOfcells[i][j].evolve();
        }
    }



  public void startWireWorld(Observer o) {
      if( canStartNewSimulation )
      {
          simulationThread = new SimulationThread();
          simulationThread.addObserver(o);
          simulationThread.start();

      }

  }

  public void pauseThread() {
        simulationThread.setStop(true);
  }





    class SimulationThread extends Thread implements Publisher {

        ArrayList <Observer> observerList;



        private Boolean stop = false;
        public void addObserver (Observer o)
        {
            observerList.add(o);
        }
        public void removeObserver (Observer o)
        {
            observerList.remove(o);
        }
        public void setStop(Boolean stop) {
            this.stop = stop;
        }
        public void notifyObservers()
        {
            for (Observer o: observerList)
                o.update(matrixOfcells);
        }

        public SimulationThread() {
            observerList = new ArrayList<>();
        }

        public  void sim()
        {
            canStartNewSimulation = false;

                for (int i = 0; i < numberOfGenerations; ++i) {
                    if (stop)
                        i = numberOfGenerations;
                    else{
                        nextGeneration();
                        notifyObservers();
                        try {
                            Thread.sleep(timeGap.intValue());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }


            canStartNewSimulation = true;
        }
        @Override
        public  void run() {
            sim();
        }

    }



}
