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

        int allTags = 0;
        String objTags[];
        Map<String, Boolean> uniqueTags = new HashMap<String, Boolean>();
        long time1,time2;

        time1 = System.currentTimeMillis()/1000;
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("local");     
        DBCollection coll = db.getCollection("train");

        DBCursor cursor = coll.find();
        try {
            while(cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject)cursor.next();
                objTags = obj.getString("Tags").split(" ");
                obj.put("Tags", objTags);
                coll.save(obj);

                allTags += objTags.length;
                for(int i=0;i<objTags.length;i++) {
                    if(uniqueTags.get(objTags[i]) == null)
                        uniqueTags.put(objTags[i], true);
                }
            }
        } finally {
            time2 = System.currentTimeMillis()/1000;
            System.out.println("Wszystkie tagi: "+allTags);
            System.out.println("Unikalne tagi: "+uniqueTags.size());
            System.out.println("Przetwarzanie zajelo "+(time2-time1)/60+"m "+(time2-time1)%60+"s");
            cursor.close();
        }

        mongoClient.close();
    }

}
