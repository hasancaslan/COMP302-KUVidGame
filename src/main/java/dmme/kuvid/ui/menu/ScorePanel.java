package dmme.kuvid.ui.menu;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class ScorePanel extends JPanel implements PropertyListener {
    private final JLabel scoreLabel;
    private final JLabel timeLabel;
    private final JLabel healthLabel;

    public ScorePanel() {
        super(new GridLayout(3, 2));
        setOpaque(true);
        this.setBackground(new Color(204, 230, 255));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));

        Font defaultFont = new Font("Sans-Serif", Font.PLAIN, Config.fontSize);

        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        healthLabel = new JLabel();

        scoreLabel.setFont(defaultFont);
        timeLabel.setFont(defaultFont);
        healthLabel.setFont(defaultFont);

        setScoreLabel(Player.getInstance().getPoint());


        setHealthLabel(Player.getInstance().getHealth());
        setTimeLabel(KUVidGame.getInstance().getTime());

        JLabel scoreTitleLabel = new JLabel("Score:", JLabel.TRAILING);
        scoreTitleLabel.setFont(defaultFont);
        this.add(scoreTitleLabel);
        this.add(scoreLabel);

        ImageIcon alarmIcon = IconImporter.getIconFromFileName("alarm.png", "", new Dimension(Config.fontSize, Config.fontSize));
        JLabel timeTitleLabel = new JLabel(alarmIcon, JLabel.TRAILING);
        this.add(timeTitleLabel);
        this.add(timeLabel);

        ImageIcon heartIcon = IconImporter.getIconFromFileName("heart.png", "", new Dimension(Config.fontSize, Config.fontSize));
        JLabel healthTitleLabel = new JLabel(heartIcon, JLabel.TRAILING);
        this.add(healthTitleLabel);
        this.add(healthLabel);

        Player.getInstance().addPropertyListener("point", this);
        Player.getInstance().addPropertyListener("health", this);
        KUVidGame.getInstance().addPropertyListener("time", this);
    }

    public void setScoreLabel(double score) { //TODO changed int to double
        scoreLabel.setText("  " + (int)score);
    }

    public void setTimeLabel(int seconds) {
        int min = seconds / 60;
        int rem = seconds % 60;
        int sec = rem % 60;
        String minStr = (min < 10 ? "0" : "") + min;
        String secStr = (sec < 10 ? "0" : "") + sec;
        String timeText = minStr + ":" + secStr;
        timeLabel.setText("  " + timeText);
        
    }

    public void setHealthLabel(int health) {
        String hpText = Integer.toString(health);
        healthLabel.setText("  " + hpText);
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {
        switch (e.getPropertyName()) {
            case "point":
                //DecimalFormat df = new DecimalFormat("#.#"); //TODO added
                //double temp = (double) e.getNewValue();
                //df.format(temp);
                //double score = temp;
                double score = (double) e.getNewValue(); //TODO actually double
                this.setScoreLabel(score);
                break;
            case "health":
                int health = (int) e.getNewValue();
                this.setHealthLabel(health);
                break;
            case "time":
                int seconds = (int) e.getNewValue();
                this.setTimeLabel(seconds);
                break;
        }
    }
}
