package v2.thread;

import v2.utils.database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class RankingBtnThread extends Thread {

    private static final String[] dialogOptions = { "OK" };
    private static final String[] topScoreColumns = { "Rank", "Name", "Total points", "Join date" };
    private static final String[] topWinColumns = { "Rank", "Name", "Wins", "Played", "Percentage", "Join date" };
    private final DefaultTableCellRenderer align = new DefaultTableCellRenderer();

    @Override
    public void run() {
        if (!Database.connected()) {
            try {
                Database.connect();
            }
            catch (SQLException ignored) { }
        }

        if (!Database.connected()) {
            showErrorDialog();
            return;
        }

        align.setHorizontalAlignment(JLabel.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Top Score", getTopScoreTab());
        tabbedPane.add("Top Wins", getTopWinTab());

        showExhibition(tabbedPane);
    }

    private void showErrorDialog() {
        JOptionPane.showOptionDialog(
                null,
                new JLabel("Cannot connect to database"),
                "Info",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                dialogOptions,
                null);
    }

    private void showExhibition(JTabbedPane tabbedPane){
        JOptionPane.showOptionDialog(
                null,
                tabbedPane,
                "Help",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                dialogOptions,
                null
        );
    }

    private JScrollPane getTopScoreTab() {
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

        return new JScrollPane(topScoreTable);
    }

    private JScrollPane getTopWinTab(){
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

        return new JScrollPane(topWinTable);
    }
}
