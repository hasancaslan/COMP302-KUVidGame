package dmme.kuvid.utils;

import dmme.kuvid.constants.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconImporter {
    public static ImageIcon getIconFromFileName(String fileName, String folderName, Dimension size) {
        Image tmp = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("mac")) {
                if (folderName.equals(""))
                    tmp = ImageIO.read(new File(Config.getAssetsPath() + fileName));
                else
                    tmp = ImageIO.read(new File(Config.getAssetsPath() + folderName + "/" + fileName));
            } else {
                if (folderName.equals(""))
                    tmp = ImageIO.read(new File(Config.getAssetsPath() + fileName));
                else
                    tmp = ImageIO.read(new File(Config.getAssetsPath() + folderName + "\\" + fileName));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assert tmp != null;
        tmp = tmp.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        return new ImageIcon(tmp);
    }
}
