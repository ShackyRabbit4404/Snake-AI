import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Display extends JPanel{
    Snake snake;
    private int foodX;
    private int foodY;
    //test
    public Display(Snake s){
        super();
        snake = s;
    }
    public void drawing(){
        repaint();
    }
    public void setFood(int x,int y){
        foodX = x;
        foodY = y;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.WHITE);
        for(Box b: snake.tail){
            g.fillRect(b.getX()*10,b.getY()*10,10,10);
        }
        g.setColor(Color.RED);
        g.fillRect(foodX*10,foodY*10,10,10);
    }
}