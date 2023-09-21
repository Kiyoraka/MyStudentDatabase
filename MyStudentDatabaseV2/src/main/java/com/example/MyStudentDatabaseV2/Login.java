package com.example.MyStudentDatabaseV2;


import com.example.MyStudentDatabaseV2.model.User;
import com.example.MyStudentDatabaseV2.repository.StudentRepository;
import com.example.MyStudentDatabaseV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JDialog {

    private JTextField TFUserId;
    private JButton btnlogin;
    private JLabel lblpassword;
    private JLabel lbluserID;
    private JPanel PLogin;
    private JButton closeButton;
    private JPasswordField JPUserPassword;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public Login(JFrame parent, UserRepository userRepository,StudentRepository studentRepository ) {
        super(parent);
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        setTitle("My Student Database V1.0");
        setContentPane(PLogin);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(parent);

        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the user data from the textfield
                String userId = TFUserId.getText();
                String password = String.valueOf(JPUserPassword.getPassword());

                //Retrieve the data from the User class
                User user = userRepository.findByUserIdAndPassword(userId, password);

                try{

                     if (user != null) {
                        // Authentication successful
                        // Perform actions for successful login
                        JOptionPane.showMessageDialog(Login.this,
                                "Authentication Success.",
                                "Success",
                                 JOptionPane.PLAIN_MESSAGE);
                        //Close the login form, clear all the textfield and proceed to open main menu
                        dispose();
                        TFUserId.setText("");
                        JPUserPassword.setText("");
                        MainMenu mainMenu = new MainMenu(parent, userRepository, studentRepository);
                        mainMenu.setVisible(true);

                    } else {
                         // Authentication failed
                         // Show an error message or handle authentication failure
                         JOptionPane.showMessageDialog(Login.this,
                                 "Authentication failed. Please check your credentials.",
                                 "Login Failed",
                                 JOptionPane.ERROR_MESSAGE);
                     }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(Login.this,
                        "Are you sure?",
                        "Close",
                        JOptionPane.YES_NO_OPTION); //showing dialog box to confirm the user choice

                if (choice == JOptionPane.YES_OPTION)
                {
                    //Close the System
                    System.exit(0);
                }

            }
        });
    }

    public Login()
    {

    }

    //Call the method display
    public void display() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

}
