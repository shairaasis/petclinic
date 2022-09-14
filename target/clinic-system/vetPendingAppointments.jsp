<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <link href="assets/img/favicon-16x16.png" rel="icon">

      <!-- Boxicons -->
      <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
      <!-- My CSS -->
      <link rel="stylesheet" href="css/admincss/style.css">
	  <link rel="stylesheet" href="css/admincss/modal.css">
      <title>Pending Appointments</title>
	  <style>
		#approve:hover{
			background-color: #13a053!important;
		}
		#cancel:hover{
			background-color: #d22a2a!important;
		}
		
	  </style>
    </head>
<body>
	<!-- SIDEBAR -->
	<s:set var = "token" value = "#session.token"/>
	<s:if test="%{#token != null}">
	<s:if test="%{#session.accountType == 'veterinarian'}">
	<section id="sidebar">
		<a href="#" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">PetClinic</span>
		</a>
		<ul class="side-menu top">
			<li>
				<s:url action="veterinarian" var="veterinarian">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${veterinarian}">
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
				<s:url action="vetPendingAppointments" var="vetPendingAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${vetPendingAppointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li>
				<s:url action="vetApprovedAppointments" var="vetApprovedAppointments">
                    <s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${vetApprovedAppointments}">
					<span style="margin-left: 50px;" class="text">Approved</span>
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
			
			
			

			<div class="table-data">
				<div class="order">
					<div class="head">
						<!-- <h3>Appointments</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
					</div>
					<table>
						<thead>
							<tr>
								<th>Pet</th>
                                <th>Client</th>
                                <th>Service</th>
                                <th>Schedule</th>
                                <th>Status</th>
								<th>Approve</th>
							</tr>
						</thead>
						<tbody>
                        <s:iterator value="appointments" var="appointment">  
                            <tr>
                                <td><s:property value="petName"/></td>
                                <td><s:property value="customer"/></td>
                                <td><s:property value="service"/></td>
                                <td><s:property value="schedule"/></td>
								<td><span class="status pending"><s:property value="status"/></span></td>
                                <td>
                                    <s:url action="approve" var="approve">
                                        <s:param name="appointmentId" value="appointmentId"></s:param>
                                        <s:param name="accountId" value="accountId"></s:param>
                                    </s:url>
                                    <s:a href="%{approve}"><button id="approve" title="Approve" type="button" style="cursor: pointer;padding: 3px; background-color: #15ac59d2; border: none; border-radius: 5px; color: white;">
									<i class='bx bxs-checkbox-checked bx-sm'></i></button></s:a>
                                    
                                    <!-- <s:url action="cancelAppointment" var="cancelAppointment">
                                        <s:param name="appointmentId" value="appointmentId"></s:param>
                                    </s:url>
                                    <s:a href="%{cancelAppointment}"><button id="cancel" title="Cancel" type="button" style="cursor: pointer;padding: 3px; background-color: #d22a2ae0; border: none; border-radius: 5px; color: white; ">
                                    <i class='bx bx-x bx-sm'></i></button></s:a>					 -->
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
</s:if>
<s:else>
REDIRECT THIS TO ERROR PAGE.
This page is only for Veterinarians.
</s:else>
</s:if>
<s:else>
REDIRECT THIS TO ERROR PAGE.
No Session.
</s:else>
</body>
</html>