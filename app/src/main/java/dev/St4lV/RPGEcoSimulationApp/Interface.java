package dev.St4lV.RPGEcoSimulationApp;

import dev.St4lV.RPGEcoSimulationApp.Utils.Textures;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Interface {

    public void displayInterface() {
        JFrame frame = new JFrame("RPG Economics Simulation App");
        Textures textures = new Textures();

        String backgroundImagePath = textures.getTexturePath("mcrpgeco:blockberg_terminal_gui", "gui");
        BufferedImage backgroundImage = loadImage(backgroundImagePath);

        JPanel contentPane = new JPanel(null); // Use a null layout for the content pane
        frame.setContentPane(contentPane);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        frame.getContentPane().setBackground(Color.getHSBColor(48, 75, 80));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image at the center and scale by 3 times
                int scaledWidth = backgroundImage.getWidth() * 3;
                int scaledHeight = backgroundImage.getHeight() * 3;
                int imgX = (getWidth() - scaledWidth) / 2;
                int imgY = (getHeight() - scaledHeight) / 2;
                g.drawImage(backgroundImage, imgX, imgY, scaledWidth, scaledHeight, this);
            }
        };
        backgroundPanel.setOpaque(false);
        backgroundPanel.setBounds(0, 0, backgroundImage.getWidth() * 3, backgroundImage.getHeight() * 3);
        contentPane.add(backgroundPanel);

        // Load image from resource path
        String texturePath = textures.getTexturePath("minecraft:iron_ingot", "item");
        BufferedImage image = loadImage(texturePath);

        JPanel itemIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image with transparency
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        itemIcon.setOpaque(false); // Make JPanel transparent
        itemIcon.setPreferredSize(new Dimension(64, 64));
        itemIcon.setBounds(24, 55, 48, 48);
        contentPane.add(itemIcon);

        JButton buyButton1 = new JButton("Buy");
        buyButton1.setBounds(78, 54, 70, 50);
        buyButton1.addActionListener(e -> handleBuy1(texturePath)); // Add ActionListener to button1
        contentPane.add(buyButton1);

        JButton sellButton1 = new JButton("Sell");
        sellButton1.setBounds(343, 53, 70, 50);
        sellButton1.addActionListener(e -> handleSell1(texturePath));
        contentPane.add(sellButton1);

        // Set z-order for components
        contentPane.setComponentZOrder(itemIcon, 0);
        contentPane.setComponentZOrder(buyButton1, 1);
        contentPane.setComponentZOrder(sellButton1, 2);

        // Adjust frame size and position
        frame.setSize((backgroundImage.getWidth() * 3), (backgroundImage.getHeight() * 3)); // Set frame size to match the background image size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to handle the buy action
    public static void handleBuy1(String texturePath) {
        Calculs.getInstance().buyItem();
    }
    public static void handleSell1(String texturePath) {
        Calculs.getInstance().sellItem();
    }

    // Method to load image from resource path
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
