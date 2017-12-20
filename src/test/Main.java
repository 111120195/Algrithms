package test;

import javax.swing.*;

public class Main {
    private static int val = 0;
    public static void main(String args[]){

        JFrame frame = new JFrame();
        JTextField tfTest = new JTextField("10",3);
        frame.add(tfTest);
        frame.setSize(200,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        tfTest.addActionListener(e->{
            try{
                val = Integer.valueOf(tfTest.getText());
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(val);
        });

        System.out.println("hello");
    }
}




































