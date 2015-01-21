package com.sufyan.demo

import grails.converters.JSON

class HomeController {

    def elasticSearchService
    def index() {
        def events = elasticSearchService.search()

        render(view: "/deals/index", model: model)
    }
}
