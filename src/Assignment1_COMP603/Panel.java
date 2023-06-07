/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jonat
 */
public class Panel extends JPanel{ 
    ArrayList<Item> items;
    LinkedList<Character> enemies;
    public Panel(ArrayList<Item> items, LinkedList<Character> enemies)
    {
        this.items = items;
        this.enemies = enemies;
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawString(enemies.get(0).name, 50, 50);
        this.repaint();
    }
    
    private void mainMenu()
    {
        JButton startGame = new JButton("Start Game");
        
    }
}
