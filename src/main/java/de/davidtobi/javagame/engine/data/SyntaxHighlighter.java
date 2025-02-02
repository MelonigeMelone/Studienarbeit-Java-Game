package de.davidtobi.javagame.engine.data;

import java.awt.*;
import java.util.HashMap;

public enum SyntaxHighlighter {
    JAVA("java", new HashMap<>() {{
        put(Color.RED, new String[] {
                "boolean"
        });
        put(Color.GREEN, new String[] {
                "true", "false"
        });
    }});

    private final String identifier;
    private final HashMap<Color, String[]> colorMap;

    SyntaxHighlighter(String identifier, HashMap<Color, String[]> colorMap) {
        this.identifier = identifier;
        this.colorMap = colorMap;
    }

    public String getIdentifier() {
        return identifier;
    }

    public HashMap<Color, String[]> getColorMap() {
        return colorMap;
    }

    public Color getColor(String keyWord, Color defaultColor) {
        for (Color color : colorMap.keySet()) {
            for (String key : colorMap.get(color)) {
                if (key.equals(keyWord)) {
                    return color;
                }
            }
        }
        return defaultColor;
    }

}
