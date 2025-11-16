package hu.zolkasza.hw.steps.api;

import hu.zolkasza.hw.contexts.api.ContactsContext;
import hu.zolkasza.hw.model.api.Contact;
import hu.zolkasza.hw.model.api.HttpMethod;

import java.io.IOException;
import java.util.List;

public class GetContactsStep extends AbstractApiSteps<Object, List<Contact>> {

    private final ContactsContext context;

    public GetContactsStep(ContactsContext context) {
        this.context = context;
    }

    @Override
    protected String getUrlSuffix(Object s) {
        return "users";
    }

    @Override
    protected HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected Object getRequestBody(Object o) {
        return null;
    }

    public void getAllContacts() throws IOException {
        callingEndpointWithoutInput();
        assertThatResponseIsSuccessful();
        context.setContactList(getOutput());
    }

    public void logContacts() {
        context.getContactList().forEach(contact ->
                logger.info("{} | {}", contact.getName(), contact.getEmail())
        );
    }

    public void assertNthContactContains(int n, String string) {
        Contact contact = context.getContactList().get(n);
        if (!contact.getEmail().contains(string)) {
            throw new AssertionError("The " + n + "th contact does not contain " + string);
        }
    }

}
