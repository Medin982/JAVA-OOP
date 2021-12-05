package ExamPreparation.Exams15August21.UnitTesting.shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {

    private Shop shop;
    private static Map<String, Goods> shelves;

    @Before
    public void setUp() {
        this.shop = new Shop();
        createShelvesAndAddedInShop();
    }

    private static void createShelvesAndAddedInShop() {
        shelves = new LinkedHashMap<>();
        shelves.put("Shelves1", null);
        shelves.put("Shelves2", null);
        shelves.put("Shelves3", null);
        shelves.put("Shelves4", null);
        shelves.put("Shelves5", null);
        shelves.put("Shelves6", null);
        shelves.put("Shelves7", null);
        shelves.put("Shelves8", null);
        shelves.put("Shelves9", null);
        shelves.put("Shelves10", null);
        shelves.put("Shelves11", null);
        shelves.put("Shelves12", null);
    }

    @Test
    public void testConstructorCorrectlyCreateShop() {
        this.shop = new Shop();
        Assert.assertEquals(shelves.size(), this.shop.getShelves().size());
    }

    @Test
    public void testAddGoodsShouldAddedGoodsInShop() throws OperationNotSupportedException {
        Goods goods = new Goods("Apple", "RedApple");
        String shelf = "Shelves1";
        shelves.put(shelf, goods);
        int expectedSize = shelves.size();
        this.shop.addGoods(shelf, goods);
        int actualSize = this.shop.getShelves().size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowsExceptionIfShelfNotExist() throws OperationNotSupportedException {
        this.shop.addGoods("Shelf", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowsExceptionIfGoodsIsAlreadyTaken() throws OperationNotSupportedException {
        Goods goods = new Goods("Apple", "RedApple");
        String shelf = "Shelves1";
        this.shop.addGoods(shelf, goods);
        this.shop.addGoods(shelf, null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsShouldThrowsExceptionIfGoodsIsAlreadyExist() throws OperationNotSupportedException {
        Goods goods = new Goods("Apple", "RedApple");
        this.shop.addGoods("Shelves1", goods);
        this.shop.addGoods("Shelves2", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowsExceptionForNoSuchShelf() {
        this.shop.removeGoods("Shelf", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowsExceptionForNoSuchGivenGoods() {
        this.shop.removeGoods("Shelves1", new Goods("Car", "redCar"));
    }

    @Test
    public void testRemoveGoodsShouldCorrectlyRemoveGoodsFromGivenShelf() throws OperationNotSupportedException {
        Goods goods = new Goods("Apple", "RedApple");
        this.shop.addGoods("Shelves1", goods);
        Goods expectedShelf = this.shop.getShelves().get("Shelves1");
        this.shop.removeGoods("Shelves1", goods);
        Goods actualShelf = this.shop.getShelves().get("Shelves1");
        Assert.assertNotEquals(expectedShelf, actualShelf);
    }

    @Test
    public void testRemoveGoodsShouldRetunedCorrectly() throws OperationNotSupportedException {
        Goods goods = new Goods("Apple", "RedApple");
        this.shop.addGoods("Shelves1", goods);
        String expectedMessage = String.format("Goods: %s is removed successfully!", goods.getGoodsCode());
        String actual = this.shop.removeGoods("Shelves1", goods);
        Assert.assertEquals(expectedMessage, actual);
    }
}