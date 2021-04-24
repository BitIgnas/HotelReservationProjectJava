package hotelReservation.registeredUserDao;

import hotelReservation.entities.RegisteredUser;

import java.math.BigInteger;

public interface RegisteredUserDao {

    public boolean checkIfUserExist(String username, String password);

    public void assignUserToHotel(BigInteger hotelId, String username);

    public void addNewUser(RegisteredUser registeredUser);

    public boolean authenticate(String username, String password);

    public String getNameByUserName(String username);


}
