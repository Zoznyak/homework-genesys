package hu.zolkasza.hw.steps.api;

import hu.zolkasza.hw.contexts.api.ContactsContext;
import hu.zolkasza.hw.model.api.Contact;
import hu.zolkasza.hw.model.api.HttpMethod;
import hu.zolkasza.hw.tools.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class GetContactsStep extends AbstractApiSteps<Object, List<Contact>> {

    private static final Logger logger = LogManager.getLogger(GetContactsStep.class);
    private final ContactsContext context;

    public GetContactsStep(ContactsContext context, Configuration config) {
        super(config);
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
        logger.info("Getting all contacts");
        callingEndpointWithoutInput();
        assertThatResponseIsSuccessful();
        context.setContactList(getOutput());
    }

    public void logContacts() {
        logger.info("Logging contacts");
        context.getContactList().forEach(contact ->
                logger.info("{} | {}", contact.getName(), contact.getEmail())
        );
    }

    public void assertNthContactContains(int n, String string) {
        logger.info("Asserting the {}th contact contains {}", n, string);
        Contact contact = context.getContactList().get(n);
        if (!contact.getEmail().contains(string)) {
            throw new AssertionError("The " + n + "th contact does not contain " + string);
        }
    }

}
