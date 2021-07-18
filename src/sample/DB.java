package sample;

import java.sql.*;
//CRUD

public class DB {
    /**
     * @param dbName
     * @param username
     * @param password
     * @return
     */
    public Connection connect_to_db(String dbName, String username, String password) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:8080/" + dbName, username, password);
            if (connection != null) {
                System.out.println("Connection effectueee");
            } else {
                System.out.println("Connection echouee");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * @param connection
     * @param table_name
     */
    public void createTable(Connection connection, String table_name) {
        Statement statement;
        try {
            String query = "create table " + table_name + "(empid SERIAL,name varchar(200),address varchar(200),primary key(empid));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Creer ");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param connection
     * @param table_name
     * @param name
     * @param address
     */
    public void insert_row(Connection connection,String table_name, String name, String address) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name,address) values('%s','%s');", table_name, name, address);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            System.out.println("Row insered");

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /**
     * @param connection
     * @param table_name
     */
public void read_data(Connection connection,String table_name){
        Statement statement;
        ResultSet rs =null;

            try
            {
                String query = String.format("select * from %s", table_name);
                statement =connection.createStatement();
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    System.out.println(rs.getString("empid")+"");
                    System.out.println(rs.getString("name")+"");
                    System.out.println(rs.getString("address")+"");

                }

            }
            catch (Exception e){
                System.out.println(e);
            }
        }

    /**
     * @param connection
     * @param table_name
     * @param old_name
     * @param new_name
     */
        public void update_name(Connection connection,String table_name,String old_name, String new_name)
        { Statement statement;
            try {
                String query = String.format("update %s set name='%s' where name='%s'",table_name,new_name,old_name);
                statement =connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Data updated");
            }catch(Exception e){
                System.out.println(e);
            }
        }

    /**
     * @param connection
     * @param table_name
     * @param name
     */
        public void search_by_name (Connection connection,String table_name,String name)
        {  ResultSet rs =null;
            Statement statement;
            try {
                String query = String.format("select * from %s where name ='%s'",table_name,name);
                statement =connection.createStatement();
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    System.out.println(rs.getString("empid")+"");
                    System.out.println(rs.getString("name")+"");
                    System.out.println(rs.getString("address")+"");

                }
            }
            catch (Exception e){
                System.out.println(e);
            }

        }
    public void search_by_id(Connection connection, String table_name,int id){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where empid= %s",table_name,id);
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_row_by_name(Connection connection,String table_name, String name){
        Statement statement;
        try{
            String query=String.format("delete from %s where name='%s'",table_name,name);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void delete_row_by_id(Connection connection,String table_name, int id){
        Statement statement;
        try{
            String query=String.format("delete from %s where empid= %s",table_name,id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_table(Connection connection, String table_name){
        Statement statement;
        try {
            String query= String.format("drop table %s",table_name);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    }


