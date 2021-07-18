package sample;

import java.sql.Connection;

public class Main  {

    public static void main(String[] args)
    {

        DB db= new DB();
       Connection connection=db.connect_to_db("javadb","postgres","admin");
        //db.createTable(connection,"employee");
       // db.insert_row(connection,"employee","momo","BF");
        db.read_data(connection,"employee");
    }

}
