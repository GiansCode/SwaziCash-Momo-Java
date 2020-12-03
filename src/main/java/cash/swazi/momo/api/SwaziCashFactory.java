package cash.swazi.momo.api;

import cash.swazi.momo.client.*;
/**
 *  Static factory class that provides access to different internal components of the API. This class would usually be
 *  used as the entrypoint into the API
 */
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
