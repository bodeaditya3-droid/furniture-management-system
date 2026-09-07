package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginPage {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Furniture Rental System");



        // Background Image
        ImageIcon bg = new ImageIcon("dash.jpg");

        Image backgroundImage = bg.getImage();



        // Background Panel
        JPanel panel = new JPanel() {

            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.drawImage(
                        backgroundImage,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );
            }
        };



        panel.setLayout(null);



        // Shadow Title
        JLabel shadowTitle =
                new JLabel("Furniture Rental System");

        shadowTitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        shadowTitle.setFont(
                new Font("Montserrat", Font.BOLD, 42));

        shadowTitle.setForeground(
                new Color(0, 0, 0, 100));



        // Main Title
        JLabel title =
                new JLabel("Furniture Rental System");

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font("Montserrat", Font.BOLD, 42));

        title.setForeground(Color.BLACK);



        // Login Card
        JPanel loginPanel = new JPanel();

        loginPanel.setLayout(null);

        loginPanel.setBackground(
                new Color(255, 255, 255, 220));



        // Login Heading
        JLabel loginLabel = new JLabel("Login");

        loginLabel.setFont(
                new Font("Montserrat", Font.BOLD, 30));

        loginLabel.setForeground(Color.BLACK);



        // Username Label
        JLabel userLabel = new JLabel("Username");

        userLabel.setFont(
                new Font("Montserrat", Font.BOLD, 18));



        // Username Field
        JTextField userText = new JTextField();

        userText.setFont(
                new Font("Montserrat", Font.PLAIN, 16));



        // Password Label
        JLabel passLabel = new JLabel("Password");

        passLabel.setFont(
                new Font("Montserrat", Font.BOLD, 18));



        // Password Field
        JPasswordField passText =
                new JPasswordField();

        passText.setFont(
                new Font("Montserrat", Font.PLAIN, 16));



        // Login Button
        JButton loginButton = new JButton("Login");

        loginButton.setFont(
                new Font("Montserrat", Font.BOLD, 18));

        loginButton.setFocusPainted(false);

        loginButton.setBackground(
                new Color(40, 40, 40));

        loginButton.setForeground(Color.WHITE);

        loginButton.setBorder(
                BorderFactory.createEmptyBorder());



        // Hover Effect
        loginButton.addMouseListener(
                new java.awt.event.MouseAdapter() {

            public void mouseEntered(
                    java.awt.event.MouseEvent evt) {

                loginButton.setBackground(
                        new Color(70, 70, 70));

            }

            public void mouseExited(
                    java.awt.event.MouseEvent evt) {

                loginButton.setBackground(
                        new Color(40, 40, 40));

            }
        });



        // Login Action
        loginButton.addActionListener(e -> {

            String username = userText.getText();

            String password = passText.getText();



            if(username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Login Successful");

                new Dashboard();

                frame.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Username or Password");
            }
        });



        // Add Components
        loginPanel.add(loginLabel);

        loginPanel.add(userLabel);

        loginPanel.add(userText);

        loginPanel.add(passLabel);

        loginPanel.add(passText);

        loginPanel.add(loginButton);



        panel.add(shadowTitle);

        panel.add(title);

        panel.add(loginPanel);



        frame.add(panel);



        // Responsive Resize
        frame.addComponentListener(
                new ComponentAdapter() {

            public void componentResized(
                    ComponentEvent e) {

                int width = frame.getWidth();

                int height = frame.getHeight();



                shadowTitle.setBounds(
                        width / 2 - 347,
                        63,
                        700,
                        60);

                title.setBounds(
                        width / 2 - 350,
                        60,
                        700,
                        60);



                loginPanel.setBounds(
                        width / 2 - 220,
                        height / 2 - 150,
                        440,
                        340);



                loginLabel.setBounds(
                        170,
                        20,
                        200,
                        40);



                userLabel.setBounds(
                        50,
                        90,
                        120,
                        30);

                userText.setBounds(
                        50,
                        125,
                        340,
                        40);



                passLabel.setBounds(
                        50,
                        185,
                        120,
                        30);

                passText.setBounds(
                        50,
                        220,
                        340,
                        40);



                loginButton.setBounds(
                        120,
                        280,
                        200,
                        45);
            }
        });



        frame.setSize(1000, 700);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}