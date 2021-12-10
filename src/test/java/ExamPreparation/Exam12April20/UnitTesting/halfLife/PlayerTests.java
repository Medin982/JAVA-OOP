package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {

    private Player player;

    @Before
    public void setUp() {
        this.player = new Player("Player", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowsExceptionIfNameIsNull() {
        this.player = new Player(null, 100);
    }

    @Test(expected =IllegalArgumentException.class)
    public void testConstructorShouldThrowsExceptionIfHealthIsLessThanZero() {
        this.player = new Player("Player", -1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGunsReturnUnmodifiableList() {
        this.player.getGuns().clear();
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageShouldThrowsExceptionIfHealthIsBellowZero() {
        Player currentPlayer = new Player("Some", 0);
        currentPlayer.takeDamage(50);
    }

    @Test
    public void testTakeDamageShouldSetHealthZeroIfDamageIsMoreThanPlayerHealth() {
        this.player.takeDamage(110);
        Assert.assertEquals(0, this.player.getHealth());
    }

    @Test
    public void testTakeDamageShouldReducedHealthCorrectly() {
        this.player.takeDamage(90);
        Assert.assertEquals(10, this.player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunShouldThrowsExceptionIfGivenGunsIsNull() {
        this.player.addGun(null);
    }

    @Test
    public void testAddGunShouldAddedGunInGunCollection() {
        Gun gun = new Gun("Ak-47", 40);
        this.player.addGun(gun);
        Assert.assertEquals(1, this.player.getGuns().size());
    }


    @Test
    public void testRemoveGunShouldRemovedGunInGunsCollection() {
        Gun gun = new Gun("Ak-47", 40);
        this.player.addGun(gun);
        this.player.removeGun(gun);
        Assert.assertEquals(0, this.player.getGuns().size());
    }

    @Test
    public void testGetGunShouldReturnedGunByGivenName() {
        Gun gun = new Gun("Ak-47", 40);
        this.player.addGun(gun);
        Gun actual = this.player.getGun("Ak-47");
        Assert.assertEquals(gun, actual);
    }

    @Test
    public void testGetGunShouldReturnNullIfNoSuchGunsInCollections() {
        Assert.assertNull(this.player.getGun("Colt"));
    }
}
