import java.util.InputMismatchException;
import java.util.Scanner;

public class w2055347_PlaneManagement {
    static int[][] seat = new int[4][];  //Array to store seats to each row
    static Scanner input = new Scanner(System.in);
    static Ticket[][] tickets = new Ticket[4][]; // Array to store tickets
    public static int user_option = 0;




    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");

        // Initialize seat array
        seat[0] = new int[14];
        seat[1] = new int[12];
        seat[2] = new int[12];
        seat[3] = new int[14];

        // Initialize ticket array
        tickets[0]=new Ticket[14];
        tickets[1]=new Ticket[12];
        tickets[2]=new Ticket[12];
        tickets[3]=new Ticket[14];
        boolean option = true;
        do {
            try {
                for (int i = 1; i <= 50; i++) {
                    System.out.print("*");
                }
                System.out.println();
                System.out.println("*                  MENU OPTIONS                  *");
                for (int i = 1; i <= 50; i++) {
                    System.out.print("*");
                }
                System.out.println("\n\t1) Buy a seat \n\t2) Cancel a seat \n\t3) Find first available seat \n\t4) Show seating plan \n\t5) Print tickets information and total sales \n\t6) Search ticket \n\t0) Quit");

                for (int i = 1; i <= 50; i++) {
                    System.out.print("*");
                }
                System.out.print("\nPlease select an option: ");
                user_option = input.nextInt();




                switch (user_option) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        System.out.println("Thank you!");
                        option=false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter 0 to 6.");
                input.nextLine();
            }
        }while (option);
    }

    // Methods
    // This method  for the input row number
    public static int row_number(int rowNum) {
        do {
            try {
                System.out.print("Enter the row letter(A/B/C/D): ");
                rowNum = input.next().charAt(0);
                rowNum = Character.toUpperCase(rowNum);
                switch (rowNum) {
                    case 'A':
                        rowNum = 0;
                        break;
                    case 'B':
                        rowNum = 1;
                        break;
                    case 'C':
                        rowNum = 2;
                        break;
                    case 'D':
                        rowNum = 3;
                        break;
                    default:
                        System.out.println("Invalid row letter.");
                        //return rowNum;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalied ABCD");
                input.nextLine();
            }
        } while (rowNum < 0 || rowNum >= 4);

        return rowNum;
    }

    // This method  for the input seat number
    public static int seat_number(int seatNum) {
        //int rowNum;
        do {
            try {
                System.out.print("Enter seat number: ");
                seatNum = input.nextInt();
                if (seatNum < 1 || seatNum > seat[0].length) {
                    System.out.println("Invalied seat number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalied seat int");
                input.nextLine();
                //seatNum=-1;
            }

        } while (seatNum < 1 || seatNum > seat[0].length);

        return seatNum;
    }
    // This method  for the calculate total price
    public static double calculate(int location){
        double price;
        if(0<= location && location <5){
            price = 200;
        }else if (location<9) {
            price = 150;
        }else{
            price = 180;
        }
        return price;
    }
    //methods to buy seat
    public static void buy_seat() {
        char rowNum;
        char row;
        int num;


        do {
            row = (char) row_number(-1);
            num = seat_number(-1);
            if (seat[row][num - 1] != 1) {
                do {
                    System.out.println("This seat is available");
                    rowNum = (char) ('A' + row);

// Ask for Person information
                    System.out.println("Please enter your name: ");
                    String name = input.next();
                    System.out.println("Please enter your surname: ");
                    String surname = input.next();
                    System.out.println("Pleace enter your Email: ");
                    String email = input.next();


// Create a new Person object
                    Person person = new Person(name, surname, email);

// Create a new Ticket object
                    double price = calculate(num);
                    Ticket ticket= new Ticket((char) ('A' + row), num ,price,  person);
                    ticket.save();
                    tickets[row][num-1] = ticket;

                    seat[row][num - 1] = 1;
                    System.out.println(rowNum + "" + num+" seat is successfully sold.");
                    break;
                } while (seat[row][num - 1] == 0);
            } else {
                System.out.println("already sold");
            }
        }while (row>= seat.length || num>seat[row].length);
    }
    // methods to cancel seat
    public static void cancel_seat() {
        int row;
        int num;
        do {
            row = row_number(-1);
            num = seat_number(-1);
            if ((row >=0 && row < seat.length) && (num >= 0 && num <= seat[row].length)) {
                if (seat[row][num - 1] == 1) {
                    seat[row][num - 1] = 0;
                    // delete from array
                    System.out.println((char)('A'+ row) + "" + num + " seat Cancelled Successfully...");
                    if(tickets[row][num] != null){
                        tickets[row][num].delete();
                        tickets[row][num] = null;

                    } else{
                        break;
                    }
                } else {
                    System.out.println("Seat is Available");
                }
            } else {
                System.out.println("Invalied seat");
            }
        } while (row >= seat.length || num > seat[row].length);
    }

    // methods to find first available
    public static int[] find_first_available() {
        for(int rowCount = 0; rowCount< seat.length; rowCount++){
            for(int seatCount=0; seatCount<seat[rowCount].length; seatCount++){
                if (seat[rowCount][seatCount] == 0){
                    System.out.println("First available seat found at row "+(char)('A'+rowCount)+" seat "+(seatCount+1));
                    return new int[]{rowCount,seatCount};
                }
            }
        }return new int[0];
    }

    // methods to show seating plan structure
    public static void show_seating_plan() {
        System.out.println("Seating Plan");
        for(int rowNum = 0; rowNum<seat.length; rowNum++){
            for(int seatNum=0; seatNum<seat[rowNum].length; seatNum++){
                if(seat[rowNum][seatNum]==0){
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
    // method to print ticket information
    public static void print_tickets_info() {
        double total_price = 0.0;
        boolean bookings = false;
        for(int i = 0; i<tickets.length; i++){
            for(int j = 0; j < tickets[i].length; j++){
                if(tickets[i][j] != null){
                    System.out.println("Ticket "+(i +1)+" :");
                    tickets[i][j].ticketInfo();
                    double ticket_price = tickets[i][j].getPrice();
                    total_price += ticket_price;
                    bookings = true;
                }
            }
        }
        if (!bookings){
            System.out.println("No Bookings.");
        }
        System.out.println("total price is: £"+total_price);
    }

    // method to search ticket
    public static void search_ticket () {

        int row = row_number(-1);
        int num = seat_number(-1);
        if (row >= 0 && row < seat.length && num >= 0 && num <= seat[row].length) {
            if (seat[row][num - 1] == 0) {
                System.out.println("Seat is Available.");
            } else {
                System.out.println("Seat is booked.");
                Ticket ticket = tickets[row][num-1];
                if (ticket != null) {
                    ticket.ticketInfo();
                }else{
                    System.out.println("No booking information available for this seat.");
                }
            }
        }else{
            System.out.println("Invalid seat selection.");
        }
    }

}