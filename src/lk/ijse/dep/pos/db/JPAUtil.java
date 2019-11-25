package lk.ijse.dep.pos.db;

import javafx.scene.control.Alert;
import lk.ijse.dep.crypto.DEPCrypt;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtil {

    public static String host;
    public static String port;
    public static String db;
    public static String username;
    public static String password;
    private static JPAUtil dbConnection;
    private Connection connection;

    private static EntityManagerFactory emf;

    private static EntityManagerFactory buildEntityManagerFactory(){
        File file = new File("resources/application.properties");
        try(FileInputStream fis = new FileInputStream(file)){
            Properties properties = new Properties();
            properties.load(fis);

            return Persistence.createEntityManagerFactory("dep4",properties);
        } catch (Exception e) {
            Logger.getLogger("lk.ijse.dep.pos.db.JPAUtil").log(Level.SEVERE,null, e);
            System.exit(1);
            return null;
        }
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }



//    private JPAUtil() {
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            Properties properties = new Properties();
//            File file = new File("resources/application.properties");
//            FileInputStream fis = new FileInputStream(file);
//            properties.load(fis);
//            fis.close();
//
//            String ip = properties.getProperty("pos.ip");
//            JPAUtil.host = ip;
//            String port = properties.getProperty("pos.port");
//            JPAUtil.port = port;
//            String db = properties.getProperty("pos.db");
//            JPAUtil.db = db;
//            String user = DEPCrypt.decode(properties.getProperty("pos.user"),"123");
//            JPAUtil.username = user;
//            String password = DEPCrypt.decode(properties.getProperty("pos.password"),"123");
//            JPAUtil.password = password;
//
//            /* Reading a file
//            File file = new File("resources/application.properties");
//            FileInputStream fis = new FileInputStream(file);        // Byte stream
//            InputStreamReader isr = new InputStreamReader(fis);     // Char stream
//            BufferedReader br = new BufferedReader(isr);            // String
//
//            String out = "";
//            String line = null;
//            while( (line = br.readLine())!=null ){
//                out += line;
//            }
//            System.out.println(out);
//             */
//
//            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
//            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
//            ResultSet resultSet = pstm.executeQuery();
//            if (!resultSet.next()) {
//                File dbScriptFile = new File("db-script.sql");
//                if (!dbScriptFile.exists()){
//                    new Alert(Alert.AlertType.ERROR,"Ubea putha file eka makala, dn ithin depponta kiyapan").show();
//                    throw new RuntimeException("Unable to find the DB Script");
//                }
//                StringBuilder sb = new StringBuilder();
//                BufferedReader brDBScript = new BufferedReader(new InputStreamReader(new FileInputStream(dbScriptFile)));
//                brDBScript.lines().forEach(s -> sb.append(s));
//                brDBScript.close();
//                System.out.println(sb.toString());
//                pstm = connection.prepareStatement(sb.toString());
//                pstm.execute();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static JPAUtil getInstance() {
//        return (dbConnection == null) ? (dbConnection = new JPAUtil()) : dbConnection;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }

}
