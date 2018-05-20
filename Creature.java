public class Creature{
    Brain brain;
    double[] inputZoneWeights;
    double[] hiddenLayer1Weights;
    double outputWeight;
    public Creature(double[] input, double[] hidden1, double output){
        brain = new Brain(input,hidden1,output);
        inputZoneWeights = input;
        hiddenLayer1Weights = hidden1;
        outputWeight = output;
    }
}