package cs320.lrsproject;

import cs320.lrsproject.GUI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage();
            }
        });
    }
}