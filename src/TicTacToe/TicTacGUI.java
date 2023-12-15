package TicTacToe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacGUI extends Application implements Observer<TicTacModel, String> {

    private TicTacModel model;
    private TicButton[][] ticButtons = new TicButton[3][3];

    private int ROWS = 3;
    private int COLS = 3;
    private int ICON_SIZE = 100;
    private int BUTTON_FONT_SIZE = 20;
    private Scene scene;

    // GUI COMPONENTS
    private BorderPane borderPane = new BorderPane();
    private BorderPane border = new BorderPane();
    private GridPane gridPane = new GridPane();
    private Label player = new Label("PLAYER 1");

    public TicTacGUI(){
        model = new TicTacModel();
    }

    @Override
    public void init(){
        model.addObserver(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label playerInfo = new Label("PLAYER: X     CPU: O   ");
        playerInfo.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(playerInfo, Pos.BOTTOM_CENTER);

        Button reset = new Button("Reset Game");
        reset.setOnAction(event -> model.reset());

        int buttonNum = 1;
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                TicButton ticButton = new TicButton(buttonNum);
                ticButton.setMinSize(ICON_SIZE, ICON_SIZE);
                ticButton.setMaxSize(ICON_SIZE, ICON_SIZE);
                ticButton.setOnAction(event -> model.select(ticButton.buttonID));
                ticButtons[row][col] = ticButton;
                gridPane.add(ticButton, col, row);
                ++buttonNum;
            }
        }

        player.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(player, Pos.CENTER);
        border.setRight(playerInfo);
        border.setLeft(reset);
        borderPane.setTop(player);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(border);
        scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("TIC TAC TOE");
        stage.show();
    }

    public void updateView(String[][] board, String message){
        player.setText(message);

        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                String ticID = board[row][col];
                ticButtons[row][col].setStyle(
                        "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
                                "-fx-font-weight: bold;");
                ticButtons[row][col].setText(ticID);

            }
        }

    }

    @Override
    public void update(TicTacModel model, String data) {
        updateView(model.getBoard(), data);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}


