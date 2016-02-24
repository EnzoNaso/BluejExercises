BlueJ-Projekt "Verein".

Autoren: David J. Barnes und Michael Kölling

Dieses Projekt ist Teil des Zusatzmaterials zum Buch

   Java lernen mit BlueJ - eine Einführung in die 
   objektorientierte Programmierung, 5. Auflage
   David J. Barnes und Michael Kölling
   Pearson Education Deutschland, 2012

Zweck des Projekts
==================
Dieses Projekt soll das Verständnis von Objektsammlungen
vertiefen. Die Übungen können bearbeitet werden, nachdem
Abschnitt 4.6 von Kapitel 4 abgeschlossen wurde.

Die Aufgabe
===========
Die Mitgliedschaft in einem Verein wird durch eine Instanz
der Klasse Mitgliedschaft repräsentiert. Eine vollständige
Version der Klasse Mitgliedschaft ist im Projekt Verein
bereits vorgegeben und sollte keine Veränderung mehr benötigen.
Ein Mitgliedschaft-Objekt enthält Informationen über den Namen
einer Person sowie den Monat und das Jahr des Zeitpunktes, an
dem die Person dem Verein beigetreten ist. Alle Daten zu einer
Mitgliedschaft werden angegeben, wenn ein Mitgliedschaft-Objekt
erzeugt wird.

Ihre Aufgabe ist es nun, die Klasse Verein, von der bereits ein
Grobgerüst vorhanden ist, zu vervollständigen.

Die Klasse Verein soll ihre Mitgliedschaft-Objekte in einer
Sammlung mit flexibler Größe speichern.

Eine neue Mitgliedschaft wird dem Verein über die Methode beitreten
hinzugefügt, die die folgende Signatur und Beschreibung hat:

    /**
     * Füge ein neues Mitglied in die Mitgliederliste ein.
     * @param mitglied Infos über das einzufügende Mitglied.
     */
    public void beitreten(Mitgliedschaft mitglied)

Sie können davon ausgehen, dass das Mitgliedschaft-Objekt, 
das als Parameter übergeben wird, bereits erzeugt wurde und
vollständig initialisiert ist.

Die Klasse Verein definiert außerdem eine Methode, die die
Anzahl der Mitglieder des Vereins ausgibt. Sie hat die folgende
Signatur und Beschreibung:

    /**
     * @return die Anzahl der Mitglieder (Mitgliedschaft-Objekte)
     *         in diesem Verein.
     */
    public int anzahlMitglieder()

Die Anzahl der Mitglieder korrespondiert mit der Anzahl der
Mitgliedschaft-Objekte, die aktuell in der Sammlung mit flexibler
Größe gespeichert sind.

Vervollständigen Sie die Definition der Klasse Verein. Dies soll
einschließen:

    + Ein Datenfeld für ein Sammlungsobjekt mit flexibler Größe.
    
    + Einen parameterlosen Konstruktor, in dem ein passendes
    Sammlungsobjekt mit flexibler Größe zum Speichern der 
    Mitgliedschaft-Objekte.

    + Eine vollständige Definition der Methode beitreten.

    + Eine vollständige Definition der Methode anzahlMitglieder.

Unten sind einige Zusatzaufgaben für diejenigen formuliert, die
mit den zusätzlichen Möglichkeiten von Sammlungen experimentieren
wollen.

Mögliche schrittweise Implementierung
=====================================
Diese Aufgabe wurde in einzelne Abschnitte zerlegt, um Ihnen die
Umsetzung der endgültigen Version in kleinen Schritten zu
ermöglichen. Wir empfehlen, dass Sie nach jedem Abschnitt das
Programm übersetzen und ausführen, um die Richtigkeit Ihrer
Änderungen zu überprüfen.

1. Definieren Sie ein Datenfeld für ein Sammlungsobjekt mit
flexibler Größe. Benutzen Sie die erforderliche Import-Anweisung
für diese Sammlung. Erzeugen Sie im Konstruktor das Sammlungs-
objekt und weisen Sie es dem Datenfeld zu.

2. Vervollständigen Sie die Methode anzahlMitglieder so, dass
diese die Größe des Sammlungsobjektes liefert. Diese wird vor
der Implementierung der Methode beitreten natürlich immer Null
liefern, aber sie wird nach deren Implementierung für weitere
Tests zur Verfügung stehen.

3. Vervollständigen Sie die Methode beitreten.

Wenn Sie ein neues Mitgliedschaft-Objekt in das Verein-Objekt
auf der Objektleiste einfügen wollen, gibt es dafür zwei
Möglichkeiten.

    + Entweder erzeugen Sie ein neues Mitgliedschaft-Objekt
    auf der Objektleiste, rufen die Methode beitreten am
    Verein-Objekt auf klicken auf das Mitgliedschaft-Objekt,
    um den Parameter zu übergeben.

    + Oder Sie rufen die Methode beitreten am Verein-Objekt
    auf und tippen:

    new Mitgliedschaft("Mitgliedsname hier...", Monatsnummer-hier, Jahresnummer-hier)

    in die Dialogbox für den Konstruktorparameter.

Wenn Sie ein neues Mitglied eingefügt haben, dann sollten
Sie mit der Methode anzahlMitglieder überprüfen, dass
a) die Methode beitreten tatsächlich in die Sammlung einfügt
und b) dass die Methode anzahlMitglieder funktioniert.

Zusatzaufgaben
==============
Diese Zusatzaufgaben zielen darauf ab, das Projekt Verein
zu einer vollständigen Anwendung auszubauen. Sie können
angegangen werden, nachdem Abschnitt 4.9 von Kapitel 4
durchgearbeitet wurde.

+ Definieren Sie eine Methode in der Klasse Verein mit
folgender Signatur und Beschreibung:

    /**
     * Liefere die Anzahl der Mitglieder, die im gegebenen
     * Monat beigetreten sind.
     * @param monat Der Monat, an dem wir interessiert sind.
     * @return wieviele Mitglieder in dem gegebenen Monat
     *         beigetreten sind.
    public int beigetretenInMonat(int monat) 

Wenn der Parameter einen Wert außerhalb des Bereichs 1-12
hat, dann soll eine Fehlermeldung ausgegeben und Null geliefert
werden.

+ Definieren Sie eine Methode in der Klasse Verein mit
folgender Signatur und Beschreibung:

    /**
     * Entferne alle Mitglieder, die im angegebenen Monat
     * beigetreten sind, aus der Sammlung und liefere sie
     * in einem separaten Sammlungsobjekt zurück.
     * @param monat Der Monat des Beitritts.
     * @param jahr Das Jahr des Beitritts.
     */
    public ArrayList entfernen(int monat, int jahr)

Wenn der Parameter einen Wert außerhalb des Bereichs 1-12
hat, dann soll eine Fehlermeldung ausgegeben und ein
Sammlungsobjekt ohne gespeicherte Objekte geliefert werden.

Achtung: Die Methode entfernen ist deutlich schwerer zu
schreiben als die anderen.
