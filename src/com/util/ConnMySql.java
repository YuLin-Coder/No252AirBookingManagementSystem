package com.util;

import java.sql.*;

class ConnMySql {
 
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Class.forName("com.mysql.jdbc.Driver");
         
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/db_companyinfo",
                "root","123456");
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from t_admin");
         
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t"
                    +rs.getString(2) + "\t"
                    +rs.getString(3) );
            }
         
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();   
        }
        if (conn != null) {
            conn.close();   
        }
    }
 
}