import java.util.*;
public class Brain{
    ArrayList<neuron> inputZone;
    ArrayList<neuron> hiddenLayer1;
    ArrayList<neuron> outputZone;
    public Brain(double[] input, double[] hidden1, double output){
        inputZone = new ArrayList<neuron>();
        hiddenLayer1 = new ArrayList<neuron>();
        outputZone = new ArrayList<neuron>();
        for(int i = 0; i < 81; i++){
            inputZone.add(new neuron(false,input[i]));
            hiddenLayer1.add(new neuron(true,hidden1[i]));
        }
        outputZone.add(new neuron(false,output));
        connectNet();
    }
    public void connectNet(){
        for(neuron n: inputZone){
            n.connect(hiddenLayer1);
        }
        for(neuron n: hiddenLayer1){
            n.connect(outputZone);
        }
    }
    public void resetInputVals(){
        for(neuron n: inputZone){
            n.setInputVal(0.0);
        }
        for(neuron n: hiddenLayer1){
            n.setInputVal(0.0);
        }
        outputZone.get(0).setInputVal(0.0);
    }
    public double think(int[][] map){
        int count = 0;
        for(int[] arr: map){
            for(int a: arr){
                inputZone.get(count).input((double)a);
                count++;
            }
        }
        /*
        for(neuron n: inputZone){
            System.out.println(n.getInputVal());
        }
        System.out.println("-----------------------------------------------------------");
        for(neuron n: hiddenLayer1){
            System.out.println(n.getInputVal());
        }
        System.out.println("-----------------------------------------------------------");
        */
        return outputZone.get(0).getInputVal();
        
    }
}