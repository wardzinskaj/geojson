# NoSql 

## Semestr zimowy 2014/15

## MongoDB a Oracle

Zadania na zaliczenie

Jak się okazało na zajęciach moje importy trwały dużo dłużej niż studentów, których projekty były przeglądane.
Prawie 14 godzin u mnie do kilkunastu minut a nawet kilku minut w przypadku bazy Oracle. W bazie Mongodb było nieco lepiej.

Pytanie - jaki był powód tak długiego importu? Jak widać na jednym z obrazków zużycie procesora było 100%.
Pracowałam na systemie operacyjnym Linux Centos (maszynie wirtualnej). Może należałoby zmienić sprzęt? Albo dostosować tak aby procesor był bardziej przyjazny.

### Jak to wyglądało?

Import do bazy Mongodb przy użyciu mongoimport.
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongodb_import.png"> 
 
Statystyki systemu podczas importu - Mongodb.
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongodb_system.png"> 
 
Zliczenie zaimportowanych rekordów w bazie Mongodb. 
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongo_count.png"> 
 
Import do bazy Oracle przy użyciu sqlldr wraz ze statystykami systemu.
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/oracle_system.png">

Zliczenie zaimportowanych rekordów w bazie Oracle. 
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/oracle_count.png">

Czas importu do bazy Oracle wyniósł 13 godzin i 47 minut podczas gdy do bazy Mongodb tylko 27 minut.
Import do bazy Mongodb zużywał zdecydowanie mniej zasobów procesora oraz mniej pamięci RAM.
W przypadku importu do bazy Oracle obciążenie procesora jest przez większość czasu maksymalne.
Na wirtualnej maszynie jest procesor jednordzeniowy.
W najbliższym czasie spróbuję dostosować nieco maszynę wirtualną i sposób działania bazy tak aby zoptymalizować nieco importy.

=========== 
 
[Program napisany w javie do dzielenia na tagi.](https://github.com/wardzinskaj/geojson/blob/master/Zad1c.java)

Użyty został sterownik  
[mongo-java-driver-2.11.4](http://central.maven.org/maven2/org/mongodb/mongo-java-driver)


Zamiana danych ze Stringa na tagi - program wypisuje czas jaki zajęło przetwarzanie danych, liczbę tagów i liczbę tagów unikalnych.
<img src="https://github.com/wardzinskaj/geojson/blob/master/zamiana_na_tagi_java.PNG"> 

## Import do bazy Oracle - drugie podejście

Po zapoznaniu się z literaturą podjęłam próbę przyspieszenia importu do bazy Oracle. Zmieniłam rozmiar podstawowego, logicznego elementu bazy danych. Zmiana db_block_size z 8192 na 32768. Oto jak to zrobiłam:
 
    ALTER SYSTEM SET DB_32K_CACHE_SIZE=16M SCOPE=SPFILE;

Później restart bazy danych i dalej tworzę tablespace:
 
    CREATE TABLESPACE test DATAFILE '/u01/oradata/TSH1/test.dbf' SIZE 30G BLOCKSIZE 32768;
 
W wyniku takich działań udało się przyspieszyć import z prawie 14 godzin do około 0,5 godziny.

Czas importu do bazy Oracle.

 <img src="https://github.com/wardzinskaj/geojson/blob/master/czas.JPG">
 
Z racji tego, że maszyna wirtualna miała tylko jeden procesor, nie użyłam opcji Parallel Loads zwielokrotniającej proces ładowania.

=======
geojson
=======

var dzakarta = { type: "Point", coordinates: [ 106.859000, -6.156000] }

db.geojson.find( { geometry: { $near: { $geometry: dzakarta } } } ).limit(1)

[wynik1.json](https://github.com/wardzinskaj/geojson/blob/master/wynik1.json)<br>
[wynik1.geojson](https://github.com/wardzinskaj/geojson/blob/master/near1.geojson) 

```
var austrIndo = {
  "type": "Polygon", 
  "coordinates": [
   [
   [99.140625,-45.828799], 
   [99.140625,3.864254], 
   [153.281250,3.864254], 
   [153.281250,-45.82879925],
   [99.140625,-45.828799] 
   	] 
   ] 
  }
  
  ```

db.geojson.find({ geometry: { $geoWithin: { $geometry: austrIndo } } })

[wynik2.json](https://github.com/wardzinskaj/geojson/blob/master/wynik8.json)<br>
[wynik2.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik8.geojson)

```
var papua = {
  "type": "Polygon",
  "coordinates": 
     [
 [154.072800, -6.502700],
 [154.237000, -6.427100],
 [154.321100, -6.442300],
 [154.328300, -6.365800],
 [154.353400, -6.473700],
 [154.458000, -6.484200],
 [154.583100, -6.499300],
 [154.072800, -6.502700],
     ]
   ]
 }
```

db.geojson.find({ geometry: { $geoIntersects: { $geometry: papua } } })

[wynik3.json](https://github.com/wardzinskaj/geojson/blob/master/wynik2.json)<br>
[wynik3.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik2.geojson)

db.geojson.find({ geometry: { $geoWithin: { $geometry: papua } } })

[wynik4.json](https://github.com/wardzinskaj/geojson/blob/master/wynik3.json)<br>
[wynik4.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik3.geojson)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [0,0]    },    $maxDistance:6250000,   }  } } )

[wynik5.json](https://github.com/wardzinskaj/geojson/blob/master/wynik5.json)<br>
[wynik5.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik5.geojson)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [126.926, 1.7742]    },    $minDistance:17000000, $maxDistance:18000000   }  } } )

[wynik6.json](https://github.com/wardzinskaj/geojson/blob/master/wynik6.json)<br>
[wynik6.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik6.geojson)

db.geojson.find( {   geometry: {    $geoIntersects: {     $geometry: {      type : "LineString",       coordinates : [[126.926, 10.7742],[126.926, -2.0656]]     }    }   }  })

[wynik7.json](https://github.com/wardzinskaj/geojson/blob/master/wynik7.json)<br>
[wynik7.geojson wraz z obiektem LineString z zapytania](https://github.com/wardzinskaj/geojson/blob/master/wynik7.geojson)


