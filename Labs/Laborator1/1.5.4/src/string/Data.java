package string;

public class Data {

    String string;
    boolean beginning = false;

    int first = 0, last = 0;

    public Data(String string) {
        this.string = string;
        last = string.length() - 1;
    }

    public synchronized char readFromBeginning(String string){

        if(beginning){
            try{
                System.out.println("Data beginning is waiting");
                wait();
                System.out.println("Data beginning was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
        beginning = true;

        if(first < string.length()) {
            return string.charAt(first++);
        }
        return 0;
    }

    public synchronized char readFromEnd(String string){

        if(!beginning) {
            try {
                System.out.println("Data end is waiting");
                wait();
                System.out.println("Data end was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
        beginning = false;

        if(last >= 0) {
            return string.charAt(last--);
        }
        return 0;
    }
}
