package UnitTesting;

import UnitTesting.Lab.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {
    private static final int HEALTH = 100;
    private static final int XP = 100;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.dummy = new Dummy(HEALTH, XP);
        this.deadDummy = new Dummy(0, XP);
    }

    @Test
    public void TestDummyLosesHealthAfterAttack() {
        int attachPoints = 10;
        this.dummy.takeAttack(attachPoints);
        Assert.assertEquals(90, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void DummyThrowsExceptionIfAttacked() {
        deadDummy.takeAttack(10);
    }

    @Test
    public void TestDummyGiveExperienceAfterDead() {
        int actualExperience = deadDummy.giveExperience();
        Assert.assertEquals(XP, actualExperience);
    }

    @Test(expected = IllegalStateException.class)
    public void TestDummyCantGiveExperienceWhileAllive() {
        dummy.giveExperience();
    }
}