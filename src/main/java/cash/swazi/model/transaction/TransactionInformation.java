package cash.swazi.model.transaction;

import cash.swazi.model.Payer;

public class TransactionInformation {
    private final double amount;
    private final String currency;
    private final String financialTransactionId;
    private final String externalId;
    private final Payer payer;
    private final TransactionStatus status;
    private final TransactionFailReason reason;

    public TransactionInformation(double amount, String currency, String financialTransactionId, String externalId, Payer payer, TransactionStatus status, TransactionFailReason reason) {
        this.amount = amount;
        this.currency = currency;
        this.financialTransactionId = financialTransactionId;
        this.externalId = externalId;
        this.payer = payer;
        this.status = status;
        this.reason = reason;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getFinancialTransactionId() {
        return financialTransactionId;
    }

    public String getExternalId() {
        return externalId;
    }

    public Payer getPayer() {
        return payer;
    }

    public TransactionFailReason getReason() {
        return reason;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public enum TransactionStatus {
        PENDING,
        SUCCESSFUL,
        FAILED;
    }
}
