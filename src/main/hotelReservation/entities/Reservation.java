package hotelReservation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "hotel_id")
    private Long hotelId;

    @Column(name = "check_In")
    private String checkIn;

    @Column(name = "check_out")
    private String checkout;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", checkIn=" + checkIn +
                ", checkout=" + checkout +
                '}';
    }
}
