package io.quarkus.narayana.jta.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.transaction.TransactionSynchronizationRegistry;

import org.jboss.tm.JBossXATerminator;
import org.jboss.tm.XAResourceRecoveryRegistry;
import org.jboss.tm.usertx.UserTransactionRegistry;

import com.arjuna.ats.internal.jbossatx.jta.jca.XATerminator;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionSynchronizationRegistryImple;
import com.arjuna.ats.jbossatx.jta.RecoveryManagerService;
import com.arjuna.ats.jta.UserTransaction;

import io.quarkus.arc.Unremovable;

@Dependent
public class NarayanaJtaProducers {

    @Produces
    @ApplicationScoped
    public UserTransactionRegistry userTransactionRegistry() {
        return new UserTransactionRegistry();
    }

    @Produces
    @ApplicationScoped
    public jakarta.transaction.UserTransaction userTransaction() {
        return UserTransaction.userTransaction();
    }

    @Produces
    @ApplicationScoped
    public XAResourceRecoveryRegistry xaResourceRecoveryRegistry() {
        return new RecoveryManagerService();
    }

    @Produces
    @ApplicationScoped
    @Unremovable
    public TransactionSynchronizationRegistry transactionSynchronizationRegistry() {
        return new TransactionSynchronizationRegistryImple();
    }

    @Produces
    @ApplicationScoped
    public JBossXATerminator xaTerminator() {
        return new XATerminator();
    }
}
