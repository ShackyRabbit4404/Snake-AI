public class neuralNet2{
    private int[][] inputToHiddenWeights;
    private int[][] hiddenToOutputWeights;
    private int[][] values;
    public neuralNet2(int[][] v){
        inputToHiddenWeights = new int[9][9];
        hiddenToOutputWeights = new int[9][9];
        values = v;
    }
    public double[][] multiply(double[][] a, double[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2){ 
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++){
            for (int j = 0; j < n2; j++){
                for (int k = 0; k < n1; k++){
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}