package france.uha.ensisa.fl.reversi;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Florent
 */
public class ReversiModel {
    public enum State {
        EMPTY("empty"),
        WHITE("white"),
        BLACK("black");
        
        private final String string;
        
        State(String string) {
            this.string = string;
        }
        
        @Override
        public String toString() {
            return this.string;
        }
    }
    
    private final int x=8;
    private final int y=8;
    private State[][] grid = new State[x][y];
    private State currentPlayer = State.BLACK;
    
    public ReversiModel() {
        this.init();
    }
    
    public State[][] init() {
        for(int i=0;i<this.x;i++) {
            for(int j=0;j<this.y;j++){
                this.grid[i][j]=State.EMPTY;
            }
        }
        this.grid[3][3]=State.WHITE;
        this.grid[4][4]=State.WHITE;
        this.grid[3][4]=State.BLACK;
        this.grid[4][3]=State.BLACK;
        
        this.currentPlayer=State.BLACK;
        
        return this.grid;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void changeState(int x, int y) {
        if(this.grid[x][y]==State.WHITE) {
            this.grid[x][y]=State.BLACK;
        }
        else if(this.grid[x][y]==State.BLACK) {
            this.grid[x][y]=State.WHITE;
        }
        else {
        }
    }   
    
    public void setState(int x, int y, State s) {
        this.grid[x][y]=s;
    }   
    
    public State getState(int x, int y) {
        return grid[x][y];
    }
    
    public State getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public void changeCurrentPlayer() {
        if(this.currentPlayer==State.WHITE) {
            this.currentPlayer=State.BLACK;
        }
        else if(this.currentPlayer==State.BLACK) {
            this.currentPlayer=State.WHITE;
        }
        else {
        }
    }
    
    public boolean isPlayable(int x, int y) {
        if(this.grid[x][y]!=State.EMPTY) {
            return false;
        }
        else {
            //up middle
            if(y-1>=0 && (this.grid[x][y-1]!=State.EMPTY && this.grid[x][y-1]!=this.currentPlayer)) {
                int yBis=y-1;
                while(yBis-1>=0) {
                    yBis--;
                    if(this.grid[x][yBis]==State.EMPTY) {
                        yBis=0;
                    }
                    else if(this.grid[x][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //up right
            if((y-1>=0 && x+1<this.x) && (this.grid[x+1][y-1]!=State.EMPTY && this.grid[x+1][y-1]!=this.currentPlayer)) {
                int yBis=y-1;
                int xBis=x+1;
                while(yBis-1>=0 && xBis+1<this.x) {
                    yBis--;
                    xBis++;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        yBis=0;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //middle right
            if(x+1<this.x && (this.grid[x+1][y]!=State.EMPTY && this.grid[x+1][y]!=this.currentPlayer)) {
                int xBis=x+1;
                while(xBis+1<this.x) {
                    xBis++;
                    if(this.grid[xBis][y]==State.EMPTY) {
                        xBis=this.x;
                    }
                    else if(this.grid[xBis][y]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //down right
            if((y+1<this.y && x+1<this.x) && (this.grid[x+1][y+1]!=State.EMPTY && this.grid[x+1][y+1]!=this.currentPlayer)) {
                int xBis=x+1;
                int yBis=y+1;
                while(xBis+1<this.x && yBis+1<this.y) {
                    xBis++;
                    yBis++;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        xBis=this.x;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //down middle
            if(y+1<this.y && (this.grid[x][y+1]!=State.EMPTY && this.grid[x][y+1]!=this.currentPlayer)) {
                int yBis=y+1;
                while(yBis+1<this.x) {
                    yBis++;
                    if(this.grid[x][yBis]==State.EMPTY) {
                        yBis=this.x;
                    }
                    else if(this.grid[x][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //down left
            if((x-1>=0 && y+1<this.y) && (this.grid[x-1][y+1]!=State.EMPTY && this.grid[x-1][y+1]!=this.currentPlayer)) {
                int yBis=y+1;
                int xBis=x-1;
                while(xBis-1>=0 && yBis+1<this.x) {
                    yBis++;
                    xBis--;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        yBis=this.x;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //middle left
            if(x-1>=0 && (this.grid[x-1][y]!=State.EMPTY && this.grid[x-1][y]!=this.currentPlayer)) {
                int xBis=x-1;
                while(xBis-1>=0) {
                    xBis--;
                    if(this.grid[xBis][y]==State.EMPTY) {
                        xBis=0;
                    }
                    else if(this.grid[xBis][y]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            //up left
            if((x-1>=0 && y-1>=0) && (this.grid[x-1][y-1]!=State.EMPTY && this.grid[x-1][y-1]!=this.currentPlayer)) {
                int xBis=x-1;
                int yBis=y-1;
                while(xBis-1>=0 && yBis-1>=0) {
                    xBis--;
                    yBis--;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        xBis=0;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        return true;
                    }
                    else {
                    }                
                }
            }
            return false;
        }
    }
    
    public boolean play(int x, int y) {
        if(!this.isPlayable(x, y)) {
            return false;
        }
        else {
            this.setState(x, y, this.currentPlayer);
            //up middle
            if(y-1>=0 && (this.grid[x][y-1]!=State.EMPTY && this.grid[x][y-1]!=this.currentPlayer)) {
                int yBis=y-1;
                while(yBis-1>=0) {
                yBis--;
                    if(this.grid[x][yBis]==State.EMPTY) {
                        yBis=0;
                    }
                    else if(this.grid[x][yBis]==this.currentPlayer) {
                        for(int i=y-1; yBis<i; i--) {
                            this.changeState(x, i);
                        }
                        yBis=0;
                    }
                    else {
                    }     
                }
            }
            //up right
            if((y-1>=0 && x+1<this.x) && (this.grid[x+1][y-1]!=State.EMPTY && this.grid[x+1][y-1]!=this.currentPlayer)) {
                int yBis=y-1;
                int xBis=x+1;
                while(yBis-1>=0 && xBis+1<this.x) {
                    yBis--;
                    xBis++;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        yBis=0;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        int j=x+1;
                        for(int i=y-1; yBis<i; i--) {
                            this.changeState(j, i);
                            j++;
                        }
                        yBis=0;
                    }
                    else {
                    }                
                }
            }
            //middle right
            if(x+1<this.x && (this.grid[x+1][y]!=State.EMPTY && this.grid[x+1][y]!=this.currentPlayer)) {
                int xBis=x+1;
                while(xBis+1<this.x) {
                    xBis++;
                    if(this.grid[xBis][y]==State.EMPTY) {
                        xBis=this.x;
                    }
                    else if(this.grid[xBis][y]==this.currentPlayer) {
                        for(int i=x+1; i<xBis; i++) {
                            this.changeState(i, y);
                        }
                        xBis=this.x;
                    }
                    else {
                    }                
                }
            }
            //down right
            if((y+1<this.y && x+1<this.x) && (this.grid[x+1][y+1]!=State.EMPTY && this.grid[x+1][y+1]!=this.currentPlayer)) {
                int xBis=x+1;
                int yBis=y+1;
                while(xBis+1<this.x && yBis+1<this.y) {
                    xBis++;
                    yBis++;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        xBis=this.x;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        int j=x+1;
                        for(int i=y+1; i<yBis; i++) {
                            this.changeState(j, i);
                            j++;
                        }
                        xBis=this.x;
                    }
                    else {
                    }                
                }
            }
            //down middle
            if(y+1<this.y && (this.grid[x][y+1]!=State.EMPTY && this.grid[x][y+1]!=this.currentPlayer)) {
                int yBis=y+1;
                while(yBis+1<this.x) {
                    yBis++;
                    if(this.grid[x][yBis]==State.EMPTY) {
                        yBis=this.x;
                    }
                    else if(this.grid[x][yBis]==this.currentPlayer) {
                        for(int i=y+1; i<yBis; i++) {
                            this.changeState(x, i);
                        }
                        yBis=this.x;
                    }
                    else {
                    }                
                }
            }
            //down left
            if((x-1>=0 && y+1<this.y) && (this.grid[x-1][y+1]!=State.EMPTY && this.grid[x-1][y+1]!=this.currentPlayer)) {
                int yBis=y+1;
                int xBis=x-1;
                while(xBis-1>=0 && yBis+1<this.x) {
                    yBis++;
                    xBis--;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        yBis=this.x;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        int j=x-1;
                        for(int i=y+1; i<yBis; i++) {
                            this.changeState(j, i);
                            j--;
                        }
                        yBis=this.x;
                    }
                    else {
                    }                
                }
            }
            //middle left
            if(x-1>=0 && (this.grid[x-1][y]!=State.EMPTY && this.grid[x-1][y]!=this.currentPlayer)) {
                int xBis=x-1;
                while(xBis-1>=0) {
                    xBis--;
                    if(this.grid[xBis][y]==State.EMPTY) {
                        xBis=0;
                    }
                    else if(this.grid[xBis][y]==this.currentPlayer) {
                        for(int i=x-1; xBis<i; i--) {
                            this.changeState(i, y);
                        }
                        xBis=0;
                    }
                    else {
                    }                
                }
            }
            //up left
            if((x-1>=0 && y-1>=0) && (this.grid[x-1][y-1]!=State.EMPTY && this.grid[x-1][y-1]!=this.currentPlayer)) {
                int xBis=x-1;
                int yBis=y-1;
                while(xBis-1>=0 && yBis-1>=0) {
                    xBis--;
                    yBis--;
                    if(this.grid[xBis][yBis]==State.EMPTY) {
                        xBis=0;
                    }
                    else if(this.grid[xBis][yBis]==this.currentPlayer) {
                        int j=x-1;
                        for(int i=y-1; yBis<i; i--) {
                            this.changeState(j, i);
                            j--;
                        }
                        xBis=0;
                    }
                    else {
                    }                
                }
            }
            return true;
        }
    }
    
    public boolean canPlay() {
        for(int i=0; i<this.x; i++) {
            for(int j=0; j<this.y; j++) {
                if(isPlayable(i,j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList<Point> listOfPlayable() {
        ArrayList<Point> temp = new ArrayList<>();
        for(int i=0; i<this.x; i++) {
            for(int j=0; j<this.y; j++) {
                if(isPlayable(i,j)) {
                    temp.add(new Point(i,j));
                }
            }
        }
        return temp;
    }
            
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<this.y;i++) {
            for(int j=0;j<this.x;j++) {
                sb.append(this.grid[j][i].toString()).append("(").append(j).append(",").append(i).append(")").append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
