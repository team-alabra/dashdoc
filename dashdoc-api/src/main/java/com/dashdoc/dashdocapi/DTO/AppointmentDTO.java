package com.dashdocnow.DTO;
import org.joda.time.DateTime;
import java.time.Duration;
import java.time.ZoneId;

public class AppointmentDTO {
    private DateTime startTime;
    private DateTime endTime;
    private Duration duration;
    private ZoneId timeZone;

    public AppointmentDTO() {
    }

    public AppointmentDTO(DateTime startTime, DateTime endTime, Duration duration, ZoneId timeZone) {
       this.startTime = startTime;
       this.endTime = endTime;
       this.duration = duration;
       this.timeZone = timeZone;
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
