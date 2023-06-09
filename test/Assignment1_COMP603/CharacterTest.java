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
public class CharacterTest {
    Character testCharacter;
    
    public CharacterTest() {
    }

    @Before
    public void setUp() {
        this.testCharacter = new Character("test", 100, 50, 20);
    }
    
    @After
    public void tearDown() {
        this.testCharacter = null;
    }

    /**
     * Test of attack method, of class Character.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        Character instance = this.testCharacter;
        int[] expResult = {0, 50};
        int result = instance.attack();
        assertTrue(expResult[0] == result || expResult[1] == result);
    }

    /**
     * Test of attacked method, of class Character.
     */
    @Test
    public void testAttacked() {
        System.out.println("attacked");
        int damage = 20;
        Character instance = this.testCharacter;
        instance.attacked(damage);
        assertSame(80, this.testCharacter.health);
    }

    /**
     * Test of printName method, of class Character.
     */
    @Test
    public void testPrintName() {
        System.out.println("printName");
        Character instance = this.testCharacter;
        String expResult = "test";
        String result = instance.printName();
        assertEquals(expResult, result);
    }
}
