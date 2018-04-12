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

</head>
<title>Your Calendar!</title>
<body class="bg-light-gray">
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>Dashboard</h1>
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
                <table>
                    <caption> April</caption>
                    <tr>
                        <th scope="col" width="50px">Sunday</th>
                        <th scope="col">Monday</th>
                        <th scope="col">Tuesday</th>
                        <th scope="col">Wednesday</th>
                        <th scope="col">Thursday</th>
                        <th scope="col">Friday</th>
                        <th scope="col">Saturday</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>6</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>9</td>
                        <td>10</td>
                        <td>11</td>
                        <td>12</td>
                        <td>13</td>
                        <td>14</td>
                    </tr>
                    <tr>
                        <td>15</td>
                        <td>16</td>
                        <td>17</td>
                        <td>18</td>
                        <td>19</td>
                        <td>20</td>
                        <td>21</td>
                    </tr>
                    <tr>
                        <td>22</td>
                        <td>23</td>
                        <td>25</td>
                        <td>26</td>
                        <td>27</td>
                        <td>28</td>
                        <td>28</td>
                    </tr>
                    <tr>
                        <td>29</td>
                        <td>30</td>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>


</html>