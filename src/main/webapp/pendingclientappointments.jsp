<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
    <head>
      <meta charset="UTF-8">
	  <link href="assets/img/favicon-16x16.png" rel="icon">

      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Boxicons -->
      <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
      <!-- My CSS -->
      <link rel="stylesheet" href="css/admincss/style.css">
	  <link rel="stylesheet" href="css/admincss/modal.css">
      <title>Pending Appointments</title>
	  <sx:head />
	  <style>
		#cancel:hover{
			background-color: #d22a2a!important;
		}
		#myBtn{
			padding: 10px;
			color: white;
			background-color: #13a052e5;
			border-radius: 5px;
			border: none;
			cursor: pointer;
			margin-top: 10px;
		}
		#myBtn:hover{
			background-color: #0e783e!important;
		}
		.tdLabel{
			display: none;
		}
		.tdInput{
			margin-left: 0px;
		}
		.wwFormTable {
			display: flex;
			align-items: center;
			justify-content: center;
			margin-top: 1em;;
		}
		.getTimeAvailableForm{
			text-align: center;
			margin: 0px auto;
			width: 100%;
			display: block;
			border-style: groove;
		    padding: 40px;
			border-radius: 10px;
			border-color: 2px #0e783e;
			background-color: #0e783e0a;
			float: center;
			margin: 10px 0px;

		}
		select#veterinarian, select#service, select#pet{
			padding: 10px; font-size: medium;
			width: 100%; margin-bottom: 0.5em;
		}
		input{
			padding: 10px;font-size: medium;
			width: 55em;
			height: 3em;
			margin-bottom: 0.5em;
		}
		#dateIcon{
			width: 34px;
			height: 40px;
		}.formButton {
			text-align: left;
			display: grid;
			display: grid;
		}
		
	  </style>
	  <script>
		function myFunction() {
			var answer;
			answer = window.confirm("Click okay to cancel this appointment. ");
			if (answer == true) {
				return true;
			} else {
				return false;
			}
		}
		</script>
    </head>
