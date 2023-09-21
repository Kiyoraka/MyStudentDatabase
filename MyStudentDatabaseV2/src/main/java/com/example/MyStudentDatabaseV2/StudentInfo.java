package com.example.MyStudentDatabaseV2;

import com.example.MyStudentDatabaseV2.model.Student;
import com.example.MyStudentDatabaseV2.repository.StudentRepository;
import com.example.MyStudentDatabaseV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class StudentInfo extends JDialog {

    private JPanel StudentInfo;
    private JLabel ICNolbl;
    private JLabel Namelbl;
    private JTextField ICNoTF;
    private JTextField NameTF;
    private JTextField HpNoTF;
    private JComboBox CourseCB;
    private JButton RegisterBtn;
    private JButton SearchBtn;
    private JButton UpdateBtn;
    private JButton DeleteBtn;
    private JButton ClearBtn;
    private JButton BackBtn;

    private Login login;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    private JFrame parent;

    public StudentInfo (JFrame parent, UserRepository userRepository, StudentRepository studentRepository) {
        this.parent = parent;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        setTitle("Student Information");
        setContentPane(StudentInfo);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null); // Center the form on the screen
        setVisible(true);

        RegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get student information from form fields
                String icNo = ICNoTF.getText();
                String name = NameTF.getText();
                String hpNo = HpNoTF.getText();
                String course = (String) CourseCB.getSelectedItem();

                // Validate input
                if (icNo.isEmpty() || name.isEmpty() || hpNo.isEmpty() || course == null) {
                    JOptionPane.showMessageDialog(StudentInfo.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop registration if any field is empty
                }

                try {
                    studentRepository.SaveStudent(icNo, name, hpNo, course);

                    // Display a message to indicate successful registration
                    JOptionPane.showMessageDialog(StudentInfo.this, "Student registered successfully.");

                    // Clear the form fields
                    clearFields();
                } catch (Exception ex) {
                    // Handle database or other errors
                    JOptionPane.showMessageDialog(StudentInfo.this, "An error occurred during registration.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Print the error for debugging (consider logging it)
                }
            }
        });

        SearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ICNo = ICNoTF.getText();

                // Validate input
                if (ICNo.isEmpty() ) {
                    JOptionPane.showMessageDialog(StudentInfo.this, "Please fill in IC No fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop searching if ICNo field is empty
                }

                try{
                    List<Student> students = studentRepository.findByICNo(ICNo);

                    if(!students.isEmpty()) {
                        // Retrieved the student data
                        Student firstStudent = students.get(0);
                        String Name = firstStudent.getName();
                        String HpNo = firstStudent.getHpNo();
                        String Course = firstStudent.getCourse();

                        // Set the retrieved information in the text fields and combo box
                        NameTF.setText(Name);
                        HpNoTF.setText(HpNo);
                        CourseCB.setSelectedItem(Course);
                    }
                    else {
                        // Handle the case where no students were found
                        JOptionPane.showMessageDialog(StudentInfo.this, "No matching student found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        // Clear the text fields and combo box
                        NameTF.setText("");
                        HpNoTF.setText("");
                        CourseCB.setSelectedItem(0);
                    }

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentInfo.this, "An error occurred during Search.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Print the error for debugging (consider logging it)
                }


            }
        });
        UpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get student information from form fields
                String icNo = ICNoTF.getText();
                String name = NameTF.getText();
                String hpNo = HpNoTF.getText();
                String course = (String) CourseCB.getSelectedItem();

                if (icNo.isEmpty() || name.isEmpty() || hpNo.isEmpty() || course == null) {
                    JOptionPane.showMessageDialog(StudentInfo.this, "Please fill in the required fields.", "Error", JOptionPane.ERROR_MESSAGE);

                }

                try {
                    studentRepository.UpdateStudent(icNo, name, hpNo, course);

                    // Display a message to indicate successful registration
                    JOptionPane.showMessageDialog(StudentInfo.this, "Student data saved successfully.");

                    // Clear the form fields
                    clearFields();
                } catch (Exception ex) {
                    // Handle database or other errors
                    JOptionPane.showMessageDialog(StudentInfo.this, "An error occurred during saved.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Print the error for debugging (consider logging it)
                }

            }
        });
        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the data from textfield
                String icNo = ICNoTF.getText();

                try {
                    studentRepository.DeleteStudent(icNo);

                    // Display a message to indicate successful Deletion
                    JOptionPane.showMessageDialog(StudentInfo.this, "Student data deleted successfully.");

                    // Clear the form fields
                    clearFields();
                } catch (Exception ex) {
                    // Handle database or other errors
                    JOptionPane.showMessageDialog(StudentInfo.this, "An error occurred during deleting.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Print the error for debugging (consider logging it)
                }

            }
        });
        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Back To the Main Menu Form
                dispose();
                MainMenu mainMenu = new MainMenu(parent, userRepository, studentRepository);
                mainMenu.setVisible(true);

            }
        });


    }

    private void clearFields() {
        ICNoTF.setText("");
        NameTF.setText("");
        HpNoTF.setText("");
        CourseCB.setSelectedIndex(0); // Reset the course selection
    }
}
