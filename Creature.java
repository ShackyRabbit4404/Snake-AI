public class Creature implements Comparable {
    Brain brain;
    private double[] inputZoneWeights;
    private double[] hiddenLayer1Weights;
    private double outputWeight;
    private int[] prevPosition;
    private int livedTo;
    private String deathReason;
    private int number;
    public Creature(double[] input, double[] hidden1, double output,int n) {
        brain = new Brain(input,hidden1,output);
        inputZoneWeights = input;
        hiddenLayer1Weights = hidden1;
        outputWeight = output;
        number = n;
    }
    public double[] getInput(){
        return inputZoneWeights;
    }
    public double[] getHidden(){
        return hiddenLayer1Weights;
    }
    public double getOutput(){
        return outputWeight;
    }
    public void setLivedTo(int l){
        livedTo = l;
    }
    public int getScore(){
        int ret = 0;
        if(deathReason.equals("hit the wall"))
            ret ++;
        return ret + livedTo;
    }
    public int act(int[][] m){
        return (int)brain.think(m);
    }
    public void setDeathReason(String r){
        deathReason = r;
    }
    public String getRD(){
        return deathReason;
    }
    public void reset(){
        brain.resetInputVals();
    }
    public int getNumber(){
        return number;
    }
    public String toString(){
        return "Creature number " + number + ", lived to be " + livedTo + " blocks long and died because it " + deathReason + " and had a score of " + getScore();
    }
    public int compareTo(Object other) {
        Creature c = (Creature)other;
        if (getScore() > c.getScore())
            return -1;
        else if(getScore() == c.getScore())
            return 0;
        return 1; 
    }
}