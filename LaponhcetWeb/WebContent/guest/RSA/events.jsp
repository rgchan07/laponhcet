<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mytechnopal.*"%>
<%@ page import="com.mytechnopal.dto.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@ page import="com.laponhcet.util.*"%>
<%@ page import="com.laponhcet.dto.*"%>
<%@ page import="com.laponhcet.dao.*"%>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	UserDTO user = (UserDTO) session.getAttribute(UserDTO.SESSION_USER);
%>


<div class="row">
	<div class="col-lg-12 text-center">
		<div class="navy-line"></div>
		<h1>
			List of <span class="navy"> Events</span>
		</h1>
		<p>List of past, present and future events</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
			<div class="project-list">
				<table class="table table-hover">
					<tbody>
						<tr>
							<td class="project-status"><span class="label label-primary">Completed</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event1Details">The 21st Century Learning
									Strategies</a> <br /> <small>October 18-20, 2017 | Planta
									Hotel, Bacolod City</small></td>
							<td class="project-completion">
								<div id="divEvent1Status" class="btn" data-toggle="modal"
									data-target="#documentation1"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event1Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="static/RSA/images/speakers/speaker1.jpg"></a>&nbsp;<em>Lanie
									C. Rallos</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event1Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-primary">Completed</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event2Details">E-Commerce Entrepreneurship</a> <br />
								<small>November 24-25, 2017 | President's Hall,
									UNO-Recoletos, Bacolod City</small></td>
							<td class="project-completion ">
								<div id="divEvent2Status" class="btn" data-toggle="modal"
									data-target="#documentation2"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event2Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="static/RSA/images/speakers/speaker2.jpg"></a>&nbsp;<em>Prof.
									Ramon Manuel Nisperos</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event2Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-primary">Completed</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event3Details">Feedmill Audit and
									Slaughtercheck </a> <br /> <small>November 24-25, 2017 |
									UNO-R Grounds, UNO-Recoletos, Bacolod City</small></td>
							<td class="project-completion">
								<div id="divEvent3Status" class="btn" data-toggle="modal"
									data-target="#documentation3"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event3Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="static/RSA/images/speakers/speaker3.jpg"></a>&nbsp;<em>Dr.
									Rodeo P. Gonzaga</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event3Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-primary">Completed</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event4Details">Workshop on Ethics Formation</a> <br />
								<small>December 1, 2017 | College of Engineering AVR,
									UNO-R, Bacolod City</small></td>
							<td class="project-completion">
								<div id="divEvent4Status" class="btn" data-toggle="modal"
									data-target="#documentation4"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event4Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="static/RSA/images/speakers/speaker4.png"></a>&nbsp;<em>Oscar
									Bulaong Jr., PhD</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event4Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-primary">Completed</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event5Details">Consultative Meeting</a> <br /> <small>December
									15, 2017 | To be stated</small></td>
							<td class="project-completion">
								<div id="divEvent5Status" class="btn" data-toggle="modal"
									data-target="#documentation5"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event5Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/baticados.jpg"></a>&nbsp;<em>Glenn
									Baticados</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event5Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event6Details">Project Implementation and
									Evaluation Workshop</a> <br /> <small>December 27-29, 2017
									| To be stated</small></td>
							<td class="project-completion">
								<div id="divEvent6Status" class="btn" data-toggle="modal"
									data-target="#documentation6"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event6Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>Vilma
									Azucena</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event6Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event7Details">Strategic Planning</a> <br /> <small>January
									26-28, 2018 | Western Visayas</small></td>
							<td class="project-completion">
								<div id="divEvent7Status" class="btn" data-toggle="modal"
									data-target="#documentation7"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event7Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>AL
									Cruz</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event7Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event8Details">Retooling of Curriculum</a> <br />
								<small>February 2, 2018 | To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent8Status" class="btn" data-toggle="modal"
									data-target="#documentation8"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event8Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event7Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event9Details">Web Ethics Seminar</a> <br /> <small>February
									3, 2018 | To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent9Status" class="btn" data-toggle="modal"
									data-target="#documentation9"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event9Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced.</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event9Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event10Details">Development of learning
									Portals</a> <br /> <small>February 9, 2018 | To be stated.</small>
							</td>
							<td class="project-completion">
								<div id="divEvent10Status" class="btn" data-toggle="modal"
									data-target="#documentation10"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event10Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced.</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event10Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event11Details">Setting Up Satellites Demo
									Farms and Virtual Farms</a> <br /> <small>February 23, 2018
									| To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent11Status" class="btn" data-toggle="modal"
									data-target="#documentation8"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event11Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event11Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>

						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event12Details">Retooling of Curriculum</a> <br />
								<small>March 2, 2018 | To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent12Status" class="btn" data-toggle="modal"
									data-target="#documentation8"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event8Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event7Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>



						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event13Details">Project Design Implementation,
									Monitoring and Training</a> <br /> <small>March 3, 2018 |
									To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent13Status" class="btn" data-toggle="modal"
									data-target="#documentation9"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event13Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced.</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event13Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event14Details">Agri-business Commodity System</a>
								<br /> <small>March 9, 2018 | To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent14Status" class="btn" data-toggle="modal"
									data-target="#documentation10"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event14Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced.</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event14Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event15Details">Training Emerging Knowledge
									Crop Science/ Bio-Technology</a> <br /> <small>May 4, 2018
									| To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent15Status" class="btn" data-toggle="modal"
									data-target="#documentation8"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event15Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event15Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
						<tr>
							<td class="project-status"><span class="label label-warning">Upcoming</span>
							</td>
							<td class="project-title"><a id="nav-button" href="#"
								class="btn btn-white btn-sm name-event" data-toggle="modal"
								data-target="#event16Details">Cascading Knowledge on
									Agri-Coursewares</a> <br /> <small>3rd week of May, 2018 |
									To be stated.</small></td>
							<td class="project-completion">
								<div id="divEvent16Status" class="btn" data-toggle="modal"
									data-target="#documentation8"
									style="width: 100%; text-align: left;"></div>
							</td>
							<td class="project-people no-padding" align="left"><a
								id="nav-button" href="#" class="btn btn-white btn-sm"
								data-toggle="modal" data-target="#event16Speaker"
								style="border-radius: 50%; padding: 5px 4px"><img
									alt="image" class="img-circle"
									src="common/inspinia/img/no-pic.jpg"></a>&nbsp;<em>To be
									announced</em></td>
							<td class="project-actions"><a id="nav-button" href="#"
								class="btn btn-white btn-sm" data-toggle="modal"
								data-target="#event16Details"><i class="fa fa-search"></i>
									Details </a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<jsp:include page="event1/details.jsp"></jsp:include>
