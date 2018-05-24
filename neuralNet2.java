public class neuralNet2{
    private double[][] inputToHiddenWeights;
    private double[][] hiddenToOutputWeights;
    private double[] hiddenLayerVals;
    private double[] values;
    public neuralNet2(){
        inputToHiddenWeights = new double[81][81];
        hiddenToOutputWeights = new double[81][81];
        values = new double[1];
        hiddenLayerVals = new double[81];
        randomizeWeigths();
    }
    
    public void randomizeWeigths(){
        for(int row = 0; row < inputToHiddenWeights.length; row++){
            for(int col = 0; col < inputToHiddenWeights[0].length; col++){
                inputToHiddenWeights[row][col] = Math.random();
            }
        }
        for(int row = 0; row < hiddenToOutputWeights.length; row++){
            for(int col = 0; col < hiddenToOutputWeights[0].length; col++){
                hiddenToOutputWeights[row][col] = Math.random();
            }
        }
    }
        
    public void reset(){
        for(int i = 0; i < 81; i++){
            hiddenLayerVals[i] = 0;
        }
    }
    
    public double[] multiply(double[] input,double[][] w){
       double[] ret = new double[81];
       for(int i = 0; i < 81; i++){
           for(int a = 0; a < 81; a++){
               ret[i] += input[a]*w[i][a];
           }
       }
       return ret;
    }
    
    public double sigmoid(double d){
        return 1/(1 + Math.exp(-1*d));
    }

    public double forward(double[] inputVals){
        hiddenLayerVals = multiply(inputVals,inputToHiddenWeights);
        for(int row = 0; row < values.length; row++){
            hiddenLayerVals[row] = sigmoid(hiddenLayerVals[row]);
        }
        values = multiply(hiddenLayerVals,hiddenToOutputWeights);
        for(int row = 0; row < values.length; row++){
            values[row] = sigmoid(values[row]);
        }
        double sum = 0;
        for(int row = 0; row < hiddenLayerVals.length; row++){
           sum += values[row];
        }
        sum = sigmoid(sum);
        return sum;
    }

}