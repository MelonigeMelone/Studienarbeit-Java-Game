package de.davidtobi.javagame.engine.data;

import java.awt.*;
import java.util.HashMap;

public enum SyntaxHighlighter {
    JAVA("java", new HashMap<>() {{
        put("//.*", Color.GRAY); // Single-line comments
        put("\\b(public|private|final|class|static|void|if|for|else|return|true|false|int|double|boolean|float|new)\\b",  Color.decode("#C678DD"));
        put("\\b(String)\\b",  Color.decode("#E5C07B"));
        put("-?\\b\\d+\\b", Color.decode("#D19A66"));
    }});

    private final String identifier;
    private final HashMap<String, Color> patternMap;

    SyntaxHighlighter(String identifier, HashMap<String, Color> patternMap) {
        this.identifier = identifier;
        this.patternMap = patternMap;
    }

    public String getIdentifier() {
        return identifier;
    }

    public HashMap<String, Color> getPatternMap() {
        return patternMap;
    }

    public Color getColor(String keyWord, Color defaultColor) {
        for(String pattern : patternMap.keySet()) {
            if(keyWord.matches(pattern)) {
                return patternMap.get(pattern);
            }
        }
        return defaultColor;
    }
}
