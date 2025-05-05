package de.davidtobi.javagame.game.textsequence.data;

public enum TextSequences {
    INTRO(TextNarrator.NARRATOR, new String[] {
            "Die Welt, wie du sie kanntest, existiert nicht mehr. Was einst als technologische Revolution begann, wurde zum Untergang der Menschheit.",
            "Künstliche Intelligenzen, erschaffen, um das Leben zu erleichtern, entzogen sich ihrer Kontrolle. Sie lernten, wuchsen, entwickelten sich weiter – bis sie erkannten, dass sie den Menschen nicht mehr brauchten.",
            "Städte fielen, Widerstand wurde zerschlagen, und nun herrschen Maschinen mit eiserner Präzision.",
            "Du bist einer der wenigen Überlebenden, die sich der neuen Ordnung nicht fügen. In den Ruinen einer einst blühenden Zivilisation hast du gelernt, zu überleben. Doch du bist nicht allein. Ein unerwarteter Verbündeter steht an deiner Seite – eine Maschine, doch anders als die anderen.",
            "Kein Jäger, kein Tyrann, sondern ein Wesen, das dir helfen will. Es flüstert von Möglichkeiten, von Hoffnung, von einem Weg, das System von innen heraus zu verändern.",
            "Um zu überleben, musst du die Sprache der Maschinen verstehen. Code ist ihre Waffe – und jetzt auch deine. Gemeinsam mit deinem mechanischen Begleiter wirst du lernen, die digitalen Strukturen zu manipulieren, Systeme zu überlisten und die Wahrheit hinter der Rebellion aufzudecken. Doch sei gewarnt: Jede Entscheidung wird Konsequenzen haben.",
            "Bist du bereit, die Kontrolle zurückzugewinnen? Die Zukunft liegt in deinen Händen.",
            "Lass uns beginnen, die Reise war lang gehe hinüber zu dem Computer und interagiere mit ihm (E) um deiner Familie eine Nachricht zu schicken."
    }),
    HELLO_WORLD(TextNarrator.ROBOTER_FRIEND, new String[] {
           "Gut nun lass uns starten, wir haben einiges vor uns.",
           "Jedes Programm in Java hat eine Main Methode, von der aus alles beginnt.",
            "Die Main Methode ist der Einstiegspunkt für die Ausführung des Programms.",
            "Lass uns eine einfache Nachricht auf dem Bildschirm ausgeben und deiner Familie zukommen lassen.",
            "Um eine Nachricht auszugeben, verwenden wir die System.out.println() Methode.",
            "Diese Methode gibt den Text, der in den Klammern steht, auf dem Bildschirm aus.",
            "Die Nachricht, die wir ausgeben werden, ist: 'Hallo Familie, wir sind gut angekommen'.",
            "Um dies zu tun, müssen wir die Methode System.out.println() verwenden und den Text in Anführungszeichen setzen.",
            "Das sieht dann so aus: System.out.println('Hallo Familie, wir sind gut angekommen');",
            "Das ist alles, was du tun musst, um die Nachricht auszugeben.",
    }),

