# Verschlossene Tore

## Level 1

Einfaches setzen eines Wahrheitsparameters

### Aufgabe:

```java
boolean istTorOffen = false; //Gibt an ob das Tor offen ist
```

### Lösung:

```java
boolean istTorOffen = true; //Gibt an ob das Tor offen ist
```

## Level 2

Verstehen vom Programmcode - Passwort für Tor herausfinden

### Aufgabe

```java
String passwort = "";

public boolean istPasswortKorrekt(String passwort) {
  if(passwort.length() != 9) {
    return false;
  }

  if(!passwort.contains("Java")) {
    return false;
  }

  if(passwort.charAt(0) != 'A') {
      return false;
  }
  
  if(!passwort.substring(5,9).equals("Game")) {
    return false;
  }

  return true;
}
```

### Lösung

```java
String passwort = "AJavaGame";

public boolean istPasswortKorrekt(String passwort) {
  if(passwort.length() != 9) {
    return false;
  }

  if(!passwort.contains("Java")) {
    return false;
  }

  if(passwort.charAt(0) != 'A') {
      return false;
  }
  
  if(!passwort.substring(5,9).equals("Game")) {
    return false;
  }

  return true;
}
```


## Level 3

Programmcode schreiben - Das Tor hat einen Sicherheitsmechanismus es öffnet sich nur, wenn die richtige Index übergeben wird 

Dem Tor wird folgendes als Beispiel übergeben: String[] sicherheitsSchluesell = ["a6khCrMk", "3D6U9wMq", 2gc633PM"", "kGdVQujX", "TOR_XbTvFyGv_KEY", "FJHmVvhs"].
Das Array ist von unbekannter Länge und wird zufällig erstellt nur ein Index enthält den richtigen Schlüssel der mit "TOR_" anfängt und mit "_KEY" aufhört. Der Inhalt zwischen den beiden Schlüsselwörtern muss dann an das Tor übergeben werden.

### Aufgabe

```java
String passwort = "";

public String findePasswort(String[] sicherheitsSchluesell) {
  //TODO 
}

/*
* Überprüft die Korrektheit des angegeben Passworts.
* Sollte es korrekt sein öffnet sich das Tor
*/
public boolean istPasswortKorrektV2(String passwort);
```

### Lösung

```java
String passwort = "";

public String findePasswort(String[] sicherheitsSchluesell) {
  for (String element : array) {
      if (element.startsWith("TOR_") && element.endsWith("_KEY")) {
          // Extrahiere den Schlüssel zwischen "TOR_" und "_KEY"
          return element.substring(4, element.length() - 4);
      }
  }
  return ""; // Kein gültiger Schlüssel gefunden
}

/*
* Überprüft die Korrektheit des angegeben Passworts.
* Sollte es korrekt sein öffnet sich das Tor
*/
public boolean istPasswortKorrektV2(String passwort);
```

## Level 4

Programmcode schreiben - Das Tor hat ein elektrisches Schloss, was sich nur öffnet wenn der richtige Schlüssel übergeben wird.
Der richtige Schlüssel befindet sich in einem gegebenen Array von Wörtern und ist ein Palindrom (ABBA, RADAR).

Dem Tor wird folgendes als Beispiel übergeben: String[] wörter = {"abc", "race car", "level", "word"};

### Aufgabe

```java
public boolean öffneTor(String[] wörter) {
  // TODO: Überprüfe, ob eines der Wörter ein Palindrom ist
}

```

### Lösung

```java
String[] wörter = {"abc", "race car", "level", "word"};

public boolean öffneTor(String[] wörter) {
    for (String wort : wörter) {
        if (istPalindrom(wort)) {
            return wort;
        }
    }
    return "";
}

public boolean istPalindrom(String wort) {
    int links = 0;
    int rechts = wort.length() - 1;

    while (links < rechts) {
        if (wort.charAt(links) != wort.charAt(rechts)) {
            return false;
        }
        links++;
        rechts--;
    }
    return wort;
}
```
## Level 5

Objektorientierung – Die magischen Türen
Ein Gebäude hat mehrere Türen, die nur mit einem speziellen Schlüssel geöffnet werden können. Jede Tür ist ein eigenes Objekt mit einer Farbe und einem Status (offen oder geschlossen).

### Aufgabe

```java
class Tuer {
  // TODO: Definiere eine Klasse Tür mit Attributen für Farbe und Status
}

```

### Lösung

```java
class Tuer {
    String farbe;
    boolean istOffen;

    Tuer(String farbe) {
        this.farbe = farbe;
        this.istOffen = false;
    }

    void oeffneTuer() {
        this.istOffen = true;
    }
}
```

## Level 6

Vererbung – Die Roboterwächter
Der Spieler trifft auf verschiedene Roboterwächter. Manche können laufen, andere fliegen. Er muss die richtigen Klassenstrukturen nutzen, um die Fähigkeiten der Roboter abzubilden.

### Aufgabe

```java
class Roboter {
  // TODO: Definiere eine Basisklasse Roboter und leite spezialisierte Klassen LaufenderRoboter und FliegenderRoboter davon ab
}

```

### Lösung

```java
class Roboter {
    String name;

    Roboter(String name) {
        this.name = name;
    }
}

class LaufenderRoboter extends Roboter {
    LaufenderRoboter(String name) {
        super(name);
    }

    void laufen() {
        System.out.println(name + " läuft...");
    }
}

class FliegenderRoboter extends Roboter {
    FliegenderRoboter(String name) {
        super(name);
    }

    void fliegen() {
        System.out.println(name + " fliegt...");
    }
}
```

## Level 7

Exceptions – Sicherheitscode validieren
Der Spieler muss einen Sicherheitscode eingeben. Falls der Code ungültig ist, soll eine InvalidCodeException geworfen werden.

### Aufgabe

```java
// TODO: Implementiere eine Methode zur Validierung des Codes
public void validateCode(String code) throws InvalidCodeException {
    // Überprüfe, ob der Code eine bestimmte Länge hat
}
```

### Lösung

```java
class InvalidCodeException extends Exception {
    public InvalidCodeException(String message) {
        super(message);
    }
}

public void validateCode(String code) throws InvalidCodeException {
    if (code.length() != 6) {
        throw new InvalidCodeException("Der Code muss genau 6 Zeichen lang sein!");
    }
}
```

## Level 8

Threads – Zeitlimit für das Rätsel
Der Spieler hat nur 10 Sekunden Zeit, um ein Rätsel zu lösen. Ein separater Thread zählt die Zeit herunter.

### Aufgabe

```java
class TimerThread extends Thread {
// TODO: Implementiere einen Timer-Thread, der nach 10 Sekunden eine Nachricht ausgibt
}
```

### Lösung

```java
class TimerThread extends Thread {
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("Zeit abgelaufen!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Starten des Timers
TimerThread timer = new TimerThread();
timer.start();
```
