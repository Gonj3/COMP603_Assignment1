/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jonat
 */
public class Player extends Character {
    private int killCounter;
    private boolean damageItemActive;
    private boolean hitChanceItemActive;
    private int damageItemIncrease;
    private int hitChanceItemIncrease;
    private int damageItemCountdown;
    private int hitChanceItemCountdown;
    private ArrayList<Item> playerItems;

    /*
    constructor for the Player class.
    */
    public Player(String name, int health, int damage, int hitChance) {
        super(name, health, damage, hitChance);
        this.killCounter = 0;
        this.damageItemActive = false;
        this.hitChanceItemActive = false;
        this.damageItemIncrease = 0;
        this.hitChanceItemIncrease = 0;
        this.damageItemCountdown = 0;
        this.hitChanceItemCountdown = 0;
        this.playerItems = new ArrayList<Item>();
    }
    /*
    override the attack method from Character class to account for hitChanceItem use and DamageItem use.
    */
    @Override
    public int attack()
    {
        Random rand = new Random();
        int hitChecker = rand.nextInt(100);
        this.hitChanceItemCounter();
        this.damageItemCounter();
        if(hitChecker <= this.hitChance + this.hitChanceItemIncrease)
        {
            return this.damage + this.damageItemIncrease;
        }
        else
        {
            return 0;
        }
    }
    /*
    returns a String of information of the items the player currently has.
    */
    public String printItems()
    {
        String output = "";
        if(!this.playerItems.isEmpty())
        {
            for(int i = 0; i < this.playerItems.size(); i++)
            {
                output += i + ". " + this.playerItems.get(i).toString();
                output += "\n";
            }
            return output;
        }
        else
        {
            output += this.name + " has no items";
            return output;
        }
    }
    /*
    returns the current size of the playerItems ArrayList 
    */
    public int itemsSize()
    {
        return playerItems.size();
    }
    
    /*
    checks the playerItems ArrayList to see if it is empty.
    returns a boolean based on this.
    */
    public boolean itemsEmpty()
    {
        if(this.playerItems.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Item getItem(int index)
    {
        return this.playerItems.get(index);
    }
    /*
    takes in an index for an item to use.
    the item is then removed from the playerItems ArrayList.
    the used item is then returned.
    */
    public Item useItem(int index)
    {
        Item usedItem = this.playerItems.get(index);
        this.playerItems.remove(index);
        usedItem.useItem(this);
        return usedItem;
    }
    /*
    takes an Item as an input and adds it the the playerItems ArrayList.
    */
    public void addItem(Item item)
    {
        this.playerItems.add(item);
    }
    /*
    adds 1 to the killCounter.
    */
    public void enemyKilled()
    {
        this.killCounter++;
    }
    /*
    fully heals the Player by using making this.health = this.maxHealth.
    */
    public void fullHeal()
    {
        this.health = this.maxHealth;
    }
    /*
    heals the Player by 1/6 of their max health.
    makes sure health does not go over maxHealth.
    */
    public void heal()
    {
        this.health += (this.maxHealth / 6);
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
    }
    
    /*
    heals the Player based on the healAmout input.
    makes sure health does not go over maxHealth.
    */
    public void heal(int healAmount)
    {
        this.health += healAmount;
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
    }
    /*
    increase the maxHealth and health by the healthIncrease parameter.
    */
    public void increaseHealth(int healthIncrease)
    {
        this.maxHealth += healthIncrease;
        this.health += healthIncrease;
    }
    /*
    increases the Player damage based on the damageIncrease parameter.
    */
    public void increaseDamage(int damageIncrease)
    {
        this.damage += damageIncrease;
    }
    /*
    used to countdown how long the current damage item will be used for.
    */
    private void damageItemCounter()
    {
        if(this.damageItemActive == true)
        {
            if(this.damageItemCountdown == 0)
            {
                this.damageItemActive = false;
                this.damageItemIncrease = 0;
            }
            else
            {
                this.damageItemCountdown--;
            }
        }
        
    }
    /*
    used to countdown how long the current hitChance item will be used for.
    */
    private void hitChanceItemCounter()
    {
        if(this.hitChanceItemActive == true)
        {
            if(this.hitChanceItemCountdown == 0)
            {
                this.hitChanceItemActive = false;
                this.hitChanceItemIncrease = 0;
            }
            else
            {
                this.hitChanceItemCountdown--;
            }
        }
        
    }
    /*
    if there is no current damage item adds a countdown amount for damageItem and changes the damageItemIncrease value to the damageIncrease parameter.
    */
    public void useDamageItem(int damageIncrease)
    {
        if(this.damageItemActive == false)
        {
            this.damageItemActive = true;
            this.damageItemCountdown = 4;
            this.damageItemIncrease = damageIncrease;
        }
    } 
    /*
    if there is no current hitChance item adds a countdown amount for hitChanceItem and changes the hitChanceItemIncrease value to the hitChanceIncrease parameter.
    */
    public void useHitChanceItem(int hitChanceIncrease)
    {
        if(this.hitChanceItemActive == false)
        {
            this.hitChanceItemActive = true;
            this.hitChanceItemCountdown = 4;
            this.hitChanceItemIncrease = hitChanceIncrease;
        }
    }
     /*
    increases the Player hitChance based on the hitChanceIncrease parameter.
    */
    public void hitChanceIncrease(int hitChanceIncrease)
    {
        if(this.hitChance + hitChanceIncrease > 100)
        {
            this.hitChance = 100;
        }
        else
        {
            this.hitChance += hitChanceIncrease;
        }
    }
    /*
    returns this.damageItemIncrease
    */
    public int getDamageItemIncrease()
    {
        return this.damageItemIncrease;
    }
    /*
    returns this.hitChanceItemIncrease
    */
    public int getHitChanceItemIncrease()
    {
        return this.hitChanceItemIncrease;
    }
    /*
    returns a String of information based on the Player.
    */
    @Override
    public String toString()
    {
        int hitChanceTotal = this.hitChance + this.hitChanceItemIncrease;
        int damageTotal = this.damage + this.damageItemIncrease;
        String output = "Name: " + this.name + " Health: " + this.health + "/" + this.maxHealth + " Damage: " + damageTotal + " Hit Chance: " + hitChanceTotal + "%";
        return output;
    }
}
