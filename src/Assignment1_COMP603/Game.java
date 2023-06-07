/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Game {
    /*
    the items.txt file is read and each line is used to represent an item that is put into the ArrayList items.
    the enemies.txt file is read and each line is used to represent an Enemy or a Boss to be put into the LinkedList enemies.
    
    the user is then prompted to input a, b or c to interact with the menu. 
    
    a. 
    creates a new player and runs through the enemies LinkedList to create BattleFields for each enemy, assuming the Player has not died in the previous battle.
    the Player score is then written into the PreviousScores.txt file on a new line.
    
    b. 
    reads the PreviousScored file and outputs each line to the console.
    
    c.
    quits the program.
    */
   /* public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<Item>();
        LinkedList<Character> enemies = new LinkedList<Character>();
        try {
            FileReader fileReader = new FileReader("Items.txt");
            BufferedReader inStream = new BufferedReader(fileReader);
            String line = null;
            
            while((line=inStream.readLine())!=null)
            {
                String [] splitLine = line.split(",");
                int itemStatIncrease = Integer.parseInt(splitLine[2]);
                
                if(splitLine[0].equals("h"))
                {
                    HealingItem newItem = new HealingItem(splitLine[1], itemStatIncrease);
                    items.add(newItem);
                }
                else if(splitLine[0].equals("a"))
                {
                    DamageItem newItem = new DamageItem(splitLine[1], itemStatIncrease);
                    items.add(newItem);
                }
                else if(splitLine[0].equals("hc"))
                {
                    HitChanceItem newItem = new HitChanceItem(splitLine[1], itemStatIncrease);
                    items.add(newItem);
                }
            }
            inStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileReader fileReader = new FileReader("Enemies.txt");
            BufferedReader inStream = new BufferedReader(fileReader);
            String line = null;
            
            while((line=inStream.readLine())!=null)
            {
                String [] splitLine = line.split(",");
                int enemyHealth = Integer.parseInt(splitLine[2]);
                int enemyAttack = Integer.parseInt(splitLine[3]);
                int enemyHitChance = Integer.parseInt(splitLine[4]);

                if(splitLine[0].equals("n"))
                {
                    Enemy newEnemy = new Enemy(splitLine[1], enemyHealth, enemyAttack, enemyHitChance);
                    enemies.add(newEnemy);
                }
                else if(splitLine[0].equals("b"))
                {
                    int enemySpecialAttack = Integer.parseInt(splitLine[5]);
                    Boss newBoss = new Boss(splitLine[1], enemyHealth, enemyAttack, enemyHitChance, enemySpecialAttack);
                    enemies.add(newBoss);
                }
            }
            inStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        char userMainMenuInput = 'f';
        Scanner scanner = new Scanner(System.in);
        
        while(userMainMenuInput != 'c')
        {
            System.out.println("Hello user, \nWhat would you like to do? \na(new game) \nb(see previous scores)\nc(quit program)");
            userMainMenuInput = scanner.next().charAt(0);

            while(userMainMenuInput != 'a' && userMainMenuInput != 'b' && userMainMenuInput != 'c')
            {
                System.out.println("Please answer with a, b or c");
                System.out.println(userMainMenuInput);
                userMainMenuInput = scanner.next().charAt(0);
            }

            scanner.nextLine();

            if(userMainMenuInput == 'a')
                {
                    System.out.println("Please enter a name for your character: ");
                    String playerNameInput = scanner.nextLine();
                    Player player = new Player(playerNameInput, 150, 40, 40);
                    System.out.println(player.toString());
                    
                    System.out.println("Welcome great warrior " + player.printName() + "." );
                    System.out.println("Please help us defeat the Monsters plagueing our village");
                    
                    for(int i = 0; i < enemies.size() && player.isAlive; i++)
                    {
                        BattleField battle = new BattleField(player, enemies.get(i), items);
                        battle.startBattle();
                    }
                    
                    if(player.isAlive)
                    {
                        System.out.println("Well Done!\nYou saved the village!\nThank you Hero!!!");
                    }
                    
                    PrintWriter pw = null;
                    try{
                        pw = new PrintWriter(new FileOutputStream("PreviousScores.txt", true));
                        pw.println(player.toString() + " Survived: " + player.isAlive);
                        pw.close();
                    } catch (FileNotFoundException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
            if(userMainMenuInput == 'b')
            {
                try {
                    FileReader fileReader = new FileReader("PreviousScores.txt");
                    BufferedReader inStream = new BufferedReader(fileReader);
                    String line = null;

                    while((line=inStream.readLine())!=null)
                    {
                        System.out.println(line);
                    }
                    inStream.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }*/
}
