import java.util.*;
public class Generation{
    private int number;
    ArrayList<Creature> creatures;
    public Generation(int n){
        number = n;
        creatures = new ArrayList<Creature>();
        randomGen();
    }

    public ArrayList<Creature> newGen2(){
        ArrayList<Creature> ret = new ArrayList<Creature>();
        int count = 0;
        for(int r = 0; r < 10; r++){
            for(int a = 0; a < 10; a++){
                int chance = (int)Math.random()*100;
                double[][] inToHid1 = creatures.get(r).brain2.getInToHidWeights();
                double[][] inToHid2 = creatures.get(chance).brain2.getInToHidWeights();
                double[][] hidToOut1 = creatures.get(r).brain2.getHidToOutWeights();
                double[][] hidToOut2 = creatures.get(chance).brain2.getHidToOutWeights();
                double rand;
                for(int row = 0; row < inToHid1.length; row++){
                    for(int col = 0; col < inToHid1[0].length; col++){
                        rand = Math.random();
                        if(rand < .25)
                            inToHid1[row][col] = inToHid2[row][col];
                        else if(rand < .30)
                            inToHid1[row][col] = Math.random();
                        rand = Math.random();
                        if(rand < .25)
                            hidToOut1[row][col] = hidToOut2[row][col];
                        else if(rand < .30)
                            hidToOut1[row][col] = Math.random();
                    }
                }
                creatures.get(count).brain2.setWeigthsInToHid(inToHid1);
                creatures.get(count).brain2.setWeigthsHidToOut(hidToOut1);
                count++;
            }
        }
        return ret;
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