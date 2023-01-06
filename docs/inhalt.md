# Paketinhalt

1. [Ordnerstruktur](#ordnerstruktur)

## Ordnerstruktur

Das Projekt ist nach dem MVC-Paradigma angelegt. Das bedeutet, dass alle
Programmaufgaben in die Schichten **Model**, **View** und **Controller**
aufgeteilt sind.

Jeder Schicht wird eine bestimmte Aufgabe zugesprochen. Aber auch die
Methoden sind auf die entsprechenden Klassen aufgeteilt. SO wird der
Code nicht nur übersichtlicher und lesbarer, sondern auch Fehlersuche
und die Programmentwicklung wird vereinfacht.

````
/
|__docs/
|
|__src/
|   |__main/
|   |   |__java/
|   |   |   |__controller/
|   |   |   |__core/
|   |   |   |   |__controller/
|   |   |   |   |__global/
|   |   |   |   |__model/
|   |   |   |   |__view/
|   |   |   |   
|   |   |   |__entity/
|   |   |   |__repository/
|   |   |   |__view/
|   |   |   |__Main.java
|   |   |
|   |   |__resources/
|   |       |__config/
|   |       |__icons/
|   |       |__META-INF/
|   |   
|   |__test/
|
|__pom.xml
|__README.md
````
