package entity;


import java.time.LocalDate;

public class Room {
    private int room_id;
    private int room_hotel_id;
    private int room_seasson_id;
    private String room_pansion_type;
    private String room_number;
    private int room_capacity;
    private int room_adult_price;
    private int room_child_price;
    private int room_stock_quantity;
    private int room_bed_count;
    private int room_square_meters;
    private boolean room_has_tv;
    private boolean room_has_mini_bar;
    private boolean room_has_gaming_console;
    private boolean room_has_safe_box;
    private boolean room_has_projector;
    private Hotel hotel;
    private Season season;
    private Pansion pansion;
    private Type roomType;
    private int room_reservation_id;
    private String room_hotel_name;
    private String room_hotel_city;
    private LocalDate room_season_start;
    private LocalDate room_season_end;
    private String room_season_name;

    public enum Type{
        SINGLE,
        DOUBLE,
        JUNIOR,
        SUITE
    }
    public Room() {
    }

    public Room(int room_id, int room_hotel_id, int room_seasson_id, String room_pansion_type, String room_number, int room_capacity, int room_adult_price, int room_child_price, int room_stock_quantity, int room_bed_count, int room_square_meters, boolean room_has_tv, boolean room_has_mini_bar, boolean room_has_gaming_console, boolean room_has_safe_box, boolean room_has_projector, Hotel hotel, Season season, Pansion pansion, Type roomType, int room_reservation_id, String room_hotel_name, String room_hotel_city, LocalDate room_season_start, LocalDate room_season_end, String room_season_name) {
        this.room_id = room_id;
        this.room_hotel_id = room_hotel_id;
        this.room_seasson_id = room_seasson_id;
        this.room_pansion_type = room_pansion_type;
        this.room_number = room_number;
        this.room_capacity = room_capacity;
        this.room_adult_price = room_adult_price;
        this.room_child_price = room_child_price;
        this.room_stock_quantity = room_stock_quantity;
        this.room_bed_count = room_bed_count;
        this.room_square_meters = room_square_meters;
        this.room_has_tv = room_has_tv;
        this.room_has_mini_bar = room_has_mini_bar;
        this.room_has_gaming_console = room_has_gaming_console;
        this.room_has_safe_box = room_has_safe_box;
        this.room_has_projector = room_has_projector;
        this.hotel = hotel;
        this.season = season;
        this.pansion = pansion;
        this.roomType = roomType;
        this.room_reservation_id = room_reservation_id;
        this.room_hotel_name = room_hotel_name;
        this.room_hotel_city = room_hotel_city;
        this.room_season_start = room_season_start;
        this.room_season_end = room_season_end;
        this.room_season_name = room_season_name;
    }

    public String getRoom_season_name() {
        return room_season_name;
    }

    public void setRoom_season_name(String room_season_name) {
        this.room_season_name = room_season_name;
    }

    public String getRoom_hotel_name() {
        return room_hotel_name;
    }

    public void setRoom_hotel_name(String room_hotel_name) {
        this.room_hotel_name = room_hotel_name;
    }

    public String getRoom_hotel_city() {
        return room_hotel_city;
    }

    public void setRoom_hotel_city(String room_hotel_city) {
        this.room_hotel_city = room_hotel_city;
    }

    public LocalDate getRoom_season_start() {
        return room_season_start;
    }

    public void setRoom_season_start(LocalDate room_season_start) {
        this.room_season_start = room_season_start;
    }

    public LocalDate getRoom_season_end() {
        return room_season_end;
    }

    public void setRoom_season_end(LocalDate room_season_end) {
        this.room_season_end = room_season_end;
    }

    public int getRoom_reservation_id() {
        return room_reservation_id;
    }

    public void setRoom_reservation_id(int room_reservation_id) {
        this.room_reservation_id = room_reservation_id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pansion getPansion() {
        return pansion;
    }

    public void setPansion(Pansion pansion) {
        this.pansion = pansion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_hotel_id() {
        return room_hotel_id;
    }

    public void setRoom_hotel_id(int room_hotel_id) {
        this.room_hotel_id = room_hotel_id;
    }

    public int getRoom_seasson_id() {
        return room_seasson_id;
    }

    public void setRoom_seasson_id(int room_seasson_id) {
        this.room_seasson_id = room_seasson_id;
    }

    public String getRoom_pansion_type() {
        return room_pansion_type;
    }

    public void setRoom_pansion_type(String room_pansion_type) {
        this.room_pansion_type = room_pansion_type;
    }

    public Type getRoomType() {
        return roomType;
    }

    public void setRoomType(Type roomType) {
        this.roomType = roomType;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public int getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(int room_capacity) {
        this.room_capacity = room_capacity;
    }

    public int getRoom_adult_price() {
        return room_adult_price;
    }

    public void setRoom_adult_price(int room_adult_price) {
        this.room_adult_price = room_adult_price;
    }

    public int getRoom_child_price() {
        return room_child_price;
    }

    public void setRoom_child_price(int room_child_price) {
        this.room_child_price = room_child_price;
    }

    public int getRoom_stock_quantity() {
        return room_stock_quantity;
    }

    public void setRoom_stock_quantity(int room_stock_quantity) {
        this.room_stock_quantity = room_stock_quantity;
    }

    public int getRoom_bed_count() {
        return room_bed_count;
    }

    public void setRoom_bed_count(int room_bed_count) {
        this.room_bed_count = room_bed_count;
    }

    public int getRoom_square_meters() {
        return room_square_meters;
    }

    public void setRoom_square_meters(int room_square_meters) {
        this.room_square_meters = room_square_meters;
    }

    public boolean isRoom_has_tv() {
        return room_has_tv;
    }

    public void setRoom_has_tv(boolean room_has_tv) {
        this.room_has_tv = room_has_tv;
    }

    public boolean isRoom_has_mini_bar() {
        return room_has_mini_bar;
    }

    public void setRoom_has_mini_bar(boolean room_has_mini_bar) {
        this.room_has_mini_bar = room_has_mini_bar;
    }

    public boolean isRoom_has_gaming_console() {
        return room_has_gaming_console;
    }

    public void setRoom_has_gaming_console(boolean room_has_gaming_console) {
        this.room_has_gaming_console = room_has_gaming_console;
    }

    public boolean isRoom_has_safe_box() {
        return room_has_safe_box;
    }

    public void setRoom_has_safe_box(boolean room_has_safe_box) {
        this.room_has_safe_box = room_has_safe_box;
    }

    public boolean isRoom_has_projector() {
        return room_has_projector;
    }

    public void setRoom_has_projector(boolean room_has_projector) {
        this.room_has_projector = room_has_projector;
    }
}
