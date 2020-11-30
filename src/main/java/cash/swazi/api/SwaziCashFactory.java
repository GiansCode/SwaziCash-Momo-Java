package cash.swazi.api;

import cash.swazi.api.CollectionDelegate;
import cash.swazi.api.RemittanceDelegate;
import cash.swazi.api.SandboxOptionProvider;
import cash.swazi.client.*;

public final class SwaziCashFactory {

    public CollectionDelegate createCollectionDelegate(Options options) {
        return new CollectionClient(options);
    }

    public DisbursementClient createDisbursementDelegate(Options options) {
        return new DisbursementClient(options);
    }

    public RemittanceDelegate createRemittanceDelegate(Options options) {
        return new RemittanceClient(options);
    }

    public SandboxOptionProvider createSandboxOptionProvider(String subscriptionKey) {
        return createSandboxOptionProvider("https://sandbox.momodeveloper.mtn.com/", subscriptionKey);
    }

    public SandboxOptionProvider createSandboxOptionProvider(String baseUrl, String subscriptionKey) {
        return new SandboxUserProvisioningClient(baseUrl, subscriptionKey);
    }
}
