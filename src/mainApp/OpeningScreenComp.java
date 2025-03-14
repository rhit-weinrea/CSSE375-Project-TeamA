package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningScreenComp {
	static final int FRAME_WIDTH = 500;
	static final int FRAME_HEIGHT = 500;
	static final Color LIGHT_GRAY = new Color(200, 200, 200);

	public OpeningScreenComp() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		JPanel imagePanel = new JPanel();

		JButton evolutionViewerButton = new JButton("Evolution Viewer");
		JButton chromosomeViewerButton = new JButton("Chromosome Viewer");
		JButton closeButton = new JButton("Close");

		Container c = frame.getContentPane();
		JLabel darwin = new JLabel();
		darwin.setIcon(new ImageIcon("darwin.jpg"));

		c.add(darwin);

		buttonPanel.add(evolutionViewerButton);
		buttonPanel.add(chromosomeViewerButton);
		buttonPanel.add(closeButton);

		imagePanel.add(darwin);

		frame.add(buttonPanel, BorderLayout.SOUTH);

		evolutionViewerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new EvolutionViewer();
			}
		});

		chromosomeViewerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					new ChromosomeViewer();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}

		});
		frame.pack();
		frame.setVisible(true);
	}
}
