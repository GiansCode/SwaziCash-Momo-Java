package cash.swazi.momo.api;

import cash.swazi.momo.client.Options;

/**
 *  Associated with api classes that handle required authentication themselves
 */
public interface SelfAuthenticating {
    TokenProvider getTokenProvider();
}
