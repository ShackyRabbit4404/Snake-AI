import java.util.*;
public class Generation{
    private int number;
    ArrayList<Creature> creatures;
    public Generation(int n){
        number = n;
        creatures = new ArrayList<Creature>();
        randomGen();
    }
    public void setGen(ArrayList<Creature> c,int n){
        creatures = c;
        number = n;
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
        }
        System.out.println("Created random gen");
    }
    public ArrayList<Creature> newGen(ArrayList<Creature> prevG){
        ArrayList<Creature> ret = new ArrayList<Creature>();
        int count = 0;
        for(int i = 0; i < 10; i++){
            for(int a = 0; a < 10; a++){
                double[] inputZone = new double[81];
                double[] hiddenLayer = new double[81];
                double output = 0;
                int mergeCreature = (int)(Math.random() * 100); 
                double chance;
                for(int b = 0; b < 81; b++){
                    chance = Math.random();
                    if(chance < .75)
                        inputZone[b] = prevG.get(i).getInput()[b];
                    else if(chance < .80)
                        inputZone[b] = Math.random();
                    else
                        inputZone[b] = prevG.get(mergeCreature).getHidden()[b];
                    chance = Math.random();
                    if(chance < .75)
                        hiddenLayer[b] = prevG.get(i).getInput()[b];
                    else if(chance < .80)
                        hiddenLayer[b] = Math.random();
                    else
                        hiddenLayer[b] = prevG.get(mergeCreature).getHidden()[b];
                }
                chance = Math.random();
                if(chance < .75)
                    output = prevG.get(i).getOutput();
                else if(chance < .80)
                    output = Math.random();
                else 
                    output = prevG.get(mergeCreature).getOutput();
                ret.add(new Creature(inputZone,hiddenLayer,output,count));
                count ++;
            }
        }
        return ret;
    }
    public int getGenNum(){
        return number;
    }
    public Creature get(int i){
        return creatures.get(i);
    }
}