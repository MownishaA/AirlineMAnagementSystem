package airline;

import java.awt.*;
import java.awt.event.*;

public class FlightList extends Frame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List flightList;
    private TextField searchField;

    public FlightList() {
        // Set title for the window
        setTitle("Flight List");

        // Create the background panel with an image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\Mounith\\Desktop\\study\\oops assign\\air3.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a search field
        searchField = new TextField(20); // Width of the search field
        searchField.setFont(new Font("Arial", Font.BOLD, 14));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterFlights(); // Call the filter function on key release
            }
        });

        // Add the search field to the panel
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.insets = new Insets(20, 20, 10, 20); // Padding
        backgroundPanel.add(searchField, gbc); // Add search field to the panel

        // Create a list of flights
        flightList = new List(20); // Specify the number of visible rows
        flightList.setFont(new Font("Arial", Font.PLAIN, 14));
        flightList.setBackground(Color.LIGHT_GRAY);
        flightList.setForeground(Color.BLACK);

        // Sample flight data
        addFlightData();

        // Create a scroll pane and add the flight list to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(flightList); // Add flight list to the scroll pane
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for scroll pane
              // Set GridBagConstraints for the scroll pane
        gbc.gridy = 1; // Row 1
        gbc.insets = new Insets(0, 20, 20, 20); // Reset padding
        backgroundPanel.add(scrollPane, gbc); // Add scroll pane to the panel
        gbc.gridy = 5;
        Button flight = new Button("Book Flight");
        flight.setBackground(Color.BLUE);
        flight.setForeground(Color.BLACK);
       
        
        gbc.gridy = 3;
        Button exitButton = new Button("Back");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
       
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == flight) {
                    new FlightBooking(); // Open flight list
                    dispose(); // Close the home window
                } else if (e.getSource() == exitButton) {
                    new Home(); // Open flight booking
                    dispose();
                }
            }
        });

        // Set GridBagConstraints for the button
        gbc.gridy = 2; // Row 2
        backgroundPanel.add(exitButton, gbc); // Add exit button to the panel

        // Add the main panel to the frame
        add(backgroundPanel);

        // Set window properties for full screen
        setExtendedState(Frame.MAXIMIZED_BOTH); // Maximize to full screen
        setUndecorated(true); // Remove window decorations
        setVisible(true); // Make frame visible

        // Add window listener for closing the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window
            }
        });
    }

    private void addFlightData() {
        // Sample flight data
        flightList.add("Flight 101 - Mumbai to Delhi");
        flightList.add("Flight 102 - Delhi to Bengaluru");
        flightList.add("Flight 103 - Bengaluru to Hyderabad");
        flightList.add("Flight 104 - Chennai to Kolkata");
        flightList.add("Flight 105 - Hyderabad to Pune");
        flightList.add("Flight 106 - Kolkata to Jaipur");
        flightList.add("Flight 107 - Ahmedabad to Surat");
        flightList.add("Flight 108 - Surat to Mumbai");
        flightList.add("Flight 109 - Delhi to Chennai");
        flightList.add("Flight 110 - Bengaluru to Kochi");
        flightList.add("Flight 111 - Hyderabad to Chandigarh");
        flightList.add("Flight 112 - Kolkata to Goa");
        flightList.add("Flight 113 - Jaipur to Udaipur");
        flightList.add("Flight 114 - Ahmedabad to Jodhpur");
        flightList.add("Flight 115 - Pune to Nagpur");
        flightList.add("Flight 116 - Chennai to Visakhapatnam");
        flightList.add("Flight 117 - Kochi to Bengaluru");
    }

    private void filterFlights() {
        String query = searchField.getText().toLowerCase(); // Get the search query
        flightList.removeAll(); // Clear the current list

        // Re-add flights that match the search query
        addFlightData(); // Re-add all flight data temporarily
        for (int i = 0; i < flightList.getItemCount(); i++) {
            String flight = flightList.getItem(i);
            if (!flight.toLowerCase().contains(query)) {
                flightList.remove(i); // Remove flights that do not match
                i--; // Decrement index due to removal
            }
        }
    }

    public static void main(String[] args) {
        new FlightList();
    }
}

// Background panel class to draw an image

