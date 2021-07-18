package sample;

import java.sql.*;

public class DB {
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
    }


