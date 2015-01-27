package com.sufyan.demo

import com.sufyan.demo.util.SortByEnum
import grails.converters.JSON
import org.elasticsearch.search.sort.SortBuilders
import org.elasticsearch.search.sort.SortOrder

class EventController {

    def eventService
    def elasticSearchService

    /**
     * Add And Event to index
     * @param title
     * @param description
     * @param latitude
     * @param longitude
     * @param startDate
     * @param endDate
     * @return response in JSON
     */
    def add(String title, String description, double latitude, double longitude, String startDate, String endDate) {

        eventService.addEvent(title, description, latitude, longitude, startDate, endDate)

        def resp = ['success':true]

        render resp as JSON
    }

    def index(String query){
        def queryClosure = query ?
            {
            query_string(fields: ["title", "description"],
                    query: query)
            } : null

        def events = elasticSearchService.search(queryClosure)
        render(view: "/event/list", model: [events:events.searchResults])
    }
    /**
     * Perform search by query only. Searches on title and description fileds
     * @param query
     * @return
     */
    def search(String query, String sortBy, String order) {
        SortOrder orderBy = (!order || order == 'asc') ? SortOrder.ASC : SortOrder.DESC
        SortByEnum sort = SortByEnum.TITLE
        if (sortBy) {
            sort = SortByEnum.find(sortBy)
            println("sort:${sort}")
        }
        def sortBuilder = SortBuilders.fieldSort(sort.value).order(orderBy)
        def events = elasticSearchService.search(
                [sort: sortBuilder],
                {
                    query_string(fields: ["title", "description"],
                            query: query)
                    suggest : { suggest_mode: 'popular' }
                })

        render events.searchResults as JSON
    }

    /**
     * Perform search along with distance search.
     * @param query
     * @param distance
     * @return
     */
    def searchWithDistance(String query, Integer distance) {
        /*def sortBuilder = SortBuilders.geoDistanceSort("location").
                point(46.239, 1.24).
                unit(DistanceUnit.KILOMETERS).
                order(SortOrder.ASC)

        Closure filter = {
            [indices: Event, types: Event, sort: sortBuilder]
            geo_distance(
                    'distance': '5km',
                    'location': [lat: 46.239, lon: 1.29]
            )
        }*/

        Closure filter = {
            geo_distance(
                    'distance': (distance ?: '0') + 'km',
                    'location': [lat: 42.23, lon: 0.11]
            )
        }

        def events = elasticSearchService.search( [:],

                {
                    query_string(fields: ["title", "description"],
                                query: query)
                },
                distance ? filter : null as Closure)
        render events as JSON
    }

    /*def searchTerm(String query, boolean fuzzy) {
        // Define the pre & post tag that will wrap each term matched in the document.
        def highlighter = {
            field 'description'
            preTags '<strong>'
            postTags '</strong>'
        }
        def events = elasticSearchService.search([searchType:'dfs_query_and_fetch'*//*, highlight: highlighter*//*],
                {

                        if (fuzzy) {
                            fuzzy :{

                                description {
                                    value: query
                                }

                            }
                        } else {
                            bool {
                                must {
                                    term(description: query)
                                }
                            }
                        }

                })
        render events as JSON
    }*/



    /*def searchPoints(String query) {

        def events = GeoPoint.search(query, [score:true])

        println("events:${events}")

        render events as JSON
    }


    def get(Long id) {
        def event = Event.get(id)
        println("Event wth id:${id}, ${event.location.lat}")
        render event as JSON
    }*/
}
