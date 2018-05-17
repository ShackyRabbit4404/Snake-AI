import java.util.*;
public class Brain{
    ArrayList<neuron> inputZone;
    ArrayList<neuron> hiddenLayer1;
    ArrayList<neuron> outputZone;
    public Brain(){
        inputZone = new ArrayList<neuron>();
        hiddenLayer1 = new ArrayList<neuron>();
        outputZone = new ArrayList<neuron>();
        for(int i = 0; i < 400; i++){
            inputZone.add(new neuron(false));
        }
        System.out.println("size() " + inputZone.size());
        for(int i = 0; i < 100; i++){
            hiddenLayer1.add(new neuron(true));
        }
        outputZone.add(new neuron(false));
    }
    public void resetInputVals(){
        for(neuron n: hiddenLayer1){
            n.setInputVal(0.0);
        }
    }
    public double think(int[][] map){
        int count = 0;
        for(int[] arr: map){
            for(int a: arr){
                System.out.println(count);
                inputZone.get(count).input((double)a);
                count++;
            }
        }
        return outputZone.get(0).getInputVal();
    }
}