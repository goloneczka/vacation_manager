# Generowanie Schemy
Poniższa linijka służy do wygenerowania schemy bazy danych w jooq-u. Musisz podać następujące parametry: 
* url do bazy danych. Przykłdowe:
  * jdbc:postgresql:database
  * jdbc:postgresql://host/database
  * jdbc:postgresql://host:port/database
* user
* hasło

Dodatkowo należy przejsc do katalogu 'database'
````
mvn jooq-codegen:generate -Djooq.codegen.jdbc.url= -Djooq.codegen.jdbc.user= -Djooq.codegen.jdbc.password=
````