package cash.swazi.momo.api;

import cash.swazi.momo.client.Options;
import org.jetbrains.annotations.NotNull;

/**
 *  Associated with api classes that handle required authentication themselves
 */
public interface SelfAuthenticating {
    /**
     * @return Provider that returns a token for specific clients when requested
     */
    @NotNull
    TokenProvider getTokenProvider();
}
