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
        g.setColor(Color.GRAY);
        g.fillRect(0,0,540,540);
        g.setColor(Color.BLACK);
        g.fillRect(20,20,500,500);
        g.setColor(Color.WHITE);
        for(Box b: snake.tail){
            g.fillRect(b.getX()*20,b.getY()*20,20,20);
        }
        g.setColor(Color.RED);
        g.fillRect(foodX*20,foodY*20,20,20);
    }
}