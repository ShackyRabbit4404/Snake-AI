public class Timer implements Runnable{
    private int time;
    private keyboard listener;
    Creature cre;
    public Timer(int t,keyboard l,Creature c){
        time = t;
        listener = l;
        cre = c;
    }
    public void run(){
        try{
            Thread.sleep(time);
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(cre.getRD() == null){
            listener.deathR = "ran out of time";
            listener.contin = false;
        }
    }
}