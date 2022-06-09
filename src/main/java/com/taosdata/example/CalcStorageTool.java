package com.taosdata.example;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcStorageTool {

    public static final Pattern PATTERN = Pattern.compile(".*Size=\\[(.*?)\\].*");

    public static void main(String[] args) throws Exception {
        String host = args[0];
        String port = args[1];
        String user = args[2];
        String password = args[3];
        // use port 6041 in url when use JDBC-restful
        String url = "jdbc:TAOS-RS://" + host + ":" + port + "/?user=" + user + "&password=" + password;

        Properties properties = new Properties();
        properties.setProperty("charset", "UTF-8");
        properties.setProperty("locale", "en_US.UTF-8");
        properties.setProperty("timezone", "UTC-8");

        Connection conn = DriverManager.getConnection(url, properties);
        Statement stmt = conn.createStatement();

        Map<String, Float> dbSizes = new HashMap<>();
        ResultSet dbrs = stmt.executeQuery("show databases");
        while (dbrs.next()) {
            String db = dbrs.getString(1);
            System.out.println("db: " + db);

            stmt.execute("use " + db);
            ResultSet strs = stmt.executeQuery("show stables");
            float sizeSum = 0;
            while (strs.next()) {
                String st = strs.getString(1);
                System.out.println("st: " + st);

                ResultSet bdrs = stmt.executeQuery("select _block_dist() from " + st);
                while (bdrs.next()) {
                    String summary = bdrs.getString(1);
                    System.out.println(summary);
                    Matcher matcher = PATTERN.matcher(summary);
                    matcher.find();
                    String size = matcher.group(1);
                    System.out.println("Size: " + size);
                    sizeSum += covertByte(size);
                }
                bdrs.close();

            }
            dbSizes.put(db, sizeSum);
            strs.close();
        }

        System.out.println();
        System.out.println();
        for (Map.Entry<String, Float> e : dbSizes.entrySet()) {
            System.out.printf("db: %-20ssize: %s\n", e.getKey(), e.getValue());
        }

        dbrs.close();
        stmt.close();
        conn.close();
    }

    private static float covertByte(String size) {
        size = size.toLowerCase();
        if (size.contains("kb"))
            return Float.valueOf(size.substring(0, size.indexOf("kb") - 1)) * 1024;
        return 0;
    }
}
