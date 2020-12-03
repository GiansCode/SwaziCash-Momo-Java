package cash.swazi.momo.model.transaction;

import com.google.gson.annotations.SerializedName;

/**
 * POJO representing data received about a User's balance
 */
public class Balance {
    @SerializedName("availableBalance")
    private final double availableBalance;
    private final String currency;

    public Balance(double availableBalance, String currency) {
        this.availableBalance = availableBalance;
        this.currency = currency;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public String getCurrency() {
        return currency;
    }
}
