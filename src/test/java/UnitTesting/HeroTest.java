package UnitTesting;

import UnitTesting.Lab.Hero;
import UnitTesting.Lab.Target;
import UnitTesting.Lab.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroTest {
    private final static int TARGET_XP = 100;

    private Weapon fakeWeapon;
    private Target fakeTarget;

    @Before
    public void setUp() {
        fakeTarget = Mockito.mock(Target.class);
        fakeWeapon = Mockito.mock(Weapon.class);
    }

    @Test
    public void TestAttackGainsExperienceIfTargetIsDead() {
        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.giveExperience()).thenReturn(TARGET_XP);

        Hero hero = new Hero("Ragnalok", this.fakeWeapon);
        hero.attack(this.fakeTarget);

        Assert.assertEquals("Wrong Expertience", TARGET_XP, hero.getExperience());
    }

}