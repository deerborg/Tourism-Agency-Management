package entity;

import java.time.LocalDate;

public class Search {
    private int hotel_id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_region;
    private String hotel_fulladress;
    private String hotel_email;
    private String hotel_phone;
    private String hotel_star;
    private boolean hotel_free_parking;
    private boolean hotel_fee_wifi;
    private boolean hotel_swimming_pool;
    private boolean hotel_fitness_center;
    private boolean hotel_concierge;
    private boolean hotel_spa;
    private boolean hotel_room_services;
    private String[] hotel_pansion_types;
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
    private int season_type;
    private int season_hotel_id;
    private LocalDate season_start_date;
    private LocalDate season_end_date;
    private String season_name;
    private String season_hotel_hotel_name;

    public Search() {
    }

    public Search(int hotel_id, String hotel_name, String hotel_city, String hotel_region, String hotel_fulladress, String hotel_email, String hotel_phone, String hotel_star, boolean hotel_free_parking, boolean hotel_fee_wifi, boolean hotel_swimming_pool, boolean hotel_fitness_center, boolean hotel_concierge, boolean hotel_spa, boolean hotel_room_services, String[] hotel_pansion_types, int room_id, int room_hotel_id, int room_seasson_id, String room_pansion_type, String room_number, int room_capacity, int room_adult_price, int room_child_price, int room_stock_quantity, int room_bed_count, int room_square_meters, boolean room_has_tv, boolean room_has_mini_bar, boolean room_has_gaming_console, boolean room_has_safe_box, boolean room_has_projector, int season_type, int season_hotel_id, LocalDate season_start_date, LocalDate season_end_date, String season_name, String season_hotel_hotel_name) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_city = hotel_city;
        this.hotel_region = hotel_region;
        this.hotel_fulladress = hotel_fulladress;
        this.hotel_email = hotel_email;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_free_parking = hotel_free_parking;
        this.hotel_fee_wifi = hotel_fee_wifi;
        this.hotel_swimming_pool = hotel_swimming_pool;
        this.hotel_fitness_center = hotel_fitness_center;
        this.hotel_concierge = hotel_concierge;
        this.hotel_spa = hotel_spa;
        this.hotel_room_services = hotel_room_services;
        this.hotel_pansion_types = hotel_pansion_types;
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
        this.season_type = season_type;
        this.season_hotel_id = season_hotel_id;
        this.season_start_date = season_start_date;
        this.season_end_date = season_end_date;
        this.season_name = season_name;
        this.season_hotel_hotel_name = season_hotel_hotel_name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_region() {
        return hotel_region;
    }

    public void setHotel_region(String hotel_region) {
        this.hotel_region = hotel_region;
    }

    public String getHotel_fulladress() {
        return hotel_fulladress;
    }

    public void setHotel_fulladress(String hotel_fulladress) {
        this.hotel_fulladress = hotel_fulladress;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_free_parking() {
        return hotel_free_parking;
    }

    public void setHotel_free_parking(boolean hotel_free_parking) {
        this.hotel_free_parking = hotel_free_parking;
    }

    public boolean isHotel_fee_wifi() {
        return hotel_fee_wifi;
    }

    public void setHotel_fee_wifi(boolean hotel_fee_wifi) {
        this.hotel_fee_wifi = hotel_fee_wifi;
    }

    public boolean isHotel_swimming_pool() {
        return hotel_swimming_pool;
    }

    public void setHotel_swimming_pool(boolean hotel_swimming_pool) {
        this.hotel_swimming_pool = hotel_swimming_pool;
    }

    public boolean isHotel_fitness_center() {
        return hotel_fitness_center;
    }

    public void setHotel_fitness_center(boolean hotel_fitness_center) {
        this.hotel_fitness_center = hotel_fitness_center;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_room_services() {
        return hotel_room_services;
    }

    public void setHotel_room_services(boolean hotel_room_services) {
        this.hotel_room_services = hotel_room_services;
    }

    public String[] getHotel_pansion_types() {
        return hotel_pansion_types;
    }

    public void setHotel_pansion_types(String[] hotel_pansion_types) {
        this.hotel_pansion_types = hotel_pansion_types;
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

    public int getSeason_type() {
        return season_type;
    }

    public void setSeason_type(int season_type) {
        this.season_type = season_type;
    }

    public int getSeason_hotel_id() {
        return season_hotel_id;
    }

    public void setSeason_hotel_id(int season_hotel_id) {
        this.season_hotel_id = season_hotel_id;
    }

    public LocalDate getSeason_start_date() {
        return season_start_date;
    }

    public void setSeason_start_date(LocalDate season_start_date) {
        this.season_start_date = season_start_date;
    }

    public LocalDate getSeason_end_date() {
        return season_end_date;
    }

    public void setSeason_end_date(LocalDate season_end_date) {
        this.season_end_date = season_end_date;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public String getSeason_hotel_hotel_name() {
        return season_hotel_hotel_name;
    }

    public void setSeason_hotel_hotel_name(String season_hotel_hotel_name) {
        this.season_hotel_hotel_name = season_hotel_hotel_name;
    }

}
