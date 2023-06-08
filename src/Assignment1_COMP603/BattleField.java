/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BattleField {
    private Player player;
    private Character enemy;
    private ArrayList<Item> items;
    public String attackInfo;
    
    
/*
Constructor for BattleField class
*/
    public BattleField(Player player, Character enemy, ArrayList<Item> items)
    {
        this.player = player; 
        this.enemy = enemy;
        this.items = items;
        this.attackInfo = null;
    }

    public boolean Attack()
    {
        while(player.isAlive == true && enemy.isAlive == true)
        {
            int playerAttack = player.attack();
            enemy.attacked(playerAttack);
            System.out.println(enemy.toString());

            int enemyAttack = enemy.attack();
            player.attacked(enemyAttack);
            System.out.println(player.toString());
            this.attackInfo = this.player.name + " was hit for " + Integer.toString(enemyAttack) + ", " + this.enemy.name + " was hit for " + Integer.toString(playerAttack);
            
            return true;
        }
        return false;
    }
    
    public boolean isPlayerAlive()
    {
        return this.player.isAlive;
    }
    
    public boolean isEnemyAlive()
    {
        return this.enemy.isAlive;
    }
    
    public String getPlayerToString()
    {
        return this.player.toString();
    }
    
    public String getEnemyToString()
    {
        return this.enemy.toString();
    }
        /*
    runs the encounter between the player and the enemy. 
    when the player defeats the enemy they choose a stat to upgrade.
    if the enemy was a boss they also find a random item and it is added to the player item storage.
    after the battle they also get the choice of healing or using an item. 
    if they have no items they will heal.
    */
    public void startBattle()
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.player.printName() + " encounters " + this.enemy.printName() + "!!!");
        
        while(player.isAlive == true && enemy.isAlive == true)
        {
            System.out.println("Type in yes to attack.");
            String userInput = scanner.nextLine();
            while(!userInput.equals("yes"))
            {
                System.out.println("Please input yes to attack.");
                userInput = scanner.nextLine();
            }
            if(userInput.equals("yes"))
            {
                enemy.attacked(player.attack());
                System.out.println(enemy.toString());
            }
            
            player.attacked(enemy.attack());
            System.out.println(player.toString());
        }
        
        if(player.isAlive == true)
        {
            System.out.println("Well Done! \nYou beat " + enemy.printName());
            
            if(enemy instanceof Boss)
            {
                int randomIndex = random.nextInt(items.size());
                System.out.println(randomIndex);
                player.addItem(items.get(randomIndex));
                System.out.println("You found:\n" + items.get(randomIndex).toString());
            }
            
            System.out.println("What stat would you like to upgrade?\na. for health\nb. for damage\nc. for hitChance ");
            String userUpgradeInput = scanner.nextLine();
            while(!userUpgradeInput.equals("a") && !userUpgradeInput.equals("b") && !userUpgradeInput.equals("c"))
            {
                System.out.println("Please input a, b or c.");
                userUpgradeInput = scanner.nextLine();
            }
            
            switch (userUpgradeInput) {
                case "a":
                    player.increaseHealth(40);
                    break;
                case "b":
                    player.increaseDamage(20);
                    break;
                case "c":
                    player.hitChanceIncrease(10);
                    break;
                default:
                    break;
            }
            System.out.println(player);
            
            System.out.println("Would you like to heal 1/6 of your health (a) or use an item (b)");
            String userInput = scanner.nextLine();
            while(!userInput.equals("a") && !userInput.equals("b"))
            {
                System.out.println("Please input a or b");
                userInput = scanner.nextLine();
            }
            
            if(userInput.equals("a"))
            {
                player.heal();
            }
            if(userInput.equals("b"))
            {
                System.out.println(player.printItems());
                if(player.itemsEmpty() == true)
                {
                    player.heal();
                    System.out.println("You have no items");
                    System.out.println("You have been healed for 1/6 of your health.");
                }
                else if(player.itemsEmpty() == false)
                {
                    int userItemChoice = -1;
                    while(userItemChoice < 0 && userItemChoice < player.itemsSize())
                    {
                        try {
                            System.out.println("Please input the a valid item number you would like to use.");
                            userItemChoice = scanner.nextInt();
                        }
                        catch(InputMismatchException exception){
                            System.out.println("Please input a valid integer.");
                        }
                    }
                    System.out.println("You just used:");
                    System.out.println(player.useItem(userItemChoice));
                    System.out.println(player.toString());
                            
                }
            }
            
        }
        if(player.isAlive == false && enemy.isAlive == false)
        {
            System.out.println("Wow!!!\nYou both died!!!");
        }
         if(player.isAlive == false)
        {
            System.out.println("Oh No! \nYou Died!!! \n" + enemy.printName() + " beat you!");
        }
    }
}
