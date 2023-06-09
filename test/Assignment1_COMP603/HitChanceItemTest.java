/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Assignment1_COMP603;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonat
 */
public class HitChanceItemTest {
    Player testPlayer;
    HitChanceItem testItem;
    
    public HitChanceItemTest() {
    }
    
    @Before
    public void setUp() {
        this.testPlayer = new Player("test", 100, 30, 30);
        this.testItem = new HitChanceItem("test", 10);
    }
    
    @After
    public void tearDown() {
        this.testPlayer = null;
        this.testItem = null;
    }

    /**
     * Test of useItem method, of class HitChanceItem.
     */
    @Test
    public void testUseItem() {
        System.out.println("useItem");
        Player player = this.testPlayer;
        HitChanceItem instance = this.testItem;
        instance.useItem(player);
        assertSame(player.hitChance + player.getHitChanceItemIncrease(), 40);
    }
}
