package dmme.kuvid.domain.database;

public class SaveLoadDatabase implements SaveMode {

    @Override
    public boolean saveGame() {
        return false;
    }

    @Override
    public boolean loadGame() {
        return false;
    }

	@Override
	public boolean loadInit() {
		// TODO Auto-generated method stub
		return false;
	}
}
