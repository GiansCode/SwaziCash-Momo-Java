package cash.swazi.momo.client;

import com.google.gson.Gson;

abstract class OptionedAPIClient extends BasicAPIClient {
    private final Options options;
    public OptionedAPIClient(Options options) {
        super(options.getBaseUrl());
        this.options = options;
    }

    public OptionedAPIClient(Options options, Gson gson) {
        super(options.getBaseUrl(), gson);
        this.options = options;
    }

    public OptionedAPIClient(Options options, IRestClient client) {
        super(client);
        this.options = options;
    }

    public OptionedAPIClient(Options options, IRestClient client, Gson gson) {
        super(client, gson);
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }
}
