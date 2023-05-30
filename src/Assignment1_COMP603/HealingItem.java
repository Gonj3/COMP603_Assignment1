/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
public class HealingItem extends Item{
    /*
    constructor for HealingItem
    */
    public HealingItem(String name, int itemStatIncrease) {
        super(name, itemStatIncrease);
    }
    /*
    heals the inputed player based on the itemStatIncrease of the healing item.
    */
    @Override
    public void useItem(Player player) {
         player.heal(this.itemStatIncrease);
    }
    /*
    returns a String of information about the HealingItem.
    */
    @Override
    public String toString() {
        String output = "";
        output += "Item Name: " + this.name + " | Heal Amount: " + this.itemStatIncrease;
        return output;
    }
    
}


