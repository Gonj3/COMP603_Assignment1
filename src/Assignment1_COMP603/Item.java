/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment1_COMP603;

/**
 *
 * @author jonat
 */
public abstract class Item {
    protected final String name;
    protected boolean itemUsed;
    protected final int itemStatIncrease;
    
    /*
    constructor for the Item class.
    */
    public Item(String name, int itemStatIncrease)
    {
        this.name = name;
        this.itemUsed = false;
        this.itemStatIncrease = itemStatIncrease;
    }
    /*
    abstract declaration of the useItem method.
    */
    abstract public void useItem(Player player);
    
    /*
    abstract declaration of the toString method.
    */
    @Override
    abstract public String toString();
    
    /*
    returns as a boolean whether the item has been used or not.
    */
    public boolean isItemUsed()
    {
        return itemUsed;
    }
}
