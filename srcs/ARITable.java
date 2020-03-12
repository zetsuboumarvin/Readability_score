import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

public class ARITable {
    private String DB_DIR;
    private String DB_NAME;
    private String DB_URL;
    private static final String DB_DRIVER = "org.h2.Driver";

    public int returnAge(double score) {
        int sc = (int)Math.round(score);
        try {
            Class.forName(DB_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement get = conn.createStatement();
            String request = "SELECT age FROM difficulty WHERE score = " + sc;
            ResultSet tempRes = get.executeQuery(request);
            tempRes.first();
            int res = tempRes.getInt("age");
            tempRes.close();
            get.close();
            conn.close();
            return res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    public void createTable() {
        File testDir = new File(currentDir() + System.getProperty("file.separator") + "SQL_table");
        this.DB_DIR = testDir.getPath();
        this.DB_NAME = testDir + System.getProperty("file.separator") + "ARI";
        this.DB_URL = "jdbc:h2:" + this.DB_NAME;
        if (new File(this.DB_NAME + ".mv.db").exists())
            return;
        String dir = createDir();
        try {
            Class.forName(DB_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement create = conn.createStatement();
            create.executeUpdate("CREATE TABLE difficulty(" +
                            "score INT NOT NULL PRIMARY KEY," +
                            "age INT NOT NULL," +
                            "grade_level VARCHAR(20) NOT NULL)");
            fillSQLTable(create);
            create.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createDir() {
        String dir = currentDir() + System.getProperty("file.separator") + "SQL_table";
        File SQLdir = new File(dir);
        if (!SQLdir.mkdir())
            return null;
        return dir;
    }

    private String currentDir() {
        try {
            File currentClass = new File(URLDecoder.decode(ARITable.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
            String classDirectory = currentClass.getParent();
            return classDirectory;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void fillSQLTable(Statement create) throws SQLException {
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(1,6,'Kindergarten')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(2,7,'First/Second Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(3,9,'Third Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(4,10,'Fourth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(5,11,'Fifth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(6,12,'Sixth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(7,13,'Seventh Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(8,14,'Eighth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(9,15,'Ninth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(10,16,'Tenth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(11,17,'Eleventh Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(12,18,'Twelfth Grade')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(13,24,'College student')");
        create.executeUpdate("INSERT INTO difficulty(score,age,grade_level)" + "VALUES(14,25,'Professor')");
    }
}
