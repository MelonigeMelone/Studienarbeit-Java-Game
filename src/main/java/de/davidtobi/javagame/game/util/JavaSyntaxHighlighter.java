package de.davidtobi.javagame.game.util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class JavaSyntaxHighlighter {
    private static final String[] KEYWORDS = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
            "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
            "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while"
    };

    private static final Color KEYWORD_COLOR = new Color(0, 0, 200);  // Blue for keywords
    private static final Color STRING_COLOR = new Color(200, 0, 0);   // Red for strings
    private static final Color COMMENT_COLOR = new Color(0, 150, 0);  // Green for comments
    private static final Color DEFAULT_COLOR = Color.BLACK;           // Default text color

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaSyntaxHighlighter::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Java Syntax Highlighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textPane);

        StyledDocument doc = textPane.getStyledDocument();
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { highlightSyntax(doc); }
            public void removeUpdate(DocumentEvent e) { highlightSyntax(doc); }
            public void changedUpdate(DocumentEvent e) { highlightSyntax(doc); }
        });

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private static void highlightSyntax(StyledDocument doc) {
        SwingUtilities.invokeLater(() -> {
            try {
                String text = doc.getText(0, doc.getLength());
                doc.setCharacterAttributes(0, text.length(), defaultStyle(doc), true);

                // Highlight keywords
                for (String keyword : KEYWORDS) {
                    Pattern pattern = Pattern.compile("\\b" + keyword + "\\b");
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()) {
                        doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), keywordStyle(doc), false);
                    }
                }

                // Highlight strings
                Pattern stringPattern = Pattern.compile("\"(.*?)\"");
                Matcher stringMatcher = stringPattern.matcher(text);
                while (stringMatcher.find()) {
                    doc.setCharacterAttributes(stringMatcher.start(), stringMatcher.end() - stringMatcher.start(), stringStyle(doc), false);
                }

                // Highlight comments
                Pattern commentPattern = Pattern.compile("//.*|/\\*(.|\\R)*?\\*/");
                Matcher commentMatcher = commentPattern.matcher(text);
                while (commentMatcher.find()) {
                    doc.setCharacterAttributes(commentMatcher.start(), commentMatcher.end() - commentMatcher.start(), commentStyle(doc), false);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    private static AttributeSet keywordStyle(StyledDocument doc) {
        Style style = doc.addStyle("KeywordStyle", null);
        StyleConstants.setForeground(style, KEYWORD_COLOR);
        StyleConstants.setBold(style, true);
        return style;
    }

    private static AttributeSet stringStyle(StyledDocument doc) {
        Style style = doc.addStyle("StringStyle", null);
        StyleConstants.setForeground(style, STRING_COLOR);
        return style;
    }

    private static AttributeSet commentStyle(StyledDocument doc) {
        Style style = doc.addStyle("CommentStyle", null);
        StyleConstants.setForeground(style, COMMENT_COLOR);
        StyleConstants.setItalic(style, true);
        return style;
    }

    private static AttributeSet defaultStyle(StyledDocument doc) {
        Style style = doc.addStyle("DefaultStyle", null);
        StyleConstants.setForeground(style, DEFAULT_COLOR);
        return style;
    }
}

