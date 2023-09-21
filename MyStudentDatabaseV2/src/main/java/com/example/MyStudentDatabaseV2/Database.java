package com.example.MyStudentDatabaseV2;

import com.example.MyStudentDatabaseV2.model.Student;
import com.example.MyStudentDatabaseV2.repository.StudentRepository;
import com.example.MyStudentDatabaseV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Database extends JFrame {

    private JButton BackBtn;
    private JLabel titlelbl;

    private JPanel DataPanel;
    private JTable table1;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    private JFrame parent;

    public Database(StudentRepository studentRepository, UserRepository userRepository, JFrame parent) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.parent = parent;

        setTitle("Database");
        setContentPane(DataPanel);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null); // Center the form on the screen
        setVisible(true); // Make the form visible

        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu(parent, userRepository, studentRepository);
                mainMenu.setVisible(true);
            }
        });

        // Fetch data and populate the table
        fetchDataAndPopulateTable();
    }

    private void fetchDataAndPopulateTable() {
        // Fetch data from the database using your repository
        List<Student> students = (List<Student>) studentRepository.findAll();

        // Create a DefaultTableModel with column names
        String[] columnNames = {"IC No", "Name", "Hp No", "Course"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate the table model with data
        for (Student student : students) {
            Object[] rowData = {student.getICNo(), student.getName(), student.getHpNo(), student.getCourse()};
            model.addRow(rowData);
        }

        // Set the model for your JTable
        table1.setModel(model);
    }
}
