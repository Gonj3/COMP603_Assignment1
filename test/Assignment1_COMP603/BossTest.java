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
public class BossTest {
    Boss testBoss;
    public BossTest() {
    }

    @Before
    public void setUp() {
        this.testBoss = new Boss("test", 20, 30, 100, 40);
    }
    
    @After
    public void tearDown() {
        this.testBoss = null;
    }

    /**
     * Test of attack method, of class Boss.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        Boss instance = this.testBoss;
        int[] expResult = {30, 40};
        int result = instance.attack();
        assertTrue(expResult[0] == result || expResult[1] == result);
    }
}
