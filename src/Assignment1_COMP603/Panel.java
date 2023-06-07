/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author jonat
 */
public class Panel extends JPanel{
    
    public Panel()
    {
        
    }
    
    public void paint(Graphics g)
    {
        this.paintComponent(g);
        
        this.repaint();
    }
    
    
}
