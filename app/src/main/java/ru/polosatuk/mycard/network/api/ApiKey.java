package ru.polosatuk.mycard.network.api;

class ApiKey {
    private ApiKey() {
        throw new IllegalAccessError("Not exist");
    }

    private static final String API_KEY_HERE = "670c530135734d8992b8840b085a76be";

    static String getApiKey() {
        return API_KEY_HERE;
    }
}
