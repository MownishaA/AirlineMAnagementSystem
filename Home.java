package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;

public class Home extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    Button viewFlightsButton, bookFlightButton, exitButton;
    Image backgroundImage;
    Label welcomeLabel;

    public Home() {
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mounith\\Pictures\\airline.png");

        setTitle("Airline Management System - Home");
        setSize(600, 400);
        setResizable(true); // Allow resizing

        // Maximize the window
        setExtendedState(Frame.MAXIMIZED_BOTH);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Initialize and add welcome label
        welcomeLabel = new Label("Welcome to the Airline Management System!", Label.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        gbc.gridwidth = 1;
        gbc.gridy = 0; // First row
        gbc.gridx = 0; // First column
        add(welcomeLabel, gbc);

        // Positioning the buttons
        gbc.gridwidth = 1; // Reset to default
        gbc.gridy = 1; // Next row
        viewFlightsButton = new Button("View Flights");
        add(viewFlightsButton, gbc);

        gbc.gridy = 2; // Next row
        bookFlightButton = new Button("Book a Flight");
        add(bookFlightButton, gbc);

        gbc.gridy = 3; // Next row
        exitButton = new Button("Exit");
        add(exitButton, gbc);

        // Add action listeners
        viewFlightsButton.addActionListener(this);
        bookFlightButton.addActionListener(this);
        exitButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Close the application
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewFlightsButton) {
            new FlightList(); // Open flight list
            dispose(); // Close the home window
        } else if (e.getSource() == bookFlightButton) {
            new AirlineManagementSystemAWT(); // Open booking system
            dispose(); // Close the home window
        } else if (e.getSource() == exitButton) {
            dispose(); // Close the application
        }
    }

    public static void main(String[] args) {
        new Home(); // Start the application
    }
}
