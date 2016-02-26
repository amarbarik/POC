package za.co.random.tests;

import javax.swing.*;
import java.awt.*;

/**
 * Created by F4742443 on 2016/01/15.
 */
class TinyWindow extends JFrame {

    public static void main(String[] args) {
        TinyWindow window = new TinyWindow();
        window.setVisible(true);
    }

    //======================================================= constructor
    public TinyWindow() {
        //... Create content panel, set layout
        JPanel content = new JPanel();                           //Note 2
        content.setLayout(new FlowLayout());   // Use FlowLayout //Note 3

        //... Add one label to the content pane.
        JLabel greeting = new JLabel("We come in peace.");       //Note 4
        content.add(greeting);                 // Add label      //Note 5

        //... Set window (JFrame) characteristics
        setContentPane(content);                                 //Note 6
        pack();                                // Do layout.     //Note 7
        setTitle("Tiny Window using JFrame Subclass");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           // Center window.
    }
}
