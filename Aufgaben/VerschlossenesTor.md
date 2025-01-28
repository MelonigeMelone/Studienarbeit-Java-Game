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


