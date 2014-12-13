// sterownik mongo-java-driver-2.11.4

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Zad1c {

    public static void main(String[] args) throws UnknownHostException {

        int Tagi = 0;
        String slowaTagi[];
        Map<String, Boolean> inneTagi = new HashMap<String, Boolean>();
        long czas1,czas2;

        czas1 = System.currentTimeMillis()/1000;
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("local");     
        DBCollection coll = db.getCollection("train");

        DBCursor cursor = coll.find();
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject)cursor.next();
                slowaTagi = dbObj.getString("Tags").split(" ");
                dbObj.put("Tags", slowaTagi);
                coll.save(dbObj);

                Tagi += slowaTagi.length;
                for(int i=0;i<slowaTagi.length;i++) {
                    if(inneTagi.get(slowaTagi[i]) == null)
                        inneTagi.put(slowaTagi[i], true);
                }
            }
        } finally {
            czas2 = System.currentTimeMillis()/1000;
            System.out.println("Wszystkie tagi: "+Tagi);
            System.out.println("Unikalne tagi: "+inneTagi.size());
            System.out.println("Przetwarzanie zajelo "+(czas2-czas1)/60+"m "+(czas2-czas1)%60+"s");
            cursor.close();
        }

        mongoClient.close();
    }

}
