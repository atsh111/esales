package com.styros.esales.utils;

/**
 * Created by atul on 11/20/2017.
 */
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Created by atul on 11/9/2017.
 */
public class JsonHelper {

    public static <T> T convertToType(String json,Type T){
        Gson gson = new Gson();
        return gson.fromJson(json,T);
    }


    public static String convertToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}

