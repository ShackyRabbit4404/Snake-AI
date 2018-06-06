import javax.swing.*;
public class Game{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Snake s = new Snake();
        Display screen = new Display(s);
        keyboard keys = new keyboard();
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.add(screen);
        frame.addKeyListener(keys);
        boolean contin = true;
        int direction = 1;
        int fx = (int)(Math.random()*20+1);
        int fy = (int)(Math.random()*20+1);
        screen.setFood(fx,fy);
        while(contin){
            if(s.headX == fx && s.headY == fy){
                s.growing = true;
                fx = (int)(Math.random()*20) + 1;
                fy = (int)(Math.random()*20) + 1;
                screen.setFood(fx,fy);
            }
            s.move(keys.direction);
            screen.setView("map");
            screen.drawing(1);
            if(s.collided(true)){
                contin = false;
            }
            try{
                Thread.sleep(200);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}