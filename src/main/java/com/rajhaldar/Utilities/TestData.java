package com.rajhaldar.Utilities;

import com.jayway.restassured.path.json.JsonPath;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class TestData {

    public JsonPath get(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName.concat(".json")).getFile());
        return new JsonPath(FileUtils.readFileToString(file, "UTF-8"));
    }
}
