package main;

import javax.swing.JFrame;

public class Main {
    private static JFrame window;

    public static void main(String[] args) throws Exception {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Test Game");
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setFullScreen();

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public static final JFrame getWindow() {
        return window;
    }
}
