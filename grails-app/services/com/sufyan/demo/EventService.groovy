package com.sufyan.demo

import grails.transaction.Transactional
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

@Transactional
class EventService {

    def addEvent(Event event) {
        event.save(flush:true)
        event.index()
    }

    def addEvent(String title, String description, double latitude, double longitude, String startDate, String endDate) {
        GeoPoint point = new GeoPoint(lat:latitude, lon:longitude)
        point.save(flush:true)
        Event event = new Event(title:title, description:description, location:point)

        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy")

        event.startDate = df.parseDateTime(startDate).toDate()
        event.endDate = df.parseDateTime(endDate).toDate()

        println("events location:${point}")

        addEvent(event)
    }
}
