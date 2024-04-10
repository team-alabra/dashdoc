package com.dashdocapi.entities;

import jakarta.persistence.*;
import org.joda.time.DateTime;

import java.time.Duration;
import java.time.ZoneId;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_time")
    private DateTime startTime;
    @Column(name = "end_time")
    private DateTime endTime;
    private Duration duration;
    // TODO: 3/5/23 remove duration, flesh this out  
    @Column(name = "time_zone")
    private ZoneId timeZone;

    public Appointment(DateTime startTime, DateTime endTime, Duration duration, ZoneId timeZone) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.timeZone = timeZone;
    }

    public Appointment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }
}
