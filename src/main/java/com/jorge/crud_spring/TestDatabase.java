package com.jorge.crud_spring;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.kphcxuvtgmgymnfodofw.supabase.co:5432/postgres";
        String user = "postgres";
        String password = "vymfej-zyrpyb-jaWcy6";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Conectado ao PostgreSQL com sucesso!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
