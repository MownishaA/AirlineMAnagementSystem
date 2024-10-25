package airline;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Flight {
    private String flightNumber;
    private String destination;
    private int totalSeats;
    private ArrayList<String> bookedSeats;

    public Flight(String flightNumber, String destination) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.totalSeats = 5; // Fixed number of seats
        this.bookedSeats = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats.size();
    }

    public boolean bookSeat(String seat) {
        if (getAvailableSeats() > 0 && !bookedSeats.contains(seat)) {
            bookedSeats.add(seat);
            return true;
        }
        return false;
    }

    public void cancelSeat(String seat) {
        bookedSeats.remove(seat);
    }

    @Override
    public String toString() {
        return flightNumber + ": " + destination + " (" + getAvailableSeats() + " seats available)";
    }

    public ArrayList<String> getAvailableSeatsList() {
        ArrayList<String> availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            String seat = "Seat " + i;
            if (!bookedSeats.contains(seat)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
}

class BackgroundPanel extends Panel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(backgroundImage, 0);
        try {
            tracker.waitForAll(); // Wait for the image to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
    	int x=0;
    	int y=0;
        g.drawImage(backgroundImage, x, y, getWidth(), getHeight(), this);
    }
}




public class FlightBooking extends Frame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField nameField;
    private List flightList;
    private List seatList;
    private List bookedList;
    private Button bookButton;
    private Button cancelButton;
    private Button backButton;
    private TextArea messageArea;

    private ArrayList<Booking> bookingList;
    private WaitingList waitingList;
    private ArrayList<Flight> flights;

    public FlightBooking() {
        bookingList = new ArrayList<>();
        waitingList = new WaitingList();
        flights = new ArrayList<>();

        populateFlights();

        setTitle("Flight Booking");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\Mounith\\Desktop\\study\\oops assign\\air2.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
        
        Label nameLabel = new Label("Passenger Name:");
        nameField = new TextField(30);

        Label flightLabel = new Label("Select Flight:");
        flightList = new List(5);
        populateFlightList();
        

        Label seatLabel = new Label("Select Seat:");
        flightLabel.setBackground(Color.WHITE);
        seatList = new List(5);
        populateSeatList(flights.get(0));

        Label bookedLabel = new Label("Booked Seats:");
        bookedList = new List(5);

        bookButton = new Button("Book Flight");
        bookButton.setBackground(Color.GREEN);
        bookButton.setForeground(Color.BLACK);

        cancelButton = new Button("Cancel Booking");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);

        backButton = new Button("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);

        messageArea = new TextArea(5, 30);
        messageArea.setEditable(false);

        flightList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Flight selectedFlight = flights.get(flightList.getSelectedIndex());
                    populateSeatList(selectedFlight);
                }
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Flight selectedFlight = flights.get(flightList.getSelectedIndex());
                String seat = seatList.getSelectedItem();

                if (!name.isEmpty() && selectedFlight != null && seat != null) {
                    Booking booking = new Booking(name, selectedFlight.getFlightNumber(), seat);
                    if (selectedFlight.bookSeat(seat)) {
                        bookingList.add(booking);
                        seatList.remove(seat);
                        bookedList.add(name + " - " + selectedFlight.getFlightNumber() + " - " + seat);
                        messageArea.append("Booking confirmed for " + name + " on " + selectedFlight.getFlightNumber() + " in seat " + seat + "\n");
                        showConfirmationDialog(name, selectedFlight.getFlightNumber(), seat);
                        notifyWaitingList(selectedFlight.getFlightNumber());
                    } else {
                        messageArea.append("Seat already booked or no seats available.\n");
                    }
                } else if (seat == null) {
                    waitingList.addPassenger(name);
                    messageArea.append("No available seats. Added " + name + " to the waiting list for " + selectedFlight.getFlightNumber() + "\n");
                } else {
                    messageArea.append("Please enter all details.\n");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBooking = bookedList.getSelectedItem();
                String passengerName = nameField.getText();

                if (selectedBooking != null && passengerName != null && !passengerName.isEmpty()) {
                    if (selectedBooking.startsWith(passengerName)) {
                        String seatToCancel = selectedBooking.split(" - ")[2];
                        Booking bookingToCancel = null;

                        for (Booking booking : bookingList) {
                            if (booking.getSeat().equals(seatToCancel) && booking.getName().equals(passengerName)) {
                                bookingToCancel = booking;
                                break;
                            }
                        }

                        if (bookingToCancel != null) {
                            bookingList.remove(bookingToCancel);
                            seatList.add(seatToCancel);
                            bookedList.remove(selectedBooking);

                            for (Flight flight : flights) {
                                if (flight.getFlightNumber().equals(bookingToCancel.getFlight())) {
                                    flight.cancelSeat(seatToCancel);
                                    break;
                                }
                            }

                            messageArea.append("Cancelled booking for " + passengerName + " in seat " + seatToCancel + "\n");
                            notifyWaitingList(bookingToCancel.getFlight());
                        } else {
                            messageArea.append("Booking not found.\n");
                        }
                    } else {
                        messageArea.append("You can only cancel your own bookings.\n");
                    }
                } else {
                    messageArea.append("Please select a booking to cancel and enter your name.\n");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                // new Home(); // Uncomment if you have a Home class
            }
        });

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(flightLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(flightList, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(seatLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(seatList, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(bookedLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(bookedList, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        backgroundPanel.add(bookButton, gbc);

        gbc.gridy = 5;
        backgroundPanel.add(cancelButton, gbc);

        gbc.gridy = 6;
        backgroundPanel.add(backButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        backgroundPanel.add(messageArea, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void showConfirmationDialog(String name, String flight, String seat) {
        Dialog confirmDialog = new Dialog(FlightBooking.this, "Booking Confirmation", true);
        confirmDialog.add(new Label("Booking confirmed for " + name + " on " + flight + " in seat " + seat));
        confirmDialog.setSize(300, 150);
        confirmDialog.setLayout(new FlowLayout());
        confirmDialog.add(new Button("OK") {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
            addActionListener(event -> confirmDialog.dispose());
        }});
        confirmDialog.setVisible(true);
    }

    private void populateFlights() {
    	flights.add(new Flight("Flight 101", "Mumbai to Delhi"));
    	flights.add(new Flight("Flight 102", "Delhi to Bengaluru"));
    	flights.add(new Flight("Flight 103", "Bengaluru to Hyderabad"));
    	flights.add(new Flight("Flight 104", "Chennai to Kolkata"));
    	flights.add(new Flight("Flight 105", "Hyderabad to Pune"));
    	flights.add(new Flight("Flight 106", "Kolkata to Jaipur"));
    	flights.add(new Flight("Flight 107", "Ahmedabad to Surat"));
    	flights.add(new Flight("Flight 108", "Surat to Mumbai"));
    	flights.add(new Flight("Flight 109", "Delhi to Chennai"));
    	flights.add(new Flight("Flight 110", "Bengaluru to Kochi"));
    	flights.add(new Flight("Flight 111", "Hyderabad to Chandigarh"));
    	flights.add(new Flight("Flight 112", "Kolkata to Goa"));
    	flights.add(new Flight("Flight 113", "Jaipur to Udaipur"));
    	flights.add(new Flight("Flight 114", "Ahmedabad to Jodhpur"));
    	flights.add(new Flight("Flight 115", "Pune to Nagpur"));
    	flights.add(new Flight("Flight 116", "Chennai to Visakhapatnam"));
    	flights.add(new Flight("Flight 117", "Kochi to Bengaluru"));

    }

    private void populateFlightList() {
        for (Flight flight : flights) {
            flightList.add(flight.toString());
        }
    }

    private void populateSeatList(Flight flight) {
        seatList.removeAll();
        for (String seat : flight.getAvailableSeatsList()) {
            seatList.add(seat);
        }
    }

    private void notifyWaitingList(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber) && flight.getAvailableSeats() > 0) {
                String nextPassenger = waitingList.removeNextPassenger();
                if (nextPassenger != null) {
                    messageArea.append("Notified " + nextPassenger + " of available seat on " + flightNumber + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        new FlightBooking();
    }
}
