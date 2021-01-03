package dmme.kuvid.domain.database;

public interface SaveMode {
    boolean saveInfo = false;
    boolean loadInfo = false;

    boolean saveGame();
    boolean loadGame();
}
