package com.example.jobsearch.db;

public class MySQLDBUtil {
    private static final String INSTANCE = "database-2.cjttsv8db7i1.us-west-1.rds.amazonaws.com";
    private static final String PORT_NUM = "3306";
    public static final String DB_NAME = "JobSearch";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345678";
    public static final String URL = "jdbc:mysql://"
            + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";
}
