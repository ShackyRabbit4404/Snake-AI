public class Timer implements Runnable{
    private int time;
    private keyboard listener;
    public Timer(int t,keyboard l){
        time = t;
        listener = l;
    }
    public void run(){
        try{
            Thread.sleep(time);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Ran out of time");
        listener.contin = false;
    }
}