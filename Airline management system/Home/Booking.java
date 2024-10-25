
package airline;

class Booking {
    private String name;
    private String flight;
    private String seat;

    public Booking(String name, String flight, String seat) {
        this.name = name;
        this.flight = flight;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public String getFlight() {
        return flight;
    }

    public String getSeat() {
        return seat;
    }
}

