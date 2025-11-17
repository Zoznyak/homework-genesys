package hu.zolkasza.hw.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonProcessor {

    protected <T> T fromJson(String json, Type type ) {
        return json().fromJson(json, type);
    }

    private Gson json() {
        return new GsonBuilder().create();
    }

}
