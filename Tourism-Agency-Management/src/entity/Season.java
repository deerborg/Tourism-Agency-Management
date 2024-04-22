package entity;

import java.time.LocalDate;

public class Season {
    private int season_id;
    private int season_hotel_id;
    private LocalDate season_start_date;
    private LocalDate season_end_date;
    private SeasonName seasonName;
    private String hotelName;
    private Hotel hotel;

    public enum SeasonName{
        SUMMER,
        WINTER,
        FULL
    }

    public Season(int season_id, int season_hotel_id, LocalDate season_start_date, LocalDate season_end_date, SeasonName seasonName, String hotelName, Hotel hotel) {
        this.season_id = season_id;
        this.season_hotel_id = season_hotel_id;
        this.season_start_date = season_start_date;
        this.season_end_date = season_end_date;
        this.seasonName = seasonName;
        this.hotelName = hotelName;
        this.hotel = hotel;
    }

    public SeasonName getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(SeasonName seasonName) {
        this.seasonName = seasonName;
    }

    public Season() {
    }

    public int getSeason_id() {
        return season_id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

}
