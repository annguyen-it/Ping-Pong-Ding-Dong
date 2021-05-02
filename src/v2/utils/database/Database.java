package v2.utils.database;

import v2.utils.database.dto.PlayerInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {

    private static Connector connector;

    public static void connect() throws SQLException {
        if (connector == null) {
            connector = new Connector();
        }
    }

    public static void disconnect() throws SQLException {
        if (connector != null) {
            connector.disconnect();
        }
    }

    public static boolean connected() {
        return connector != null && connector.connected();
    }

    public static void createPlayer(PlayerInfo playerInfo) {
        connector.createPlayer(playerInfo);
    }

    public static void createHistory(PlayerInfo user1, PlayerInfo user2) {
        connector.createHistory(user1, user2);
    }

    public static List<String[]> getTopScore() {
        return connector.getTopScore();
    }

    public static List<String[]> getTopWin() {
        return connector.getTopWin();
    }

    private static class Connector {

        private final Connection connection;

        private Connector() throws SQLException {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ping-pong-db", "root", "");
        }

        private void disconnect() throws SQLException {
            if (connection != null) {
                connection.close();
            }
        }

        private boolean connected() {
            try {
                return !connection.isClosed();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
                return true;
            }
        }

        private void createPlayer(PlayerInfo userInfo) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                String sql = "INSERT INTO player(name)" + " VALUES ('" + userInfo.getName() + "')";
                statement.executeUpdate(sql);
            }
            catch (SQLIntegrityConstraintViolationException ignored) { }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        private void createHistory(PlayerInfo user1, PlayerInfo user2) {
            Statement statement = null;
            int historyIndex;

            try {
                statement = connection.createStatement();
                String sql = "INSERT INTO history" + " VALUES ()";
                statement.executeUpdate(sql);
                historyIndex = getLastRow("history");
            }
            catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            connectHistory(user1, historyIndex);
            connectHistory(user2, historyIndex);
        }

        private List<String[]> getTopScore() {
            Statement statement;
            List<String[]> results = new ArrayList<>();

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery(
                                "SELECT player.name, player.join_date, sum(player_game.score) as score " +
                                "FROM player, player_game " +
                                "WHERE player.id = player_game.id_player " +
                                "GROUP BY player_game.id_player " +
                                "ORDER BY score DESC"
                        );

                int rank = 1;

                while (resultSet.next()) {
                    String date = "";

                    try {
                        DateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                        Date dbDate = dbDateFormat.parse(resultSet.getString(2));
                        date = dateFormat.format(dbDate);
                    }
                    catch (ParseException ignored) { }

                    results.add(new String[]{
                            String.valueOf(rank++),
                            resultSet.getString(1),
                            String.valueOf(resultSet.getInt(3)),
                            date
                    });
                }

                return results;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            return results;
        }

        private List<String[]> getTopWin() {
            Statement statement;
            List<String[]> results = new ArrayList<>();

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery(
                                "SELECT player.name, player.join_date, SUM(player_game.result) AS win, COUNT(player_game.id_player) AS played" +
                                " FROM player, player_game" +
                                " WHERE player.id = player_game.id_player" +
                                " GROUP BY player_game.id_player" +
                                " ORDER BY win DESC"
                        );

                int rank = 1;

                while (resultSet.next()) {
                    String date = "";

                    try {
                        DateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                        Date dbDate = dbDateFormat.parse(resultSet.getString(2));
                        date = dateFormat.format(dbDate);
                    }
                    catch (ParseException ignored) { }

                    results.add(new String[]{
                            String.valueOf(rank++),
                            resultSet.getString(1),
                            String.valueOf(resultSet.getInt(3)),
                            String.valueOf(resultSet.getInt(4)),
                            String.format("%.2f", resultSet.getInt(3)*100.0/resultSet.getInt(4)) + "%",
                            date
                    });
                }

                return results;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            return results;
        }

        private void connectHistory(PlayerInfo user, int historyIndex) {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                String sql = " INSERT INTO player_game" +
                             " SELECT player.id, " + historyIndex + ", " + user.getScore() + ", " + user.isWinner() +
                             " FROM player" +
                             " WHERE player.name = '" + user.getName() + "'";

                statement.executeUpdate(sql);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        private int getLastRow(String table) {
            Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id FROM " + table + " ORDER BY id DESC LIMIT 1");

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }

    }
}
