package com.sufyan.demo

import grails.converters.JSON

class EventController {

    def eventService
    def elasticSearchService

    def add(String title, String description) {
        Event event = new Event(title:title, description:description)

        eventService.addEvent(event)
        println("event:${Event.list()}")
        def resp = ['success':true]

        respond resp as JSON
    }

    def search() {
        def events = elasticSearchService.search("title:some")

        println("events:${events}")

        respond events as JSON
    }
}
