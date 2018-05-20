import java.util.*;
public class Generation{
    private int number;
    ArrayList<Creature> creatures;
    public Generation(int n){
        number = n;
    }
    public void randomGen(){
        double[] input = new double[400];
        double[] hidden = new double[400];
        double output = Math.random();
        for(int i = 0; i < 100; i++){
            for(int a = 0; a < 400; a++){
                input[a] = Math.random();
                hidden[a] = Math.random();
            }
            output = Math.random();
            creatures.add(new Creature(input,hidden,output,i));
        }
    }
}