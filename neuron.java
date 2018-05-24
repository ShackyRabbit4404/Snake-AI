import java.util.*;
public class neuron{
    private double weight;
    private double threshold;
    private ArrayList<neuron> connections;
    private double inputVal;
    private boolean isHiddenLayer;
    public neuron(boolean i,double w){
        weight = w;
        threshold = .2; 
        connections = new ArrayList<neuron>();
        inputVal = 0.0;
        isHiddenLayer = i;
    }
    public void input(double in){
        inputVal += (in*weight);
    }
    public void fire(){
        for(neuron n: connections){
            n.input(inputVal);
        }
    }
    public void compute(){
        inputVal = 1/(1 + Math.exp(inputVal));
        if(inputVal >= threshold)
            fire();
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