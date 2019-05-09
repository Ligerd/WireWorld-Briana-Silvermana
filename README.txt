# WireWorld-Briana-Silvermana
Podstawowe funkcje programu:

- wczytywanie do programu początkowej konfiguracji z pliku w wybranym formacie,

- przeprowadzenie zadanej liczby generacji,

- wizualizacja on-line,

- zapisywanie bieżącej generacji do pliku (który może zostać potem wczytany).


Zasady działania:
Komórka może znajdować się w jednym z czterech stanów:

   Pusta,
   Głowa elektronu,
   Ogon elektrony,
   Przewodnik.

Zwykle przymuje się następujące kolory stanów: biały, czerwony, żółty, czarny.

Kolejne generacje budowane są z wykorzystaniem zestawu pięciu zasad:

   Komórka pozostaje Pusta, jeśli była Pusta.
   Komórka staje się Ogonem elektronu, jeśli była Głową elektronu.
   Komórka staje się Przewodnikiem, jeśli była Ogonem elektronu.
   Komórka staje się Głową elektronu tylko wtedy, gdy dokładnie 1 lub 2 sąsiadujące komórki są Głowami Elektronu.
   Komórka staje się Przewodnikiem w każdym innym wypadku.

W WireWorld stosuje się sąsiedztwo Moore'a.
