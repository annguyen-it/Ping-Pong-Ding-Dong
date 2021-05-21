package main.java.mvc.menu;

import main.java.mvc.game.GameController;
import main.java.App;
import main.java.mvc.common.Controller;
import main.java.mvc.common.flow.FlowController;
import main.java.mvc.common.model.PlayerNamesModel;
import main.java.mvc.game.GameModel;
import main.java.utils.database.Database;
import main.java.mvc.game.GameView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class MenuController extends Controller<MenuView, MenuModel> {

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
        model.soundPlayer.stopBackgroundAudio();

        final String playerName1 = view.getPlayerNameTextField1().getText();
        final String playerName2 = view.getPlayerNameTextField2().getText();

        PlayerNamesModel model = new PlayerNamesModel(playerName1, playerName2);
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
        view.getRankingButton().setAction(new RankingBtnAction());
    }

    static class RankingBtnAction extends AbstractAction {

        private final JProgressBar progressBar = new JProgressBar();
        private final JPanel panel = new JPanel(new BorderLayout());

        public RankingBtnAction() {
            super("RANKING");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RankingButtonWorker worker = new RankingButtonWorker();
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
                if (!Database.connected()) {
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
                    if (evt.getNewValue() == SwingWorker.StateValue.DONE && dialog.isShowing()) {
                        dialog.dispose();

                        if (!Database.connected()) {
                            showErrorDialog();
                            return;
                        }

                        align.setHorizontalAlignment(JLabel.CENTER);

                        tabbedPane.add("Top Score", getTopScoreTab());
                        tabbedPane.add("Top Wins", getTopWinTab());

                        tabbedPane.setBackground(Color.yellow);
                        tabbedPane.setPreferredSize(new Dimension(500, 255));
                        tabbedPane.setFocusable(false);

                        showRanking(tabbedPane);
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

            private void showRanking(JTabbedPane tabbedPane) {
                JOptionPane.showOptionDialog(
                        null,
                        tabbedPane,
                        "Ranking",
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

                topScoreTable.getColumnModel().getColumn(0).setCellRenderer(align);
                topScoreTable.getColumnModel().getColumn(1).setCellRenderer(align);
                topScoreTable.getColumnModel().getColumn(2).setCellRenderer(align);
                topScoreTable.getColumnModel().getColumn(3).setCellRenderer(align);

                topScoreTable.getColumnModel().getColumn(0).setMaxWidth(50);
                topScoreTable.getColumnModel().getColumn(2).setMinWidth(90);
                topScoreTable.getColumnModel().getColumn(2).setMaxWidth(110);
                topScoreTable.getColumnModel().getColumn(3).setMaxWidth(110);

                decorateTable(topScoreTable);
                setupTable(topScoreTable);

                return new JScrollPane(topScoreTable);
            }

            private JScrollPane getTopWinTab() {
                DefaultTableModel topWinTableModel = new DefaultTableModel(topWinColumns, 0);
                List<String[]> topWinData = Database.getTopWin();

                topWinData.forEach(topWinTableModel::addRow);

                JTable topWinTable = new JTable(topWinTableModel);

                topWinTable.getColumnModel().getColumn(0).setCellRenderer(align);
                topWinTable.getColumnModel().getColumn(1).setCellRenderer(align);
                topWinTable.getColumnModel().getColumn(2).setCellRenderer(align);
                topWinTable.getColumnModel().getColumn(3).setCellRenderer(align);
                topWinTable.getColumnModel().getColumn(4).setCellRenderer(align);
                topWinTable.getColumnModel().getColumn(5).setCellRenderer(align);

                topWinTable.getColumnModel().getColumn(0).setMaxWidth(50);

                decorateTable(topWinTable);
                setupTable(topWinTable);

                return new JScrollPane(topWinTable);
            }

            private void setupTable(JTable table) {
                table.setDefaultEditor(Object.class, null);
                table.setFocusable(false);
            }

            private void decorateTable(JTable table) {

                table.setGridColor(Color.YELLOW);
                table.setBackground(Color.YELLOW);
                table.setSelectionBackground(Color.orange);
                table.setSelectionForeground(Color.blue);
                table.setRowHeight(40);

                JTableHeader tableHeader = table.getTableHeader();
                tableHeader.setBackground(Color.orange);
                tableHeader.setBorder(BorderFactory.createLineBorder(App.primaryColor, 2));
                tableHeader.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                tableHeader.setForeground(Color.blue);
                tableHeader.setEnabled(false);
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
