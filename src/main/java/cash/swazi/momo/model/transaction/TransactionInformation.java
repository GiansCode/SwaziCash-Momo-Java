package cash.swazi.momo.model.transaction;

import cash.swazi.momo.model.requests.Party;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class TransactionInformation {
    private final double amount;
    private final String currency;
    private final String financialTransactionId;
    private final String externalId;
    @SerializedName(value = "party", alternate = {"payer", "payee"})
    private final Party party;
    private final TransactionStatus status;
    private final @Nullable TransactionFailReason reason;

    public TransactionInformation(double amount, String currency, String financialTransactionId, String externalId, Party party, TransactionStatus status, TransactionFailReason reason) {
        this.amount = amount;
        this.currency = currency;
        this.financialTransactionId = financialTransactionId;
        this.externalId = externalId;
        this.party = party;
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

    public Party getParty() {
        return party;
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
