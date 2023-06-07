/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonat
 */
public class GameGui implements ActionListener{
    JButton startGame = new JButton("Start Game");
    JPanel panelMainMenu = new JPanel();
    JButton prevScores = new JButton("Previous Scores");

    
    public static void main(String[] args) {
        GameGui gameGui = new GameGui();
        DBManager dataBase = new DBManager();
        dataBase.establishConnection();
        ArrayList<Item> items = new ArrayList<Item>();
        LinkedList<Character> enemies = new LinkedList<Character>();
        System.out.println(dataBase.getConnection());
        loadItems(items, dataBase);
        loadEnemies(enemies, dataBase);
        dataBase.closeConnections();
        JFrame frame = new JFrame("RPG_Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
        gameGui.mainMenu(gameGui.panelMainMenu);
        frame.add(gameGui.panelMainMenu, BorderLayout.SOUTH);
    }
    
    private void mainMenu(JPanel panel)
    {
        this.startGame.setPreferredSize(new Dimension(100, 40));
        this.prevScores.setPreferredSize(new Dimension(100, 40));
        this.startGame.addActionListener(this);
        this.prevScores.addActionListener(this);
        panel.add(startGame, BorderLayout.SOUTH);
        panel.add(prevScores, BorderLayout.SOUTH);
    }
    
    private static void loadItems(ArrayList<Item> items, DBManager dataBase)
    {
        String sqlStatement = "SELECT * FROM ITEMS";
        ResultSet rs = dataBase.queryDB(sqlStatement);
        
        try {
            while(rs.next())
            {
                if(rs.getString(1).charAt(0) == 'h')
                {
                    HealingItem item = new HealingItem(rs.getString(2), rs.getInt(3));
                    items.add(item);
                }
                else if(rs.getString(1).charAt(0) == 'a')
                {
                    DamageItem item = new DamageItem(rs.getString(2), rs.getInt(3));
                    items.add(item);
                }
                else if(rs.getString(1).charAt(0) == 'c')
                {
                    HitChanceItem item = new HitChanceItem(rs.getString(2), rs.getInt(3));
                    items.add(item);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(items);
    }
    
    private static void loadEnemies(LinkedList<Character> enemies, DBManager dataBase)
    {   
        String sqlStatement = "SELECT * FROM ENEMIES";
        ResultSet rs = dataBase.queryDB(sqlStatement);
        
        try {
            while(rs.next())
            {
                if(rs.getBoolean(1))
                {
                    Boss enemy = new Boss(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                    enemies.add(enemy);
                }
                else
                {
                    Enemy enemy = new Enemy(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    enemies.add(enemy);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(enemies);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == startGame)
        {
            System.out.println("Start Game clicked");
            this.panelMainMenu.removeAll();
        }
        if(source == prevScores)
        {
            System.out.println("prev Scores clicked");
        }
    }
    
    
    
}
