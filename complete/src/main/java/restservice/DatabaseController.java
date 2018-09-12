package restservice;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseController {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/locations?serverTimezone=EST"; //SET TIME ZONE

    //sets up credentials for DB access
    static final Credential DB_CREDS = new Credential("DatabaseCreds.txt");

    /**
     * Executes a select query from String parameter.
     * @param sqlQuery, queryType
     * @return ArrayList of items in database containing HashMaps of each row.
     */
    public static List doQuery(String sqlQuery, String queryType) {

        try {
            Connection conn;
            Statement stmt;
            ResultSet resultsOfSelectQuery = null;

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

            //clean up connection
            conn.close();
            stmt.close();

            //returns results as ArrayList
            return resultSetToArrayList(resultsOfSelectQuery);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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
