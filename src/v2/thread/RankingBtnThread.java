package v2.thread;

import v2.utils.database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class RankingBtnThread extends Thread {

    @Override
    public void run() {
        String[] topScoreColumns = { "Rank", "Name", "Total points", "Join date" };
        String[] topWinColumns = { "Rank", "Name", "Wins", "Played", "Percentage", "Join date" };

        DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(JLabel.CENTER);

        String[] str = { "OK" };
        //        String[] emp = {};

        //        JOptionPane.showOptionDialog(
        //                null,
        //                new JLabel("Connecting..."),
        //                "Info",
        //                JOptionPane.OK_CANCEL_OPTION,
        //                JOptionPane.PLAIN_MESSAGE,
        //                null,
        //                emp,
        //                null);

        if (!Database.connected()) {
            try {
                Database.connect();
            }
            catch (SQLException ignored) { }
        }

        //        JOptionPane.getRootFrame().dispose();

        if (!Database.connected()) {
            JOptionPane.showOptionDialog(
                    null,
                    new JLabel("Cannot connect to database"),
                    "Info",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    str,
                    null);

            return;
        }


        //  Top Score
        DefaultTableModel topScoreTableModel = new DefaultTableModel(topScoreColumns, 0);
        List<String[]> topScoreData = Database.getTopScore();

        topScoreData.forEach(topScoreTableModel::addRow);

        JTable topScoreTable = new JTable(topScoreTableModel);
        topScoreTable.setRowHeight(20);
        topScoreTable.getColumnModel().getColumn(0).setCellRenderer(align);
        topScoreTable.getColumnModel().getColumn(2).setCellRenderer(align);
        topScoreTable.getColumnModel().getColumn(3).setCellRenderer(align);

        topScoreTable.getColumnModel().getColumn(0).setMaxWidth(50);
        topScoreTable.getColumnModel().getColumn(2).setMaxWidth(110);
        topScoreTable.getColumnModel().getColumn(3).setMaxWidth(110);

        JScrollPane topScoreScrollPane = new JScrollPane(topScoreTable);


        //  Top Win
        DefaultTableModel topWinTableModel = new DefaultTableModel(topWinColumns, 0);
        List<String[]> topWinData = Database.getTopWin();

        topWinData.forEach(topWinTableModel::addRow);

        JTable topWinTable = new JTable(topWinTableModel);
        topWinTable.setRowHeight(20);
        topWinTable.getColumnModel().getColumn(0).setCellRenderer(align);
        topWinTable.getColumnModel().getColumn(2).setCellRenderer(align);
        topWinTable.getColumnModel().getColumn(3).setCellRenderer(align);
        topWinTable.getColumnModel().getColumn(4).setCellRenderer(align);
        topWinTable.getColumnModel().getColumn(5).setCellRenderer(align);

        topWinTable.getColumnModel().getColumn(0).setMaxWidth(50);

        JScrollPane topWinScrollPane = new JScrollPane(topWinTable);


        //  Add to tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Top Score", topScoreScrollPane);
        tabbedPane.add("Top Wins", topWinScrollPane);

        JOptionPane.showOptionDialog(
                null,
                tabbedPane,
                "Help",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                str,
                null);
    }
}
