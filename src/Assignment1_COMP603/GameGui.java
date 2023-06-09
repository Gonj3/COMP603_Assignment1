/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jonat
 */
public class GameGui extends Frame implements ActionListener{
    JButton startGame;
    JPanel panelMainMenu;
    JPanel panelGame;
    JButton prevScores;
    JButton nextButton;
    JButton attackUpgrade;
    JButton healthUpgrade;
    JButton hitChanceUpgrade;
    JTextField userNameInput = new JTextField("", 30);
    JButton userNameSubmit = new JButton("Submit");
    JFrame frame = new JFrame("RPG_Game");
    JButton attackButton = new JButton("Attack");
    JLabel playerLabel;
    JLabel enemyLabel;
    JLabel attackInfo;
    JLabel finalAttackInfo;
    boolean userNameInputed;
    int enemyIndex;
    Player player;
    BattleField battle;
    ArrayList<Item> items = new ArrayList<Item>();
    LinkedList<Character> enemies = new LinkedList<Character>();
    
    public GameGui()
    {
        this.panelGame = new JPanel(new GridLayout(8, 1));
        this.enemyIndex = 0;
    }
    
    public static void main(String[] args) {
        GameGui gameGui = new GameGui();
        DBManager dataBase = new DBManager();
        dataBase.establishConnection();
        System.out.println(dataBase.getConnection());
        gameGui.loadEnemies(gameGui.enemies, dataBase);
        gameGui.loadItems(gameGui.items, dataBase);
        dataBase.closeConnections();
        gameGui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameGui.frame.setSize(700, 600);
        gameGui.frame.setVisible(true);
        gameGui.mainMenu();
       // gameGui.frame.add(gameGui.panelMainMenu, BorderLayout.SOUTH);
        gameGui.frame.setVisible(true);
    }
    
    private void mainMenu()
    {
        panelMainMenu = new JPanel();
        startGame = new JButton("Start Game");
        prevScores = new JButton("Previous Scores");
        this.startGame.setPreferredSize(new Dimension(100, 40));
        this.prevScores.setPreferredSize(new Dimension(100, 40));
        this.startGame.addActionListener(this);
        this.prevScores.addActionListener(this);
        panelMainMenu.add(startGame);
        panelMainMenu.add(prevScores);
        this.frame.add(this.panelMainMenu);
        this.frame.setVisible(true);
        this.panelMainMenu.repaint();
    }
    
    private void createUser()
    {
        this.userNameInputed = false;
        this.userNameSubmit.addActionListener(this);
        this.userNameSubmit.setPreferredSize(new Dimension(100,50));
        this.panelGame.add(userNameInput);
        this.panelGame.add(userNameSubmit);
        this.frame.add(this.panelGame);
        this.panelGame.revalidate();
        this.panelGame.repaint();
        this.frame.repaint();
        this.frame.setVisible(true);
    }
    
    private void battleStart()
    {
        this.panelGame.removeAll();
        this.panelGame.repaint();
        if(this.enemyIndex < this.enemies.size())
        {
            this.battle = new BattleField(this.player, this.enemies.get(enemyIndex), this.items);
            this.enemyIndex++;
            System.out.println("tset");
            //battle.mainBattle();
            //this.frame.add(battle.panel, BorderLayout.CENTER);
            this.attackButton.addActionListener(this);
            this.playerLabel = new JLabel(this.battle.getPlayerToString());
            this.enemyLabel = new JLabel(this.battle.getEnemyToString());
            this.panelGame.add(attackButton);
            this.panelGame.add(this.playerLabel);
            this.panelGame.add(this.enemyLabel);
            this.frame.setVisible(true);
        }
    }
    
