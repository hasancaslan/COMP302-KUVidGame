package dmme.kuvid;

import javax.swing.*;
import dmme.kuvid.logging.Logger;
import dmme.kuvid.logging.LoggerFactory;
import java.io.IOException;

public class Application implements Runnable {
    private static volatile Application _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private Application() {
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
        startInitialization();
    }

    public void startInitialization() {

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