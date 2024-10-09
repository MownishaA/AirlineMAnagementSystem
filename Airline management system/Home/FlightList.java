package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;

public class FlightList extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Button backButton, bookButton;
    private List flightList;
    Image backgroundImage;

    public FlightList() {
        // Set up the frame
        setTitle("Available Flights");
        setExtendedState(Frame.MAXIMIZED_BOTH); // Make the frame fullscreen
        setUndecorated(true); // Remove window decorations
        setLayout(new BorderLayout());
        setBackground(new Color(240, 230, 250)); // Light lavender background
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mounith\\Pictures\\airline.png");
        // Initialize the flight list with scrolling capability
        flightList = new List(58); // Set a fixed number of visible items
        flightList.add("Flight 101: Chennai to Mumbai");
        flightList.add("Flight 202: Chennai to Bangalore");
        flightList.add("Flight 303: Chennai to Delhi");
        flightList.add("Flight 404: Chennai to Goa");
        flightList.add("Flight 505: Chennai to Kashmir");
        flightList.add("Flight 606: Chennai to Kolkata");
        flightList.add("Flight 707: Chennai to Hyderabad");
        flightList.add("Flight 808: Chennai to Jaipur");
        flightList.add("Flight 909: Chennai to Ahmedabad");
        flightList.add("Flight 1010: Chennai to Cochin");
        flightList.add("Flight 1111: Chennai to Lucknow");
        flightList.add("Flight 1212: Chennai to Chandigarh");
        flightList.add("Flight 1313: Chennai to Srinagar");
        flightList.add("Flight 1414: Chennai to Udaipur");
        flightList.add("Flight 1515: Chennai to Mangalore");
        flightList.add("Flight 1616: Chennai to Nagpur");
        flightList.add("Flight 1717: Chennai to Varanasi");
        flightList.add("Flight 1818: Chennai to Indore");
        flightList.add("Flight 1919: Chennai to Patna");
        flightList.add("Flight 2020: Chennai to Amritsar");
        flightList.add("Flight 2121: Chennai to Surat");
        flightList.add("Flight 2222: Chennai to Vadodara");
        flightList.add("Flight 2323: Chennai to Aurangabad");
        flightList.add("Flight 2424: Chennai to Jodhpur");
        flightList.add("Flight 2525: Chennai to Guwahati");
        flightList.add("Flight 2626: Chennai to Ranchi");
        flightList.add("Flight 2727: Chennai to Bhopal");
        flightList.add("Flight 2828: Chennai to Siliguri");
        flightList.add("Flight 2929: Chennai to Tirupati");
        flightList.add("Flight 3030: Chennai to Nashik");
        flightList.add("Flight 3131: Chennai to Thiruvananthapuram");
        flightList.add("Flight 3232: Chennai to Bhubaneswar");
        flightList.add("Flight 3333: Chennai to Gwalior");
        flightList.add("Flight 3434: Chennai to Jaisalmer");
        flightList.add("Flight 3535: Chennai to Port Blair");
        flightList.add("Flight 3636: Chennai to Dibrugarh");
        flightList.add("Flight 3737: Chennai to Bhavnagar");
        flightList.add("Flight 3838: Chennai to Dehradun");
        flightList.add("Flight 3939: Chennai to Jorhat");
        flightList.add("Flight 4040: Chennai to Madurai");
        flightList.add("Flight 4141: Chennai to Coimbatore");
        flightList.add("Flight 4242: Chennai to Raipur");
        flightList.add("Flight 4343: Chennai to Salem");
        flightList.add("Flight 4444: Chennai to Imphal");
        flightList.add("Flight 4545: Chennai to Sikkim");
        flightList.add("Flight 4646: Chennai to Aizawl");
        flightList.add("Flight 4747: Chennai to Shillong");
        flightList.add("Flight 4848: Chennai to Kohima");
        flightList.add("Flight 4949: Chennai to Agartala");
        flightList.add("Flight 5050: Chennai to Cuttack");
        flightList.add("Flight 5151: Chennai to Aligarh");
        flightList.add("Flight 5252: Chennai to Jabalpur");
        flightList.add("Flight 5353: Chennai to Gaya");
        flightList.add("Flight 5454: Chennai to Tiruvannamalai");
        flightList.add("Flight 5555: Chennai to Kanyakumari");

        // Centering the flight list in a scrollable area
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 255)); // Light blue panel
        
        // Add flight list to the center panel
        centerPanel.add(flightList, new GridBagConstraints());

        // Add the centered flight list to the center of the main frame
        add(centerPanel, BorderLayout.CENTER);

        // Initialize buttons with styles
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout());

        bookButton = new Button("Book Flight");
        bookButton.setBackground(new Color(70, 130, 180)); // Steel blue
        bookButton.setForeground(Color.WHITE);
        buttonPanel.add(bookButton);

        backButton = new Button("Back to Home");
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);
        
        // Add button panel to the bottom
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        bookButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set frame visibility and close operation
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new Home(); // Assuming Home is another Frame or class
            dispose(); // Close the flight list window
        } else if (e.getSource() == bookButton) {
        	new AirlineManagementSystemAWT();
           
        }
    }

    public static void main(String[] args) {
        new FlightList(); // Start the Flight List window for testing
    }
}

class Home extends Frame {
    public Home() {
        setTitle("Home");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setBackground(new Color(230, 230, 250)); // Light lavender background

        Label welcomeLabel = new Label("Welcome to the Airline Management System!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel);

        Button flightListButton = new Button("View Available Flights");
        flightListButton.setBackground(new Color(70, 130, 180)); // Steel blue
        flightListButton.setForeground(Color.GREEN);
        flightListButton.addActionListener(e -> {
            new FlightList(); // Open flight list
            dispose(); // Close the home window
        });

        add(flightListButton);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window
            }
        });
    }
}
