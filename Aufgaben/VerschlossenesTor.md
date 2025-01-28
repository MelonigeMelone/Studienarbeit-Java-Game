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

Verstehen vom Programmcode

### Aufgabe

```java
String passwort = "";

public boolean istPasswortKorrekt() {
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

public boolean istPasswortKorrekt() {
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

Programmcode schreiben
