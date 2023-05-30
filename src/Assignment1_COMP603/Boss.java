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

public class Boss extends Character {
    private int specialAttackDamage;
    private Random rand = new Random();
    /*
    constructor for Boss class
    */
    public Boss(String name, int health, int damage, int hitChance, int specialAttackDamage) {
        super(name, health, damage, hitChance);
        this.specialAttackDamage = specialAttackDamage;
    }
    /*
    Overrides the character attack() method. 
    same as the character attack method, but if the boss is less than half health there is a chance they will use their special attack.
    */
    @Override
    public int attack()
    {
        Random rand = new Random();
        int hitChecker = rand.nextInt(100);
        
        if(hitChecker <= this.hitChance)
        {
            if(health < (maxHealth / 2))
            {
                int specialAttackRandom;
                specialAttackRandom = rand.nextInt(4);

                if(specialAttackRandom == 3)
                {
                    System.out.println(printName() + " hit for " + this.damage);
                    return specialAttackDamage;
                }
                else
                {
                    System.out.println(printName() + " hit for " + this.damage);
                    return damage;
                }
            }
            else
            {
                System.out.println(printName() + " hit for " + this.damage);
                return this.damage;
            }
        }
        else
        {
            System.out.println(printName() + " failed to hit");
            return 0;
        }
    }
    
    /*
    returns information on the Boss as a String.
    */
    @Override
    public String toString()
    {
        String output = "";
        output += super.toString();
        output += " Special Attack Damage: " + this.specialAttackDamage;
        return output;
    }
    
}
