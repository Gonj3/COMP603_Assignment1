/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import javax.swing.JFrame;

/**
 *
 * @author jonat
 */
public class GameGui {
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("RPG_Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
