import javax.swing.*;
public class main{
    public static void main(){
        JFrame frame = new JFrame();
        frame.setBounds(0,0,1000,1000);
        frame.setVisible(true);
        Snake s = new Snake();
        Display screen = new Display(s);
        frame.add(screen);
        keyboard listener = new keyboard();
        frame.addKeyListener(listener);
        int fx = (int)(Math.random()*30);
        int fy = (int)(Math.random()*30);
        screen.setFood(fx,fy);
        while(listener.contin){
            if(s.headX == fx && s.headY == fy){
                s.growing = true;
                fx = (int)(Math.random()*30);
                fy = (int)(Math.random()*30);
                screen.setFood(fx,fy);
            }
            s.move(listener.direction);
            screen.drawing();
            try{
                Thread.sleep(300);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}