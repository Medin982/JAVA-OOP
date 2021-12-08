package ExamPreparation.RetakeExam18April21.UnitTesting.heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {

    HeroRepository heroRepository;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowsExceptionIfHeroIsNull() {
        heroRepository = new HeroRepository();
        heroRepository.create(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateShouldThrowsExceptionIfHeroExist() {
        Hero hero = new Hero("Captain", 5);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero);
    }

    @Test
    public void testCreateShouldAddedHeroInRepository() {
        Hero hero = new Hero("Captain", 5);
        String expectedMessage = String.format("Successfully added hero %s with level %d", hero.getName(), hero.getLevel());
        String actualMessage = this.heroRepository.create(hero);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveShouldThrowsExceptionIfGivenNameIsNullOrWhiteSpace() {
        this.heroRepository.remove(null);
    }

    @Test
    public void testRemoveShouldRemovedHeroFromRepositoryAndReturnBoolean() {
        Hero hero = new Hero("Captain", 5);
        Assert.assertFalse(this.heroRepository.remove("Captain"));
        this.heroRepository.create(hero);
        Assert.assertTrue(this.heroRepository.remove(hero.getName()));
    }

    @Test
    public void testGetHeroShouldReturnHeroByGivenName() {
        Hero hero = new Hero("Captain", 5);
        this.heroRepository.create(hero);
        Hero actual = this.heroRepository.getHero(hero.getName());
        Assert.assertEquals(hero, actual);
    }

    @Test
    public void testGetHeroShouldReturnNullIfHeroNotExist() {
        Assert.assertNull(this.heroRepository.getHero("Captain"));
    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnHeroWithHighestLevel() {
        Hero hero = new Hero("Captain", 5);
        Hero hero2 = new Hero("Comandir", 10);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero2);
        Hero actual = this.heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals(hero2, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRepositoryReturnUnmodifiableCollection() {
        this.heroRepository.getHeroes().clear();
    }
}
