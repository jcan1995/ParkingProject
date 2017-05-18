package com.example.bruhshua.parking.Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by bruhshua on 4/24/17.
 */

public class Course implements Serializable {


    private String subject;
    private String catalog;
    private String section;
    private String title;
    private String description;
    private String location;
    private String room;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private String dayOfWeek;

    public Course(String title, String subject, String catalog, String section, String description,
                  String location, String room, String startTime, String endTime, String startDate, String endDate, String DOW) {
        this.title = title;

        this.subject = subject;
        this.catalog = catalog;
        this.section = section;
        this.description = description;
        this.location = location;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfWeek = DOW;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
