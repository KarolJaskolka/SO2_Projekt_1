package so2;

import javax.swing.*;

public class Fork {

    private static int ID = 0;
    public static int taken = 0;

    private Philosopher owner;
    private int idNumber;
    private JLabel label;

    public Fork(JLabel label) {
        this.owner = null;
        this.label = label;
        idNumber = ++ID;
    }

    public boolean isAvailable(){
        if(owner == null) return true;
        return false;
    }

    public void setOwner(Philosopher philosopher){
        taken++;
        owner = philosopher;
        label.setText(this.toString());
    }

    public Philosopher getOwner() { return owner; }

    public void removeOwner(){
        taken--;
        owner = null;
        label.setText(this.toString());
    }

    public int getIdNumber(){
        return idNumber;
    }

    public String toString(){
        return "Fork " + idNumber + " -> " + owner;
    }

}