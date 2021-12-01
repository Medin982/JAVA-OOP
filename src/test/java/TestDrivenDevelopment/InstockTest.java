package TestDrivenDevelopment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private Instock stock;

    @Before
    public void setUp() {
        this.stock = new Instock();
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductIsAbsentAndTheTrueWhenAdded() {
        Product product = createAndAddedProducts(this.stock)[0];
        Instock stock = new Instock();
        Assert.assertFalse(stock.contains(product));
        stock.add(product);
        assertTrue(stock.contains(product));
    }

    @Test
    public void testCountShouldReturnCorrectlyCountOfProductInStock() {
        Product[] products = createAndAddedProducts(this.stock);
        int count = this.stock.getCount();
        assertEquals(3, count);
    }

    @Test
    public void testFindShouldReturnTheNThProductsInStock() {
        Product[] andAddedProducts = createAndAddedProducts(stock);
        Product testingProduct = new Product("testing_Product", 5, 1);
        this.stock.add(testingProduct);
        Product product = this.stock.find(3);
        assertEquals(testingProduct, product);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowExceptionForInvalidIndex() {
        Product[] andAddedProducts = createAndAddedProducts(stock);
        stock.find(-1);
    }

    @Test
    public void testChangeQuantityShouldChangesTheQuantityOfAGivenProduct() {
        Product[] addedProducts = createAndAddedProducts(stock);
        int newQuantity = addedProducts[1].getQuantity() + 10;
        stock.changeQuantity(addedProducts[1].getLabel(), newQuantity);
        assertEquals(newQuantity, addedProducts[1].getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldThrowsExceptionIfNoSuchProductInStock() {
        stock.changeQuantity("no_SUch_Product", 10);
    }

    @Test
    public void testFindByLabelShouldReturnProductByLabel() {
        Product[] addedProducts = createAndAddedProducts(stock);
        Product expectedProduct = addedProducts[0];
        Product actualProduct = stock.findByLabel(expectedProduct.getLabel());
        assertEquals(expectedProduct, actualProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindbyLabelSholudThrowsExceptionIfNoSuchProductInStock() {
        Product noSuchProduct = stock.findByLabel("no_such_Product");
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheCorrectNumberOfProducts() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        this.stock.add(new Product("not_return_test_product", 9.99, 1));
        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(addedProducts.length);
        int count = assertAndExtract(iterable).size();
        assertEquals(addedProducts.length, count);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollection() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(-1);
        int count = assertAndExtract(iterable).size();
        Assert.assertEquals(0, count);
    }

    @Test
    public void testFindAllByPriceShouldReturnAllProductsWithGivenPrice() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        double price = addedProducts[2].getPrice();
        Iterable<Product> allByPrice = this.stock.findAllByPrice(price);
        List<Double> returnedPrices = assertAndExtract(allByPrice, Product::getPrice);
        assertEquals(1, returnedPrices.size());
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollection() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        double price = 1.00;
        Iterable<Product> allByPrice = this.stock.findAllByPrice(price);
        List<Double> returnedPrices = assertAndExtract(allByPrice, Product::getPrice);
        assertEquals(0, returnedPrices.size());
    }

    @Test
    public void testFindAllInPriceRangeShouldReturnAllProductsWithGivenRange() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        double start = addedProducts[0].getPrice();
        double end = addedProducts[2].getPrice();
        Iterable<Product> allInRange = this.stock.findAllInRange(start, end);
        List<Double> returnedPrices = assertAndExtract(allInRange, Product::getPrice);
        List<Double> expected = Arrays.stream(addedProducts)
                .map(Product::getPrice)
                .filter(p -> p > start && p <= end)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        assertEquals(expected, returnedPrices);
    }

    @Test
    public void testFindAllByRangeShouldReturnEmptyCollectionIfNoSuchElementsInGivenRange() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        double start = 20;
        double end = 60;
        Iterable<Product> allInRange = this.stock.findAllInRange(start, end);
        List<Double> returnedPrices = assertAndExtract(allInRange, Product::getPrice);
        assertEquals(0, returnedPrices.size());
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnProductWithHighestPrice() {
        List<Double> expected = Arrays.stream(createAndAddedProducts(this.stock))
                .map(Product::getPrice)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .collect(Collectors.toList());
        Iterable<Product> iterable = this.stock.findFirstMostExpensiveProducts(2);
        List<Double> actual = assertAndExtract(iterable, Product::getPrice);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllByQuantityShouldReturnProductWithQuantity() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        Integer quantity = 2;
        Iterable<Product> iterable = this.stock.findAllByQuantity(quantity);
        List<Integer> actual = assertAndExtract(iterable, Product::getQuantity);
        assertTrue(actual.stream().allMatch(q -> q.equals(quantity)));
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollection() {
        Product[] addedProducts = createAndAddedProducts(this.stock);
        Iterable<Product> iterable = this.stock.findAllByQuantity(50);
        List<Integer> actual = assertAndExtract(iterable, Product::getQuantity);
        assertEquals(0, actual.size());
    }

    @Test
    public void testGetIterableProductShouldReturnAllProductsInStock() {
        List<Product> expected = Arrays.asList(createAndAddedProducts(this.stock));
        Iterator<Product> iterator = this.stock.iterator();
        assertNotNull(iterator);
        List<Product> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            actual.add(p);
        }
        assertEquals(expected, actual);
    }

    private static List<Product> assertAndExtract(Iterable<Product> iterable) {
        return assertAndExtract(iterable, product -> product);
    }

    private static <T> List<T> assertAndExtract(Iterable<Product> iterable, Function<Product, T> mapper) {
        assertNotNull(iterable);
        List<T> list = new ArrayList<>();
        for (Product product : iterable) {
            list.add(mapper.apply(product));
        }
        return list;
    }

    private static Product[] createAndAddedProducts(ProductStock stock) {
        Product[] products = new Product[]{
                new Product("product_1", 11.99, 1),
                new Product("product_2", 7.99, 2),
                new Product("product_3", 22.99, 3)};
        for (Product product : products) {
            stock.add(product);
        }
        return products;
    }
}