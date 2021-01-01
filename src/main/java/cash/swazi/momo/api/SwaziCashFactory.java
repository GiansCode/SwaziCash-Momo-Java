package cash.swazi.momo.api;

import cash.swazi.momo.api.delegate.CollectionDelegate;
import cash.swazi.momo.api.delegate.RemittanceDelegate;
import cash.swazi.momo.api.delegate.SandboxOptionDelegate;
import cash.swazi.momo.client.*;
import cash.swazi.momo.client.data.Options;

/**
 *  Static factory class that provides access to different components of the API. This would usually be
 *  used as the entrypoint into the API
 */
public final class SwaziCashFactory {
    private SwaziCashFactory() {}

    /**
     * @param options The API info used to authenticate requests
     * @return A delegate implementation that handles requests for the collections API
     */
    public static CollectionDelegate createCollectionDelegate(Options options) {
        return new CollectionClient(options);
    }

    /**
     * @param options The API info used to authenticate requests
     * @return A delegate implementation that handles requests for the disbursements API
     */
    public static DisbursementClient createDisbursementDelegate(Options options) {
        return new DisbursementClient(options);
    }

    /**
     * @param options The API info used to authenticate requests
     * @return A delegate implementation that handles requests for the remittance API
     */
    public static RemittanceDelegate createRemittanceDelegate(Options options) {
        return new RemittanceClient(options);
    }

    /**
     * Uses the default sandbox environment, i,e. <a href="https://sandbox.momodeveloper.mtn.com/">MoMo Developer Sandbox Environment</a>
     * @param subscriptionKey subscription key provided by sandbox environment provider.
     * @return SandboxOptionProvider, used to create sandbox API Users
     */
    public static SandboxOptionDelegate createSandboxOptionProvider(String subscriptionKey) {
        return createSandboxOptionProvider("https://sandbox.momodeveloper.mtn.com/", subscriptionKey);
    }

    /**
     * @param baseUrl base URL to sandbox environment
     * @param subscriptionKey Subscription key provided by sandbox environment provider.
     * @return SandboxOptionProvider, used to create sandbox API Users
     */
    public static SandboxOptionDelegate createSandboxOptionProvider(String baseUrl, String subscriptionKey) {
        return new SandboxUserProvisioningClient(baseUrl, subscriptionKey);
    }
}
