import java.util.*;
public class neuron{
    private double weight;
    private int threshold;
    private ArrayList<neuron> connections;
    private double inputVal;
    private boolean isHiddenLayer;
    public neuron(boolean i,double w){
        weight = w;
        threshold = 1; 
        connections = new ArrayList<neuron>();
        inputVal = 0.0;
        isHiddenLayer = i;
    }
    public void input(double in){
        inputVal += (in*weight);
        if(!isHiddenLayer || inputVal > threshold)
            fire();
    }
    public void fire(){
        for(neuron n: connections){
            n.input(inputVal);
        }
    }
    public void connect(ArrayList<neuron> n){
        connections = n;
    }
    public void setWeight(double w){
        weight = w;
    }
    public void setThreshold(int t){
        threshold = t;
    }
    public void setInputVal(double i){
        inputVal = i;
    }
    public double getInputVal(){
        return inputVal;
    }
}