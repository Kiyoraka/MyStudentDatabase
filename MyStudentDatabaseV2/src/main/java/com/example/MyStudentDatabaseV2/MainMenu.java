package com.example.MyStudentDatabaseV2;

import com.example.MyStudentDatabaseV2.repository.StudentRepository;
import com.example.MyStudentDatabaseV2.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton StudentBtn;
    private JButton DataBtn;
    private JButton BackBtn;
    private JPanel MAINMENU;

    private Login login;
    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private JFrame parent;
    private Login loginDialog;


    public MainMenu(JFrame parent, UserRepository userRepository, StudentRepository studentRepository) {
        this.parent = parent;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        setTitle("Main Menu");
        setContentPane(MAINMENU);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);

        StudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open The Student Registration Form
                dispose();
                StudentInfo studentInfo = new StudentInfo( parent, userRepository, studentRepository);
            }
        });

        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Back to the Login Form
                dispose();
                // Create a new Login form if it doesn't exist
                Login loginDialog = new Login(parent, userRepository, studentRepository);
                loginDialog.display();
            }
        });

        DataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Database Form
                dispose();
                Database database = new Database(studentRepository, userRepository, parent);
            }
        });
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
