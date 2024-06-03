package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Util.UserServiceUtil;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.entities.User.*;
import java.util.stream.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ticket.booking.entities.Ticket;

public class UserBookingService {

    private User user;
    private List<User> userList;

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "../LocalDB.Users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loudUsers();
    }

    public UserBookingService() throws IOException {

        loudUsers();
    }

    public List<User> loudUsers() throws IOException {
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }


    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException exception) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File users = new File(USERS_PATH);
        objectMapper.writeValue(users, userList);
    }
    // json --> Object(User) --> Deserialize
    // Object (User) --> json --> Serialize

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public void fetchBooking() {
        user.printTickets();
    }

//    do cancel booking same like fetchBooking
//    todo: Complete the function

    public Boolean cancelBooking(String ticketId) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;  //Because strings are immutable
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }
        System.out.println("No ticket found with ID " + ticketId);
        return Boolean.FALSE;

    }

//    public List<Ticket> getTicketID(String ticketId){
//        return  Ticket.stream().filter(ticket -> ticket.ti.equals(getTicketID(ticketId))).collect(Collectors.toList());
//    }


    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrain(source, destination);
        } catch (IOException exception) {
            return new ArrayList<>();
        }
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
}
