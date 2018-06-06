import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Display extends JPanel{
    Snake snake;
    private int foodX;
    private int foodY;
    private int creatureNumber;
    private int genNumber;
    private String type;
    private Creature best;
    public Display(Snake s){
        super();
        snake = s;
    }

    public void setGen(int g){
        genNumber = g;
    }

    public void drawing(int n){
        creatureNumber = n;
        repaint();
    }
    public void setView(String v){
        type = v;
    }
    public void setCreature(Creature c){
        best = c;
    }
    public void paintBrain(Creature c){
        best = c;
        repaint();
    }
    public void setFood(int x,int y){
        foodX = x;
        foodY = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(type.contains("map")){
            g.setColor(Color.GRAY);
            g.fillRect(0,0,440,440);
            g.setColor(Color.BLACK);
            g.fillRect(20,20,400,400);
            g.setColor(Color.WHITE);
            for(Box b: snake.tail){
                g.fillRect(b.getX()*20,b.getY()*20,20,20);
            }
            g.setColor(Color.RED);
            g.fillRect(foodX*20,foodY*20,20,20);
            g.setColor(Color.BLACK);
            g.drawString("Generation number: " + genNumber,10,540);
            g.drawString("Creature Number: " + creatureNumber,10,550);
            g.drawString("Creature Head Position (" + snake.headX + "," + snake.headY + ")",10,560);
            g.drawString("Food position (" + foodX + "," + foodY + ")",10,570);
        }
        if (type.contains("brain")){
            g.setColor(Color.BLACK);
            for(int row = 1; row < 4; row ++){
                for(int col = 1; col < 5; col++){
                    g.fillOval(row*200+500,col*200,40,40);
                }
            }
            for(int row = 0; row < 1; row ++){
                for(int col = 0; col < 4; col++){
                    for(int a = 0; a < 4; a++){
                        Color temp = new Color((int)(255*best.brain2.getInToHidWeights()[row][col]),0,0);
                        g.setColor(temp);
                        g.drawLine((row+1)*200+520,(col+1)*200+20,(row+2)*200+520,(a+1)*200+20);
                    }
                }
            }
            for(int row = 1; row < 2; row ++){
                for(int col = 0; col < 4; col++){
                    for(int a = 0; a < 4; a++){
                        Color temp = new Color((int)(255*best.brain2.getHidToOutWeights()[row][col]),0,0);
                        g.setColor(temp);
                        g.drawLine((row+1)*200+520,(col+1)*200+20,(row+2)*200+520,(a+1)*200+20);
                    }
                }
            }
        }
    }
}