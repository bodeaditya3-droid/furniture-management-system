package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomerForm {

    JFrame frame;
    JPanel panel;
    JPanel formPanel;

    JLabel title;
    JLabel shadowTitle;

    JLabel idLabel;
    JLabel nameLabel;
    JLabel phoneLabel;
    JLabel addressLabel;

    JTextField idText;
    JTextField nameText;
    JTextField phoneText;
    JTextField addressText;

    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    JButton backButton;

    Image backgroundImage;

    CustomerForm(JFrame dashboardFrame) {

        frame = new JFrame("Customer Form");



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
        shadowTitle = new JLabel("Customer Management");

        shadowTitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        shadowTitle.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        shadowTitle.setForeground(
                new Color(0, 0, 0, 100));



        // Main Title
        title = new JLabel("Customer Management");

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font("Montserrat", Font.BOLD, 38));

        title.setForeground(Color.BLACK);



        // Labels
        idLabel = createLabel("Customer ID");

        nameLabel = createLabel("Customer Name");

        phoneLabel = createLabel("Phone Number");

        addressLabel = createLabel("Address");



        // Text Fields
        idText = createTextField();

        nameText = createTextField();

        phoneText = createTextField();

        addressText = createTextField();



        // Buttons
        addButton = createButton("Add");

        viewButton = createButton("View");

        updateButton = createButton("Update");

        deleteButton = createButton("Delete");

        backButton = createButton("Back");



        // Add Button Action
        addButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                        "insert into customer values (?, ?, ?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1,
                        Integer.parseInt(idText.getText()));

                pst.setString(2, nameText.getText());

                pst.setString(3, phoneText.getText());

                pst.setString(4, addressText.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Added Successfully");

                clearFields();

                con.close();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // View Button Action
        viewButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query = "select * from customer";

                PreparedStatement pst =
                        con.prepareStatement(query);

                ResultSet rs = pst.executeQuery();

                String columns[] = {
                        "Customer ID",
                        "Name",
                        "Phone",
                        "Address"
                };

                DefaultTableModel model =
                        new DefaultTableModel(columns, 0);

                while(rs.next()) {

                    Object row[] = {

                            rs.getInt(1),

                            rs.getString(2),

                            rs.getString(3),

                            rs.getString(4)
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
                        new JFrame("Customer Records");

                tableFrame.add(scrollPane);

                tableFrame.setSize(700, 400);

                tableFrame.setVisible(true);

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // Update Button Action
        updateButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                		"update customer set customer_name=?, phone=?, city=? where customer_id=?";
                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setString(1, nameText.getText());

                pst.setString(2, phoneText.getText());

                pst.setString(3, addressText.getText());

                pst.setInt(4,
                        Integer.parseInt(idText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Updated Successfully");

                clearFields();

                con.close();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(frame, ex);

            }

        });



        // Delete Button Action
        deleteButton.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/furniture_rental",
                        "root",
                        "Mysql@123");

                String query =
                        "delete from customer where customer_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1,
                        Integer.parseInt(idText.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Deleted Successfully");

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

        formPanel.add(phoneLabel);

        formPanel.add(addressLabel);

        formPanel.add(idText);

        formPanel.add(nameText);

        formPanel.add(phoneText);

        formPanel.add(addressText);

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



    // Label Design
    JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Montserrat", Font.BOLD, 20));

        label.setForeground(Color.BLACK);

        return label;
    }



    // TextField Design
    JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Montserrat", Font.PLAIN, 17));

        return field;
    }



    // Button Design
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



    // Clear Fields
    void clearFields() {

        idText.setText("");

        nameText.setText("");

        phoneText.setText("");

        addressText.setText("");
    }



    // Responsive Layout
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
                540);



        idLabel.setBounds(70, 70, 200, 40);

        idText.setBounds(320, 70, 280, 40);



        nameLabel.setBounds(70, 150, 200, 40);

        nameText.setBounds(320, 150, 280, 40);



        phoneLabel.setBounds(70, 230, 200, 40);

        phoneText.setBounds(320, 230, 280, 40);



        addressLabel.setBounds(70, 310, 200, 40);

        addressText.setBounds(320, 310, 280, 40);



        addButton.setBounds(40, 430, 110, 45);

        viewButton.setBounds(170, 430, 110, 45);

        updateButton.setBounds(300, 430, 120, 45);

        deleteButton.setBounds(450, 430, 120, 45);

        backButton.setBounds(280, 490, 120, 45);
    }
}