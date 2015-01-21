<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12" >
            <g:form controller="event" action="index">
                <div class="input-group">
                    <input type="text" class="search form-control" name="query">
                    <span class="input-group-btn">
                        <button class="btn btn-default glyphicon glyphicon-search" type="submit"></button>
                    </span>
                </div>
            </g:form>
        </div>
    </div>
    <div class="row">

        <div class="[ col-xs-12 col-sm-offset-2 col-sm-8 ]">
            <ul class="event-list">
                <g:each in="${events}" var="event" >
                <li>
                    <time datetime="${event.startDate}">
                        <span class="day">${event.startDate.getDay()}</span>
                        <span class="month">${new java.text.SimpleDateFormat("MMM").format(event.startDate.getTime())}</span>
                        <span class="year">${new java.text.SimpleDateFormat("YYYY").format(event.startDate.getTime())}</span>
                    </time>
                    <img alt="Independence Day" src="https://farm4.staticflickr.com/3100/2693171833_3545fb852c_q.jpg" />
                    <div class="info">
                        <h2 class="title">${event.title}</h2>
                        <p class="desc">${event.description}</p>
                    </div>
                </li>
                </g:each>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
