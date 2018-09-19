package restservice;

import com.mysql.cj.jdbc.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseController {

    /*
        USES FORMAT: DB locations, Table trucklocations.
        (date, time_index, truck_name, latitude, longitude)
        DATE, MEDIUMINT, VARCHAR, DOUBLE, DOUBLE
     */

    //NOTE!!! Database max_connections must be increased to support this volume of queries.

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/locations?serverTimezone=EST"; //SET TIME ZONE

    //sets up credentials for DB access
    static final Credential DB_CREDS = new DatabaseCredential();

    /**
     * Executes a select query from String parameter.
     * @param sqlQuery, queryType
     * @return ArrayList of items in database containing HashMaps of each row.
     */
    public static List doQuery(String sqlQuery, String queryType) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultsOfSelectQuery = null;
        ArrayList resultsAsArrayList = new ArrayList();

        try {

            Class.forName(JDBC_DRIVER);

            //opens connection
            conn = DriverManager.getConnection(DB_URL,DB_CREDS.getUsername(),DB_CREDS.getPassword());

            //executes select query
            stmt = conn.createStatement();

            //determines how to execute query via jdbc
            if (queryType.toUpperCase().equals("SELECT")) {
                resultsOfSelectQuery = stmt.executeQuery(sqlQuery);
            } else if (queryType.toUpperCase().equals("INSERT")) {
                stmt.executeUpdate(sqlQuery);
                return new ArrayList(); //must return something
            } else if (queryType.toUpperCase().equals("UPDATE")) {
                stmt.executeUpdate(sqlQuery);
                return new ArrayList(); //see above
            }

            resultsAsArrayList = resultSetToArrayList(resultsOfSelectQuery);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultsOfSelectQuery!=null) {
                try {
                    resultsOfSelectQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //returns results as ArrayList
        return resultsAsArrayList;
    }


    /**
     * Converts ResultSet into ArrayList of HashMaps.
     * @param rs
     * @return
     * @throws SQLException
     */
    public static ArrayList resultSetToArrayList(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList();
        while (rs.next()){
            HashMap row = new HashMap(columns);
            for(int i=1; i<=columns; ++i){
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

}