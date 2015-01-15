package com.sufyan.demo

import org.joda.time.DateTime


class Event {

    //Long eventId
    String title
    String description
    GeoPoint location
    Date startDate
    Date endDate

    //static hasMany = [locations: GeoPoint]

    static constraints = {
        startDate nullable:true
        endDate nullable:true
    }

    static searchable = {
        //eventId index:'not_analyzed'
        title index:'analyzed', boost:4
        description index:'analyzed', boost:3
        location geoPoint: true, component: true
    }
}
