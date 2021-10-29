package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightCabinClass> flightCabinClasses = new ArrayList<>();

    public void addFlightCabinClass(FlightCabinClass flightCabinClass) {
        flightCabinClass.setFlight(this);
    }

    protected Flight(){

    }

    public void setAirline(Airline airline) {
        if(Objects.nonNull(this.airline)) {
            this.airline.getFlights().remove(this);
        }
        this.airline = airline;
        airline.getFlights().add(this);
    }

    public FlightCabinClass findFlightCabinClassBy(CabinClass cabinClass){
        return this.flightCabinClasses.stream().filter(flightCabinClass -> flightCabinClass.getCabinClass().equals(cabinClass)).findFirst().get();
    }

    //GETTER
    public Long getId(){
        return this.id;
    }

    public List<FlightCabinClass> getFlightCabinClasses() {
        return this.flightCabinClasses;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public Airline getAirline() {
        return airline;
    }
}
