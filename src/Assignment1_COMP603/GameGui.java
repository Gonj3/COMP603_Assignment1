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
    DBManager dataBase;
    JButton startGame;
    JPanel panelMainMenu;
    JPanel panelGame;
    JButton prevScores;
    JButton prevView;
    JButton prevDelete;
    JButton toMainMenu;
    JButton nextButton;
    JButton attackUpgrade;
    JButton healthUpgrade;
    JButton hitChanceUpgrade;
    JButton chooseItem;
    JButton submitItem;
    JTextField itemInput;
    JTextField userNameInput;
    JButton userNameSubmit;
    JFrame frame;
    JButton attackButton;
    JLabel playerLabel;
    JLabel enemyLabel;
    JLabel attackInfo;
    JLabel finalAttackInfo;
    boolean userNameInputed;
    int enemyIndex;
    Player player;
    BattleField battle;
    ArrayList<Item> items;
    LinkedList<Character> enemies;
    
    /*
    constructor for GameGui
    */
    public GameGui()
    {
        this.panelGame = new JPanel(new GridLayout(8, 1));
        this.enemyIndex = 0;
        this.dataBase = new DBManager();
        this.itemInput = new JTextField("", 30);
        this.userNameInput = new JTextField("", 30);
        this.userNameSubmit = new JButton("Submit");
        this.frame = new JFrame("RPG_GAME");
        this.attackButton = new JButton("Attack");
        this.items = new ArrayList<Item>();
        this.enemies = new LinkedList<Character>();
    }
    
    public static void main(String[] args) {
        GameGui gameGui = new GameGui();
        gameGui.dataBase.establishConnection();
        System.out.println(gameGui.dataBase.getConnection());
        gameGui.loadItems(gameGui.items, gameGui.dataBase);
        //gameGui.dataBase.closeConnections();
        gameGui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameGui.frame.setSize(700, 600);
        gameGui.frame.setVisible(true);
        gameGui.mainMenu();
       // gameGui.frame.add(gameGui.panelMainMenu, BorderLayout.SOUTH);
        gameGui.frame.setVisible(true);
    }
    
    /*
    main menu method which adda all the buttons and adds ActionListener to buttons
    then displays the panel
    */
    private void mainMenu()
    {
        panelMainMenu = new JPanel(new GridLayout(2,8));
        startGame = new JButton("Start Game");
        prevScores = new JButton("Previous Scores");
        this.startGame.setPreferredSize(new Dimension(100, 40));
        this.prevScores.setPreferredSize(new Dimension(100, 40));
        this.startGame.addActionListener(this);
        this.prevScores.addActionListener(this);
        panelMainMenu.add(startGame);
        panelMainMenu.add(prevScores);
        this.frame.add(this.panelMainMenu);
        this.frame.repaint();
        this.frame.setVisible(true);
        this.panelMainMenu.repaint();
        this.panelMainMenu.setVisible(true);
        
    }
    /*
    adds the prevScore menu buttons to the panel and adds the button functionality
    */
    private void prevScoreMenu()
    {
        this.prevView = new JButton("View Previous Scores");
        this.prevView.addActionListener(this);
        this.prevDelete = new JButton("Delete all previous scores");
        this.prevDelete.addActionListener(this);
        this.panelMainMenu.removeAll();
        this.panelMainMenu.add(this.prevView);
        this.panelMainMenu.add(this.prevDelete);
        this.panelMainMenu.repaint();
        this.frame.setVisible(true);
    }
    /*
    querys the previousscores table in the database and places all entry in labels and displays them for the user
    */
    private void viewPreviousScores()
    {
        String sqlStatement = "SELECT * FROM PREVIOUSSCORES";
        ResultSet rs = dataBase.queryDB(sqlStatement);
        this.panelMainMenu.removeAll();
        this.panelMainMenu.repaint();
        this.toMainMenu = new JButton("Main Menu");
        this.toMainMenu.addActionListener(this);
        this.panelMainMenu.add(this.toMainMenu);
        try {
            while(rs.next())
            {
                String output = "Name: " + rs.getString(1)+"Health: "+rs.getInt(2)+"/"+rs.getInt(3)+" Damage: "+rs.getInt(4)+"Hit Chance: "+rs.getInt(5);
                JLabel label = new JLabel(output);
                this.panelMainMenu.add(label);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.panelMainMenu.repaint();
        this.panelMainMenu.setVisible(true);
        this.frame.repaint();
        this.frame.setVisible(true);
    }
    
    /*
    adds the text fields and buttons for user creation on the the frame
    */
    private void createUser()
    {
        this.userNameInputed = false;
        this.userNameSubmit.addActionListener(this);
        this.userNameSubmit.setPreferredSize(new Dimension(100,50));
        JLabel label = new JLabel("Please type a username below");
        this.panelGame.add(label);
        this.panelGame.add(userNameInput);
        this.panelGame.add(userNameSubmit);
        this.frame.add(this.panelGame);
        this.panelGame.revalidate();
        this.panelGame.repaint();
        this.frame.repaint();
        this.frame.setVisible(true);
    }
    
    /*
    starts a new battle with the player and an enemy and adds the buttons and labels
    if the player has items it will also create a button for entering item use menu
    */
    private void battleStart()
    {
        this.panelGame.removeAll();
        this.panelGame.repaint();
        if(this.enemyIndex < this.enemies.size())
        {
            this.battle = new BattleField(this.player, this.enemies.get(enemyIndex), this.items);
            this.enemyIndex++;
            //battle.mainBattle();
            //this.frame.add(battle.panel, BorderLayout.CENTER);
            this.attackButton.addActionListener(this);
            this.playerLabel = new JLabel(this.battle.getPlayerToString());
            this.enemyLabel = new JLabel(this.battle.getEnemyToString());
            this.panelGame.add(attackButton);
            this.panelGame.add(this.playerLabel);
            this.panelGame.add(this.enemyLabel);
            if(!this.player.itemsEmpty())
            {
                this.chooseItem = new JButton("Choose Item");
                this.chooseItem.addActionListener(this);
                this.panelGame.add(this.chooseItem);
            }
            this.frame.setVisible(true);
        }
        else
        {
            String queury = "INSERT INTO PREVIOUSSCORES VALUES ('"+this.player.name+"', "+this.player.health+", "+this.player.maxHealth+", "+this.player.damage+", "+this.player.hitChance+", TRUE)";
            this.dataBase.updateDB(queury);
            this.panelGame.removeAll();
            this.panelGame.repaint();
            this.frame.repaint();
            this.mainMenu();
        }
    }
    
    /*
    creates a menu for attacks after the first
    displays info about the last attack
    also checks if you have one the current battle and whether you found an item.
    */
    private void battle()
    {
        this.panelGame.removeAll();
        this.panelGame.repaint();
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
    /*
    creates and displays an upgrade menu for user to choose there upgrade.
    */
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
    
    /*
    create a menu that displays the user items and allows them to choose one.
    */
    private void itemChooserMenu()
    {
        if(!this.player.itemsEmpty())
        {
            this.panelGame.removeAll();
            this.panelGame.repaint();
            this.submitItem = new JButton("Submit Item Choice And Attack");
            this.submitItem.addActionListener(this);
            this.panelGame.add(this.itemInput);
            this.panelGame.add(this.submitItem);
            for(int i = 0; i < this.player.itemsSize(); i++)
            {
                String labelInput = i + " :" + this.player.getItem(i);
                JLabel label = new JLabel(labelInput);
                this.panelGame.add(label);
            }
            this.frame.setVisible(true);
        }
    }
    
    /*
    loads items from the database into the items ArrayList.
    */
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
    }
    
    /*
    loads enemies from the database and put them in a LinkedList.
    */
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
    }
    /*
    add functionality to all the buttons
    when a button is pressed it is called and the method checks the source of the button
    based on the source it will do a desired action of the button.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == startGame)
        {
            this.panelMainMenu.removeAll();
            this.panelMainMenu.repaint();
            this.frame.remove(this.panelMainMenu);
            this.frame.repaint();
           // this.dataBase.getConnection();
            this.enemies = new LinkedList<Character>();
            this.loadEnemies(enemies, dataBase);
          //  this.dataBase.closeConnections();
            this.createUser();
        }
        if(source == prevScores)
        {
            this.prevScoreMenu();
        }
        if(source == userNameSubmit)
        {
            String username = this.userNameInput.getText();
            if(username.equals(""))
            {
                JLabel label = new JLabel("Please type a username into the entry box above");
                this.panelGame.add(label);
                this.panelGame.repaint();
                this.panelGame.setVisible(true);
                this.frame.repaint();
                this.frame.setVisible(true);
            }
            else{
                this.userNameInputed = true;
                this.player = new Player(username, 150, 40, 40);
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
        if(source == chooseItem)
        {
            this.itemChooserMenu();
        }
        if(source == submitItem)
        {
            String itemInput = this.itemInput.getText();
            try{
                int itemInputToInt = Integer.parseInt(itemInput);
                
                if(itemInputToInt < this.player.itemsSize())
                {
                    this.player.useItem(itemInputToInt);
                    this.battle.Attack();
                    this.battle();
                }
                else{
                    JLabel label = new JLabel("Please input a valid Number");
                    this.panelGame.add(label);
                    this.panelGame.repaint();
                    this.panelGame.setVisible(true);
                    this.frame.repaint();
                    this.frame.setVisible(true);
                }
            }
            catch(NumberFormatException ex){
                JLabel label = new JLabel("Please input a valid Number");
                this.panelGame.add(label);
                this.panelGame.repaint();
                this.panelGame.setVisible(true);
                this.frame.repaint();
                this.frame.setVisible(true);
            }
            
        }
        if(source == prevView)
        {
            this.viewPreviousScores();
        }
        if(source == prevDelete)
        {
            this.dataBase.updateDB("DELETE FROM PREVIOUSSCORES");
            this.panelMainMenu.removeAll();
            this.panelMainMenu.repaint();
            this.frame.repaint();
            this.mainMenu();
        }
        if(source == toMainMenu)
        {
            this.panelMainMenu.removeAll();
            this.panelMainMenu.repaint();
            this.frame.repaint();
            this.mainMenu();
        }
    }
   }