<body>
  <!-- SIDEBAR -->
	<section id="sidebar">
		<a href="#" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">PetClinic</span>
		</a>
		<ul class="side-menu top">
			<li>
				<s:url action="client" var="client">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${client}">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Dashboard</span>
				</a>
			</li>
			<li>
				<s:url action="profile" var="profile">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${profile}">
					<i class='bx bxs-id-card'></i>
					<span class="text">Profile</span>
				</a>
			</li>
			<li>
				<a href="#" style="pointer-events: none; cursor: default;" >
					<i class='bx bxs-calendar'></i>
					<span class="text">Appointments</span>
				</a>
			</li>
			<li class="active">
				<s:url action="customerAppointments" var="customerAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${customerAppointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li>
				<s:url action="approvedClientAppointments" var="approvedClientAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${approvedClientAppointments}">
					<span style="margin-left: 50px;" class="text">Approved</span>
				</a>
			</li>
			<li>
				<s:url action="pets" var="pets">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${pets}">
					<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAATRJREFUSEvtluExBEEQhb+LgBCIgBCIgAwQASJABkRABogAESADIRAB9antq63Znptbbv3Sf652Z9687vfebN2MiWs28fn0CW6Awwah6+6zHoFPYHcRpk/gZsFHFYCHX8O8qXdgDdgE3mokJYH7MpI43PXA3AF7wCVwVSPJCFq2BGYDeOmmCIzPp5183+9+QyBeEifYKYj0RY9SgjDtoWurfK4lbx04B46Bp450pQQhk2GZNz9Wog/ATrPy/RlwArwC26VEGrTVcNjR1fy2sS/1YH9JoB0az6zuu5gb4UGK4g4cVMAXgMDnBd0PApAlwsjZpb+W0pkONfYm1zwoJU8niOY0yIMtiZyq9Z0aRRBRa3g5WF5KIlF/ShBd9Ulr9+dHE/wTpEHJ9F6pB2PjWd0/+b+KL5dQRBmTzVIhAAAAAElFTkSuQmCC" style="width: 16px; height: 16px; margin: 10px;"/>
					<span class="text">Pets</span>
				</a>
			</li>
		</ul>
		<ul class="side-menu">
			<li>
				<s:url action="logout" var="logout">
				</s:url>
				<a href="${logout}" class="logout">
					<i class='bx bxs-log-out-circle' ></i>
					<span class="text">Logout</span>
				</a>
			</li>
		</ul>
	</section>
	<!-- SIDEBAR -->
  <!-- CONTENT -->
	<section id="content">
		<!-- NAVBAR -->
		<nav>
			<i class='bx bx-menu' ></i>
			<a href="#" class="nav-link">Categories</a>
			<form style="visibility: hidden;" action="#">
				<div class="form-input">
					<input type="search" placeholder="Search...">
					<button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
				</div>
			</form>
			<input type="checkbox" id="switch-mode" hidden>
			<label for="switch-mode" class="switch-mode"></label>
			<!-- <a href="#" class="notification">
				<i class='bx bxs-bell' ></i>
				<span class="num">8</span>
			</a>
			<a href="#" class="profile">
				<img src="img/people.png">
			</a> -->
		</nav>
		<!-- NAVBAR -->
    <!-- MAIN -->
		<main>
			


			
			<div class="head-title">
				<div class="left">
					<h1>Appointments</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">Pending Appointments</a>
						</li>
					</ul>
				</div>
				
			</div>
				<s:url action="clickCreateAppointment" var="clickCreateAppointment">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${clickCreateAppointment}">
					<button id="myBtn"><i class='bx bx-plus-circle bx-s'></i> Create Appointment</button>
				</a>
				<br>
				<s:set var="clickUpdateAppointment" value="clickUpdateAppointment"/>
				<s:if test='%{#clickUpdateAppointment == "true"}'>
					<div class="getTimeAvailableForm">
						<h2>Reschedule Appointment</h2>
							<s:form action="updateAppointment" id="form" style="width: 100%;">
								<s:hidden name="appointmentBean.clientId" value="%{accountId}" />
								<p style="color:red;"><s:property value="formError"></s:property></p>
								<s:textfield label="Owner" name="appointmentBean.customer" value="%{appointmentBean.customer}" disabled="true" />
								<s:textfield label="Pet Name" name="appointmentBean.petName" value="%{appointmentBean.petName}" disabled="true" />
								<s:textfield label="Service" name="appointmentBean.service" value="%{appointmentBean.service}" disabled="true" />
								<s:textfield label="Current Schedule" value="%{appointmentBean.schedule}" disabled="true" />
								
								<p style="color:red;">Please select veterinarian name and choose the date you prefer*</p>
								<s:select label="Please select the name of Veterinarian." headerKey="-1" id="veterinarian" headerValue="Select Veterinarian"
									list="%{veterinarianId.entrySet()}"
									name="appointmentBean.veterinarian" listKey="key"
									listValue="key"/>
								<sx:datetimepicker name="appointmentBean.dateOfAppointment" labelposition="top" toggleType="fade" label="Choose preferred date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="%{now}" startDate="%{now}" endDate="%{'2023-12-31'}" requiredLabel="true"/>							
								<s:submit id="myBtn" value="Check Time Available" class="btn btn-primary py-3 px-5"/>								
							</s:form>
					</div>	
				</s:if>
				
				<s:set var="createAppointment" value="createAppointment"/>
				<s:if test='%{#createAppointment == "true"}'>
				<div class="getTimeAvailableForm">
					<h2>Schedule Appointment</h2>
						<s:form action="getTimeAvailable" id="form" style="width: 100%;">
							<s:hidden name="appointmentBean.clientId" value="%{accountId}" />
							<p style="color:red;"><s:property value="formError"></s:property></p>
							<s:select label="Please select the name of Veterinarian." headerKey="-1" id="veterinarian" headerValue="Select Veterinarian"
								list="%{veterinarianId.entrySet()}"
								name="appointmentBean.veterinarian" listKey="key"
								listValue="key"/>
							<sx:datetimepicker name="appointmentBean.dateOfAppointment" labelposition="top" toggleType="fade" label="Choose preferred date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="%{now}" startDate="%{now}" endDate="%{'2023-12-31'}" requiredLabel="true"/>							
							<s:submit id="myBtn" value="Check Time Available" class="btn btn-primary py-3 px-5"/>								
						</s:form>
				</div>	
            	</s:if>
				
				
				<s:set var="timeIsAvailable" value="timeIsAvailable"/>
							<s:if test='%{#timeIsAvailable == "yes"}'>
								<s:property value="veterinarian"/>
								<table>
                                    <thead>
                                        <tr>
                                            <th>Appointment ID</th>
                                            <th>Customer ID</th>
                                            <th>Pet ID</th>
                                            <th>Vet ID </th>
                                            <th>Service</th>
                                            <th>Schedule</th>
                                            <th>Time of Appointment</th>
                                            <th>Status</th>
                                            <th>Veterinarian</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="vetAppointments" var="vetAppointment">  
                                        <tr>
                                            <td><s:property value="appointmentId"/></td>
                                            <td><s:property value="clientId"/></td>
                                            <td><s:property value="petId"/></td>
                                            <td><s:property value="veterinarianId"/></td>
                                            <td><s:property value="serviceId"/></td>
                                            <td><s:property value="schedule"/></td>
                                            <td><s:property value="timeOfAppointment"/></td>
                                            <td><span class="status completed"><s:property value="status"/></span></td>
                                            <td><s:property value="veterinarian"/></td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
							<s:form action="getTimeAvailable" id="form" style="width: 100%;">
									<s:hidden name="appointmentBean.clientId" value="%{accountId}" />
									<s:hidden name="appointmentBean.dateOfAppointment" value="%{appointmentBean.dateOfAppointment}" />
									<div class="control-group">
                                        <s:select headerKey="-1" id="veterinarian" headerValue="Select Time"
										list="%{listOfTime.entrySet()}"
										name="appointmentBean.timeOfAppointment" listKey="key"
										listValue="value"/>
									</div>
									<div class="control-group">
                                        <s:select headerKey="-1" id="service" headerValue="Select Service"
										list="listOfServices" 
										name="appointmentBean.service" />
									</div>
									<div class="control-group"></div>
									<s:select headerKey="-1" id="pet" headerValue="Select Pet"
											list="listOfPets" 
											name="appointmentBean.petName" />
										</div>
                                    <s:property value="appointmentBean.dateOfAppointment"></s:property>
                                    <s:property value="appointmentBean.veterinarian"></s:property>
									<!-- <select name="appointmentBean.veterinarian">
										<c:forEach items="${veterinarianId}" var="vet">
											<option value="${vet.key}">${country.value}</option>
										</c:forEach>
									</select> -->

								  	<s:submit id="myBtn" value="Create" class="btn btn-primary py-3 px-5"/>
							</s:form>
						</s:if>

			<h3 style="color: green;"><s:property value="appointmentStatus"></s:property></h3>
			
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Appointments</h3>
						<!-- <i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
					</div>
					<table>
						<thead>
							<tr>
								<th>Pet</th>
                                <th>Veterinarian</th>
                                <th>Service</th>
                                <th>Schedule</th>
                                <th>Status</th>
								<th>Cancel</th>
							</tr>
						</thead>
						<tbody>
                        <s:iterator value="appointments" var="appointment">  
                            <tr>
                                <td><s:property value="petName"/></td>
                                <td><s:property value="veterinarian"/></td>
                                <td><s:property value="service"/></td>
                                <td><s:property value="schedule"/></td>
								<td><span class="status completed"><s:property value="status"/></span></td>
                                <td>
									<s:url action="updateAppointment" var="updateAppointment">
										<s:param name="accountId" value="accountId"></s:param>
										<s:param name="appointmentId" value="appointmentId"></s:param>
									</s:url>
									<s:a href="%{updateAppointment}"><button id="update" title="Update" type="button" style="cursor: pointer;padding: 3px; background-color: green; border: none; border-radius: 5px; color: white; ">
										<i class='bx bxs-pencil bx-sm' ></i></button></s:a>
									
                                    <s:url action="cancel" var="cancel">
										<s:param name="accountId" value="accountId"></s:param>
                                        <s:param name="appointmentId" value="appointmentId"></s:param>
                                    </s:url>
                                    <s:a href="%{cancel}" onclick="return myFunction();"><button id="cancel" title="Cancel" type="button" style="cursor: pointer;padding: 3px; background-color: #d22a2ae0; border: none; border-radius: 5px; color: white; ">
                                    <i class='bx bx-x bx-sm'></i></button></s:a>	
										
                                </td>
                            </tr>
                        </s:iterator>
						</tbody>
					</table>
				</div>
				
			</div>

			
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
  </div>

  <script src="css/admincss/script.js"></script>
  <script src="css/admincss/modal.js"></script>
</body>
</html>