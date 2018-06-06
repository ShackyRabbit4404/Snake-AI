public class neuralNet2{
    private double[][] inputToHiddenWeights;
    private double[][] hiddenToOutputWeights;
    private double[] hiddenLayerVals;
    private double[] values;
    public neuralNet2(){
        inputToHiddenWeights = new double[4][4];
        hiddenToOutputWeights = new double[4][4];
        hiddenLayerVals = new double[4];
        randomizeWeigths();
    }
    public void setWeigthsInToHid(double[][] a){
        inputToHiddenWeights = a;
    }
    public double[][] getInToHidWeights(){
        return inputToHiddenWeights;
    }
    public void setWeightsHidToOut(double[][] a){
        hiddenToOutputWeights = a;
    }
    public double[][] getHidToOutWeights(){
        return hiddenToOutputWeights;
    }
    public void randomizeWeigths(){
        for(int row = 0; row < inputToHiddenWeights.length; row++){
            for(int col = 0; col < inputToHiddenWeights[0].length; col++){
                inputToHiddenWeights[row][col] = Math.random();
                hiddenToOutputWeights[row][col] = Math.random();
            }
        }
    }
    public void reset(){
        for(int i = 0; i < hiddenLayerVals.length; i++){
            hiddenLayerVals[i] = 0;
        }
    }
    public double[] multiply(double[] input,double[][] w){
       double[] ret = new double[input.length];
       for(int i = 0; i < input.length; i++){
           for(int a = 0; a < input.length; a++){
               ret[i] += input[i]*w[i][a];
           }
       }
       return ret;
    }
    public double sigmoid(double d){
        return 1/(1 + Math.pow(Math.E,(-1*d)));
    }
    public int forward(double[] inputVals){
        hiddenLayerVals = multiply(inputVals,inputToHiddenWeights);
        for(int row = 0; row < hiddenLayerVals.length; row++){
            hiddenLayerVals[row] = sigmoid(hiddenLayerVals[row]);
        }
        values = new double[hiddenLayerVals.length];
        values = multiply(hiddenLayerVals,hiddenToOutputWeights);
        for(int row = 0; row < values.length; row++){
            values[row] = sigmoid(values[row]);
        }
        double sum = 0;
        for(int row = 0; row < hiddenLayerVals.length; row++){
           values[row] = sigmoid(values[row]);
        }
        int largest  = 0;
        for(int i = 0; i < values.length; i++){
            if(values[largest] > values[i])
                largest = i;
        }
        return largest;
    }

}