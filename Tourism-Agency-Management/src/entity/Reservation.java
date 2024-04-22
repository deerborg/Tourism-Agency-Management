package entity;

import java.time.LocalDate;

public class Reservation {

    private int reservation_id;
    private int reservation_hotel_id;
    private int reservation_room_id;
    private String reservation_guest_fullname;
    private String reservation_guest_id;
    private String reservation_guest_mpno;
    private String reservation_guest_email;
    private LocalDate reservation_checkin_date;
    private LocalDate reservation_checkout_date;
    private int reservation_child_count;
    private int reservation_adult_count;
    private int reservation_totol_price;
    private LocalDate reservation_date;

    public Reservation(int reservation_id, int reservation_hotel_id, int reservation_room_id, String reservation_guest_fullname, String reservation_guest_id, String reservation_guest_mpno, String reservation_guest_email, LocalDate reservation_checkin_date, LocalDate reservation_checkout_date, int reservation_child_count, int reservation_adult_count, int reservation_totol_price, LocalDate reservation_date) {
        this.reservation_id = reservation_id;
        this.reservation_hotel_id = reservation_hotel_id;
        this.reservation_room_id = reservation_room_id;
        this.reservation_guest_fullname = reservation_guest_fullname;
        this.reservation_guest_id = reservation_guest_id;
        this.reservation_guest_mpno = reservation_guest_mpno;
        this.reservation_guest_email = reservation_guest_email;
        this.reservation_checkin_date = reservation_checkin_date;
        this.reservation_checkout_date = reservation_checkout_date;
        this.reservation_child_count = reservation_child_count;
        this.reservation_adult_count = reservation_adult_count;
        this.reservation_totol_price = reservation_totol_price;
        this.reservation_date = reservation_date;
    }

    public Reservation() {
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getReservation_hotel_id() {
        return reservation_hotel_id;
    }

    public void setReservation_hotel_id(int reservation_hotel_id) {
        this.reservation_hotel_id = reservation_hotel_id;
    }

    public int getReservation_room_id() {
        return reservation_room_id;
    }

    public void setReservation_room_id(int reservation_room_id) {
        this.reservation_room_id = reservation_room_id;
    }

    public String getReservation_guest_fullname() {
        return reservation_guest_fullname;
    }

    public void setReservation_guest_fullname(String reservation_guest_fullname) {
        this.reservation_guest_fullname = reservation_guest_fullname;
    }

    public String getReservation_guest_id() {
        return reservation_guest_id;
    }

    public void setReservation_guest_id(String reservation_guest_id) {
        this.reservation_guest_id = reservation_guest_id;
    }

    public String getReservation_guest_mpno() {
        return reservation_guest_mpno;
    }

    public void setReservation_guest_mpno(String reservation_guest_mpno) {
        this.reservation_guest_mpno = reservation_guest_mpno;
    }

    public String getReservation_guest_email() {
        return reservation_guest_email;
    }

    public void setReservation_guest_email(String reservation_guest_email) {
        this.reservation_guest_email = reservation_guest_email;
    }

    public LocalDate getReservation_checkin_date() {
        return reservation_checkin_date;
    }

    public void setReservation_checkin_date(LocalDate reservation_checkin_date) {
        this.reservation_checkin_date = reservation_checkin_date;
    }

    public LocalDate getReservation_checkout_date() {
        return reservation_checkout_date;
    }

    public void setReservation_checkout_date(LocalDate reservation_checkout_date) {
        this.reservation_checkout_date = reservation_checkout_date;
    }

    public int getReservation_child_count() {
        return reservation_child_count;
    }

    public void setReservation_child_count(int reservation_child_count) {
        this.reservation_child_count = reservation_child_count;
    }

    public int getReservation_adult_count() {
        return reservation_adult_count;
    }

    public void setReservation_adult_count(int reservation_adult_count) {
        this.reservation_adult_count = reservation_adult_count;
    }

    public int getReservation_totol_price() {
        return reservation_totol_price;
    }

    public void setReservation_totol_price(int reservation_totol_price) {
        this.reservation_totol_price = reservation_totol_price;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }
}
