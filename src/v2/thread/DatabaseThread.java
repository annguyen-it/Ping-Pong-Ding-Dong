package v2.thread;

import v2.utils.database.Database;

import java.sql.SQLException;

public class DatabaseThread extends Thread {

    @Override
    public void run() {
        try {
            Database.connect();
        }
        catch (SQLException ignored) { }
    }
}