    private void battle()
    {
        this.panelGame.removeAll();
        if(this.battle.isPlayerAlive() && this.battle.isEnemyAlive())
        {
            this.playerLabel = new JLabel(this.battle.getPlayerToString());
            this.enemyLabel = new JLabel(this.battle.getEnemyToString());
            this.panelGame.add(this.attackButton);
            this.panelGame.add(this.playerLabel);
            this.panelGame.add(this.enemyLabel);
            if(!this.battle.attackInfo.equals(null))
            {
                this.attackInfo = new JLabel(this.battle.attackInfo);
                this.panelGame.add(this.attackInfo);
            }
            this.frame.setVisible(true);
        }
        else{
            this.panelGame.removeAll();
            this.panelGame.repaint();
            this.attackInfo = null;
            this.playerLabel = new JLabel(this.battle.getPlayerToString());
            this.enemyLabel = new JLabel(this.battle.getEnemyToString());
            this.panelGame.add(this.playerLabel);
            this.panelGame.add(this.enemyLabel);
            String battleInfo;
            if(this.battle.isPlayerAlive())
            {
                battleInfo = "You Won!!! " + this.battle.attackInfo;
                this.finalAttackInfo = new JLabel(battleInfo);
            }
            else{
                battleInfo = "You Lost!!! " + this.battle.attackInfo;
                this.finalAttackInfo = new JLabel(battleInfo);
            }
            this.panelGame.add(this.finalAttackInfo);
            
            if(this.battle.isPlayerAlive() && this.battle.isEnemyBoss())
            {
                Item itemFound = this.battle.givePlayerItem();
                JLabel foundItem = new JLabel("You found an Item!!! " + itemFound.toString());
                this.panelGame.add(foundItem);
            }
            this.nextButton = new JButton("Next");
            this.nextButton.addActionListener(this);
            this.panelGame.add(this.nextButton);
            this.frame.setVisible(true);
        }
    }
    
    private void upgradeMenu()
    {
        this.panelGame.removeAll();
        this.panelGame.repaint();
        this.attackUpgrade = new JButton("Upgrade Attack by 20");
        this.healthUpgrade = new JButton("Upgrade Health by 40");
        this.hitChanceUpgrade = new JButton("Upgrade Hit Chance by 10");
        this.attackUpgrade.addActionListener(this);
        this.healthUpgrade.addActionListener(this);
        this.hitChanceUpgrade.addActionListener(this);
        this.panelGame.add(this.attackUpgrade);
        this.panelGame.add(this.healthUpgrade);
        this.panelGame.add(this.hitChanceUpgrade);
        this.frame.setVisible(true);
    }
    
    private void itemChooserMenu()
    {
        
    }
    
    private void loadItems(ArrayList<Item> items, DBManager dataBase)
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
    
    private void loadEnemies(LinkedList<Character> enemies, DBManager dataBase)
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
            this.panelMainMenu.repaint();
            this.frame.remove(this.panelMainMenu);
            this.frame.repaint();
            this.createUser();
        }
        if(source == prevScores)
        {
            System.out.println("prev Scores clicked");
        }
        if(source == userNameSubmit)
        {
            String username = this.userNameInput.getText();
            if(username.equals(""))
            {
                
            }
            else{
                this.userNameInputed = true;
                this.player = new Player(username, 10, 40, 40);
                this.panelGame.removeAll();
                this.enemyIndex = 0;
                this.battleStart();
            }
        }
        if(source == attackButton)
        {
            this.battle.Attack();
            this.battle();
        }
        if(source == nextButton)
        {
            System.out.println("nextButton worked");
            if(this.battle.isPlayerAlive())
            {
                this.upgradeMenu();
            }
            else if(!this.battle.isPlayerAlive())
            {
                this.panelGame.removeAll();
                this.panelGame.repaint();
                this.frame.remove(this.panelGame);
                this.frame.remove(this.panelMainMenu);
                this.frame.repaint();
                this.enemyIndex = 0;
                this.mainMenu();
            }
        }
        if(source == attackUpgrade)
        {
            this.battle.upgradePlayerAttack();
            this.battleStart();
        }
        if(source == healthUpgrade)
        {
            this.battle.upgradePlayerHealth();
            this.battleStart();
        }
        if(source == hitChanceUpgrade)
        {
            this.battle.upgradePlayerHitChance();
            this.battleStart();
        }
    }
   }


