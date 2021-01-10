package dmme.kuvid.domain.database;

public class SaveLoadAdapter {
    private final SaveMode saveMode;

    public SaveLoadAdapter(SaveMode saveMode) {
        this.saveMode = saveMode;
    }

    public boolean save() {
        return getSaveMode().saveGame();
    }

    public boolean load() {
        return getSaveMode().loadGame();
    }

    public SaveMode getSaveMode() {
        return saveMode;
    }
}
