package de.davidtobi.javagame.engine.log;

public enum EngineLoggerLevel {
    ERROR(100, AnsiColor.ANSI_RED),
    WARNING(75, AnsiColor.ANSI_YELLOW),
    INFORMATION(50, AnsiColor.ANSI_RESET),
    DEBUG(0, AnsiColor.ANSI_CYAN);

    private final int value;
    private final String ansiColorCode;

    EngineLoggerLevel(int value, String ansiColorCode) {
        this.value = value;
        this.ansiColorCode = ansiColorCode;
    }

    public int getValue() {
        return value;
    }

    public String getAnsiColorCode() {
        return ansiColorCode;
    }
}