<jsp:include page="event1/speaker.jsp"></jsp:include>
<jsp:include page="event1/documentation1.jsp"></jsp:include>
<jsp:include page="event2/details.jsp"></jsp:include>
<jsp:include page="event2/documentation2.jsp"></jsp:include>
<jsp:include page="event2/speaker.jsp"></jsp:include>
<jsp:include page="event3/details.jsp"></jsp:include>
<jsp:include page="event3/documentation3.jsp"></jsp:include>
<jsp:include page="event3/speaker.jsp"></jsp:include>
<jsp:include page="event4/details.jsp"></jsp:include>
<jsp:include page="event4/speaker.jsp"></jsp:include>
<jsp:include page="event4/documentation4.jsp"></jsp:include>



<script>
    CountDownTimer('10/18/2017 08:0 AM', 'divEvent1Status');
    CountDownTimer('11/24/2017 08:0 AM', 'divEvent2Status');
    CountDownTimer('11/24/2017 08:0 AM', 'divEvent3Status');
    CountDownTimer('12/1/2017 08:0 AM', 'divEvent4Status');
    CountDownTimer('12/15/2017 08:0 AM', 'divEvent5Status');
    CountDownTimer('12/27/2017 08:0 AM', 'divEvent6Status');
    CountDownTimer('1/26/2018 08:0 AM', 'divEvent7Status');
    CountDownTimer('2/2/2018 08:0 AM', 'divEvent8Status');
    CountDownTimer('2/3/2018 08:0 AM', 'divEvent9Status');
    CountDownTimer('2/9/2018 08:0 AM', 'divEvent10Status');
    CountDownTimer('2/23/2018 08:0 AM', 'divEvent11Status');
    CountDownTimer('3/2/2018 08:0 AM', 'divEvent12Status');
    CountDownTimer('3/9/2018 08:0 AM', 'divEvent13Status');
    CountDownTimer('5/4/2018 08:0 AM', 'divEvent14Status');
  	CountDownTimer('5/11/2018 08:0 AM', 'divEvent15Status');
  	CountDownTimer('5/18/2018 08:0 AM', 'divEvent16Status');
      

  	function CountDownTimer(dt, id)
    {
        var end = new Date(dt);

        var _second = 1000;
        var _minute = _second * 60;
        var _hour = _minute * 60;
        var _day = _hour * 24;
        var timer;

        function showRemaining() {
            var now = new Date();
            var distance = end - now;
            if (distance < 0) {

                clearInterval(timer);
                document.getElementById(id).innerHTML = 'Finished 100% - <small>Click to view summary</small> <div class=\"progress progress-mini\"><div style=\"width: 100%;\" class=\"progress-bar\"></div> </div>';
                return;
            }
            var days = Math.floor(distance / _day);
            var hours = Math.floor((distance % _day) / _hour);
            var minutes = Math.floor((distance % _hour) / _minute);
            var seconds = Math.floor((distance % _minute) / _second);

  //           document.getElementById(id).innerHTML = "<span class='fa fa-clock-o fa-lg'>&nbsp;";
  //           document.getElementById(id).innerHTML += days + '<small>days</small>';
  //           document.getElementById(id).innerHTML += hours + '<small>hrs</small> ';
 //            document.getElementById(id).innerHTML += minutes + '<small>mins</small> ';
  //           document.getElementById(id).innerHTML += seconds + '<small>secs</small>';
  //           document.getElementById(id).innerHTML += "</span>";
  
            document.getElementById(id).innerHTML = "<div class='col-sm-12'>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fff7f8;border-top-left-radius:5px;font-size:20px;'><strong>" + days + "</strong></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fff7f8;font-size:20px;'><strong>" + hours + "</strong></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fff7f8;font-size:20px;'><strong>" + minutes + "</strong></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fff7f8;border-top-right-radius:5px;font-size:20px;'><strong>" + seconds + "</strong></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fad9df;border-bottom-left-radius:5px;'><small>days</small></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fad9df;'><small>hours</small></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fad9df;'><small>minutes</small></div>";
            document.getElementById(id).innerHTML += "<div class='col-sm-3 no-padding' style='text-align:center;background-color:#fad9df;border-bottom-right-radius:5px;'><small>seconds</small></div>";
            document.getElementById(id).innerHTML += "</div>";
        }
        timer = setInterval(showRemaining, 1000);
    }
    
