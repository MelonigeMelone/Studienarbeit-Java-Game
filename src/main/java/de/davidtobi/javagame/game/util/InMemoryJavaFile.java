package de.davidtobi.javagame.game.util;

import javax.tools.JavaFileObject;

public class InMemoryJavaFile extends javax.tools.SimpleJavaFileObject {
    private final String content;

    protected InMemoryJavaFile(String name, String content) {
        super(java.net.URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }
}
