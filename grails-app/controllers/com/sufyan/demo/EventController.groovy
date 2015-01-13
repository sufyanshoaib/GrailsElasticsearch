package com.sufyan.demo

import grails.converters.JSON
import org.elasticsearch.common.unit.DistanceUnit
import org.elasticsearch.search.sort.SortBuilders

import org.elasticsearch.search.sort.SortOrder

class EventController {

    def eventService
    def elasticSearchService

    def add(String title, String description, double latitude, double longitude) {
        GeoPoint point = new GeoPoint(lat:latitude, lon:longitude)
        point.save(flush:true)
        Event event = new Event(title:title, description:description, location:point)

        println("events location:${point}")

        event.save(flush:true)

        def resp = ['success':true]

        render resp as JSON
    }

    def search(String query) {

        Closure filter = {
            geo_distance(
                    'distance': '5km',
                    'location': [lat: 46.239, lon: 1.29]
            )
        }
        def sortBuilder = SortBuilders.geoDistanceSort("location").
                point(46.239, 1.24).
                unit(DistanceUnit.KILOMETERS).
                order(SortOrder.ASC)

        def result = elasticSearchService.search(
                [indices: Event, types: Event, sort: sortBuilder],
                null as Closure,
                filter)
        def result2 = Event.search(
                [indices: Event, types: Event, sort: sortBuilder],
                null as Closure,
                filter)



        println("result:${result.searchResults}, result2:${result2}")
        result2.searchResults.each {
            println("Event:${it.location.lat}")
        }

        render result as JSON
    }

    def searchPoints(String query) {

        def events = GeoPoint.search(query, [score:true])

        println("events:${events}")

        render events as JSON
    }


    def get(Long id) {
        def event = Event.get(id)
        println("Event wth id:${id}, ${event.location.lat}")
        render event as JSON
    }
}
