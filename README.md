zadania na zaliczenie

MongoDB a Oracle
========
<p>Jak się okazało na zajęciach moje importy trwały dużo dłużej niż studentów, których projekty były przeglądane.<br>
Prawie 14 godzin u mnie do kilkunastu minut a nawet kilku minut w przypadku bazy Oracle. W bazie Mongodb było nieco lepiej.<br>
<p>Pytanie - jaki był powód tak długiego importu? Jak widać na jednym z obrazków zużycie procesora było 100%<br>
<p>Pracowałam na systemie operacyjnym Linux Centos (maszynie wirtualnej). Może należałoby zmienić sprzęt :) ? Albo dostosować tak aby procesor był bardziej przyjazny </p>

 <p>Import do bazy Mongodb przy użyciu mongoimport</p>
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongodb_import.png"> 
 <p>Statystyki systemu podczas importu - Mongodb</p>
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongodb_system.png"> 
 
 <p>Zliczenie zaimportowanych rekordów w bazie Mongodb. </p>
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/mongo_count.png"> 
 
 
 <p>Import do bazy Oracle przy użyciu sqlldr wraz ze statystykami systemu. </p>
 <img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/oracle_system.png">

<p>Zliczenie zaimportowanych rekordów w bazie Oracle. </p>
<img src="https://github.com/wardzinskaj/nosqlzal/blob/master/java_project/oracle_count.png">
<p></p>
<p>Czas importu do bazy Oracle wyniósł 13 godzin i 47 minut podczas gdy do bazy Mongodb tylko 27 minut.</p>
<p>Import do bazy Mongodb zużywał zdecydowanie mniej zasobów procesora oraz mniej pamięci RAM.</p>
<p>W przypadku importu do bazy Oracle obciążenie procesora jest przez większość czasu maksymalne</p>
<p>Na wirtualnej maszynie jest procesor jednordzeniowy</p>
<p>W najbliższym czasie spróbuję dostosować nieco maszynę wirtualną i sposób działania bazy tak aby zoptymalizować nieco importy.</p>


=========== 
 
Program napisany w javie do dzielenia na tagi. Użyty został sterownik  [mongo-java-driver-2.11.4](http://central.maven.org/maven2/org/mongodb/mongo-java-driver)
<p>https://github.com/wardzinskaj/geojson/blob/master/Zad1c.java</p>

<p>Zamiana danych ze Stringa na tagi - program wypisuje czas jaki zajęło przetwarzanie danych, liczbę tagów i liczbę tagów unikalnych.</p>
<img src="https://github.com/wardzinskaj/geojson/blob/master/zamiana_na_tagi_java.PNG"> 



geojson
=======

var dzakarta = { type: "Point", coordinates: [ 106.859000, -6.156000] }

db.geojson.find( { geometry: { $near: { $geometry: dzakarta } } } ).limit(1)

<p>Markery można zmienić wykorzystując Id z projektu [Maki](https://www.mapbox.com/maki/), tak jak to zrobiłam w </p>


[wynik1.json](https://github.com/wardzinskaj/geojson/blob/master/wynik1.json)

<p>Markery można zmienić wykorzystując Id z projektu [Maki](https://www.mapbox.com/maki/), tak jak to zrobiłam w poniższym pliku </p>
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

[wynik_austrIndo.json](https://github.com/wardzinskaj/geojson/blob/master/wynik8.json)
[wynik_austrIndo.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik8.geojson)

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

[wynik2.json](https://github.com/wardzinskaj/geojson/blob/master/wynik2.json)<br>
[wynik2.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik2.geojson)


db.geojson.find({ geometry: { $geoWithin: { $geometry: papua } } })

[wynik3.json](https://github.com/wardzinskaj/geojson/blob/master/wynik3.json)<br>
[wynik3.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik3.geojson)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [126.926, 1.7742]    },    $maxDistance:5000,   }  } } )

[wynik4.json](https://github.com/wardzinskaj/geojson/blob/master/wynik4.json)<br>
[wynik4.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik4.geojson)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [0,0]    },    $maxDistance:6250000,   }  } } )

[wynik5.json](https://github.com/wardzinskaj/geojson/blob/master/wynik5.json)<br>
[wynik5.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik5.geojson)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [126.926, 1.7742]    },    $minDistance:17000000, $maxDistance:18000000   }  } } )

[wynik6.json](https://github.com/wardzinskaj/geojson/blob/master/wynik6.json)<br>
[wynik6.geojson](https://github.com/wardzinskaj/geojson/blob/master/wynik6.geojson)

db.geojson.find( {   geometry: {    $geoIntersects: {     $geometry: {      type : "LineString",       coordinates : [[126.926, 10.7742],[126.926, -2.0656]]     }    }   }  })

[wynik7.json](https://github.com/wardzinskaj/geojson/blob/master/wynik7.json)<br>
[wynik7.geojson wraz z obiektem LineString z zapytania](https://github.com/wardzinskaj/geojson/blob/master/wynik7.geojson)


