package v2.controller;

import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.model.MenuModel;
import v2.utils.database.Database;
import v2.view.GameView;
import v2.view.MenuView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class MenuController extends Controller<MenuView, MenuModel> {

    public static String playerName1;
    public static String playerName2;

    public MenuController(FlowController flowController) {
        super(flowController, new MenuView(), new MenuModel());
    }

    @Override
    public void initEvent() {
        addPlayButtonEvent();
        addHelpButtonEvent();
        addRankingButtonEvent();
        addExitButtonEvent();
    }

    //#region Play button

    private void addPlayButtonEvent() {
        view.getPlayButton().addActionListener(e -> {
            int result = showInputNameDialog();

            if (result == JOptionPane.OK_OPTION) {
                switchToGameController();
            }
        });
    }

    private int showInputNameDialog() {
        return JOptionPane.showConfirmDialog(
                null,
                view.getPlayDialog(),
                "Fill in your names",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
    }

    private void switchToGameController() {
        playerName1 = view.getPlayerNameTextField1().getText();
        playerName2 = view.getPlayerNameTextField2().getText();

        EnterNameDialogModel model = new EnterNameDialogModel(playerName1, playerName2);
        GameView gameView = new GameView(model);
        GameModel gameModel = new GameModel();

        GameController gameController = new GameController(flowController, gameView, gameModel);
        gameView.setController(gameController);
        gameModel.setController(gameController);

        switchController(gameController);
    }

    //#endregion

    //#region Help button

    private void addHelpButtonEvent() {
        view.getHelpButton().addActionListener(e -> showHelpDialog());
    }

    private void showHelpDialog() {
        String[] options = { "OK" };

        JOptionPane.showOptionDialog(
                null,
                view.getHelpDialog(),
                "Help",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                null
        );
    }

    //#endregion

    //#region Ranking button

    private void addRankingButtonEvent() {
        view.getRankingButton().setAction(new RankingBtnAction("Ranking"));
    }

    static class RankingBtnAction extends AbstractAction {
        private final RankingButtonWorker worker = new RankingButtonWorker();
        private final JProgressBar progressBar = new JProgressBar();
        private final JPanel panel = new JPanel(new BorderLayout());

        public RankingBtnAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Window window = SwingUtilities.getWindowAncestor((AbstractButton) e.getSource());
            final JDialog dialog = new JDialog(window, "Information", Dialog.ModalityType.APPLICATION_MODAL);

            worker.addPropertyChangeListener(new RankingButtonPropertyChangeListener(dialog));
            worker.execute();

            progressBar.setIndeterminate(true);
            panel.add(progressBar, BorderLayout.CENTER);
            panel.add(new JLabel("Connecting to database......."), BorderLayout.PAGE_START);

            dialog.add(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(window);
            dialog.setVisible(true);
        }

        static class RankingButtonWorker extends SwingWorker<Void, Void> {

            @Override
            protected Void doInBackground() {
                if (Database.notConnected()) {
                    try {
                        Database.connect();
                    }
                    catch (SQLException ignored) { }
                }

                return null;
            }
        }

        static class RankingButtonPropertyChangeListener implements PropertyChangeListener {

            private final JDialog dialog;
            private final JTabbedPane tabbedPane = new JTabbedPane();
            private final DefaultTableCellRenderer align = new DefaultTableCellRenderer();

            private static final String[] dialogOptions = { "OK" };
            private static final String[] topScoreColumns = { "Rank", "Name", "Total points", "Join date" };
            private static final String[] topWinColumns = { "Rank", "Name", "Wins", "Played", "Percentage", "Join date" };

            public RankingButtonPropertyChangeListener(JDialog dialog) {
                this.dialog = dialog;
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                        dialog.dispose();

                        if (Database.notConnected()) {
                            showErrorDialog();
                            return;
                        }

                        align.setHorizontalAlignment(JLabel.CENTER);

                        tabbedPane.add("Top Score", getTopScoreTab());
                        tabbedPane.add("Top Wins", getTopWinTab());

                        showExhibition(tabbedPane);
                    }
                }
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
                        null
                );
            }

            private void showExhibition(JTabbedPane tabbedPane) {
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
                java.util.List<String[]> topScoreData = Database.getTopScore();

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

            private JScrollPane getTopWinTab() {
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
    }

    //#endregion

    //#region Exit button

    private void addExitButtonEvent() {
        view.getExitButton().addActionListener(e -> {
            int output = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to exit",
                    " ",
                    JOptionPane.YES_NO_OPTION
            );

            if (output == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    //#endregion
}
