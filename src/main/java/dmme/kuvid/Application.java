package dmme.kuvid;

import dmme.kuvid.lib.logger.Logger;
import dmme.kuvid.lib.logger.LoggerFactory;
import javax.swing.*;

public class Application implements Runnable {
    private static volatile Application _instance = null;
    private final Logger logger;

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
    }

    public void init() { }

    public boolean startClient(String clientName, String ip, int port) {
        return false;
    }

    public boolean startServer(int port) {
        return false;
    }

    public void startGame() { }
}