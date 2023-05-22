
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class DAOConnection {
    private static DataSource dataSource;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataSource = createDataSource();
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found: " + e.getMessage());
        }
    }

    private static DataSource createDataSource() {
        Properties props = new Properties();
        try (InputStream input = DAOConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }

        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.username"),
                        props.getProperty("db.password")
                );
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection(props.getProperty("db.url"), username, password);
            }

            // Other DataSource interface methods (not used in this example)

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public java.io.PrintWriter getLogWriter() throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) throws SQLException {
                throw new UnsupportedOperationException();
            }
