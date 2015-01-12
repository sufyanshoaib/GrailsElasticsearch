package com.sufyan.demo

import grails.transaction.Transactional

@Transactional
class EventService {

    def addEvent(Event event) {
        event.save(flush:true)
        event.index()
    }
}
