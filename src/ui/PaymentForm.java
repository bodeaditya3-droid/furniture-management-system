package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PaymentForm {

    JFrame frame;
    JPanel panel;
    JPanel formPanel;

    JLabel title;
    JLabel shadowTitle;

    Image backgroundImage;

    PaymentForm(JFrame dashboardFrame) {

        frame = new JFrame("Payment Form");



        // Background Image
        ImageIcon bg = new ImageIcon("dash.jpg");

        backgroundImage = bg.getImage();



        // Background Panel
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



        // Glass Form Panel
        formPanel = new JPanel();

        formPanel.setLayout(null);

        formPanel.setBackground(
                new Color(255, 255, 255, 220));



        // Shadow Title
        shadowTitle = new JLabel("Payment Management");

        shadowTitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        shadowTitle.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        shadowTitle.setForeground(
                new Color(0, 0, 0, 100));



        // Main Title
        title = new JLabel("Payment Management");

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        title.setForeground(Color.BLACK);



        // Labels
        JLabel paymentIdLabel =
                createLabel("Payment ID");

        JLabel rentalIdLabel =
                createLabel("Rental ID");

        JLabel amountLabel =
                createLabel("Amount");

        JLabel statusLabel =
                createLabel("Payment Status");



        // TextFields
        JTextField paymentIdText =
                createTextField();

        JTextField rentalIdText =
                createTextField();

        JTextField amountText =
                createTextField();

        JTextField statusText =
                createTextField();



        // Buttons
        JButton addButton =
                createButton("Add");

        JButton viewButton =
                createButton("View");

        JButton updateButton =
                createButton("Update");

        JButton deleteButton =
                createButton("Delete");

        JButton backButton =
                createButton("Back");



        // Add Action
        addButton.addActionListener(e -> {

            try {

                Connection con =
                        DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/furniture_rental",
                                "root",
                                "Mysql@123");

                String query =
                        "insert into payment values (?, ?, ?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                paymentIdText.getText()));

                pst.setInt(
                        2,
                        Integer.parseInt(
                                rentalIdText.getText()));

                pst.setDouble(
                        3,
                        Double.parseDouble(
                                amountText.getText()));

                pst.setString(
                        4,
                        statusText.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Payment Added Successfully");

                clearFields(
                        paymentIdText,
                        rentalIdText,
                        amountText,
                        statusText);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex);
            }
        });



        // View Action
        viewButton.addActionListener(e -> {

            try {

                Connection con =
                        DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/furniture_rental",
                                "root",
                                "Mysql@123");

                String query =
                        "select * from payment";

                PreparedStatement pst =
                        con.prepareStatement(query);

                ResultSet rs =
                        pst.executeQuery();

                String columns[] = {

                        "Payment ID",

                        "Rental ID",

                        "Amount",

                        "Payment Status"
                };

                DefaultTableModel model =
                        new DefaultTableModel(columns, 0);

                while(rs.next()) {

                    Object row[] = {

                            rs.getInt(1),

                            rs.getInt(2),

                            rs.getDouble(3),

                            rs.getString(4)
                    };

                    model.addRow(row);
                }

                JTable table =
                        new JTable(model);

                table.setFont(
                        new Font(
                                "Montserrat",
                                Font.PLAIN,
                                15));

                table.setRowHeight(28);

                JScrollPane scrollPane =
                        new JScrollPane(table);

                JFrame tableFrame =
                        new JFrame("Payment Records");

                tableFrame.add(scrollPane);

                tableFrame.setSize(700, 400);

                tableFrame.setVisible(true);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex);
            }
        });



        // Update Action
        updateButton.addActionListener(e -> {

            try {

                Connection con =
                        DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/furniture_rental",
                                "root",
                                "Mysql@123");

                String query =
                        "update payment set rental_id=?, amount=?, payment_status=? where payment_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                rentalIdText.getText()));

                pst.setDouble(
                        2,
                        Double.parseDouble(
                                amountText.getText()));

                pst.setString(
                        3,
                        statusText.getText());

                pst.setInt(
                        4,
                        Integer.parseInt(
                                paymentIdText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Payment Updated Successfully");

                clearFields(
                        paymentIdText,
                        rentalIdText,
                        amountText,
                        statusText);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex);
            }
        });



        // Delete Action
        deleteButton.addActionListener(e -> {

            try {

                Connection con =
                        DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/furniture_rental",
                                "root",
                                "Mysql@123");

                String query =
                        "delete from payment where payment_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                paymentIdText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Payment Deleted Successfully");

                clearFields(
                        paymentIdText,
                        rentalIdText,
                        amountText,
                        statusText);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex);
            }
        });



        // Back Button
        backButton.addActionListener(e -> {

            frame.dispose();

            dashboardFrame.setVisible(true);

        });



        // Add Components
        formPanel.add(paymentIdLabel);
        formPanel.add(paymentIdText);

        formPanel.add(rentalIdLabel);
        formPanel.add(rentalIdText);

        formPanel.add(amountLabel);
        formPanel.add(amountText);

        formPanel.add(statusLabel);
        formPanel.add(statusText);

        formPanel.add(addButton);
        formPanel.add(viewButton);
        formPanel.add(updateButton);
        formPanel.add(deleteButton);
        formPanel.add(backButton);

        panel.add(shadowTitle);
        panel.add(title);
        panel.add(formPanel);

        frame.add(panel);



        // Resize Listener
        frame.addComponentListener(
                new ComponentAdapter() {

            public void componentResized(
                    ComponentEvent e) {

                resizeComponents(

                        paymentIdLabel,
                        paymentIdText,

                        rentalIdLabel,
                        rentalIdText,

                        amountLabel,
                        amountText,

                        statusLabel,
                        statusText,

                        addButton,
                        viewButton,
                        updateButton,
                        deleteButton,
                        backButton
                );
            }
        });



        frame.setSize(1000, 750);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);



        resizeComponents(

                paymentIdLabel,
                paymentIdText,

                rentalIdLabel,
                rentalIdText,

                amountLabel,
                amountText,

                statusLabel,
                statusText,

                addButton,
                viewButton,
                updateButton,
                deleteButton,
                backButton
        );
    }



    JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Montserrat",
                        Font.BOLD,
                        20));

        label.setForeground(Color.BLACK);

        return label;
    }



    JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Montserrat",
                        Font.PLAIN,
                        17));

        return field;
    }



    JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Montserrat",
                        Font.BOLD,
                        17));

        button.setFocusPainted(false);

        button.setBackground(
                new Color(40, 40, 40));

        button.setForeground(Color.WHITE);

        button.setBorder(
                BorderFactory.createEmptyBorder());



        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

            public void mouseEntered(
                    java.awt.event.MouseEvent evt) {

                button.setBackground(
                        new Color(70, 70, 70));
            }

            public void mouseExited(
                    java.awt.event.MouseEvent evt) {

                button.setBackground(
                        new Color(40, 40, 40));
            }
        });

        return button;
    }



    void clearFields(

            JTextField paymentIdText,

            JTextField rentalIdText,

            JTextField amountText,

            JTextField statusText
    ) {

        paymentIdText.setText("");

        rentalIdText.setText("");

        amountText.setText("");

        statusText.setText("");
    }



    void resizeComponents(

            JLabel paymentIdLabel,
            JTextField paymentIdText,

            JLabel rentalIdLabel,
            JTextField rentalIdText,

            JLabel amountLabel,
            JTextField amountText,

            JLabel statusLabel,
            JTextField statusText,

            JButton addButton,
            JButton viewButton,
            JButton updateButton,
            JButton deleteButton,
            JButton backButton
    ) {

        int width = frame.getWidth();



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



        formPanel.setBounds(
                width / 2 - 350,
                170,
                700,
                470);



        paymentIdLabel.setBounds(
                70,
                60,
                220,
                40);

        paymentIdText.setBounds(
                320,
                60,
                280,
                40);



        rentalIdLabel.setBounds(
                70,
                140,
                220,
                40);

        rentalIdText.setBounds(
                320,
                140,
                280,
                40);



        amountLabel.setBounds(
                70,
                220,
                220,
                40);

        amountText.setBounds(
                320,
                220,
                280,
                40);



        statusLabel.setBounds(
                70,
                300,
                220,
                40);

        statusText.setBounds(
                320,
                300,
                280,
                40);



        addButton.setBounds(
                40,
                370,
                110,
                45);

        viewButton.setBounds(
                170,
                370,
                110,
                45);

        updateButton.setBounds(
                300,
                370,
                120,
                45);

        deleteButton.setBounds(
                450,
                370,
                120,
                45);



        backButton.setBounds(
                290,
                420,
                120,
                40);
    }
}