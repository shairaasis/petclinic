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
			answer = window.confirm("Click okay to cancel this appointment.");
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
							<h1><s:property value="appointmentId"></s:property></h1>
							<h1><s:property value="appointmentBean.petId"></s:property></h1>
							<h1><s:property value="petId"></s:property></h1>
							<h1><s:property value="%{petId}"></s:property></h1>
							<h1><s:property value="appointmentId"></s:property></h1>
							<h1><s:property value="appointmentBean.petId"></s:property></h1>
							<h1><s:property value="appointmentId"></s:property></h1>
							<h1><s:property value="appointmentBean.petId"></s:property></h1>
							<h1><s:property value="appointmentId"></s:property></h1>
							<h1><s:property value="appointmentBean.petId"></s:property></h1>

				<div class="getTimeAvailableForm">
					<h2>Schedule Appointment</h2>
						<s:form action="getTimeAvailable" id="form" style="width: 100%;">
							<s:hidden name="appointmentBean.clientId" value="%{accountId}" />
							<s:textfield name="appointmentBean.appointmentId" value="%{appointmentBean.appointmentId}" disabled="true" />
							<s:textfield name="appointmentBean.petId" value="%{appointmentBean.petId}" disabled="true" />
							<s:textfield name="appointmentBean.petName" value="%{appointmentBean.petName}" disabled="true" />
							<h1><s:property value="appointmentId"></s:property></h1>
							<h1><s:property value="appointmentBean.petName"></s:property></h1>
							
							<h1><s:property value="petName"></s:property></h1>
							<s:textfield name="appointmentBean.customer" value="%{appointmentBean.customer}" disabled="true" />
							<p style="color:red;"><s:property value="formError"></s:property></p>
							<s:select label="Please select the name of Veterinarian." headerKey="-1" id="veterinarian" headerValue="Select Veterinarian"
								list="%{veterinarianId.entrySet()}"
								name="appointmentBean.veterinarian" listKey="key"
								listValue="key"/>
							<sx:datetimepicker name="appointmentBean.dateOfAppointment" labelposition="top" toggleType="fade" label="Choose preferred date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="%{now}" startDate="%{now}" endDate="%{'2023-12-31'}" requiredLabel="true"/>							
							<s:submit id="myBtn" value="Check Time Available" class="btn btn-primary py-3 px-5"/>								
						</s:form>
				</div>	
            	







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
								<s:textfield label="Current Date" value="%{appointmentBean.schedule}" disabled="true" />
								<s:textfield label="Current Time" value="%{appointmentBean.timeOfAppointment}" disabled="true" />
								
								<p style="color:red;">Please select veterinarian time and choose the new date you prefer*</p>
								<s:select label="Please select the name of Veterinarian." headerKey="-1" id="veterinarian" headerValue="Select Veterinarian"
									list="%{veterinarianId.entrySet()}"
									name="appointmentBean.veterinarian" listKey="key"
									listValue="key"/>
								<sx:datetimepicker name="appointmentBean.dateOfAppointment" labelposition="top" toggleType="fade" label="Choose preferred date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="%{now}" startDate="%{now}" endDate="%{'2023-12-31'}" requiredLabel="true"/>							
								<s:submit id="myBtn" value="Check Time Available" class="btn btn-primary py-3 px-5"/>								
							</s:form>
					</div>	
				</s:if>

		
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
  </div>

  <script src="css/admincss/script.js"></script>
  <script src="css/admincss/modal.js"></script>
</body>
</html>