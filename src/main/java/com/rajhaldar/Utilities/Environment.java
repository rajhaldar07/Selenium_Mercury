package com.rajhaldar.Utilities;

import com.jayway.restassured.path.json.JsonPath;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Environment {

    public static String fileName = null;

    public static String get() {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(new StringBuilder(fileName).append(".json").toString()).getFile());
            JsonPath jsonPath = new JsonPath(FileUtils.readFileToString(file, "UTF-8"));
            return jsonPath.getString("environment");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}