package so2;

import javax.swing.*;

public class Philosopher extends Thread {

    private static int ID = 0;

    private Fork leftFork;
    private Fork rightFork;
    private int idNumber;
    private int mealsCounter = 0;
    private boolean running = true;

    // JTextArea text;
    JLabel label;
    JLabel labelStats;


    Philosopher(JLabel label, JLabel labelStats, Fork left, Fork right){
        this.label = label;
        this.labelStats = labelStats;

        leftFork = left;
        rightFork = right;
        idNumber = ++ID;
    }

    @Override
    public void run() {
        try{
            while(running){
                think();
                sleep((int)(Math.random() * 1000) + 5000); // 5.0 - 6.0 s
                // ask waiter for permission to join the table
                if(askWaiter()){
                    sleep((int)(Math.random() * 1000) + 2500); // 2.5 - 3.5 s
                    // try to grab left fork
                    if(grabLeft()){
                        sleep((int)(Math.random() * 1000) + 2500); // 2.5 - 3.5 s
                        // try to grab right fork
                        if(grabRight()){
                            sleep((int)(Math.random() * 1000) + 2500); // 2.5 - 3.5 s
                            eat();
                            sleep((int)(Math.random() * 1000) + 5000); // 5.0 - 6.0 s
                            putDownRight();
                        }
                        sleep((int)(Math.random() * 1000) + 2500); // 2.5 - 3.5 s
                        putDownLeft();
                    }
                }
                sleep((int)(Math.random() * 1000) + 2500); // 2.5 - 3.5 s
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void think(){
        label.setText(this.toString());
    }

    public synchronized void eat(){
        if(leftFork.getOwner() == this && rightFork.getOwner() == this) {
            mealsCounter++;
            labelStats.setText(this + " meals : " + mealsCounter);
            label.setText(this + " IS EATING !!! ");
        }
    }

    public synchronized boolean grabLeft(){
        if(leftFork.isAvailable()){
            leftFork.setOwner(this);
            label.setText(this.toString());
            return true;
        }
        return false;
    }

    public synchronized boolean grabRight(){
        if(rightFork.isAvailable()){
            rightFork.setOwner(this);
            label.setText(this.toString());
            return true;
        }
        return false;
    }

    public synchronized void putDownLeft(){
        if(leftFork.getOwner() == this){
            leftFork.removeOwner();
            label.setText(this.toString());
        }
    }

    public synchronized void putDownRight(){
        if(rightFork.getOwner() == this){
            rightFork.removeOwner();
            label.setText(this.toString());
        }
    }

    public synchronized boolean askWaiter() {
        if(Fork.taken < 4) return true;
        return false;
    }

    public String toString(){
        return "Philosopher " + idNumber;
    }

    public void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread(){
        running = false;
    }

}