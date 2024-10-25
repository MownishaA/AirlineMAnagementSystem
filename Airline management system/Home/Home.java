package airline;

import java.awt.*;
import java.awt.event.*;

public class Home extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    Button viewFlightsButton, bookFlightButton, exitButton;
    Image backgroundImage;
    Label welcomeLabel;

    public Home() {
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mounith\\Desktop\\study\\Project\\airline.png");

        setTitle("Airline Management System - Home");
        setExtendedState(Frame.MAXIMIZED_BOTH); // Maximize the window
        setResizable(false); // Optional: Prevent resizing
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize and add welcome label
        welcomeLabel = new Label("Welcome to the Airline Management System!", Label.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        //welcomeLabel.setBackground(Color.GREEN);
        //welcomeLabel.setForeground(Color.BLACK);
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(welcomeLabel, gbc);

        // Add buttons
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        viewFlightsButton = new Button("View Flights");
        viewFlightsButton.setBackground(Color.GREEN);
        viewFlightsButton.setForeground(Color.BLACK);
        add(viewFlightsButton, gbc);

        gbc.gridy = 2;
        bookFlightButton = new Button("Book a Flight");
        bookFlightButton.setBackground(Color.BLUE);
        bookFlightButton.setForeground(Color.BLACK);
        add(bookFlightButton, gbc);

        gbc.gridy = 3;
        exitButton = new Button("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
        add(exitButton, gbc);

        // Add action listeners
        viewFlightsButton.addActionListener(this);
        bookFlightButton.addActionListener(this);
        exitButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Ensure application exits
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
            new FlightBooking(); // Open flight booking
            dispose();
        } else if (e.getSource() == exitButton) {
            System.exit(0); // Exit the application
        }
    }

    public static void main(String[] args) {
        new Home(); // Start the application
    }
}
