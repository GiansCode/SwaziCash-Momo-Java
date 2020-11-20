package cash.swazi.client;

public abstract class BasicAPIClient {
    private final Options options;
    private final IRestClient client;

    public BasicAPIClient(Options options) {
        this(options, new RestClient(options.getBaseUrl()));
    }

    public BasicAPIClient(Options options, IRestClient client) {
        this.options = options;
        this.client = client;
    }

    public Options getOptions() {
        return options;
    }

    protected IRestClient getRestClient() {
        return client;
    }
}
