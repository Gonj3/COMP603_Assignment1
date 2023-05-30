/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
public class DamageItem extends Item{
    /*
    constructor for DamageItem 
    */
    public DamageItem(String name, int itemStatIncrease) {
        super(name, itemStatIncrease);
    }

    /*
    takes in the paramete Player player. 
    uses the useDamageItem method in player and inputs the itemStatIncrease.
    */
    @Override
    public void useItem(Player player) {
        player.useDamageItem(this.itemStatIncrease);
    }
    /*
    returns a string of information about the DamageItem.
    */
    @Override
    public String toString() {
        String output = "";
        output += "Item Name: " + this.name + " | Damage Increase: " + this.itemStatIncrease;
        return output;
    }
    
}
