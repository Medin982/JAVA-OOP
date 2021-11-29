package UnitTesting;

import UnitTesting.Lab.Axe;
import UnitTesting.Lab.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_XP = 10;

    private Axe axe;
    private Axe brokenAxe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.brokenAxe = new Axe(AXE_ATTACK, 0);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void TestAxeLosesDurabilityAfterAttack() {
        this.axe.attack(this.dummy);
        Assert.assertEquals( "Wrong durability!",
                axe.getDurabilityPoints() - 1, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void TestBrokenAxeCantAttack() {
        this.brokenAxe.attack(this.dummy);
    }

}