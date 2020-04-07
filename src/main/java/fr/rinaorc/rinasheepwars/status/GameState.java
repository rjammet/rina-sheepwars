package fr.rinaorc.rinasheepwars.status;

public enum GameState {

    LOBBY(true),
    PREGAME(false),
    GAME(false),
    FINISH(false);

    private static GameState current;
    private boolean canJoin;

    GameState(boolean b){
        canJoin = b;
    }

    public boolean isCanJoin() {
        return canJoin;
    }

    public static void setState(GameState state){
        current = state;
    }

    public static boolean isState(GameState state){
        return current == state;
    }

    public static GameState getState(){
        return current;
    }
}
