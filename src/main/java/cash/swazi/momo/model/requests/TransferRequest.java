package cash.swazi.momo.model.requests;
/**
 * Defines a transfer request. Used to transfer money to user's account
 * @see <a href="https://momodeveloper.mtn.com/docs/services/disbursement/operations/transfer-POST?">disbursement/transfer</a>
 * @see <a href="https://momodeveloper.mtn.com/docs/services/remittance/operations/transfer-POST?">remittance/transfer</a>
 */
public final class TransferRequest extends Payment {
    private final Party payee;

    public TransferRequest(double amount, String currency, String externalId, Party payee, String payerMessage, String payeeNote) {
        super(amount, currency, externalId, payerMessage, payeeNote);
        this.payee = payee;
    }

    public Party getPayee() {
        return payee;
    }
}
