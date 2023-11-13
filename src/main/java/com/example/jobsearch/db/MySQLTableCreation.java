package com.example.jobsearch.db;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.glassfish.jersey.internal.guava.ListenableFuture;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MySQLTableCreation {
    public static void main(String[] args) {
        try {

            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.URL);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

            if (conn == null) {
                return;
            }

            // Step 2 Drop tables in case they exist.
            Statement statement = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS keywords";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS history";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS items";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);


            // Step 3 Create new tables
            sql = "CREATE TABLE items ("
                    + "item_id VARCHAR(255) NOT NULL,"
                    + "name VARCHAR(255),"
                    + "address VARCHAR(255),"
                    + "image_url VARCHAR(255),"
                    + "url VARCHAR(255),"
                    + "PRIMARY KEY (item_id)" //primary key for items
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE users ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "PRIMARY KEY (user_id)"  //primary key for users
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE keywords ("
                    + "item_id VARCHAR(255) NOT NULL,"
                    + "keyword VARCHAR(255) NOT NULL,"
                    + "PRIMARY KEY (item_id, keyword)," //normally we dont use tow columns to set up a table
                    + "FOREIGN KEY (item_id) REFERENCES items(item_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE history ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "item_id VARCHAR(255) NOT NULL,"
                    + "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "PRIMARY KEY (user_id, item_id),"
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id),"
                    + "FOREIGN KEY (item_id) REFERENCES items(item_id)"
                    + ")";
            statement.executeUpdate(sql);

            // Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
            sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
            statement.executeUpdate(sql);

            long start = System.currentTimeMillis();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                sql = "INSERT INTO users VALUES (" + i + ", " + "'3229c1097c00d497a0fd282d586be050', " + "'John', " + "'Smith')";
                list.add(sql);
//                Statement statement1 = conn.createStatement();
//                statement1.executeUpdate(sql);
            }
            conn.close();

            System.out.println("Latency111: " + (System.currentTimeMillis()- start));

//            List<CompletableFuture<Void>> toBeDone =
//            list.stream().map(sql1-> {
//                         return CompletableFuture.runAsync(()-> {
//                            try {
//                                long c = System.currentTimeMillis();
//                                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//                                System.out.println("inside33333: ");
//                                Connection conn1 = DriverManager.getConnection(MySQLDBUtil.URL);
//                                System.out.println("latency create connection : " + (System.currentTimeMillis() - c));
//                                c = System.currentTimeMillis();
//                                Statement statement1 = conn1.createStatement();
//                                statement1.executeUpdate(sql1);
//                                System.out.println("latency create statement : " + (System.currentTimeMillis() - c));
//                                conn1.close();
//                            } catch (Exception e) {
//                                System.out.println("exceptions : " + e);
//                            }
//                            //return statement1;
//                        });
//                    }
//            ).collect(Collectors.toList());
//
//            toBeDone.forEach(f -> {
//                try {
//                    f.get();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (ExecutionException e) {
//                    throw new RuntimeException(e);
//                }
//            });

            System.out.println("Latency222: " + (System.currentTimeMillis()- start));
            System.out.println("Import done successfully");


            long end  = System.currentTimeMillis();
            System.out.println("Latency: " + (end - start));

            start = System.currentTimeMillis();

            List<String> list1 = new ArrayList<>();

//            for (int i = 0; i < 100; i++) {
//                list1.add("SELECT * FROM users WHERE user_id = " + i);
//            }

            List<ResultSet> query_return = new ArrayList<>();

            System.out.println("Query Latency " + (System.currentTimeMillis() - start));

//            for (int i = 0; i < 100; i++){
//                String sql1 = "SELECT * FROM users WHERE user_id = " + i;
//                PreparedStatement statement1 = conn.prepareStatement(sql1);
//                ResultSet rs = statement1.executeQuery();
//                if (rs.next()) {
//                    System.out.println(rs.getString("user_id"));
//                }
//                System.out.println(rs);
//
//            }

//            list1.forEach(sql1 ->{
//                    CompletableFuture.runAsync(()-> {
//                        try {
//                            PreparedStatement statement1 = conn.prepareStatement(sql1);
//                            ResultSet rs = statement1.executeQuery();
//                            if (rs.next()) {
//                                System.out.println(rs.getString("user_id"));
//                            }
////                            query_return.add(rs);
//                        } catch (Exception e){
//
//                        }
//
//                    });
//                }
//            );

            System.out.println("Latency111 " + (System.currentTimeMillis() - start));
//            for (int i = 0; i < 100; i++) {
//                if (query_return.get(i).next()) {
//                    String user_id = query_return.get(i).getString("user_id");
//                    String firstName = query_return.get(i).getString("first_name");
//                    System.out.println(user_id + firstName);
//                }
//            }
//            System.out.println(query_return.size());
            System.out.println("Latency " + (System.currentTimeMillis() - start));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



