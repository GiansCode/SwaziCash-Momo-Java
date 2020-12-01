package cash.swazi.api;

import cash.swazi.api.CollectionDelegate;
import cash.swazi.api.RemittanceDelegate;
import cash.swazi.api.SandboxOptionProvider;
import cash.swazi.client.*;

public final class SwaziCashFactory {
    private SwaziCashFactory() {}

    public static CollectionDelegate createCollectionDelegate(Options options) {
        return new CollectionClient(options);
    }

    public static DisbursementClient createDisbursementDelegate(Options options) {
        return new DisbursementClient(options);
    }

    public static RemittanceDelegate createRemittanceDelegate(Options options) {
        return new RemittanceClient(options);
    }

    public static SandboxOptionProvider createSandboxOptionProvider(String subscriptionKey) {
        return createSandboxOptionProvider("https://sandbox.momodeveloper.mtn.com/", subscriptionKey);
    }

    public static SandboxOptionProvider createSandboxOptionProvider(String baseUrl, String subscriptionKey) {
        return new SandboxUserProvisioningClient(baseUrl, subscriptionKey);
    }
}