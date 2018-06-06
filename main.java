import javax.swing.*;
import java.util.*;
public class main{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setVisible(true);
        Snake s = new Snake();
        Display screen = new Display(s);
        frame.add(screen);
        keyboard listener = new keyboard();
        frame.addKeyListener(listener);
        int fx = (int)(Math.random()*10) + 1;
        int fy = (int)(Math.random()*10) + 1;
        screen.setFood(fx,fy);  
        Generation g = new Generation(1);
        Creature best = g.get(0);
        screen.setView("brain");
        for(int genNum = 1; genNum < 10001; genNum++){
            int direction = 1;
            int prevDirection = 3;
            screen.setGen(genNum);
            for(int i = 0; i < 100; i++){
                int count = 0;
                fx = (int)(Math.random()*20) + 1;
                fy = (int)(Math.random()*20) + 1;
                screen.setFood(fx,fy);
                listener.deathR = "";
                while(listener.contin){
                    if(s.headX == fx && s.headY == fy){
                        s.growing = true;
                        fx = (int)(Math.random()*20) + 1;
                        fy = (int)(Math.random()*20) + 1;
                        screen.setFood(fx,fy);
                        count = 0;
                    }
                    double[] inputV = new double[4];
                    inputV[0] = (double)s.headX/20;
                    inputV[1] = (double)s.headY/20;
                    inputV[2] = (double)fx/20;
                    inputV[3] = (double)fy/20;
                    direction = g.get(i).think2(inputV);
                    s.move(direction);
                    g.get(i).reset();
                    if(s.collided(false)){
                        listener.contin = false;
                        listener.deathR = "hit the wall";
                    }
                    count ++;
                    if(count > 50){
                        listener.contin = false;
                        listener.deathR = "ran out of time";
                    }
                }
                g.get(i).setDeathReason(listener.deathR);
                g.get(i).setLivedTo(s.tail.size());
                listener.contin = true;
                s = new Snake();
                screen.snake = s;
            }
            Collections.sort(g.creatures);
            if(g.get(0).getScore() > best.getScore())
                best = g.get(0);
            System.out.println(best);
            System.out.println(genNum);
            g.creatures = g.newGen2();
        }
        screen.setView("brain");
        screen.paintBrain(best);
    }

    public static int getSenario(int[] v){
        if(v[0] == 0 && v[1] == 0){
            return 0;
        }
        if(v[0] == 1 && v[1] == 0){
            return 1;
        }
        if(v[0] == 1 && v[1] == 1){
            return 2;
        }
        if(v[0] == 0 && v[1] == 1){
            return 3;
        }
        if(v[0] == 2 && v[1] == 0){
            return 4;
        }
        if(v[0] == 0 && v[1] == 2){
            return 5;
        }
        if(v[0] == 2 && v[1] == 1){
            return 6;
        }
        return 7;
    }

    public static void printMap(int[][] m){
        for(int[] arr: m){
            for(int i: arr){
                System.out.print(i); 
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------");
    }

    public static int[] getVision(Snake s,int[][] m,int d){
        int[] view = new int[2];
        if(d == 0){
            view[0] = m[s.headX-1][s.headY-1];
            view[1] = m[s.headX][s.headY-1];
        }
        else if(d == 1){
            view[0] = m[s.headX+1][s.headY-1];
            view[1] = m[s.headX+1][s.headY];
        }
        else if(d == 2){
            view[0] = m[s.headX+1][s.headY+1];
            view[1] = m[s.headX][s.headY+1];
        }
        else{
            view[0] = m[s.headX-1][s.headY+1];
            view[1] = m[s.headX-1][s.headY];
        }
        return view;
    }

    public static double[][] getMap(Snake s,int x,int y){
        int[][] ret = new int[20][20];
        for(int row = 0; row < ret.length; row++){
            for(int col = 0; col < ret[0].length; col++){
                if(col == 0 || col == ret[0].length-1 || row == 0 || row == ret.length-1){
                    ret[row][col] = 1;
                }
                else if(s.contains(row,col)){
                    ret[row][col] = 1;
                }
                else if(row == s.headX && col == s.headY){
                    ret[row][col] = 6;
                }
                else if(row == x && col == y){
                    ret[x][y] = 2;    
                }
                else{
                    ret[row][col] = 0;
                }
            }
        }
        double[][] map = new double[9][9];
        int rowCount = 0;
        int colCount = 0;
        for(int row = s.headY-4; row <= s.headY+4; row++){
            for(int col = s.headX-4; col <= s.headX+4; col++){
                if ((col == 0 && row >= 0 && row <= 20) || (col == 20 &&  row >= 0 && row <= 20)|| (row == 0 && col >= 0 && col <= 20) || (row == 20 &&  col >= 0 && col <= 20))
                    map[rowCount][colCount] = 0.2;
                else if (s.contains(col,row))
                    map[rowCount][colCount] = 0.2;
                else if(col == s.headX && row == s.headY)
                    map[rowCount][colCount] = 0.5;
                else if (col == x && row == y)
                    map[rowCount][colCount] = 1;
                colCount++;
            }
            rowCount++;
            colCount = 0;
        }
        return map;
    }
}