/*
 * This source file was generated by the Gradle 'init' task
 */
package ticket.booking;

import ticket.booking.Util.UserServiceUtil;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;

import java.io.IOException;
import java.util.*;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;

public class App {


    public boolean someLibraryMethod() {
        // Implementation that should return true
        return true;
    }



    public static void main(String[] args) throws IOException {

        System.out.println("Running Train Booking System");

        // Example logic
        boolean isError = false;

        if (isError) {
            System.out.println("There is something wrong");
        } else {
            System.out.println("System is running smoothly");
        }




        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
    int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();

        }catch(IOException exception){
            System.out.println("There is something wrong");
            return;
        }

       while(option != 7){
           System.out.println("Choose option from below");
           System.out.println("1. Sign Up");
           System.out.println("2. Login");
           System.out.println("3. Fetch Bookings");
           System.out.println("4. Search Trains");
           System.out.println("5. Book Ticket");
           System.out.println("6. Cancel Ticket");
              System.out.println("7. Exit");
              option = scanner.nextInt();
           Train trainSelectedForBooking = new Train();
            switch (option){
            case 1:
                System.out.println("Enter the username to signup");
                String nameToSignUp = scanner.next();
                System.out.println("Enter the password to signup");
                String passwordToSignUp = scanner.next();
                User userToSignUp = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());

                userBookingService.signUp(userToSignUp);
            break;
            case 2:
                System.out.println("Enter the username to login");
                String nameToLogin = scanner.next();
                System.out.println("Enter the password to signup");
                String passwordToLogin = scanner.next();
                User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                try {
                    userBookingService = new UserBookingService(userToLogin);
                }
                catch (IOException exception) {
                    System.out.println("There is something wrong");
                    return;
                }
                break;
           case 3:
               System.out.println("Fetch your bookings");
               userBookingService.fetchBooking();
               break;
           //  A --> B ke liye ek function ya case define kro
            case 4:
                System.out.println("Type your source station");
                String source = scanner.next();
                System.out.println("Type your destination station");
                String dest = scanner.next();
                System.out.println("Type your date of journey");
                String date = scanner.next();
                List<Train> trains = userBookingService.getTrains(source, dest);
                int index = 1;
                for (Train t: trains){
                    System.out.println(index+" Train id : "+t.getTrainId());
                    for (Map.Entry<String, String> entry: t.getStationTime().entrySet()){
                        System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                    }
                }
                System.out.println("Select a train by typing 1,2,3...");
                trainSelectedForBooking = trains.get(scanner.nextInt());
                break;
///////////////////////////////////////////////////////////////////////////////////////
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }break;
                default:
                    System.out.println("Invalid option");
                    break;
         }
       }

    }
}
