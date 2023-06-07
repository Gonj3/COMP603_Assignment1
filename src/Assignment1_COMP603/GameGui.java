/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jonat
 */
public class GameGui {
   
    public static void main(String[] args) {
        DBManager dataBase = new DBManager();
        dataBase.establishConnection();
        ArrayList<Item> items = new ArrayList<Item>();
        LinkedList<Character> enemies = new LinkedList<Character>();
        System.out.println(dataBase.getConnection());
        loadItems(items, dataBase);
        dataBase.closeConnections();
        JFrame frame = new JFrame("RPG_Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Panel panel = new Panel();
        //frame.add(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
        JButton test = new JButton("Hello");
        frame.add(test);
    }
    
    private static void loadItems(ArrayList<Item> items, DBManager dataBase)
    {
        String sqlStatement = "SELECT NAME FROM ITEMS";
        ResultSet rs = dataBase.queryDB(sqlStatement);
        try {
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(GameGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(GameGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void loadEnemies(LinkedList<Character> enemies, DBManager database)
    {
        
    }
}
