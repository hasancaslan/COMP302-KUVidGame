package dmme.kuvid.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class PauseWindow {
	
	public static JDialog infoBox(String infoMessage, String titleBar)
    {
		JOptionPane optionPane = new JOptionPane(infoMessage, JOptionPane.WARNING_MESSAGE);

		final JDialog dialog = new JDialog();
		dialog.setTitle(titleBar);

		dialog.setContentPane(optionPane);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		
		return dialog;
    }

}
