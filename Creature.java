public class Creature{
    Brain brain;
    private double[] inputZoneWeights;
    private double[] hiddenLayer1Weights;
    private double outputWeight;
    private int livedTo;
    int number;
    public Creature(double[] input, double[] hidden1, double output,int n){
        brain = new Brain(input,hidden1,output);
        inputZoneWeights = input;
        hiddenLayer1Weights = hidden1;
        outputWeight = output;
        int number = n;
    }
}