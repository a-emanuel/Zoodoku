# Zoodoku
### Anton Emanuel

Zoodoku este o versiune de sudoku in care in loc de numere de la 1 la 9 avem diferite animale. Scopul este acelasi, pentru a castiga 
este necesar ca pe fiecare rand, fiecare coloana si in fiecare partitie de 3x3 animalele sa fie diferite.  

Ca si componente avem Cell, fiecare avand o valoare de la 0 la 9 (0 insemnand ca este necompletata). Valoarea poate fi schimbata (incrementata) prin apasarea de click-stanga pe 
componenta din ui respectiva. Mai avem si Grid ce reprezinta starea jocului la un momentdat, si contine un array multi-dimensional de Cells si un HashMap pentru fiecare subsectiune 
de 3x3 si celulele respective.  

ZoodokuGame: Pentru a forma un nou puzzle generez un puzzle rezolvat (si il retin in solution) si aleg random celule care sa aiba valoarea 0. Grid-ul astfel obtinut il retin in 
initialState, iar la inceputul jocului currentState va lua valoarea lui initialState. Pe parcurs ce jucatorul modifica celule si currentState se va modifica corespunzator.  

SolutionCheck: Pentru a verifica daca solutia (starea curenta) este completa si corecta verificam ca fiecare celula sa aiba o valoare diferita de 0 si pe fiecare linie, coloana si 
subgrup sa nu existe aceeasi valoare de mai multe ori. In urma verificarii aflam daca puzzel-ul este INCOMPLETE, WRONG sau SOLVED.  

GameInterface: Se ocupa cu afisarea elementelor grafice. Ca elemente grafice avem: fereastra, tabla de joc (background + linii), celulele ce isi schimba icon-ul in functie de valoare 
(celulele care sunt hint, care dezvaluie o parte din solutie nu pot fi modificate), si butoanele ce permit verificarea starii actuale (va aparea un pop-up cu starea curenta, pentru 
a-l inchide click-stanga pe pop-up), resetarea jocului (jocul va reveni la starea initiala, inainte ca utilizatorul sa fi modificat vre-o celula), afisarea solutiei si generarea unui nou joc. 
