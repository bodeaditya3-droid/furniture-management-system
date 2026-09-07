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

public class RentalForm {

    JFrame frame;
    JPanel panel;
    JPanel formPanel;

    JLabel title;
    JLabel shadowTitle;

    Image backgroundImage;

    RentalForm(JFrame dashboardFrame) {

        frame = new JFrame("Rental Form");



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
        shadowTitle = new JLabel("Rental Management");

        shadowTitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        shadowTitle.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        shadowTitle.setForeground(
                new Color(0, 0, 0, 100));



        // Main Title
        title = new JLabel("Rental Management");

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        title.setForeground(Color.BLACK);



        // Labels
        JLabel rentalIdLabel =
                createLabel("Rental ID");

        JLabel customerIdLabel =
                createLabel("Customer ID");

        JLabel furnitureIdLabel =
                createLabel("Furniture ID");

        JLabel rentalDateLabel =
                createLabel("Rental Date");

        JLabel returnDateLabel =
                createLabel("Return Date");



        // TextFields
        JTextField rentalIdText =
                createTextField();

        JTextField customerIdText =
                createTextField();

        JTextField furnitureIdText =
                createTextField();

        JTextField rentalDateText =
                createTextField();

        JTextField returnDateText =
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
                        "insert into rental values (?, ?, ?, ?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                rentalIdText.getText()));

                pst.setInt(
                        2,
                        Integer.parseInt(
                                customerIdText.getText()));

                pst.setInt(
                        3,
                        Integer.parseInt(
                                furnitureIdText.getText()));

                pst.setString(
                        4,
                        rentalDateText.getText());

                pst.setString(
                        5,
                        returnDateText.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Rental Added Successfully");

                clearFields(
                        rentalIdText,
                        customerIdText,
                        furnitureIdText,
                        rentalDateText,
                        returnDateText);

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
                        "select * from rental";

                PreparedStatement pst =
                        con.prepareStatement(query);

                ResultSet rs =
                        pst.executeQuery();

                String columns[] = {

                        "Rental ID",

                        "Customer ID",

                        "Furniture ID",

                        "Rental Date",

                        "Return Date"
                };

                DefaultTableModel model =
                        new DefaultTableModel(columns, 0);

                while(rs.next()) {

                    Object row[] = {

                            rs.getInt(1),

                            rs.getInt(2),

                            rs.getInt(3),

                            rs.getString(4),

                            rs.getString(5)
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
                        new JFrame("Rental Records");

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
                        "update rental set customer_id=?, furniture_id=?, rent_start=?, rent_end=? where rental_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                customerIdText.getText()));

                pst.setInt(
                        2,
                        Integer.parseInt(
                                furnitureIdText.getText()));

                pst.setString(
                        3,
                        rentalDateText.getText());

                pst.setString(
                        4,
                        returnDateText.getText());

                pst.setInt(
                        5,
                        Integer.parseInt(
                                rentalIdText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Rental Updated Successfully");

                clearFields(
                        rentalIdText,
                        customerIdText,
                        furnitureIdText,
                        rentalDateText,
                        returnDateText);

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
                        "delete from rental where rental_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(
                        1,
                        Integer.parseInt(
                                rentalIdText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Rental Deleted Successfully");

                clearFields(
                        rentalIdText,
                        customerIdText,
                        furnitureIdText,
                        rentalDateText,
                        returnDateText);

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
        formPanel.add(rentalIdLabel);
        formPanel.add(rentalIdText);

        formPanel.add(customerIdLabel);
        formPanel.add(customerIdText);

        formPanel.add(furnitureIdLabel);
        formPanel.add(furnitureIdText);

        formPanel.add(rentalDateLabel);
        formPanel.add(rentalDateText);

        formPanel.add(returnDateLabel);
        formPanel.add(returnDateText);

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

                        rentalIdLabel,
                        rentalIdText,

                        customerIdLabel,
                        customerIdText,

                        furnitureIdLabel,
                        furnitureIdText,

                        rentalDateLabel,
                        rentalDateText,

                        returnDateLabel,
                        returnDateText,

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

                rentalIdLabel,
                rentalIdText,

                customerIdLabel,
                customerIdText,

                furnitureIdLabel,
                furnitureIdText,

                rentalDateLabel,
                rentalDateText,

                returnDateLabel,
                returnDateText,

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

            JTextField rentalIdText,

            JTextField customerIdText,

            JTextField furnitureIdText,

            JTextField rentalDateText,

            JTextField returnDateText
    ) {

        rentalIdText.setText("");

        customerIdText.setText("");

        furnitureIdText.setText("");

        rentalDateText.setText("");

        returnDateText.setText("");
    }



    void resizeComponents(

            JLabel rentalIdLabel,
            JTextField rentalIdText,

            JLabel customerIdLabel,
            JTextField customerIdText,

            JLabel furnitureIdLabel,
            JTextField furnitureIdText,

            JLabel rentalDateLabel,
            JTextField rentalDateText,

            JLabel returnDateLabel,
            JTextField returnDateText,

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
                150,
                700,
                560);



        rentalIdLabel.setBounds(
                70,
                50,
                220,
                40);

        rentalIdText.setBounds(
                320,
                50,
                280,
                40);



        customerIdLabel.setBounds(
                70,
                130,
                220,
                40);

        customerIdText.setBounds(
                320,
                130,
                280,
                40);



        furnitureIdLabel.setBounds(
                70,
                210,
                220,
                40);

        furnitureIdText.setBounds(
                320,
                210,
                280,
                40);



        rentalDateLabel.setBounds(
                70,
                290,
                220,
                40);

        rentalDateText.setBounds(
                320,
                290,
                280,
                40);



        returnDateLabel.setBounds(
                70,
                370,
                220,
                40);

        returnDateText.setBounds(
                320,
                370,
                280,
                40);



        addButton.setBounds(
                40,
                430,
                110,
                45);

        viewButton.setBounds(
                170,
                430,
                110,
                45);

        updateButton.setBounds(
                300,
                430,
                120,
                45);

        deleteButton.setBounds(
                450,
                430,
                120,
                45);



        backButton.setBounds(
                290,
                495,
                120,
                45);
    }
}