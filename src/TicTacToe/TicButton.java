package TicTacToe;

import javafx.scene.control.Button;

public class TicButton extends Button {

    private String ticID;
    public int buttonID;
    private int BUTTON_FONT_SIZE = 40;

    public TicButton(int buttonID){
        this.buttonID = buttonID;
    }

    public void setTicID(String ticID) {
        this.ticID = ticID;
        this.setStyle(
                "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
                        "-fx-font-weight: bold;");
        this.setText(ticID);
    }

    public String getTicID() {
        return ticID;
    }

    public int getButtonID() {
        return buttonID;
    }
}
