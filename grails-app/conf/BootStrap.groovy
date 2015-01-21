import com.sufyan.demo.Event

class BootStrap {

    def eventService

    def init = { servletContext ->

        eventService.addEvent("Expo Pakistan", "2 days expo of Pakistan, all stuffs...at expo center", 42.11, 0.23, "12/02/2015", "14/02/2015")
        eventService.addEvent("New Year Even", "Enjoy new year night at beach club", 42.12, 0.21, "31/12/2014", "01/01/2015")
        eventService.addEvent("Children's Day", "Kids play, activities, sports, fun, family", 42.21, 0.13, "03/02/2015", "05/02/2015")
        eventService.addEvent("Education Expo", "3 days Eduction expo, college, schools at expo center...", 42.11, 0.23, "05/01/2015", "07/01/2015")
        eventService.addEvent("Arena sports area", "All year open, indoor activities, bowling, snooker, ice skating", 42.278, 0.55, "01/01/2015", "31/12/2015")
        eventService.addEvent("All you can eat", "All you can eat offer is back in this ramadan at Pizza Hut", 43.001, 0.25, "06/06/2015", "05/07/2015")
    }

    def destroy = {

    }

}
