package com.obf.movie.service;

import com.obf.movie.Application;
import com.obf.movie.domain.Transaction;
import com.obf.movie.repository.TransactionRepository;

import com.obf.movie.config.ApplicationProperties;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("unit-test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class TransactionServiceTest {

    private TransactionService transactionService;

    @Inject
    private ApplicationProperties applicationProperties;
    @Inject
    private TransactionRepository transactionRepository;

    //@Ignore
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionService(transactionRepository);
    }

    @Ignore
    @Test
    public void getProperties() {
        assertThat(applicationProperties.getFooBar().getFoo()).as("Property was null").isNotNull();
        assertThat(applicationProperties.getFooBar().getBar()).as("Property was null").isNotNull();
        assertThat(applicationProperties.getFoo()).as("Property was null").isNotNull();
        assertThat(applicationProperties.getBar()).as("Property was null").isNotNull();
    }

   // @Ignore
    @Test
    public void handleGet() {
        Long txAmount = 100L;
        addTransaction(txAmount);

        Transaction tx = transactionService.getTransaction(1L);
        assertThat(tx).as("Transaction was null").isNotNull();
        assertThat(tx.getAmount()).as("Transaction has wrong amount").isEqualTo(txAmount);
    }

    private void addTransaction(long amount) {
        Transaction tx = new Transaction();
        tx.setOid(1L);
        tx.setAmount(amount);
        tx.setAmountRemaining(amount);
        tx.setClientTxId(UUID.randomUUID().toString());
        tx.setStatus("0");
        tx.setOfflineTx(false);
        tx.setSessionId(UUID.randomUUID().toString());
        tx.setType(7L); //ManualAuth
        tx.setCardInfoOid(0L);
        tx.setHashedPan(UUID.randomUUID().toString());
        tx.setCurrencyIso("NOK");
        tx.setIssuerOid(0L);
        tx.setProviderOid(0L);
        tx.setCardProductOid(0L);
        tx.setMerchantid("-1");
        tx.setStoreid("-1");
        tx.setTerminalid("-1");
        tx.setAcquirerid("-1");
        tx.setFlag(false);
        tx.setReversable(true);
        tx.setReportRetrycount(0L);
        tx.setSettlementRetrycount(0L);
        tx.setAuthcode(String.valueOf(tx.getOid()));
        tx.setCreated(new Date());
        tx.setCreatedBy("test");
        tx.setModified(new Date());
        tx.setModifiedBy("test");
        transactionRepository.save(tx);
    }
}
