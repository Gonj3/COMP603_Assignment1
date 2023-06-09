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
public class HealingItemTest {
    Player testPlayer;
    HealingItem testHealingItem;
    
    public HealingItemTest() {
    }

    @Before
    public void setUp() {
        this.testPlayer = new Player("test", 100, 30, 30);
        this.testHealingItem = new HealingItem("testItem", 30);
        this.testPlayer.attacked(40);
    }
    
    @After
    public void tearDown() {
        this.testPlayer = null;
        this.testHealingItem = null;
    }

    /**
     * Test of useItem method, of class HealingItem.
     */
    @Test
    public void testUseItem() {
        System.out.println("useItem");
        Player player = this.testPlayer;
        HealingItem instance = this.testHealingItem;
        instance.useItem(player);
        assertSame(player.health, 90);
    }
}
