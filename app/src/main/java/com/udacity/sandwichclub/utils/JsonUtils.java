package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject name = jsonSandwich.getJSONObject("name");

            String mainName = name.getString("mainName");
            List<String> otherNames = prepareList(name.getJSONArray("alsoKnownAs"));

            String placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
            String description = jsonSandwich.getString("description");
            String image = jsonSandwich.getString("image");
            List<String> ingredients = prepareList(jsonSandwich.getJSONArray("ingredients"));

            return new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
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
