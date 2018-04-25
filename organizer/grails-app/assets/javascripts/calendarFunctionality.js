var today = new Date();
var month = today.getMonth();
var userEvents = [];
var token = "";
var eventMonth;
var numberOfDays;
var actual_month;
var month_listing;
var actual_calendar

Date.prototype.getMonthNames = function() {
    return [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];
};

Date.prototype.getDaysInMonth = function() {
    return new Date( this.getFullYear(), this.getMonth() + 1, 0 ).getDate();
};

Date.prototype.calendar = function() {
    numberOfDays = this.getDaysInMonth();
    var startingDay = new Date(this.getFullYear(), this.getMonth(), 1).getDay();
    var calendarTable = '<div id="month">' + this.getMonthNames()[this.getMonth()] + '&nbsp;' + this.getFullYear() + '</div>';
    calendarTable += '<table summary="Calendar" class="calendar" style="text-align: center;">';
    calendarTable += '<tr><td colspan="7"></td></tr>';
    calendarTable += '<tr>';
    calendarTable += '<td class="titleColumn">SUN</font></td>';
    calendarTable += '<td class="titleColumn">MON</td>';
    calendarTable += '<td class="titleColumn">TUES</td>';
    calendarTable += '<td class="titleColumn">WED</td>';
    calendarTable += '<td class="titleColumn">THURS</td>';
    calendarTable += '<td class="titleColumn">FRI</td>';
    calendarTable += '<td class="titleColumn">SAT</td></tr>';

    for ( var i = 0; i < startingDay; i++ ) {
        calendarTable += '<td class="dayBlock">&nbsp;</td>';
    }

    var border = startingDay;

    for ( var id = '',  j = 1; j <= numberOfDays; j++ ) {
        if (( month === this.getMonth() ) && ( today.getDate() === j )) {
            id = 'id="current_day"';
        } else {
            id = 'id="day'+j+'"';
        }
        calendarTable += '<td class="dayBlock" ' + id + '>' + j+'</td>'; border++;

        if ((( border % 7 ) === 0 ) && ( j < numberOfDays )) {
            calendarTable += '<tr></tr>';
        }
    }
    while(( border++ % 7)!== 0) {
        calendarTable += '<td>&nbsp;</td>';
    }
    calendarTable += '</table>';
    return calendarTable;
};

window.onload = function() {
    var selected_month = '<form name="month_holder">';
    selected_month += '<select id="month_items" size="1" onchange="month_picker();">';
    for ( var x = 0; x <= today.getMonthNames().length; x++ ) { selected_month += '<option value="' + today.getMonthNames()[x] + ' 1, ' + today.getFullYear() + '">' + today.getMonthNames()[x] + '</option>'; }
    selected_month += '</select></form>';
    actual_calendar = document.getElementById('show_calendar');
    actual_calendar.innerHTML = today.calendar();
    month_listing = document.getElementById('current_month');
    month_listing.innerHTML = selected_month;
    actual_month = document.getElementById('month_items');
    actual_month.selectedIndex = month;
    console.log("done")
    //addEvents()
};


//--> Month Picker <--\\
function month_picker() {
    var month_menu = new Date(actual_month.value);
    actual_calendar.innerHTML = month_menu.calendar();
}

var monthFullDate

function getEvents() {
     monthFullDate= new Date(actual_month.value);
    eventMonth = 4;
    $.ajax({
        url: '/user/auth',
        method: "GET",
        success: function (data) {
            token = data.data.token;

            $.ajax({
                url:'api/event/user/month?accessToken='+token+'&monthString=4',
                method: "GET",
                success: function (data) {
                    userEvents = data.data.userEvents;
                    console.log(userEvents)
                },
                error: function () {
                    alert("it didn't work.")
                }
            });
        },
        error: function () {
            alert("Cant Authorize User")
        }
    });
}

function addEvents() {
    var days = [];
    for(var i=1; i<=numberOfDays; i++) {
        if(today.getDate() !== i) {
            days[i-1] = document.getElementById("day" + i);
        } else {
            days[i-1] = document.getElementById("current_day")
        }
        days[i-1].innerHTML = insertEventsIntoCalendar(i)

    }

}

function insertEventsIntoCalendar(i) {
    var eventString;
    //console.log(dayBox.id);
    //console.log(userEvents.length);
    for(var j=0; j<userEvents.length; j++){
        eventString = i;
        //console.log('dueDay:' + userEvents[j].dueDay +' | i: '+ i);
        if(userEvents[j].dueDay === i){
            eventString += '<div id="'+j+'" class="eventDiv">'+ userEvents[j].eventName +'</div>';
        }
    }
    return eventString

}
