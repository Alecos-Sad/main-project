package by.sadovnik.multithreadspringrest.service;

import by.sadovnik.multithreadspringrest.entity.Account;
import by.sadovnik.multithreadspringrest.exception.AccNotFoundException;
import by.sadovnik.multithreadspringrest.exception.BalanceException;
import by.sadovnik.multithreadspringrest.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final Map<String, ReentrantLock> accountLocks = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(AccNotFoundException::new);
    }

    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(BalanceException::new);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Long id, Account account) {
        Account oldAccount = getAccountById(id);
        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        return accountRepository.save(oldAccount);
    }

    public void delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccNotFoundException();
        }
        accountRepository.deleteById(id);
    }

    public BigDecimal getBalance(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccNotFoundException();
        }
        Account accountById = getAccountById(id);
        return accountById.getBalance();
    }

    public CompletableFuture<Void> transferAsyncMoney(String fromAccount, String toAccount, BigDecimal amount) {
        return CompletableFuture.runAsync(() -> {
                            String firstLock = fromAccount.compareTo(toAccount) < 0 ? fromAccount : toAccount;
                            String secondLock = toAccount.equals(firstLock) ? toAccount : fromAccount;

                            ReentrantLock firstAccountLock = accountLocks.computeIfAbsent(firstLock, k -> new ReentrantLock());
                            ReentrantLock secondAccountLock = accountLocks.computeIfAbsent(secondLock, k -> new ReentrantLock());

                            firstAccountLock.lock();
                            secondAccountLock.lock();

                            try {
                                Account sender = getByAccountNumber(fromAccount);
                                Account receiver = getByAccountNumber(toAccount);

                                if (sender.getBalance().compareTo(amount) < 0) {
                                    throw new AccNotFoundException();
                                }

                                sender.setBalance(sender.getBalance().subtract(amount));
                                receiver.setBalance(receiver.getBalance().add(amount));

                                accountRepository.save(sender);
                                accountRepository.save(receiver);
                            } catch (OptimisticLockingFailureException e) {
                                System.err.println(e.getMessage());
                            } finally {
                                firstAccountLock.unlock();
                                secondAccountLock.unlock();
                            }
                        }, executorService
                )
                .thenAccept(accept -> System.out.println("transferAsyncMoney done"))
                .exceptionally(e -> {
                            System.err.println("Error while transfer: " + e.getMessage());
                            if (e instanceof BalanceException) {
                                throw new BalanceException();
                            } else {
                                if (e.getCause().getClass().equals(AccNotFoundException.class)) {
                                    throw new AccNotFoundException();
                                }
                            }
                            throw new RuntimeException("Error while transfer: " + e.getMessage());
                        }
                );
    }

    //синхронный перевод
    public void transferMoney(String fromAccount, String toAccount, BigDecimal amount) {
        Account sender = getByAccountNumber(fromAccount);
        Account receiver = getByAccountNumber(toAccount);

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new BalanceException();
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
}
