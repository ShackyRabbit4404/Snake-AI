import java.util.*;
public class Generation{
    private int number;
    ArrayList<Creature> creatures;
    ArrayList<Integer> ranking;
    public Generation(int n){
        number = n;
        ranking = new ArrayList<Integer>();
        creatures = new ArrayList<Creature>();
        randomGen();
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
            creatures.add(new Creature(input,hidden,output,i + 1));
        }
    }
    public void setRanking(){
        for(int i = 0; i < creatures.size(); i++){
            int count = 0;
            while(creatures.get(i).getLivedTo() < creatures.get(count).getLivedTo()){
                count++;
            }
            ranking.add(creatures.get(i).getLivedTo(),count);
        }
    }
    public int getGenNum(){
        return number;
    }
    public Creature get(int i){
        return creatures.get(i);
    }
}