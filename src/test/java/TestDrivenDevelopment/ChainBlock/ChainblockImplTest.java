package TestDrivenDevelopment.ChainBlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;


public class ChainblockImplTest {

    private static List<Transaction> transactions;
    private Chainblock chainblock;

    @Before
    public void setUp() {
        transactions = addedTransaction();
        this.chainblock = new ChainblockImpl();
    }

    private static List<Transaction> addedTransaction() {
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Gosho", "Toshko", 10.90);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.FAILED, "Gosho", "Toshko", 2.90);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Ivan", "Gosho", 8.00);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Pesho", "Ivan", 15.80);
        List<Transaction> list = new ArrayList<>();
        list.add(transaction);
        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);
        return list;
    }

    private static void addedTransactionInChainBlock(Chainblock chainblock) {
        for (Transaction transaction : transactions) {
            chainblock.add(transaction);
        }
    }

    private static <T> List<T> transformToLIst(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @Test
    public void testContainsReturnTrueIfSuchTransaction() {
        this.chainblock.add(transactions.get(0));
        Assert.assertTrue(this.chainblock.contains(transactions.get(0)));
    }

    @Test
    public void testContainsReturnFalseIfNoSuchTransaction() {
        Assert.assertFalse(this.chainblock.contains(10));
    }

    @Test
    public void testAddShouldCorrectlyAddedTransactionInChainBlock() {
        Assert.assertEquals(0, this.chainblock.getCount());
        this.chainblock.add(transactions.get(1));
        Assert.assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void testCountShouldReturnSizeFromAllTransaction() {
        Assert.assertEquals(0, this.chainblock.getCount());
        this.chainblock.add(transactions.get(0));
        Assert.assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void testChangeTransactionStatusShouldChangeStatusCorrectly() {
        Transaction current = transactions.get(1);
        this.chainblock.add(current);
        this.chainblock.changeTransactionStatus(current.getId(), TransactionStatus.FAILED);
        Assert.assertEquals(TransactionStatus.FAILED, current.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldReturnExceptionForInValidId() {
        this.chainblock.changeTransactionStatus(10, TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void testRemoveTransactionByIdShouldRemoveSearchingTransaction() {
        Transaction removedTransaction = transactions.get(2);
        this.chainblock.add(removedTransaction);
        int expectedCount = this.chainblock.getCount();
        this.chainblock.removeTransactionById(removedTransaction.getId());
        int actualCount = this.chainblock.getCount();
        Assert.assertEquals(expectedCount - 1, actualCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdShouldThrowsExceptionForInvalidId() {
        this.chainblock.removeTransactionById(10);
    }

    @Test
    public void testGetByIdShouldReturnSearchingTransaction() {
        Transaction expectedTransaction = transactions.get(1);
        this.chainblock.add(expectedTransaction);
        Transaction actualTransaction = this.chainblock.getById(expectedTransaction.getId());
        Assert.assertEquals(expectedTransaction, actualTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdShouldThrowsExceptionIfNoSuchTransaction() {
        this.chainblock.getById(10);
    }

    @Test
    public void testGetByTransactionStatusShouldReturnCorrectlySortedTransactionByGivenStatus() {
        addedTransactionInChainBlock(this.chainblock);
        List<Transaction> expected = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .filter(t -> t.getStatus().equals(TransactionStatus.FAILED))
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getByTransactionStatus(TransactionStatus.FAILED);
        List<Transaction> actual = new ArrayList<>();
        iterable.forEach(actual::add);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusShouldThrowsExceptionForNoSuchTransactionByGivenStatus() {
        this.chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusShouldReturnAllSendarsWithGivenStatus() {
        addedTransactionInChainBlock(this.chainblock);
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        Iterable<String> iterable = this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        Assert.assertNotNull(iterable);
        List<String> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusShouldThrowsExceptionIfNoSuchSenderByGivenStatus() {
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatusShouldReturnAllSendarsWithGivenStatus() {
        addedTransactionInChainBlock(this.chainblock);
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        Iterable<String> iterable = this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        Assert.assertNotNull(iterable);
        List<String> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusShouldThrowsExceptionIfNoSuchSenderByGivenStatus() {
        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllOrderedByAmountDescendingThenByIdShouldReturnAllTransactionSortedByAmountAndById() {
        addedTransactionInChainBlock(this.chainblock);
        List<Transaction> expected = transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getAllOrderedByAmountDescendingThenById();
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturnSenderOrderedByAmountDescending() {
        addedTransactionInChainBlock(this.chainblock);
        String searchingSender = "Gosho";
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(searchingSender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getBySenderOrderedByAmountDescending(searchingSender);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingShouldThrowsExceptionIfNoSuchSender() {
        this.chainblock.getBySenderOrderedByAmountDescending("Petko");
    }

    @Test
    public void testGetByReceiverOrderedByAmountDescendingAndByIdAscendingShouldReturnSenderOrderedByAmountDescending() {
        addedTransactionInChainBlock(this.chainblock);
        String searchingReceiver = "Toshko";
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(searchingReceiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getByReceiverOrderedByAmountThenById(searchingReceiver);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountDescendingAndByIdAscendingShouldThrowsExceptionIfNoSuchSender() {
        this.chainblock.getByReceiverOrderedByAmountThenById("Petko");
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnAllTransactionWithGivenStatusAndMaximumAmountOrLess() {
        addedTransactionInChainBlock(this.chainblock);
        double amount = 30;
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, amount);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBySenderAndMinimumAmountDescendingShouldReturnTransactionWithSenderAndMinimumAmount() {
        addedTransactionInChainBlock(this.chainblock);
        String searchingSender = "Gosho";
        double amount = 5;
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(searchingSender))
                .filter(t -> t.getAmount() >= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getBySenderAndMinimumAmountDescending(searchingSender, amount);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingShouldThrowsExceptionIfNoSuchTransactionByGivenSenderAndMinAmount() {
        this.chainblock.getBySenderAndMinimumAmountDescending("Tinko", 100);
    }

    @Test
    public void testGetByReceiverAndAmountRangeShouldReturnedCorrectlyTransaction() {
        addedTransactionInChainBlock(this.chainblock);
        String searchingReceiver = "Toshko";
        double lower = 2.00;
        double higer = 15.99;
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(searchingReceiver))
                .filter(t -> t.getAmount() >= lower && t.getAmount() < higer)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getByReceiverAndAmountRange(searchingReceiver, lower, higer);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeShouldThrowsExceptionIfNoSuchTransactionByGivenReceiverAndAmountRange() {
        this.chainblock.getByReceiverAndAmountRange("Toshko", 50, 80);
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnCorrectlyTransactionByGivenAmountRange() {
        addedTransactionInChainBlock(this.chainblock);
        double lower = 2.00;
        double higer = 15.99;
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getAmount() >= lower && t.getAmount() <= higer)
                .sorted()
                .collect(Collectors.toList());
        Iterable<Transaction> iterable = this.chainblock.getAllInAmountRange(lower, higer);
        assertNotNull(iterable);
        List<Transaction> actual = transformToLIst(iterable);
        Assert.assertEquals(expected, actual);
    }
}