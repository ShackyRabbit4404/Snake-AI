import javax.swing.*;
public class displayAI{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Snake s = new Snake();
        Display screen = new Display(s);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.add(screen);
        boolean contin = true;
        int direction = 1;
        int fx = (int)(Math.random()*20+1);
        int fy = (int)(Math.random()*20+1);
        screen.setFood(fx,fy);
        int count = 0;
        while(count < 30){
            Creature temp = new Creature(count);
            while(contin){
                if(s.headX == fx && s.headY == fy){
                    s.growing = true;
                    fx = (int)(Math.random()*20) + 1;
                    fy = (int)(Math.random()*20) + 1;
                    screen.setFood(fx,fy);
                }
                double[] inputV = new double[4];
                inputV[0] = (double)s.headX/20;
                inputV[1] = (double)s.headY/20;
                inputV[2] = (double)fx/20;
                inputV[3] = (double)fy/20;
                direction = temp.think2(inputV);
                s.move(direction);
                screen.setCreature(temp);
                screen.setView("map brain");
                screen.drawing(temp.getNumber());
                if(s.collided(false)){
                    contin = false;
                }
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            s = new Snake();
            screen.snake = s;
            contin = true;
            direction = 1;
            count++;
        }
    }
}