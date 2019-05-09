package CA;

public class Cell {
    public static enum State{
        EMPTY,
        ELECTRONHEAD,
        ELECTRONTAIL,
        CONDUCTOR
    }
    private State state;
    private int amoutOfElectronHeadNeighbours;



    public Cell(){
        this.state=State.EMPTY;
        amoutOfElectronHeadNeighbours=0;
    }

    public State getState(){
        return state;
    }
    public void setAmoutOfElectronHeadNeighbours(int amoutOfElectronHeadNeighbours) {
        this.amoutOfElectronHeadNeighbours = amoutOfElectronHeadNeighbours;
    }

    public int getAmoutOfElectronHeadNeighbours_for_test(){
        return amoutOfElectronHeadNeighbours;
    }

    public void setState(State state){
        this.state=state;
    }

    public void evolve(){
        switch (state){
            case EMPTY:
                setState(State.EMPTY);
                break;
            case ELECTRONHEAD:
                setState(State.ELECTRONTAIL);
                break;
            case ELECTRONTAIL:
                setState(State.CONDUCTOR);
                break;
            case CONDUCTOR:
                if(amoutOfElectronHeadNeighbours==1 || amoutOfElectronHeadNeighbours==2){
                    setState(State.ELECTRONHEAD);}
                    else{
                    setState(State.CONDUCTOR);
                }
                break;
            default:
        }
    }

    public Boolean isElectronHead(){
        if(state.equals(State.ELECTRONHEAD)){
            return true;
    }else
        {
            return false;

        }
    }

    public Boolean isEmpty(){
        if(state==State.EMPTY){
            return true;
        }else
        {
            return false;

        }
    }
    public Boolean isElectronTail(){
        if(state==State.ELECTRONTAIL){
            return true;
        }else
        {
            return false;

        }
    }
    public Boolean isConductor(){
        if(state==State.CONDUCTOR){
            return true;
        }else
        {
            return false;

        }
    }


}
