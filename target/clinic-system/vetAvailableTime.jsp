<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="assets/img/favicon-16x16.png" rel="icon">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <!-- My CSS -->
    <link rel="stylesheet" href="css/admincss/style.css">
    <link rel="stylesheet" href="css/admincss/modal.css">
    <sx:head />
    <style>
    #cancel:hover{
			background-color: #d22a2a!important;
		}
		#myBtn{
			padding: 5px;
			color: white;
			background-color: #13a052e5;
			border-radius: 5px;
			border: none;
			cursor: pointer;
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
		}
		.getTimeAvailableForm{
			text-align: center;
			margin: 0px auto;
			width: 50%;
			display: block;
		}
		select#veterinarian{
			padding: 10px; font-size: medium;
		}
		input{
			padding: 10px;font-size: medium;
		}
		#dateIcon{
			width: 34px;
			height: 40px;
		}.formButton {
			text-align: left;
			display: grid;
		}
    </style>
        <title>Vet Available Time</title>
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
							<a class="active" href="#">Create Appointment</a>
						</li>
					</ul>
				</div>
				
			</div>

			<h3 style="color: green;"><s:property value="appointmentStatus"></s:property></h3>
            <h2>Schedule an appointment</h2>
            <s:form action="createAppointment" id="form" style="width: 100%;">
                <s:hidden name="appointmentBean.clientId" value="%{accountId}" />
                <s:textfield name="appointmentBean.veterinarianId" value="%{appointmentBean.veterinarian}" disabled="true" />
                <s:textfield name="appointmentBean.dateOfAppointment" value="%{appointmentBean.dateOfAppointment}" disabled="true" />
                <div class="control-group">
                    <s:select headerKey="-1" id="veterinarian" headerValue="Select Time"
                    list="%{listOfTimes.entrySet()}"
                    name="appointmentBean.timeOfAppointment" listKey="key"
                    listValue="value"/>
                </div>
                <div class="control-group">
                    <s:select headerKey="-1" id="service" headerValue="Select Service"
                    list="listOfServices" 
                    name="appointmentBean.service" />
                </div>
                <div class="control-group">
                <s:select headerKey="-1" id="pet" headerValue="Select Pet"
                        list="listOfPets" 
                        name="appointmentBean.petName" />
                </div>

                <s:submit id="myBtn" value="Create" class="btn btn-primary py-3 px-5"/>
            </s:form>
			

            <h1>Veterinarian<s:property value="appointmentBean.veterinarian"/></h1>
    <h1>DATE<s:property value="appointmentBean.dateOfAppointment"></s:property></h1>
    <h1>ACCOUNT ID<s:property value="accountId"></s:property></h1>

  
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
			
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->


    
    
</body>
</html>