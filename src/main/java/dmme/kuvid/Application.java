package dmme.kuvid;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.logger.Logger;
import dmme.kuvid.lib.logger.LoggerFactory;
import dmme.kuvid.ui.BuildingWindow;

import javax.swing.*;
import java.awt.*;

public class Application implements Runnable {
    private static volatile Application _instance = null;
    private final Logger logger;
    private KUVidGame game;

    public Application() {
        LoggerFactory loggerFactory = new LoggerFactory();
        this.logger = loggerFactory.createLogger(Application.class);

        logger.i("Created new KUVid instance.");
    }

    public static synchronized Application getInstance() {
        if (_instance == null) {
            _instance = new Application();
        }

        return _instance;
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();
        SwingUtilities.invokeLater(application);
    }

    @Override
    public void run() {
        logger.i("Running KUVid...");
        initGame();
        
    }

    public BuildingWindow buildWindow() {
        BuildingWindow buildWindow = new BuildingWindow(this.game);
        buildWindow.setTitle("BUILDING WINDOW");
        buildWindow.setSize(510, 510);
        buildWindow.setLocationRelativeTo((Component) null);
        buildWindow.setDefaultCloseOperation(3);
        buildWindow.setVisible(true);
        
        return buildWindow;
        
    }

    public void initGame() {
    	this.game=new KUVidGame();
        BuildingWindow window=buildWindow();
        logger.i(""+this.game.getNumAtoms());
        
    }

    public boolean startClient(String clientName, String ip, int port) {
        return false;
    }

    public boolean startServer(int port) {
        return false;
    }

    public void startGame() {
    }
}