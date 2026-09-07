package ui;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FurnitureForm {

    JFrame frame;
    JPanel panel;
    JPanel formPanel;

    JLabel title;
    JLabel shadowTitle;

    JLabel idLabel;
    JLabel nameLabel;
    JLabel typeLabel;
    JLabel rentLabel;
    JLabel statusLabel;

    JTextField idText;
    JTextField nameText;
    JTextField typeText;
    JTextField rentText;
    JTextField statusText;

    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    JButton backButton;

    Image backgroundImage;

    FurnitureForm(JFrame dashboardFrame) {

        frame = new JFrame("Furniture Form");



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



        // Form Card Panel
        formPanel = new JPanel();

        formPanel.setLayout(null);

        formPanel.setBackground(
                new Color(255, 255, 255, 220));



        // Shadow Title
        shadowTitle = new JLabel("Furniture Management");

        shadowTitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        shadowTitle.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        shadowTitle.setForeground(
                new Color(0, 0, 0, 100));



        // Main Title
        title = new JLabel("Furniture Management");

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        title.setForeground(Color.BLACK);



        // Labels
        idLabel = createLabel("Furniture ID");

        nameLabel = createLabel("Furniture Name");

        typeLabel = createLabel("Furniture Type");

        rentLabel = createLabel("Rent Per Day");

        statusLabel = createLabel("Availability");



        // Text Fields
        idText = createTextField();

        nameText = createTextField();

        typeText = createTextField();

        rentText = createTextField();

        statusText = createTextField();



        // Buttons
        addButton = createButton("Add");

        viewButton = createButton("View");

        updateButton = createButton("Update");

        deleteButton = createButton("Delete");

        backButton = createButton("Back");



        // Add Action
        addButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                        "insert into furniture values (?, ?, ?, ?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1,
                        Integer.parseInt(idText.getText()));

                pst.setString(2, nameText.getText());

                pst.setString(3, typeText.getText());

                pst.setDouble(4,
                        Double.parseDouble(rentText.getText()));

                pst.setString(5, statusText.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Furniture Added Successfully");

                clearFields();

                con.close();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // View Action
        viewButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query = "select * from furniture";

                PreparedStatement pst =
                        con.prepareStatement(query);

                ResultSet rs = pst.executeQuery();

                String columns[] = {

                        "Furniture ID",

                        "Furniture Name",

                        "Furniture Type",

                        "Rent Per Day",

                        "Availability"
                };

                DefaultTableModel model =
                        new DefaultTableModel(columns, 0);

                while(rs.next()) {

                    Object row[] = {

                            rs.getInt(1),

                            rs.getString(2),

                            rs.getString(3),

                            rs.getDouble(4),

                            rs.getString(5)
                    };

                    model.addRow(row);
                }

                JTable table = new JTable(model);

                table.setFont(
                        new Font("Montserrat", Font.PLAIN, 15));

                table.setRowHeight(28);

                JScrollPane scrollPane =
                        new JScrollPane(table);

                JFrame tableFrame =
                        new JFrame("Furniture Records");

                tableFrame.add(scrollPane);

                tableFrame.setSize(700, 400);

                tableFrame.setVisible(true);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // Update Action
        updateButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                        "update furniture set furniture_name=?, category=?, rent_per_month=?, availability=? where furniture_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setString(1, nameText.getText());

                pst.setString(2, typeText.getText());

                pst.setDouble(3,
                        Double.parseDouble(rentText.getText()));

                pst.setString(4, statusText.getText());

                pst.setInt(5,
                        Integer.parseInt(idText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Furniture Updated Successfully");

                clearFields();

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // Delete Action
        deleteButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                        "delete from furniture where furniture_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1,
                        Integer.parseInt(idText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Furniture Deleted Successfully");

                clearFields();

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // Back Button
        backButton.addActionListener(e -> {

            frame.dispose();

            dashboardFrame.setVisible(true);

        });



        // Add Components
        formPanel.add(idLabel);

        formPanel.add(nameLabel);

        formPanel.add(typeLabel);

        formPanel.add(rentLabel);

        formPanel.add(statusLabel);



        formPanel.add(idText);

        formPanel.add(nameText);

        formPanel.add(typeText);

        formPanel.add(rentText);

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



    JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Montserrat", Font.BOLD, 20));

        label.setForeground(Color.BLACK);

        return label;
    }



    JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Montserrat", Font.PLAIN, 17));

        return field;
    }



    JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Montserrat", Font.BOLD, 17));

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



    void clearFields() {

        idText.setText("");

        nameText.setText("");

        typeText.setText("");

        rentText.setText("");

        statusText.setText("");
    }



    void resizeComponents() {

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



        formPanel.setBounds(
                width / 2 - 350,
                150,
                700,
                560);



        idLabel.setBounds(70, 50, 220, 40);

        idText.setBounds(320, 50, 280, 40);



        nameLabel.setBounds(70, 130, 220, 40);

        nameText.setBounds(320, 130, 280, 40);



        typeLabel.setBounds(70, 210, 220, 40);

        typeText.setBounds(320, 210, 280, 40);



        rentLabel.setBounds(70, 290, 220, 40);

        rentText.setBounds(320, 290, 280, 40);



        statusLabel.setBounds(70, 370, 220, 40);

        statusText.setBounds(320, 370, 280, 40);



        addButton.setBounds(40, 430, 110, 45);

        viewButton.setBounds(170, 430, 110, 45);

        updateButton.setBounds(300, 430, 120, 45);

        deleteButton.setBounds(450, 430, 120, 45);



        backButton.setBounds(280, 495, 120, 45);
    }
}