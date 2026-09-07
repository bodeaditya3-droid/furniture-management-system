package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Dashboard {

    JFrame frame;
    JPanel panel;

    JLabel title;
    JLabel shadowTitle;
    JLabel line;

    JButton customerBtn;
    JButton furnitureBtn;
    JButton rentalBtn;
    JButton paymentBtn;
    JButton exitBtn;

    Image backgroundImage;

    Dashboard() {

        frame = new JFrame("Furniture Rental System Dashboard");



        // Load Background Image
        ImageIcon bg = new ImageIcon("dash.jpg");

        backgroundImage = bg.getImage();



        // Custom Background Panel
        panel = new JPanel() {

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
        shadowTitle = new JLabel("Furniture Rental System");

        shadowTitle.setHorizontalAlignment(SwingConstants.CENTER);

        shadowTitle.setFont(new Font("Montserrat", Font.BOLD, 42));

        shadowTitle.setForeground(new Color(0, 0, 0, 100));



        // Main Title
        title = new JLabel("Furniture Rental System");

        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setFont(new Font("Montserrat", Font.BOLD, 42));

        title.setForeground(Color.BLACK);



        // Decorative Line
        line = new JLabel("________________________________");

        line.setHorizontalAlignment(SwingConstants.CENTER);

        line.setFont(new Font("Montserrat", Font.PLAIN, 20));

        line.setForeground(new Color(40, 40, 40));



        // Buttons
        customerBtn = createButton("Customers");

        furnitureBtn = createButton("Furniture");

        rentalBtn = createButton("Rentals");

        paymentBtn = createButton("Payments");



        // Exit Button
        exitBtn = new JButton("Exit System");

        exitBtn.setFont(new Font("Montserrat", Font.BOLD, 24));

        exitBtn.setFocusPainted(false);

        exitBtn.setBackground(new Color(220, 70, 70));

        exitBtn.setForeground(Color.WHITE);

        exitBtn.setBorder(BorderFactory.createEmptyBorder());



        // Exit Hover Effect
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                exitBtn.setBackground(new Color(180, 40, 40));

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                exitBtn.setBackground(new Color(220, 70, 70));

            }
        });



        // Button Actions
        customerBtn.addActionListener(e -> {

            frame.setVisible(false);

            new CustomerForm(frame);

        });




        furnitureBtn.addActionListener(e -> {

            frame.setVisible(false);

            new FurnitureForm(frame);

        });



        rentalBtn.addActionListener(e -> {

            frame.setVisible(false);

            new RentalForm(frame);

        });;



        paymentBtn.addActionListener(e -> {

            frame.setVisible(false);

            new PaymentForm(frame);

        });


        exitBtn.addActionListener(e -> {

            System.exit(0);

        });



        // Add Components
        panel.add(shadowTitle);

        panel.add(title);

        panel.add(line);

        panel.add(customerBtn);

        panel.add(furnitureBtn);

        panel.add(rentalBtn);

        panel.add(paymentBtn);

        panel.add(exitBtn);



        frame.add(panel);



        // Resize Listener
        frame.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent e) {

                resizeComponents();

            }
        });



        frame.setSize(1000, 750);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        resizeComponents();
    }



    // Modern Button Design
    JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Montserrat", Font.BOLD, 22));

        button.setFocusPainted(false);

        button.setBackground(new Color(255, 255, 255, 220));

        button.setForeground(Color.BLACK);

        button.setBorder(BorderFactory.createEmptyBorder());



        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                button.setBackground(new Color(230, 230, 230));

                button.setForeground(new Color(20, 20, 20));

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                button.setBackground(new Color(255, 255, 255, 220));

                button.setForeground(Color.BLACK);

            }
        });

        return button;
    }



    // Responsive Layout
    void resizeComponents() {

        int width = frame.getWidth();

        int height = frame.getHeight();



        shadowTitle.setBounds(width / 2 - 347, 43, 700, 60);

        title.setBounds(width / 2 - 350, 40, 700, 60);

        line.setBounds(width / 2 - 250, 80, 500, 40);



        customerBtn.setBounds(width / 2 - 180, 170, 360, 65);

        furnitureBtn.setBounds(width / 2 - 180, 270, 360, 65);

        rentalBtn.setBounds(width / 2 - 180, 370, 360, 65);

        paymentBtn.setBounds(width / 2 - 180, 470, 360, 65);

        exitBtn.setBounds(width / 2 - 180, 590, 360, 70);
    }
}