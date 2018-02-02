package com.obf.movie.service;

import com.obf.movie.domain.Transaction;
import com.obf.movie.repository.TransactionRepository;
import com.obf.movie.util.PartialUpdateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Transactional(readOnly = true)
    public Transaction getTransaction(Long oid) {

        Transaction tx = transactionRepository.findOneByOid(oid);
        if (tx == null)
            log.info("Could not find Transaction by oid: {}", oid);

        return tx;
    }

    @Transactional
    public Transaction update(Transaction transaction) {

        Transaction tx = transactionRepository.findOneByOid(transaction.getOid());
        if (tx == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Updating Transaction with oid: {}", tx.getOid());
        transaction.setModified(Date.from(Instant.now()));
        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Transaction partialUpdate(Transaction transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Transaction tx = transactionRepository.findOneByOid(transaction.getOid());
        if (tx == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", tx.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, tx);
        tx.setModified(Date.from(Instant.now()));
        tx = transactionRepository.save(tx);

        return tx;
    }

    @Transactional
    public Transaction save(Transaction transaction) {
        log.info("Saving tx");
        transaction.setModified(Date.from(Instant.now()));
        transactionRepository.save(transaction);

        return transaction;
    }
}
