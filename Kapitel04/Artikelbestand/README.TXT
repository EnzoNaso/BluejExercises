BlueJ-Projekt "Artikelbestand".
Autoren: David J. Barnes und Michael Kölling

Dieses Projekt ist Teil des Zusatzmaterials zum Buch

   Java lernen mit BlueJ - eine Einführung in die 
   objektorientierte Programmierung, 5. Auflage
   David J. Barnes und Michael Kölling
   Pearson Education Deutschland, 2012

Zweck des Projektes
===================
Dieses Projekt soll das Verständnis von Objektsammlungen
vertiefen, inklusive der Benutzung von Schleifen, Iteratoren
und dem Cast-Operator. Die Übungen können bearbeitet werden,
nachdem Abschnitt 4.9 von Kapitel 4 abgeschlossen wurde.


Die Aufgabe
===========
Eine Firma verwaltet den Bestand der Artikel, die sie
verkauft. Ein Objekt der Klasse Bestandsverwalter hält
eine Liste beliebiger Länge mit Artikel-Objekten. Ihre
Aufgabe ist es, die Rumpfimplementierung der Klasse
Bestandsverwalter zu vervollständigen.

Die Klasse BestandDemo soll demonstrieren, wie Objekte der
Klassen Bestandsverwalter und Artikel benutzt werden können.
Sie können ein Objekt der Klasse BestandDemo interaktiv
erzeugen und seine Methode demo aufrufen. Während Sie die
Klasse Bestandsverwalter weiterentwickeln, sollte diese Demo
die zunehmende Funktionalität demonstrieren.

Die Klasse Bestandsverwalter
============================
Die Klasse Bestandsverwalter benutzt ein LinkedList-Objekt, um
null oder mehr Artikel zu speichern. Ihre Methode neuerArtikel
fügt einen neuen Artikel in die Sammlung ein. Die folgenden
Methoden müssen noch implementiert werden: aufnehmen,
findeArtikel, alleArtikelAnzeigen und mengeImBestand.

+ Die Methode aufnehmen soll den Artikel mit der gegebenen
Nummer finden und dann an diesem die Methode erhoeheBestand
aufrufen.

+ Die Methode findeArtikel soll die Sammlung durchsuchen nach
einem Artikel, dessen Nummer dem Parameter dieser Methode
entspricht. Wenn ein passender Artikel gefunden wurde, dann
soll der Artikel als Ergebnis des Aufrufs zurückgeliefert
werden. Wenn kein passender Artikel gefunden wurde, soll die
Methode eine null-Referenz zurückliefern.

+ Die Methode alleArtikelAnzeigen soll über die Liste der
Artikel iterieren und für jeden das Aufrufergebnis seiner
Methode toString() ausgeben.

+ Die Methode mengeImBestand soll einen Artikel in der
Sammlung mit passender Nummer finden und für diesen die
aktuelle Menge im Bestand als Methodenergebnis liefern.
Wenn kein Artikel mit einer passenden Nummer gefunden
wurde, soll Null geliefert werden.

Die Klasse Artikel
==================
Diese Klasse ist vorgegeben und Sie sollten keine
Veränderungen an ihr vornehmen müssen.

Jeder Artikel, den die Firma anbietet, ist repräsentiert
durch eine Instanz der Klasse Artikel, die die Artikelnummer,
den Artikelnamen und den aktuellen Bestand dieses Artikels
verwaltet. Die Klasse Artikel definiert die Methode aufnehmen,
mit der Anlieferungen dieses Artikels in den Bestand
registriert werden. Die Methode verkaufeEinen zeichnet auf,
dass dieser Artikel einmal verkauft wurde, indem der
Bestandszähler um Eins dekrementiert wird.


Schrittweise Implementierung
============================
Diese Aufgabe wurde in einzelne Abschnitte zerlegt, um Ihnen die
Umsetzung der endgültigen Version in kleinen Schritten zu
ermöglichen. Wir empfehlen, dass Sie nach jedem Abschnitt das
Programm übersetzen und ausführen, um die Richtigkeit Ihrer
Änderungen zu überprüfen.

1. Implementieren Sie die Methode alleArtikelAnzeigen, um 
sicher zu stellen, dass Sie über die Sammlung der Artikel
iterieren können. Geben Sie jeden Artikel einfach über System.out
aus. Die Benutzung eines Iterators ist der bevorzugte Ansatz,
aber Sie können auch eine int-Variable als Index verwenden, wenn
Sie das besser verständlich finden.

2. Implementieren Sie die Methode findeArtikel. Diese
unterscheidet sich von der Methode alleArtikelAnzeigen dadurch,
dass nicht notwendigerweise alle Artikel betrachtet werden
müssen, bevor der passende gefunden wird. Wenn beispielsweise
der erste Artikel bereits die richtige Nummer hat, dann kann
die Iteration früh beendet und der Artikel zurückgeliefert
werden. Andererseits ist es möglich, dass es keinen Artikel
mit passender Nummer gibt. Dann wird die gesamte Sammlung
durchsucht, ohne dass ein Artikel zurückgeliefert werden kann.
In diesem Fall sollte eine null-Referenz zurückgeliefert
werden.

Wenn Sie nach einem passenden Artikel suchen, werden Sie
die Methode gibNummer an den Artikeln aufrufen müssen.
Dazu müssen Sie den Cast-Operator einsetzen, wenn Sie ein
Element aus der Liste bearbeiten wollen. Sie können sich
über den Cast-Operator informieren, indem Sie die Diskussion
des Projektes Auktion in Kapitel 4 lesen. Insbesondere
Abschnitt 4.9.3 beschäftigt sich mit dem Cast-Operator.

3. Implementieren Sie die Methode mengeImBestand. Diese
ist relativ einfach umzusetzen, sobald die Methode
findeArtikel fertig ist. Beispielsweise kann mengeImBestand
die Methode findeArtikel für die Suche aufrufen und am
Ergebnis dann die Methode gibBestand aufrufen. Achten Sie
jedoch auf Artikel, die nicht gefunden werden können.

4. Implementieren Sie die Methode aufnehmen nach einem 
ähnlichen Schema wie bei der Methode mengeImBestand.

Freiwillige Zusatzaufgaben
==========================
+ Implementieren Sie eine Methode im Bestandsverwalter,
die alle Artikel ausgibt, deren Bestand unter einem 
gegebenen Wert liegt (der als Parameter an die Methode
übergeben wird).

+ Modifizieren Sie die Methode neuerArtikel so, dass
ein neuer Artikel nicht in die Liste eingefügt werden
kann, wenn diese bereits einen Artikel mit derselben
Nummer enthält.

+ Fügen Sie dem Bestandsverwalter eine Methode hinzu,
die einen Artikel anhand des Namens, nicht über seine
Nummer findet.

    public Artikel findeArtikel(String name)

Um dies implementieren zu können, müssen Sie wissen, dass
zwei Strings s1 und s2 mit dem booleschen Ausdruck

    s1.equals(s2)
 
auf Gleichheit untersucht werden können. Mehr zu diesem
Thema finden Sie in Abschnitt 5.3.3.
