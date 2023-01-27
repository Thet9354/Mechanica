package com.example.mechanica.Model;

public class Games {

    private int gamePic;
    private String game;

    public Games(int gamePic, String game) {
        this.gamePic = gamePic;
        this.game = game;
    }

    public Games() {

    }

    public int getGamePic() {
        return gamePic;
    }

    public void setGamePic(int gamePic) {
        this.gamePic = gamePic;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
