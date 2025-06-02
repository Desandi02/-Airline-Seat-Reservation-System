import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    //constructor
    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void ticketInfo() {
        System.out.println("-----------------------------");
        System.out.println("Ticket Information");
        //if (person != null) {
        person.personInfo();
        //}else {
        //System.out.println("Person information is not available.");
        //}
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println("-----------------------------");
    }
    public  void save() {
        String filename = row + "" + seat + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Ticket Information\n");
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price £: " + price + "\n");
            writer.write("Person Information:\n");
            if (person != null) {
                writer.write ("\nName:" + person.getName ());
                writer.write ("\nSurname:" + person.getSurname ());
                writer.write ("\nemail:" + person.getEmail ());

            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error while saving the ticket information to file");;
        }
    }

    public  void delete() {
        String filename = row + "" + seat + ".txt";
        File file = new File (filename);
        if (file.exists ()) {
            file.delete();
        }else {
            System.out.println("File does not exist");
        }
        System.out.println("File deleted");
    }
}