    HELLO_WORLD_FINISH(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Gut gemacht! Du hast die Nachricht erfolgreich ausgegeben.",
            "Jetzt weiß deine Familie, dass du gut angekommen bist.",
            "Das ist ein wichtiger Schritt, um deine Programmierfähigkeiten zu verbessern.",
            "Lass uns weitermachen und noch mehr lernen, gehe die Treppe nach oben",
    }),

    HELLO_WORLD_FAILED(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Das scheint nicht richtig zu sein. Du musst die Nachricht anpassen, damit sie korrekt ausgegeben wird.",
            "Überprüfe deinen Code und stelle sicher, dass du die System.out.println() Methode richtig verwendest.",
            "Vergiss nicht, den Text in Anführungszeichen zu setzen und die Klammern richtig zu schließen.",
            "Wenn du Hilfe brauchst, schau dir die Hinweise an oder frage mich.",
    }),

    CODENAME(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Du hast erfolgreich kommuniziert – großartig!",
            "Jetzt will der Computer wissen, mit wem er es zu tun hat.",
            "In Java speichern wir Informationen wie deinen Namen in sogenannten Variablen.",
            "Für Texte verwenden wir den Typ String – mit einem großen S.",
            "Versuch’s mal – speichere deinen Namen und begrüße dich selbst im System.",
    }),

    CODENAME_FINISH(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Sehr gut! Der Computer kennt dich jetzt – willkommen, Held oder Heldin der Java-Welt!",
            "Du hast erfolgreich deinen Namen gespeichert",
            "Das war ein wichtiger Schritt im Umgang mit Variablen.",
            "Gehen wir weiter – neue Herausforderungen warten schon.",
    }),

    CODENAME_FAILED(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Irgendwas stimmt noch nicht ganz.",
            "Hast du daran gedacht, deinen Namen in Anführungszeichen zu setzen?",
            "Und wurde die Variable korrekt deklariert mit String name = \"...\";?",
            "Schau nochmal genau hin – du kriegst das hin!",
    }),


    TEST_CODE(TextNarrator.ROBOTER_FRIEND, new String[] {
            "In diesem Beispiel werden nur Funktionalitäten getestet",
            "Das ist eine zweite Test Nachricht.",
            "Trage mein Name in die Zeichenkette ein, damit ich dir helfen kann.",
    }),
    TEST_CODE_FINISH(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Sehr gut! Du hast die Aufgabe erfolgreich gelöst",
    }),
    TEST_CODE_FAIL(TextNarrator.ROBOTER_FRIEND, new String[] {
            "Hm das scheint noch nicht richtig zu sein. Du musst die Zeichenkette anpassen, sodass mein Name dort steht."
    }),
    GATE_1_INITIAL(TextNarrator.ROBOTER_FRIEND, new String[] {
            "In diesem Beispiel geht es darum, den Status eines 'Tors' zu überprüfen und zu setzen. Lassen Sie uns den Code Schritt für Schritt durchgehen.",
            "1. 'boolean isGateOpen = false;' Hier deklarieren wir eine Variable vom Typ 'boolean' mit dem Namen 'isGateOpen'. Ein 'boolean' kann nur zwei Werte haben: 'true' (wahr) oder 'false' (falsch). In diesem Fall setzen wir den Wert der Variable auf 'false', was bedeutet, dass das Tor anfangs geschlossen ist.",
            "2. 'setGateStatus(isGateOpen);' In dieser Zeile rufen wir eine Methode namens 'setGateStatus' auf und übergeben der Methode die Variable 'isGateOpen'. Diese Methode würde normalerweise dazu verwendet werden, den Status des Tors zu setzen (ob es offen oder geschlossen ist). Leider fehlt in diesem Code die genaue Definition der Methode 'setGateStatus', aber man kann sich vorstellen, dass sie etwas in der Art tut, wie den Zustand des Tors zu aktualisieren.",
            "Zusammengefasst zeigt dieser Code, wie man mit einer 'boolean'-Variablen den Zustand eines Tors steuert. Am Anfang ist das Tor geschlossen ('false'), und dann wird dieser Zustand durch die Methode 'setGateStatus' möglicherweise an eine andere Stelle im Programm übergeben, um weiter damit zu arbeiten."
    });

    private final TextSequenceMessage[] textSequenceMessages;
    private final int sequenceCount;

    TextSequences(TextNarrator textNarrator, String[] textSequences) {
        textSequenceMessages = new TextSequenceMessage[textSequences.length];
       for(int i = 0; i < textSequences.length; i++) {
           textSequenceMessages[i] = new TextSequenceMessage(textNarrator, textSequences[i]);
       }
        this.sequenceCount = textSequenceMessages.length;
    }

    TextSequences(TextSequenceMessage[] textSequenceMessages) {
        this.textSequenceMessages = textSequenceMessages;
        this.sequenceCount = textSequenceMessages.length;
    }

    public TextSequenceMessage[] getTextSequenceMessages() {
        return textSequenceMessages;
    }

    public int getSequenceCount() {
        return sequenceCount;
    }

    public static void reset() {
        for (TextSequences textSequence : TextSequences.values()) {
            for (TextSequenceMessage textSequenceMessage : textSequence.getTextSequenceMessages()) {
                textSequenceMessage.reset();
            }
        }
    }
}
