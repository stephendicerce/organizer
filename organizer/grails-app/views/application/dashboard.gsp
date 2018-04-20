<!--
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="578019460076-9h535196sj93sernlo2l09njo22fcn2e.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <asset:javascript src="auth/config.js"/>
    <asset:javascript src="auth/logout.js"/>
    <asset:stylesheet src="calendarView.css"/>

</head>

<title>Your Calendar!</title>
<body class="bg-light-gray">
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div id="viewBorder">
                    <div id=headerGrad class="intro-message">
                        <div id="title">
                            Dashboard
                        </div>
                        <button type="button" onclick="logout()">
                            <span class="sr-only">Toggle navigation</span> LOGOUT <i class="fa fa-bars"></i>
                        </button>
                        <button type="button" onclick="newEvent()">
                            <span class="sr-only">Toggle navigation</span> NEW EVENT <i class="fa fa-bars"></i>
                        </button>
                        <button type="button" onclick="createOrganization()">
                            <span class="sr-only">Toggle navigation</span> CREATE ORGANIZATION <i class="fa fa-bars"></i>
                        </button>
                        <h3></h3>
                    </div>
                    <div id="month">April</div>
                    <table>

                        <tr>
                            <th class="titleColumn" scope="col">Sun</th>
                            <th class="titleColumn" scope="col">Mon</th>
                            <th class="titleColumn" scope="col">Tues</th>
                            <th class="titleColumn" scope="col">Wed</th>
                            <th class="titleColumn" scope="col">Thur</th>
                            <th class="titleColumn" scope="col">Fri</th>
                            <th class="titleColumn" scope="col">Sat</th>
                        </tr>
                        <tr>
                            <td class="dayBlock">1</td>
                            <td class="dayBlock">2</td>
                            <td class="dayBlock">3</td>
                            <td class="dayBlock">4</td>
                            <td class="dayBlock">5</td>
                            <td class="dayBlock">6</td>
                            <td class="dayBlock">7</td>
                        </tr>
                        <tr>
                            <td class="dayBlock">8</td>
                            <td class="dayBlock">9</td>
                            <td class="dayBlock">10</td>
                            <td class="dayBlock">11</td>
                            <td class="dayBlock">12</td>
                            <td class="dayBlock">13</td>
                            <td class="dayBlock">14</td>
                        </tr>
                        <tr>
                            <td class="dayBlock">15</td>
                            <td class="dayBlock">16</td>
                            <td class="dayBlock">17</td>
                            <td class="dayBlock">18</td>
                            <td class="dayBlock">19</td>
                            <td class="dayBlock">20</td>
                            <td class="dayBlock">21</td>
                        </tr>
                        <tr>
                            <td class="dayBlock">22</td>
                            <td class="dayBlock">23</td>
                            <td class="dayBlock">25</td>
                            <td class="dayBlock">26</td>
                            <td class="dayBlock">27</td>
                            <td class="dayBlock">28</td>
                            <td class="dayBlock">28</td>
                        </tr>
                        <tr>
                            <td class="dayBlock">29</td>
                            <td class="dayBlock">30</td>
                            <td class="nextMonthDayBlock">1</td>
                            <td class="nextMonthDayBlock">2</td>
                            <td class="nextMonthDayBlock">3</td>
                            <td class="nextMonthDayBlock">4</td>
                            <td class="nextMonthDayBlock">5</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>

-->

<!DOCTYPE html>
<html>
<head>
    <title>Calendar</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="578019460076-9h535196sj93sernlo2l09njo22fcn2e.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <asset:javascript src="auth/config.js"/>
    <asset:javascript src="auth/logout.js"/>
    <asset:stylesheet src="calendarView.css"/>
    <style type="text/css">
    <!--
    /* This is our current day marker */

    #current_day { background-color: rgb(0,189,255); font-weight: bold; }
    -->
    </style>
</head>
<body>
<script type="text/javascript">
    <!-- Begin Hiding
    var today = new Date();
    var month = today.getMonth();
    //Using the Date prototype to assign our month names-->
    Date.prototype.getMonthNames = function() { return [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ]; }
    //Getting the number of day in the month.-->
    Date.prototype.getDaysInMonth = function()
    { return new Date( this.getFullYear(), this.getMonth() + 1, 0 ).getDate(); }
    Date.prototype.calendar = function()
    {  var numberOfDays = this.getDaysInMonth();
        //This will be the starting day to our calendar-->
        var startingDay = new Date(this.getFullYear(), this.getMonth(), 1).getDay();
        //We will build the calendar_table variable then pass what we build back-->
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
        //Lets create blank boxes until we get to the day which actually starts the month-->
        for ( var i = 0; i < startingDay; i++ )
        { calendarTable += '<td class="dayBlock">&nbsp;</td>'; }
        //border is a counter, initialize it with the "blank"-->
        //days at the start of the calendar-->
        //Now each time we add new date we will do a modulus-->
        //7 and check for 0 (remainder of border/7 = 0)
        //if it's zero then it's time to make new row-->
        var border = startingDay;
        //For each day in the month, insert it into the calendar-->
        for ( var id = '',  i = 1; i <= numberOfDays; i++ )
        { if (( month == this.getMonth() ) && ( today.getDate() == i )) { id = 'id="current_day"'; }
        else { id = ''; }
            calendarTable += '<td class="dayBlock" ' + id + '>' + i + '</td>'; border++;
            if ((( border % 7 ) == 0 ) && ( i < numberOfDays ))
            {
                //Time to make new row, if there are any days left.-->
                calendarTable += '<tr></tr>'; } }
                //All the days have been used up, so pad the empty days until the end of calendar-->
        while(( border++ % 7)!= 0)
        { calendarTable += '<td>&nbsp;</td>'; }
        //Finish the table-->
        calendarTable += '</table>';
        //Return it-->
        return calendarTable; }
        //--> Let's add up some dynamic effect
    window.onload = function() {
        selected_month = '<form name="month_holder">';
        selected_month += '<select id="month_items" size="1" onchange="month_picker();">';
        for ( var x = 0; x <= today.getMonthNames().length; x++ ) { selected_month += '<option value="' + today.getMonthNames()[x] + ' 1, ' + today.getFullYear() + '">' + today.getMonthNames()[x] + '</option>'; }
        selected_month += '</select></form>';
        actual_calendar = document.getElementById('show_calendar');
        actual_calendar.innerHTML = today.calendar();
        var month_listing = document.getElementById('current_month');
        month_listing.innerHTML = selected_month;
        actual_month = document.getElementById('month_items');
        actual_month.selectedIndex = month;
    }
    //--> Month Picker <--\\
    function month_picker()
    { month_menu = new Date(actual_month.value);
        actual_calendar.innerHTML = month_menu.calendar();
    }
    // Done Hiding -->
</script>

<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div id="viewBorder">
                    <div id=headerGrad class="intro-message">
                        <div id="title">
                            Dashboard
                        </div>
                        <button type="button" onclick="logout()">
                            <span class="sr-only">Toggle navigation</span> LOGOUT <i class="fa fa-bars"></i>
                        </button>
                        <button type="button" onclick="document.location.href='../createEvent'">
                            <span class="sr-only">Toggle navigation</span> NEW EVENT <i class="fa fa-bars"></i>
                        </button>
                        <button type="button" onclick="document.location.href='../createOrganization'">
                            <span class="sr-only">Toggle navigation</span> CREATE ORGANIZATION <i class="fa fa-bars"></i>
                        </button>
                        <h3></h3>
                    </div>
                    <p>&nbsp</p>
                    <div id="current_month">&nbsp;</div>
                    <div id="show_calendar">&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>