import javax.swing.*;
import java.util.*;
public class main{
    public static void main(){
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
        for(int genNum = 1; genNum < 3; genNum++){
            int direction = 1;
            int prevDirection = 3;
            screen.setGen(genNum);
            for(int i = 0; i < 100; i++){
                //(new Thread(new Timer(1000,listener,g.get(i)))).start();
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
                    //System.out.println(g.get(i).act(getMap(s,fx,fy)));
                    for(int a = 0; a < 7; a++){
                        System.out.print(g.get(i).genom[a]);
                    }
                    //System.out.println(getSenario(getVision(s,getMap(s,fx,fy),direction)));
                    //direction = g.get(i).genom[getSenario(getVision(s,getMap(s,fx,fy),direction))];
                    System.out.println("-----------------------------------------");
                    printMap(getMap(s,fx,fy));
                    System.out.println(g.get(i).think2(getMap(s,fx,fy)));
                    System.out.println(g.get(i).think2(getMap(s,fx,fy))%4);
                    System.out.println("-----------------------------------------");
                    direction = g.get(i).think2(getMap(s,fx,fy));
                    if(direction == prevDirection){
                        if(direction == 0){
                            direction = 2;
                        }
                        else if(direction == 1){
                            direction = 3;
                        }
                        else if(direction == 2){
                            direction = 0;
                        }
                        else if(direction == 3){
                            direction = 1;
                        }
                    }
                    if(direction == 0){
                        prevDirection = 2;
                    }
                    else if(direction == 1){
                        prevDirection = 3;
                    }
                    else if(direction == 2){
                        prevDirection = 0;
                    }
                    else if(direction == 3){
                        prevDirection = 1;
                    }
                    System.out.println(direction);  
                    /*
                    for(neuron n: g.get(i).brain.inputZone){
                    System.out.println("Input val: " + n.getInputVal());
                    }
                    System.out.println("-------------------------------------------------");
                    for(neuron n: g.get(i).brain.hiddenLayer1){
                    System.out.println("Input val: " + n.getInputVal());
                    }
                    System.out.println("-------------------------------------------------");
                    System.out.println(g.get(i).brain.outputZone.get(0).getInputVal());
                    System.out.println(((int)g.get(i).brain.outputZone.get(0).getInputVal())%4);
                    System.out.println("-------------------------------------------------");
                     */
                    s.move(direction);
                    screen.drawing(i);
                    g.get(i).reset();
                    if(s.collided()){
                        listener.contin = false;
                        listener.deathR = "hit the wall";
                    }
                    count ++;
                    if(count > 50){
                        listener.contin = false;
                        listener.deathR = "ran out of time";
                    }

                    try{
                        Thread.sleep(500);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }

                }
                g.get(i).setDeathReason(listener.deathR);
                g.get(i).setLivedTo(s.tail.size());
                listener.contin = true;
                s = new Snake();
                screen.snake = s;
            }
            Collections.sort(g.creatures);
            //System.out.println(genNum);
            for(Creature c: g.creatures){
                System.out.println(c);
            }
            //System.out.println("--------------------------------------------------------------");
            g.setGen(g.newGen(g.creatures),genNum + 1);
        }
        /*
        System.out.println("--------------------------------------------------------------");
        for(Creature c: g.ranking){
        System.out.println(c);
        }
         */
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

    public static int[][] getMap(Snake s,int x,int y){
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
        int[][] map = new int[9][9];
        int rowCount = 0;
        int colCount = 0;
        for(int row = s.headY-4; row <= s.headY+4; row++){
            for(int col = s.headX-4; col <= s.headX+4; col++){
                if ((col == 0 && row >= 0 && row <= 20) || (col == 20 &&  row >= 0 && row <= 20)|| (row == 0 && col >= 0 && col <= 20) || (row == 20 &&  col >= 0 && col <= 20))
                    map[rowCount][colCount] = 1;
                else if (s.contains(col,row))
                    map[rowCount][colCount] = 2;
                else if(col == s.headX && row == s.headY)
                    map[rowCount][colCount] = 3;
                else if (col == x && row == y)
                    map[rowCount][colCount] = 4;
                colCount++;
            }
            rowCount++;
            colCount = 0;
        }
        return map;
    }
}