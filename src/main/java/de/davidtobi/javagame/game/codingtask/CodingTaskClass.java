package de.davidtobi.javagame.game.codingtask;

public class CodingTaskClass {

    private final String className;
    private final String visibleCode;
    private final String hiddenCode;

    private String currentCode;

    public CodingTaskClass(String className, String visibleCode, String hiddenCode) {
        this.className = className;
        this.visibleCode = visibleCode;
        this.hiddenCode = hiddenCode;
        this.currentCode = buildClassCode();
    }

    public String getClassName() {
        return className;
    }

    public String getVisibleCode() {
        return visibleCode;
    }

    public String getHiddenCode() {
        return hiddenCode;
    }

    public boolean hasVisibleCode() {
        return visibleCode != null && !visibleCode.isEmpty();
    }

    public boolean hasHiddenCode() {
        return hiddenCode != null && !hiddenCode.isEmpty();
    }

    public String buildClassCode() {
        StringBuilder classCode = new StringBuilder();
        classCode.append("public class ").append(className).append(" {\n");
        if (hasVisibleCode()) {
            classCode.append(visibleCode).append("\n");
        }
        if (hasHiddenCode()) {
            classCode.append(hiddenCode).append("\n");
        }
        classCode.append("}");
        return classCode.toString();
    }

    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }
}
