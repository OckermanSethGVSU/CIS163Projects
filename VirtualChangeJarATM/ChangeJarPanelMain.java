package ChangeJarPro;;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeJarPanelMain extends JPanel {

	private JMenuItem quitItem;
	private JMenuItem suspendItem;

	public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {
		JPanel panel = new JPanel();
		ChangeJarPanel first = new ChangeJarPanel();
		ChangeJarPanel second = new ChangeJarPanel();
		ChangeJarPanel third = new ChangeJarPanel();
		first.setPreferredSize(new Dimension(400,600));
		second.setPreferredSize(new Dimension(400,600));
		third.setPreferredSize(new Dimension(400,600));
		panel.add(first);
		panel.add(second);
		panel.add(third);
		add(panel);

		this.quitItem = quitItem;
		this.suspendItem = suspendItem;

		quitItem.addActionListener(new Mylistener());
		suspendItem.addActionListener(new Mylistener());
	}

	private class Mylistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == quitItem){
				System.exit(1);
			}
			if(e.getSource() == suspendItem){
				ChangeJar.mutate(suspendItem.isSelected());
			}
		}

	}
}
