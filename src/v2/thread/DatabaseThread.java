package v2.thread;

import v2.utils.database.Database;

import java.sql.SQLException;

public class DatabaseThread extends Thread {

    @Override
    public void run() {
        try {
            Database.connect();
            System.out.println("OK");
        }
        catch (SQLException e) {
            System.out.println("Connect to database failed!");
        }
    }
}
