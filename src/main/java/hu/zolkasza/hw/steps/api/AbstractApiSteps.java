package hu.zolkasza.hw.steps.api;

import com.google.gson.Gson;
import hu.zolkasza.hw.model.api.HttpMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractApiSteps<INPUT, OUTPUT> {

    private final Gson gson = new Gson();
    private final OkHttpClient client = new OkHttpClient();
    private Response response;
    private OUTPUT output;

    public void callingEndpointWithoutInput() throws IOException {
        sendAndRecieve(null);
    }

    public void assertThatResponseIsSuccessful() {
        if (!response.isSuccessful()) {
            throw new AssertionError("Response is not successful");
        }
    }

    public OUTPUT getOutput() {
        return output;
    }

    protected abstract String getUrlSuffix(INPUT input);

    protected abstract HttpMethod getMethod();

    protected abstract Object getRequestBody(INPUT input);

    private void sendAndRecieve(INPUT input) throws IOException{
        Request.Builder requestBuilder = new Request.Builder()
                .url(getUrl(input));
        String method = getMethod().name();
        Object requestBody = getRequestBody(input);
        if (requestBody != null) {
             // TODO add request body handling
        } else {
            requestBuilder.method(method, null);
        }
        requestBuilder.method(method, null);
        Request request = requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            if (response.isSuccessful() && response.body() != null) {
                Type outputType = getOutputType();
                output = gson.fromJson(response.body().charStream(), outputType);
            }
        }
    }

    private Type getOutputType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return parameterized.getActualTypeArguments()[1];
        }
        throw new RuntimeException("Unable to determine output type");
    }

    private String getUrl(INPUT input) {
        String url = getBaseUrl();
        if (!url.endsWith("/")) {
            url += "/";
        }
        String suffix = getUrlSuffix(input);
        if (suffix.startsWith("/")) {
            suffix = suffix.substring(1);
        }
        return url + suffix;
    }

    private String getBaseUrl() {
        return "https://jsonplaceholder.typicode.com";
    }

}
