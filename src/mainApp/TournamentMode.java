package mainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TournamentMode {
    public TournamentMode() {
        // Create the main tournament frame
        JFrame tournamentFrame = new JFrame("Tournament Mode");
        tournamentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tournamentFrame.setLayout(new BorderLayout());


        JPanel viewerPanel = new JPanel();
        viewerPanel.setLayout(new GridLayout(1, 2));

        EvolutionViewer viewer1 = new EvolutionViewer();
        EvolutionViewer viewer2 = new EvolutionViewer();

        JPanel viewerContainer1 = new JPanel(new BorderLayout());
        JPanel viewerContainer2 = new JPanel(new BorderLayout());

        viewerContainer1.add(viewer1, BorderLayout.CENTER);
        viewerContainer2.add(viewer2, BorderLayout.CENTER);

        viewerPanel.add(viewerContainer1);
        viewerPanel.add(viewerContainer2);

        // Shared Start button at the bottom
        JButton startButton = new JButton("Start Both");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer1.getViewerSwingComponents().enterButton.doClick();
                viewer2.getViewerSwingComponents().enterButton.doClick();
            }
        });

        tournamentFrame.add(viewerPanel, BorderLayout.CENTER);
        tournamentFrame.add(startButton, BorderLayout.SOUTH);

        tournamentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tournamentFrame.setVisible(true);
    }
}
