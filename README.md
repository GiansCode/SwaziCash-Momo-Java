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
		CollectionsDelegate collections = SwaziCashFactory.createCollectionDelegate(options);
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
The API provides easier way to create Options for Sandbox testing.

```java
SandboxOptionDelegate sandboxDelegate = SwaziCashFactory.createSandboxOptionProvider("YOUR_SUBSCRIPTION_KEY");
String callbackHost = null; /* If using callbacks, specify the callback url base here */
Options options = sandboxDelegate.requestSandboxOptions(id, callbackHost);
```
The returned options can be used to interact with the sandbox environment just as normal.
