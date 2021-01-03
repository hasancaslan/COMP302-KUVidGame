package dmme.kuvid.domain.database;

public class SaveLoadFile implements SaveMode {

    @Override
    public boolean saveGame() {
        return false;
    }

    @Override
    public boolean loadGame() {
        return false;
    }
}
