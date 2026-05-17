SPIG - Sustav za prijavu igrača na utakmice
Ovaj projekt je razvijen za potrebe treće zadaće iz kolegija na temu "Ugradnja odabrane arhitekture". Sustav se sastoji od Spring Boot backenda s H2 in-memory bazom podataka i React (Vite) frontenda koji komuniciraju preko REST API-ja.

Preduvjeti za pokretanje
Prije pokretanja aplikacije, potrebno je imati instalirano sljedeće:

Java 21 (JDK 21) - Verzija se može provjeriti naredbom java -version.

Node.js (v18 ili noviji) - Verzija se može provjeriti naredbom node -v.

Projekt je podijeljen u dvije odvojene mape: spig-backend i spig-frontend.

1. Pokretanje Backenda (Spring Boot)
Backend koristi in-memory bazu podataka (H2), što znači da nema potrebe za instalacijom ili konfiguracijom vanjske baze. Baza se automatski kreira i puni početnim podacima čim se aplikacija pokrene.

Otvorite terminal i uđite u mapu spig-backend:

Bash
cd spig-backend
Pokrenite aplikaciju preko Gradle wrappera:

Bash
   # Za Windows:
   .\gradlew.bat bootRun
   
   # Za macOS/Linux:
   ./gradlew bootRun
Backend će se pokrenuti na portu 8080.

2. Pokretanje Frontenda (React + Vite)
Frontend je napravljen pomoću Vite-a. Za pokretanje je potrebno prvo instalirati sve pakete i tek onda pokrenuti razvojni server.

Otvorite novi terminal i uđite u mapu spig-frontend:

Bash
cd spig-frontend
Instalirajte sve potrebne ovisnosti (ovo treba napraviti samo prije prvog pokretanja):

Bash
npm install
Pokrenite razvojni server:

Bash
npm run dev
Frontend će se podići na adresi http://localhost:5173.

3. Pokretanje testova
U sklopu projekta napisani su jedinični (Unit) testovi za sve slojeve aplikacije (Controller, Service, Repository) te integracijski testovi koji provjeravaju ispravnu međusobnu povezanost tih slojeva.

Za pokretanje testova:

Pozicionirajte se u mapu spig-backend.

Pokrenite naredbu:

Bash
   # Za Windows:
   .\gradlew.bat test
   
   # Za macOS/Linux:
   ./gradlew test

4. Pristup bazi podataka (H2 Console)
Pošto je baza u memoriji, podaci se mogu vizualno pregledavati kroz web sučelje dok god je backend upaljen.

U pregledniku otvorite: http://localhost:8080/h2-console

U polja unesite sljedeće podatke:

JDBC URL: jdbc:h2:mem:spigdb

User Name: sa

Password: (ostavite prazno)

Kliknite na Connect.

Implementirane funkcionalnosti:
Kompletan CRUD nad entitetima.

Master-Detail struktura (prikaz utakmica i pripadajućih prijava igrača).

Sučelje sa šifrarnikom za sportove.

Validacija kapaciteta utakmice na razini poslovne logike (automatsko prebacivanje na LISTA_CEKANJA ako je utakmica već puna).

Pokrivene funkcionalnosti kroz izolirane jedinične i integracijske testove.