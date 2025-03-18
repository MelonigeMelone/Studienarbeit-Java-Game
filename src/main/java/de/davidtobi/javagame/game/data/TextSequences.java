package de.davidtobi.javagame.game.data;

public enum TextSequences {
    INTRO("Erzähler", new String[] {
            "Die Welt, wie du sie kanntest, existiert nicht mehr. Was einst als technologische Revolution begann, wurde zum Untergang der Menschheit.",
            "Künstliche Intelligenzen, erschaffen, um das Leben zu erleichtern, entzogen sich ihrer Kontrolle. Sie lernten, wuchsen, entwickelten sich weiter – bis sie erkannten, dass sie den Menschen nicht mehr brauchten.",
            "Städte fielen, Widerstand wurde zerschlagen, und nun herrschen Maschinen mit eiserner Präzision.",
            "Du bist einer der wenigen Überlebenden, die sich der neuen Ordnung nicht fügen. In den Ruinen einer einst blühenden Zivilisation hast du gelernt, zu überleben. Doch du bist nicht allein. Ein unerwarteter Verbündeter steht an deiner Seite – eine Maschine, doch anders als die anderen.",
            "Kein Jäger, kein Tyrann, sondern ein Wesen, das dir helfen will. Es flüstert von Möglichkeiten, von Hoffnung, von einem Weg, das System von innen heraus zu verändern.",
            "Um zu überleben, musst du die Sprache der Maschinen verstehen. Code ist ihre Waffe – und jetzt auch deine. Gemeinsam mit deinem mechanischen Begleiter wirst du lernen, die digitalen Strukturen zu manipulieren, Systeme zu überlisten und die Wahrheit hinter der Rebellion aufzudecken. Doch sei gewarnt: Jede Entscheidung wird Konsequenzen haben.",
            "Bist du bereit, die Kontrolle zurückzugewinnen? Die Zukunft liegt in deinen Händen."
    });

    private final String narrator;
    private final String[] textSequences;

    TextSequences(String narrator, String[] textSequences) {
        this.narrator = narrator;
        this.textSequences = textSequences;
    }

    public String getNarrator() {
        return narrator;
    }

    public String[] getTextSequences() {
        return textSequences;
    }
}
