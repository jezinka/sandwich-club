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

        String mainName = "";
        List<String> otherNames = new ArrayList<>();

        try {
            JSONObject jsonSandwich = new JSONObject(json);

            if (jsonSandwich.has(NAME)) {
                JSONObject name = jsonSandwich.getJSONObject(NAME);

                mainName = name.optString(MAIN_NAME);
                if (name.has(ALSO_KNOWN_AS)) {
                    otherNames = prepareList(name.getJSONArray(ALSO_KNOWN_AS));
                }
            }

            String placeOfOrigin = jsonSandwich.optString(PLACE_OF_ORIGIN);
            String description = jsonSandwich.optString(DESCRIPTION);
            String image = jsonSandwich.optString(IMAGE);

            List<String> ingredients = new ArrayList<>();
            if (jsonSandwich.has(INGREDIENTS)) {
                ingredients = prepareList(jsonSandwich.getJSONArray(INGREDIENTS));
            }

            return new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e("JsonUtils", e.getMessage());
        }
        return null;
    }

    /**
     * retrieve String properties from JSONArray
     *
     * @param jsonArray
     * @return List
     * @throws JSONException
     */
    @NonNull
    private static List<String> prepareList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.optString(i));
        }
        return stringList;
    }
}
