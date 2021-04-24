package hotelReservation.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_Code")
    private String hotelCode;

    @Column(name = "country")
    private String hotelCountry;

    @Column(name = "rooms")
    private int hotelRooms;

    @Column(name = "available_Rooms")
    private int availableRooms;

    @OneToMany(mappedBy = "hotel")
    private Set<RegisteredUser> users;



    //custom toString method to avoid infinitive recursion
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", hotelCode='" + hotelCode + '\'' +
                ", hotelCountry='" + hotelCountry + '\'' +
                ", hotelRooms=" + hotelRooms +
                ", availableRooms=" + availableRooms +
                '}';
    }
}
