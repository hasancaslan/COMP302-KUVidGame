package dmme.kuvid.domain.database;

public interface SaveMode {
    boolean saveGame();
    boolean loadGame();
    boolean loadInit();
}
