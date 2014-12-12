geojson
=======
https://github.com/Esri/geojson-utils <br>
http://doublebyteblog.wordpress.com/2014/12/03/json-to-geojson-with-jq/ <br>
https://jqplay.org/# <br>
https://github.com/wbzyl/rails4-tutorial/blob/master/lib/doc/leafletjs/javascripts/geojson.js <br>
http://blog.mapillary.com/technology/2014/08/12/jq-power.html

https://github.com/wardzinskaj/geojson/blob/master/proba.geojson


var dzakarta = { type: "Point", coordinates: [ 106.859000, -6.156000] }

db.geojson.find( { geometry: { $near: { $geometry: dzakarta } } } ).limit(1)

[wynik1.json](https://github.com/wardzinskaj/geojson/blob/master/wynik1.json)

[wynik1.geojson](https://github.com/wardzinskaj/geojson/blob/master/near1.geojson) 

var papua = {
<br><ln>   "type": "Polygon",</ln>
<br><ln>   "coordinates": [</ln>
<br>     [
<br> [154.072800, -6.502700],
<br> [154.237000, -6.427100],
<br> [154.321100, -6.442300],
<br> [154.328300, -6.365800],
<br> [154.353400, -6.473700],
<br> [154.458000, -6.484200],
<br> [154.583100, -6.499300],
<br> [154.072800, -6.502700],
<br>     ]
<br>   ]
<br> }

db.geojson.find({ geometry: { $geoIntersects: { $geometry: papua } } })

[wynik2.json](https://github.com/wardzinskaj/geojson/blob/master/wynik2.json)

db.geojson.find({ geometry: { $geoWithin: { $geometry: papua } } })

[wynik3.json](https://github.com/wardzinskaj/geojson/blob/master/wynik3.json)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [126.926, 1.7742]    },    $maxDistance:5000,   }  } } )

[wynik4.json](https://github.com/wardzinskaj/geojson/blob/master/wynik4.json)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [0,0]    },    $maxDistance:6250000,   }  } } )

[wynik5.json](https://github.com/wardzinskaj/geojson/blob/master/wynik5.json)

db.geojson.find( {  geometry: {   $near: {    $geometry: {     type : "Point",      coordinates : [126.926, 1.7742]    },    $minDistance:17000000, $maxDistance:18000000   }  } } )

[wynik6.json](https://github.com/wardzinskaj/geojson/blob/master/wynik6.json)

db.geojson.find( {   geometry: {    $geoIntersects: {     $geometry: {      type : "LineString",       coordinates : [[126.926, 10.7742],[126.926, -2.0656]]     }    }   }  })

[wynik7.json](https://github.com/wardzinskaj/geojson/blob/master/wynik7.json)