</script>


<script>
<%-- 	alert(<%=sessionInfo.getCurrentUser().getUserGroup().getCode()%>); --%>
<%-- 	alert(<%=sessionInfo.getCurrentUser().getCode()%>); --%>
	<%
	if(sessionInfo.getCurrentUser().getUserGroup().getCode().equalsIgnoreCase("9")
			&& !sessionInfo.getCurrentUser().getUserGroup().getCode().equalsIgnoreCase(UserGroupDTO.USER_GROUP_GUEST_CODE)
			&& new EventParticipantDAO().getEventParticipantByUserCode(sessionInfo.getCurrentUser().getCode())!=null){
	%>
		document.getElementById('divRegister').innerHTML = "<a id='registerBtn' class='btn btn-success btn-sm' href='#' onclick='addEventParticipant()'><i class='fa fa-pencil'></i><font style='font-size:11px'>&nbsp;Register</font></a>";
	<%
	} else if(sessionInfo.getCurrentUser().getUserGroup().getCode().equalsIgnoreCase(UserGroupDTO.USER_GROUP_GUEST_CODE)){
	%>
		document.getElementById('divRegister').innerHTML = "<a id='registerBtn' class='btn btn-success btn-sm' data-toggle='modal' href='#modal-form'><i class='fa fa-pencil'></i><font style='font-size:11px'>&nbsp;Register</font></a>";
	<%
	} else if(new EventParticipantDAO().getEventParticipantByUserCode(sessionInfo.getCurrentUser().getCode()) != null){
	%>
		document.getElementById('divRegister').innerHTML = "<a id='registerBtn' class='btn btn-danger btn-sm' title='You are Registered!'><i class='fa fa-check'></i><font style='font-size:11px'>&nbsp;Registered</font></a>";
	<%
	}
	%>
</script>



<script>
 function addEventParticipant(){
	 document.getElementById('txtEventCode').value = '005';
	 openLink('UE0143');
 }
</script>
