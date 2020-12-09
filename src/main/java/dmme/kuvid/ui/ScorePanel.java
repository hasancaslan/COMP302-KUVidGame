package dmme.kuvid.ui;

import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel implements ActionListener, PropertyListener {
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    private String score;
    private String time;
    private String hp;

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel hpLabel;
    private String scoreText;

    public ScorePanel() {
        setOpaque(false);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));

        score = "0";
        time = "10:00";
        hp = "100";
        scoreText = "Score: ";

        scoreLabel = new JLabel(scoreText + score);
        timeLabel = new JLabel();
        hpLabel = new JLabel(scoreText + score);
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
