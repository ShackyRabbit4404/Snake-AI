import java.awt.event.*;
public class keyboard extends KeyAdapter
{
    int direction;
    boolean contin;
    int prevDi;
    String deathR;
    public keyboard()
    {
        direction = 1;
        prevDi = 1;
        contin = true;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W && prevDi != 0)
            direction = 0;
        if(key == KeyEvent.VK_A && prevDi != 3)
            direction = 3;
        if(key == KeyEvent.VK_S && prevDi != 2)
            direction = 2;
        if(key == KeyEvent.VK_D && prevDi != 1)
            direction = 1;
        if(key == KeyEvent.VK_Q)
            contin = false;
        if(direction == 0)
            prevDi = 2;
        else if(direction == 1)
            prevDi = 3;
        else if(direction == 2)
            prevDi = 0;
        else if(direction == 3)
            prevDi = 1;
    }
    /*
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
    }
    */
}