import java.util.*;
public class Generation{
    private int number;
    ArrayList<Creature> creatures;
    ArrayList<Creature> ranking;
    public Generation(int n){
        number = n;
        ranking = new ArrayList<Creature>();
        creatures = new ArrayList<Creature>();
        randomGen();
    }
    public void randomGen(){
        double[] input = new double[81];
        double[] hidden = new double[81];
        double output = Math.random();
        for(int i = 0; i < 100; i++){
            for(int a = 0; a < 81; a++){
                input[a] = Math.random();
                hidden[a] = Math.random();
            }
            output = Math.random();
            Creature c = new Creature(input,hidden,output,i);
            creatures.add(c);
            System.out.println(i);
        }
        System.out.println("creature random gen");
    }
    public void setRanking(){
        int count = 0;
        while(creatures.size() > 0){
            Creature temp = creatures.get(count);
            for(Creature c: creatures){
                if(c.getScore() > temp.getScore() && rankContains(c) == false)
                    temp = c;
            }
            System.out.println("------ " + temp);
            ranking.add(temp);
            creatures.remove(temp);
            System.out.println(ranking.size());
            count++;
        }
    }
    private boolean rankContains(Creature c){
        for(Creature a: creatures){
            if(c.getNumber() == a.getNumber())
                return true;
        }
        return false;
    }
    public int getGenNum(){
        return number;
    }
    public Creature get(int i){
        return creatures.get(i);
    }
}