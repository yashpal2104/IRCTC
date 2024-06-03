package ticket.booking.Util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public static Boolean checkPassword(String PlainPassword, String HashedPassword){
        return BCrypt.checkpw(PlainPassword, HashedPassword);
    }
}
