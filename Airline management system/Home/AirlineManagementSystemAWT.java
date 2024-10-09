
        

        package airlinemanagementsystem;

        import java.awt.*;
        import java.awt.event.*;
        import java.util.ArrayList;
        import java.util.List;

        class Flight {
            private String flightNumber;
            private String destination;
            private int capacity;
            private int bookedSeats;

            public Flight(String flightNumber, String destination, int capacity) {
                this.flightNumber = flightNumber;
                this.destination = destination;
                this.capacity = capacity;
                this.bookedSeats = 0;
            }

            public String getFlightNumber() {
                return flightNumber;
            }

            public String getDestination() {
                return destination;
            }

            public int getCapacity() {
                return capacity;
            }

            public int getBookedSeats() {
                return bookedSeats;
            }

            public boolean bookSeat() {
                if (bookedSeats < capacity) {
                    bookedSeats++;
                    return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return flightNumber + " to " + destination + " (Capacity: " + capacity + ", Booked: " + bookedSeats + ")";
            }
        }

        public class AirlineManagementSystemAWT {
            private List<Flight> flights;
            private TextArea displayArea;
            private TextField nameField;
            private TextField flightField;

            public AirlineManagementSystemAWT() {
                flights = new ArrayList<>();
                flights.add(new Flight("101", "Chennai to Mumbai", 1));
                flights.add(new Flight("202", "Chennai to Bangalore", 1));
                flights.add(new Flight("303", "Chennai to Delhi", 1));
                flights.add(new Flight("404", "Chennai to Goa", 1));
                flights.add(new Flight("505", "Chennai to Kashmir", 1));
                flights.add(new Flight("606", "Chennai to Kolkata", 1));
                flights.add(new Flight("707", "Chennai to Hyderabad", 1));
                flights.add(new Flight("808", "Chennai to Jaipur", 1));
                flights.add(new Flight("909", "Chennai to Ahmedabad", 1));
                flights.add(new Flight("1010", "Chennai to Cochin", 1));
                flights.add(new Flight("1111", "Chennai to Lucknow", 1));
                flights.add(new Flight("1212", "Chennai to Chandigarh", 1));
                flights.add(new Flight("1313", "Chennai to Srinagar", 1));
                flights.add(new Flight("1414", "Chennai to Udaipur", 1));
                flights.add(new Flight("1515", "Chennai to Mangalore", 1));
                flights.add(new Flight("1616", "Chennai to Nagpur", 1));
                flights.add(new Flight("1717", "Chennai to Varanasi", 1));
                flights.add(new Flight("1818", "Chennai to Indore", 1));
                flights.add(new Flight("1919", "Chennai to Patna", 1));
                flights.add(new Flight("2020", "Chennai to Amritsar", 1));
                flights.add(new Flight("2121", "Chennai to Surat", 1));
                flights.add(new Flight("2222", "Chennai to Vadodara", 1));
                flights.add(new Flight("2323", "Chennai to Aurangabad", 1));
                flights.add(new Flight("2424", "Chennai to Jodhpur", 1));
                flights.add(new Flight("2525", "Chennai to Guwahati", 1));
                flights.add(new Flight("2626", "Chennai to Ranchi", 1));
                flights.add(new Flight("2727", "Chennai to Bhopal", 1));
                flights.add(new Flight("2828", "Chennai to Siliguri", 1));
                flights.add(new Flight("2929", "Chennai to Tirupati", 1));
                flights.add(new Flight("3030", "Chennai to Nashik", 1));
                flights.add(new Flight("3131", "Chennai to Thiruvananthapuram", 1));
                flights.add(new Flight("3232", "Chennai to Bhubaneswar", 1));
                flights.add(new Flight("3333", "Chennai to Gwalior", 1));
                flights.add(new Flight("3434", "Chennai to Jaisalmer", 1));
                flights.add(new Flight("3535", "Chennai to Port Blair", 1));
                flights.add(new Flight("3636", "Chennai to Dibrugarh", 1));
                flights.add(new Flight("3737", "Chennai to Bhavnagar", 1));
                flights.add(new Flight("3838", "Chennai to Dehradun", 1));
                flights.add(new Flight("3939", "Chennai to Jorhat", 1));
                flights.add(new Flight("4040", "Chennai to Madurai", 1));
                flights.add(new Flight("4141", "Chennai to Coimbatore", 1));
                flights.add(new Flight("4242", "Chennai to Raipur", 1));
                flights.add(new Flight("4343", "Chennai to Salem", 1));
                flights.add(new Flight("4444", "Chennai to Imphal", 1));
                flights.add(new Flight("4545", "Chennai to Sikkim", 1));
                flights.add(new Flight("4646", "Chennai to Aizawl", 1));
                flights.add(new Flight("4747", "Chennai to Shillong", 1));
                flights.add(new Flight("4848", "Chennai to Kohima", 1));
                flights.add(new Flight("4949", "Chennai to Agartala", 1));
                flights.add(new Flight("5050", "Chennai to Cuttack", 1));
                flights.add(new Flight("5151", "Chennai to Aligarh", 1));
                flights.add(new Flight("5252", "Chennai to Jabalpur", 1));
                flights.add(new Flight("5353", "Chennai to Gaya", 1));
                flights.add(new Flight("5454", "Chennai to Tiruvannamalai", 1));
                flights.add(new Flight("5555", "Chennai to Kanyakumari", 1));

                Frame frame = new Frame("Airline Management System");
                frame.setExtendedState(Frame.MAXIMIZED_BOTH); // Maximize the window
                frame.setResizable(false); // Optional: Prevent resizing
                frame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                displayArea = new TextArea(10, 40);
                displayArea.setEditable(false);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 3;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(10, 10, 10, 10);
                frame.add(displayArea, gbc);

                nameField = new TextField(20);
                flightField = new TextField(10);

                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                Label nameLabel = new Label("Passenger Name:");
                nameLabel.setBackground(Color.CYAN);
                nameLabel.setForeground(Color.BLACK);

                gbc.gridx = 0;
                gbc.gridy = 1;
                frame.add(nameLabel, gbc);

                gbc.gridx = 1;
                frame.add(nameField, gbc);

                Label flightLabel = new Label("Flight Number:");
                flightLabel.setBackground(Color.CYAN);
                flightLabel.setForeground(Color.BLACK);

                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(flightLabel, gbc);

                gbc.gridx = 1;
                frame.add(flightField, gbc);

                Button bookButton = new Button("Book Flight");
                bookButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        bookFlight(nameField.getText(), flightField.getText());
                    }
                });
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                frame.add(bookButton, gbc);

                Button showButton = new Button("Show Flights");
                showButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showAvailableFlights();
                    }
                });
                gbc.gridx = 2;
                frame.add(showButton, gbc);

                // Add Back Button
                Button backButton = new Button("Back");
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Home();
                        nameField.setText("");
                        flightField.setText("");
                        displayArea.setText("");
                    }
                });
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 3;
                frame.add(backButton, gbc);

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });

                frame.setVisible(true);
            }

            public void showAvailableFlights() {
                StringBuilder flightInfo = new StringBuilder("Available Flights:\n");
                for (Flight flight : flights) {
                    flightInfo.append(flight).append("\n");
                }
                displayArea.setText(flightInfo.toString());
            }

            public void bookFlight(String passengerName, String flightNumber) {
                for (Flight flight : flights) {
                    if (flight.getFlightNumber().equals(flightNumber)) {
                        if (flight.bookSeat()) {
                            displayArea.append("Booking successful for " + passengerName + " on flight " + flightNumber + "\n");
                        } else {
                            displayArea.append("No seats available on flight " + flightNumber + "\n");
                        }
                        return;
                    }
                }
                displayArea.append("Flight number " + flightNumber + " not found.\n");
            }

            public static void main(String[] args) {
                new AirlineManagementSystemAWT();
            }
        }
