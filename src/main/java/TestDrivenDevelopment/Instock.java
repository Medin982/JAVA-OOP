package TestDrivenDevelopment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> productList;

    public Instock() {
        this.productList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.productList.size();
    }

    @Override
    public boolean contains(Product product) {
        return productList.contains(product);
    }

    @Override
    public void add(Product product) {
        this.productList.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product currentProduct = this.productList.stream()
                .filter(p -> p.getLabel().equals(product))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Product not exist in stock"));
        currentProduct.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return this.productList.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return this.productList.stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No such product in stock"));
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count >= this.productList.size()) {
            return new ArrayList<>();
        }
        return this.productList.stream()
                .limit(count)
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.productList.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() >= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.productList.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.productList.size()) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.productList.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.productList.stream()
                .filter(q -> q.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.productList.iterator();
    }
}


