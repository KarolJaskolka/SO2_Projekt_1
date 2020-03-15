package so2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {

    JButton buttonStart = new JButton("Start");
    JButton buttonStop = new JButton("Stop");

    JLabel labelP1 = new JLabel("Philosopher 1");
    JLabel labelP2 = new JLabel("Philosopher 2");
    JLabel labelP3 = new JLabel("Philosopher 3");
    JLabel labelP4 = new JLabel("Philosopher 4");
    JLabel labelP5 = new JLabel("Philosopher 5");

    JLabel labelP1Stats = new JLabel("Philosopher 1 Meals : 0");
    JLabel labelP2Stats = new JLabel("Philosopher 2 Meals : 0");
    JLabel labelP3Stats = new JLabel("Philosopher 3 Meals : 0");
    JLabel labelP4Stats = new JLabel("Philosopher 4 Meals : 0");
    JLabel labelP5Stats = new JLabel("Philosopher 5 Meals : 0");

    JLabel labelF1 = new JLabel("Fork 1 -> null");
    JLabel labelF2 = new JLabel("Fork 2 -> null");
    JLabel labelF3 = new JLabel("Fork 3 -> null");
    JLabel labelF4 = new JLabel("Fork 4 -> null");
    JLabel labelF5 = new JLabel("Fork 5 -> null");

    // JTextArea output = new JTextArea();
    // JScrollPane scroll = new JScrollPane(output);

    ArrayList<Philosopher> philosophers = new ArrayList<>(5);
    ArrayList<Fork> forks = new ArrayList<>(5);

    public Window() {
        init();
    }

    public static void main(String[] args) throws IOException {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void start(){
        buttonStart.setEnabled(false);
        buttonStop.setEnabled(true);

        forks.add(new Fork(labelF1));
        forks.add(new Fork(labelF2));
        forks.add(new Fork(labelF3));
        forks.add(new Fork(labelF4));
        forks.add(new Fork(labelF5));

        philosophers.add(new Philosopher(labelP1, labelP1Stats, forks.get(0), forks.get(1)));
        philosophers.add(new Philosopher(labelP2, labelP2Stats, forks.get(2), forks.get(1)));
        philosophers.add(new Philosopher(labelP3, labelP3Stats, forks.get(2), forks.get(3)));
        philosophers.add(new Philosopher(labelP4, labelP4Stats, forks.get(4), forks.get(3)));
        philosophers.add(new Philosopher(labelP5, labelP5Stats, forks.get(4), forks.get(0)));

        for(int i=0;i<5;i++){
            philosophers.get(i).start();
        }

    }

    private void stop(){
        buttonStop.setEnabled(false);
        for(int i=0;i<5;i++){
            philosophers.get(i).stopThread();
        }
    }

    private void init() {

        setSize(900,1000);
        setTitle("S02 - Projekt 1");
        setLocationRelativeTo(null);
        setLayout(null);

        buttonStart.setBounds(275, 15, 75, 25);
        buttonStop.setBounds(425,15,75,25);

        labelF1.setBounds(100, 450, 400, 25);
        labelF2.setBounds(500, 450, 400, 25);
        labelF3.setBounds(500, 650, 400, 25);
        labelF4.setBounds(300, 750, 400, 25);
        labelF5.setBounds(100, 650, 400, 25);

        labelP1.setBounds(325, 350, 400, 75);
        labelP2.setBounds(600, 525, 400, 75);
        labelP3.setBounds(600, 800, 400, 75);
        labelP4.setBounds(50, 800, 400, 75);
        labelP5.setBounds(50, 525, 400, 75);

        labelP1Stats.setBounds(275, 75, 400, 25);
        labelP2Stats.setBounds(275, 125, 400, 25);
        labelP3Stats.setBounds(275, 175, 400, 25);
        labelP4Stats.setBounds(275, 225, 400, 25);
        labelP5Stats.setBounds(275, 275, 400, 25);

        Font font = new Font("SansSerif",Font.BOLD,20);

        labelF1.setFont(font);
        labelF2.setFont(font);
        labelF3.setFont(font);
        labelF4.setFont(font);
        labelF5.setFont(font);

        labelP1.setFont(font);
        labelP2.setFont(font);
        labelP3.setFont(font);
        labelP4.setFont(font);
        labelP5.setFont(font);

        labelP1Stats.setFont(font);
        labelP2Stats.setFont(font);
        labelP3Stats.setFont(font);
        labelP4Stats.setFont(font);
        labelP5Stats.setFont(font);

        buttonStart.addActionListener((e) -> start());
        buttonStop.addActionListener((e) -> stop());
        buttonStop.setEnabled(false);

        //scroll.setBounds(1100, 25,200, 900);
        //output.setEditable(false);

        add(labelF1);
        add(labelF2);
        add(labelF3);
        add(labelF4);
        add(labelF5);

        add(labelP1);
        add(labelP2);
        add(labelP3);
        add(labelP4);
        add(labelP5);

        add(labelP1Stats);
        add(labelP2Stats);
        add(labelP3Stats);
        add(labelP4Stats);
        add(labelP5Stats);

        //add(scroll);
        add(buttonStart);
        add(buttonStop);
    }

}
