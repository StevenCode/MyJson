package com.steven;

import com.google.gson.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;


import java.util.Map;
import java.util.Set;

public class Controller {
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextArea textArea;

    public void init() {

    }


    public void keyTyped(KeyEvent keyEvent) {
        try {
            String text = textArea.getText();
            Gson gson = new Gson();

            TreeItem<String> root = new TreeItem<>("root");
            treeView.setRoot(root);

            JsonObject jsonObject = gson.fromJson(text, JsonObject.class);

            parseObject(root, jsonObject);
            treeView.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseObject(TreeItem<String> root, JsonObject jsonObject) {
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {

            TreeItem<String> objectItem = new TreeItem<>(entry.getKey());
            root.getChildren().add(objectItem);

            JsonElement value = entry.getValue();
            if (value.isJsonObject()) {
                JsonObject asJsonObject = value.getAsJsonObject();
                 parseObject(objectItem, asJsonObject);
            } else if (value.isJsonArray()) {
                JsonArray asJsonArray = value.getAsJsonArray();
                parseArray(objectItem, asJsonArray);
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = value.getAsJsonPrimitive();
                String s = asJsonPrimitive.toString();
                String value1 = objectItem.getValue();
                objectItem.setValue(value1 + "=" + s);

                System.out.println(s);
            } else if (value.isJsonNull()) {

            }
        }
    }

    private void parseArray(TreeItem<String> root, JsonArray jsonArray) {


        for (JsonElement value : jsonArray) {

            TreeItem<String> objectItem = new TreeItem<>("[]");
            root.getChildren().add(objectItem);

            if (value.isJsonObject()) {
                JsonObject asJsonObject = value.getAsJsonObject();
                parseObject(objectItem, asJsonObject);
            } else if (value.isJsonArray()) {
                JsonArray asJsonArray = value.getAsJsonArray();
                parseArray(objectItem, asJsonArray);
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = value.getAsJsonPrimitive();
                String s = asJsonPrimitive.toString();
                String value1 = objectItem.getValue();
                objectItem.setValue(value1 + "=" + s);
            } else if (value.isJsonNull()) {

            }
        }
    }
}
