import java.util.*;
public class Snake{
    ArrayList<Box> tail;
    int headX;
    int headY;
    private int tailX;
    private int tailY;
    boolean growing;
    public Snake(){
        headX = 5;
        headY = 5;
        tailX = 5;
        tailY = 5;
        growing = false;
        tail = new ArrayList<Box>();
        tail.add(new Box(5,5));
    }
    public void move(int direction){
        if(direction == 0)
            headY--;
        else if(direction == 1)
            headX++;
        else if(direction == 2)
            headY++;
        else if(direction == 3)
            headX--;
        tailX = tail.get(tail.size()-1).getX();
        tailY = tail.get(tail.size()-1).getY();
        tail.get(0).setLocation(headX,headY);
        if(growing){
            growing = false;
            grow();
        }
    }
    public void grow(){
        tail.add(new Box(tailX,tailY));
        tail.get(tail.size()-2).connect(tail.get(tail.size()-1));
    }
    public boolean collided(){
        if(isOnEdge())
            return true;
        for(int i = 1; i < tail.size(); i++){
            if(headX == tail.get(i).getX() && headY == tail.get(i).getY())
                return true;
        }
        return false;
    }
    public boolean isOnEdge(){
        if(headX == 0 || headX == 26 || headY == 0 || headY == 26)
            return true;
        return false;
    }
}