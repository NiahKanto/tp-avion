package dbtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {
    PreparedStatement s;
    public Connection getConnection() throws Exception
    {
         Class.forName("org.postgresql.Driver");
         Connection con=DriverManager.getConnection("jdbc:postgresql://postgresql-dranadison.alwaysdata.net:5432/avion","dranadison","alwaysdata12");
         return con;
    }
}
