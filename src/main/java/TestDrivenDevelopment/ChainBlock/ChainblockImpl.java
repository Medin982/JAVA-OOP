package TestDrivenDevelopment.ChainBlock;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new LinkedHashMap<>();
    }

    public int getCount() {
        return this.transactionMap.size();
    }

    public void add(Transaction transaction) {
        this.transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getId());
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        Transaction transaction = this.transactionMap.get(id);
        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        this.transactionMap.remove(id);
    }

    public Transaction getById(int id) {
        if (this.transactionMap.containsKey(id)) {
            return this.transactionMap.get(id);
        }
        throw new IllegalArgumentException("No such transaction");
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> returnedList = this.transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .collect(Collectors.toList());
        if (returnedList.isEmpty()) {
            throw new IllegalArgumentException("No such transaction by given status");
        }
        return returnedList.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        if (senders.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = this.transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactionMap.values().stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> senders = this.transactionMap.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (senders.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return senders;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> receivers = this.transactionMap.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return receivers;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactionsBySenderAndMinAmount = this.transactionMap.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .filter(t -> t.getAmount() >= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (transactionsBySenderAndMinAmount.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactionsBySenderAndMinAmount;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactionsByAmountRangeAndReceiver = this.transactionMap.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .filter(t -> t.getAmount() >= lo && t.getAmount() < hi)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparingInt(Transaction::getId))
                .collect(Collectors.toList());
        if (transactionsByAmountRangeAndReceiver.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactionsByAmountRangeAndReceiver;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.transactionMap.values().stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .sorted()
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
