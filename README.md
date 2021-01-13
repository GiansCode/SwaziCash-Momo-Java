# SwaziCash
## Example Usage

```java
import java.util.UUID;
import java.io.IOException;
import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.api.delegate.CollectionDelegate;
import cash.swazi.momo.model.transaction.Balance;
public class Example {
	public static void main(String[] args) {
		UUID id = ...; // Your MoMo API user id
		Options options = new Options(
			"YOUR_SUBSCRIPTION_KEY", 
			"YOUR_API_KEY", 
			 id, 
			"YOUR_SERVICE_BASE_URL", 
			"TARGET_ENVIRONMENT", 
			"CURRENCY"
		);
		CollectionDelegate collections = SwaziCashFactory.createCollectionDelegate(options);
		try {
			Balance balance = collections.getBalance();
			double amt = balance.getAvailableBalance();
			System.out.printf("The balance is %d %s\n", amt, balance,getCurrency());
		} catch (IOException ioe) {
			System.out.println("Unable to send request to API endpoint");
		} catch (RequestFailedException rfe) {
			System.out.println("Request failed! Status code: " + rfe.getStatusCode() + " Reason:" + rfe.getReason());
		}
	}
}
```
### Using Sandbox Environment
The API provides an easier way to create Options for Sandbox testing.

```java
SandboxOptionDelegate sandboxDelegate = SwaziCashFactory.createSandboxOptionProvider("YOUR_SUBSCRIPTION_KEY");
String callbackHost = null; /* If using callbacks, specify the callback url base here */
Options options = sandboxDelegate.requestSandboxOptions(id, callbackHost);
```
The returned options can be used to interact with the sandbox environment just as normal.
---
### Example of sending a Collection payment request
[MTN MoMo Collections / RequestToPay](https://momodeveloper.mtn.com/docs/services/collection/operations/requesttopay-POST)
```java
Options options = ...; // Create options using above examples
CollectionDelegate collections = SwaziCashFactory.createCollectionDelegate(options);

// Reference to the transaction, to be used later to get transaction info
UUID referenceId = UUID.randomUUID();

// Info about payer
Party payer = new Party(Party.PartyIdType.MSISDN, "9658965889");

PaymentRequest request = new PaymentRequest(
        1000,
        options.getCurrency(),
        "4546",
        payer,
        "Note for Payer",
        "Note for Payee"
);
/*
* URL for callback to be used by MoMo API. Must be on the same host as registered with your auth userId
* Can be null if not required
*/
String callbackURL = null; 
try {
    collections.requestPayment(referenceId, callbackURL, request);
} catch (IOException | RequestFailedException e) {
    e.printStackTrace();
}
```

### Example of getting transaction info (From collections for an example case)
[MTN MoMo Collections / RequestToPay - Transacton Info ](https://momodeveloper.mtn.com/docs/services/collection/operations/requesttopay-referenceId-GET?)
```java
import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.delegate.TransactionDelegate;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.model.transaction.TransactionInformation;

import java.io.IOException;
import java.util.UUID;

public class TransactionInfoExample {
    public static void main(String[] args) {
        Options options = ...; // Create options using above examples
        TransactionDelegate collections = SwaziCashFactory.createCollectionDelegate(options);
        UUID referenceId = ...; // reference Id used to send transaction request
        try {
            TransactionInformation info = collections.getTransactionInformation(referenceId);
            System.out.println("The transaction status is: " + info.getStatus());
            if (info.getStatus() == TransactionInformation.TransactionStatus.FAILED) {
                System.out.println("Failure reason for transaction: " + info.getReason());
            }
        } catch (IOException | RequestFailedException e) {
            e.printStackTrace();
        }
    }
}```
