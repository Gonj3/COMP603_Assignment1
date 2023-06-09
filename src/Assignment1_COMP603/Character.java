/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
import java.util.Random;

public class Character {
    protected String name; 
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected int hitChance;
    public boolean isAlive;
    /*
    constructor for Character class.
    */
    public Character(String name, int health, int damage, int hitChance)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.hitChance = hitChance;
        this.isAlive = true;
    }
    /*
    generates a random number between 0 and 100.
    if this number is greater than the Characters hit chance it will return 0 as the attack did not hit
    if this number the same or less than the hit chance it will return the Characters attack.
    */
    public int attack()
    {
        Random rand = new Random();
        int hitChecker = rand.nextInt(100);
        
        //attack hit
        if(hitChecker <= this.hitChance)
        {
            return this.damage;
        }
        //attack failed
        else
        {
            return 0;
        }
        
    }
    
    /*
    takes away health from the Character based on the damage parameter.
    it then checks if the Character health is less or equal to 0, if it the isAlive boolean variable is set to false.
    */
    public void attacked(int damage)
    {
        this.health -= damage;
        if(this.health <= 0)
        {
            this.isAlive = false;
        }
    }
    
    /*
    returns the name variable. 
    */
    public String printName()
    {
        return this.name;
    }
    /*
    returns information about the Character as a String.
    */
    @Override
    public String toString()
    {
        String output = "Name: " + this.name + " Health: " + this.health + "/" + this.maxHealth + " Damage: " + this.damage + " Hit Chance: " + this.hitChance + "%";
        return output;
    }
    
}
