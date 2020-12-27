package dmme.kuvid.domain.database;

public class SaveLoadLocal implements Saveable, Loadable{
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
