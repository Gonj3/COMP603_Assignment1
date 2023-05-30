/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
public class HitChanceItem extends Item{
    /*
    constructor for HitChanceItem.
    */
    public HitChanceItem(String name, int itemStatIncrease) {
        super(name, itemStatIncrease);
    }
    /*
    increases the hit chance of the inputed player based on the itemStatIncrease.
    */
    @Override
    public void useItem(Player player) {
        player.useHitChanceItem(this.itemStatIncrease);
    }
    /*
    returns a String of information about the HitChanceItem.
    */
    @Override
    public String toString() {
        String output = "";
        output += "Item Name: " + this.name + " | Hit Chance Increase: " + this.itemStatIncrease;
        return output;
    }
    
}
