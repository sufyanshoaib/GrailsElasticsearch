package com.sufyan.demo

class Event {

    //Long eventId
    String title
    String description
    GeoPoint location

    //static hasMany = [locations: GeoPoint]

    static searchable = {
        //eventId index:'not_analyzed'
        title index:'analyzed', boost:4
        description index:'analyzed', boost:3
        location geoPoint: true, component: true
    }
}
