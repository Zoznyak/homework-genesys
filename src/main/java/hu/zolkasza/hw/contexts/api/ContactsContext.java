package hu.zolkasza.hw.contexts.api;

import hu.zolkasza.hw.model.api.Contact;
import okhttp3.Response;

import java.util.List;

public class ContactsContext {

    private Response lastResponse;
    private List<Contact> contactList;

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    public Response getLastResponse() {
        return this.lastResponse;
    }

    public void setContactList(List<Contact> contacts) {
        this.contactList = contacts;
    }

    public List<Contact> getContactList() {
        return this.contactList;
    }

}
