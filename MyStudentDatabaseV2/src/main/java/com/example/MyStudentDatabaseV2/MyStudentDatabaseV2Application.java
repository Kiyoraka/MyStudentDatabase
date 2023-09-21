package com.example.MyStudentDatabaseV2;

import com.example.MyStudentDatabaseV2.repository.UserRepository;
import com.example.MyStudentDatabaseV2.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;



import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.MyStudentDatabaseV2")
public class MyStudentDatabaseV2Application {

	public static void main(String[] args) {
		// Ensure that your application is not running in headless mode
		System.setProperty("java.awt.headless", "false");

		// Start the Spring Boot application context
		ConfigurableApplicationContext context = SpringApplication.run(MyStudentDatabaseV2Application.class, args);

		// Start the Swing UI on the EDT
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("My Student Database V1.0");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setMinimumSize(new Dimension(800, 600));
			frame.setLocationRelativeTo(null);

			// Retrieve the UserRepository bean from the Spring context
			// Retrieve the UserRepository and StudentRepository beans from the Spring context

			UserRepository userRepository = context.getBean(UserRepository.class);
			StudentRepository studentRepository = context.getBean(StudentRepository.class);

			// Create and show the login dialog
			Login loginDialog = new Login(frame, userRepository,studentRepository  );
			loginDialog.setVisible(true);
		});
	}
}