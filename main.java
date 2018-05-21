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
        int fx = (int)(Math.random()*20) + 1;
        int fy = (int)(Math.random()*20) + 1;
        screen.setFood(fx,fy);
        Generation g = new Generation(1);
        for(int i = 0; i < 100; i++){
            fx = (int)(Math.random()*20) + 1;
            fy = (int)(Math.random()*20) + 1;
            while(listener.contin){
                if(s.headX == fx && s.headY == fy){
                    s.growing = true;
                    fx = (int)(Math.random()*20) + 1;
                    fy = (int)(Math.random()*20) + 1;
                    screen.setFood(fx,fy);
                }
                System.out.println(g.get(i).act(getMap(s,fx,fy)));
                listener.direction = g.get(i).act(getMap(s,fx,fy))%4;
                s.move(listener.direction);
                screen.drawing(i);
                g.get(0).reset();
                if(s.collided())
                    listener.contin = false;
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            listener.contin = true;
            s = new Snake();
            screen.snake = s;
            System.out.println("The snake lived to be " + s.tail.size() + " blocks long.");
        }
    }

    public static int[][] getMap(Snake s,int x,int y){
        int[][] ret = new int[20][20];
        for(int row = 0; row < ret.length; row++){
            for(int col = 0; col < ret[0].length; col++){
                if(col == 0 || col == ret[0].length-1 || row == 0 || row == ret.length-1){
                    ret[row][col] = 1;
                }
                else if(s.contains(row,col)){
                    ret[row][col] = 2;
                }
                else if(row == s.headX && col == s.headY){
                    ret[row][col] = 3;
                }
                else if(row == x && col == y){
                    ret[x][y] = 4;    
                }
                else{
                    ret[row][col] = 0;
                }
            }
        }
        return ret;
    }
}