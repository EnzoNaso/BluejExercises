Projekt: Mail-System
Autoren: David Barnes und Michael Kölling

Dieses Projekt ist Teil des Zusatzmaterials zum Buch

   Java lernen mit BlueJ - eine Einführung in die 
   objektorientierte Programmierung, 5. Auflage
   David J. Barnes und Michael Kölling
   Pearson Education Deutschland, 2012

Dieses Projekt simuliert ein simples Email-System. MailClients simulieren
die Email-Programme von unterschiedlichen Benutzern. Wenn zwei oder
mehr MailClients erzeugt werden, können sich diese gegenseitig Nachrichten
schicken. (Dies ist nur eine Simulation: Es werden hier keine echten Emails
versendet. Alle Nachrichten werden nur zwischen den Exemplaren von
EmailClient in diesem Projekt ausgetauscht.)

Startpunkt: Um dieses Projekt zu benutzen erzeugen Sie
 - ein Exemplar von MailServer;
 - ein Exemplar von MailClient; Diesem MailClient muss der MailServer
   als Parameter übergeben werden, und es muss ein Name für den Benutzer
   des Clients vergeben werden.
 - ein weiteres Exemplar von MailClient mit dem selben MailServer
   und einem anderen Benutzernamen.
   
Weiterhin
 - benutzen Sie die Operation 'sendeNachricht' eines der MailClients, 
   um dem anderen Client eine Nachricht zu schicken;
 - benutzen Sie die Operation 'naechsteNachrichtAusgeben' an dem anderen
   Client, um die empfangene Nachricht anzuzeigen.

Mit diesem Projekt soll die Interaktion zwischen Objekten demonstriert werden.
Es enthält externe Operationsaufrufe (Aufrufe von einem Objekt an ein anderes)
und Anweisungen zur Objekterzeugung.

Der Debugger kann benutzt werden, um die Interaktionen zu verfolgen.
