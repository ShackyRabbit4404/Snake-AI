import java.awt.event.*;
public class keyboard extends KeyAdapter
{
    int direction;
    boolean contin;
    public keyboard()
    {
        direction = 1;
        contin = true;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W)
            direction = 0;
        if(key == KeyEvent.VK_A)
            direction = 3;
        if(key == KeyEvent.VK_S)
            direction = 2;
        if(key == KeyEvent.VK_D)
            direction = 1;
        if(key == KeyEvent.VK_Q)
            contin = false;
    }
    /*
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
    }
    */
}