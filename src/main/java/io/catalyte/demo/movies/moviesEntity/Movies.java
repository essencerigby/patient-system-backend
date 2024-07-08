package io.catalyte.demo.movies.moviesEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="movies")
public class Movies {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String genre;
    private String director;
    private BigDecimal dailyRentalCost;


    // Constructor - empty params
    public Movies() {}

    // Constructors - default values + params
    public Movies(String title, String genre,
                  String director, BigDecimal dailyRentalCost) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.dailyRentalCost = dailyRentalCost;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public BigDecimal getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(BigDecimal dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }
}
