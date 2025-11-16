package hu.zolkasza.hw.steps.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.zolkasza.hw.contexts.api.ContactsContext;
import hu.zolkasza.hw.model.api.Contact;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GetContactsStep {

    private final Gson gson = new Gson();
    private OkHttpClient client = new OkHttpClient();
    private ContactsContext context;

    public GetContactsStep(ContactsContext context) {
        this.context = context;
    }

    public void getAllContacts() throws IOException {
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            context.setLastResponse(response);
            if (response.isSuccessful()) {
                Type listType = new TypeToken<List<Contact>>(){}.getType();
                List<Contact> contacts = gson.fromJson(response.body().charStream(), listType);
                context.setContactList(contacts);
            }
        }
    }

    public void logContacts() {
        // TODO logger
        context.getContactList().forEach(contact -> System.out.println(contact.getName() + " | " + contact.getEmail()));
    }

    public void assertNthContactContains(int n, String string) {
        Contact contact = context.getContactList().get(n);
        if (!contact.getEmail().contains(string)) {
            throw new AssertionError("The " + n + "th contact does not contain " + string);
        }
    }
}
