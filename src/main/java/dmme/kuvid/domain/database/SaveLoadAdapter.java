package dmme.kuvid.domain.database;

public class SaveLoadAdapter implements Saveable, Loadable {

    public SaveLoadAdapter() {
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
