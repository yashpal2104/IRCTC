package ticket.booking.entities;

import java.util.List;

public class User {
 private String name;
 private String password;
 private String hashedPassword;

 private List<Ticket> ticketsBooked;
private String userID;

 public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userID) {
  this.name = name;
  this.password = password;
  this.hashedPassword = hashedPassword;
  this.ticketsBooked = ticketsBooked;
  this.userID = userID;
 }

 public User(){

 }


 public void setName(String name) {
  this.name = name;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getHashedPassword() {
  return hashedPassword;
 }


 public List<Ticket> getTicketsBooked() {
  return ticketsBooked;
 }
 public void printTickets(){
  for (int i = 0; i < ticketsBooked.size(); i++) {
   System.out.println(ticketsBooked.get(i).getTicketInfo());
  }
 }


 public String getUserID() {
  return userID;
 }
 public String getName() {
  return name;
 }

 public void setHashedPassword(String hashedPassword) {
  this.hashedPassword = hashedPassword;
 }
 public void setUserID(String userID) {
  this.userID = userID;
 }
 public void setTicketsBooked(List<Ticket> ticketsBooked){
  this.ticketsBooked = ticketsBooked;
 }
}


