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
public class DamageItemTest {
    DamageItem testItem;
    Player testPlayer;
    
    public DamageItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.testItem = new DamageItem("testDamageItem", 40);
        this.testPlayer = new Player("testPlayer", 150, 30, 20);
    }
    
    @After
    public void tearDown() {
        this.testItem = null;
        this.testPlayer = null;
    }

    /**
     * Test of useItem method, of class DamageItem.
     */
    @Test
    public void testUseItem() {
        System.out.println("useItem");
        Player player = this.testPlayer;
        DamageItem instance = this.testItem;
        instance.useItem(player);
        assertSame(player.damage + player.getDamageItemIncrease(), 70);
    }

}
