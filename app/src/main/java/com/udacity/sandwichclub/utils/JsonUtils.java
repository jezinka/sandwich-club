package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject name = jsonSandwich.getJSONObject(NAME);

            String mainName = name.getString(MAIN_NAME);
            List<String> otherNames = prepareList(name.getJSONArray(ALSO_KNOWN_AS));

            String placeOfOrigin = jsonSandwich.getString(PLACE_OF_ORIGIN);
            String description = jsonSandwich.getString(DESCRIPTION);
            String image = jsonSandwich.getString(IMAGE);
            List<String> ingredients = prepareList(jsonSandwich.getJSONArray(INGREDIENTS));

            return new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e("JsonUtils", e.getMessage());
        }
        return null;
    }

    @NonNull
    private static List<String> prepareList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }
}
