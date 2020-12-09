package dmme.kuvid;

import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.logger.Logger;
import dmme.kuvid.lib.logger.LoggerFactory;
import dmme.kuvid.ui.BuildingWindow;
import dmme.kuvid.ui.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Application implements Runnable {
    private static volatile Application _instance = null;
    private final Logger logger;
    int i = 1;
    private KUVidGame game;
    private GameFrame mainWindow;

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
        //SwingUtilities.invokeLater(movementHandler.getInstance());

    }

    @Override
    public void run() {
        logger.i("Running KUVid...");
        initGame();

    }

    public void initGame() {
        this.game = new KUVidGame();
        new BuildingWindow();
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