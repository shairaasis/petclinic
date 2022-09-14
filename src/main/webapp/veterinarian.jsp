<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>Veterinarian</title>
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
			<li class="active">
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
			<li>
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
					<h1>Dashboard</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="client.jsp">Home</a>
						</li>
					</ul>
				</div>
				
			</div>

			<ul class="box-info">
				
				<li>
					<i class='bx bxs-calendar-check' ></i>
					<span class="text">
						<h3><s:property value="%{pendingAppointments}"/></h3>
						<p>Pending Appointments</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-calendar-check' ></i>
					<span class="text">
						<h3><s:property value="%{approvedAppointments}"/></h3>
						<p>Approved Appointments</p>
					</span>
				</li>
			</ul>
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3><s:property value="%{today}"/><br>Appointments</h3>
						<!-- <i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
					</div>
					<table>
						<thead>
							<tr>
								<th>Pet</th>
                                <th>Owner</th>
                                <th>Service</th>
                                <th>Schedule</th>
                                <th>Status</th>
							</tr>
						</thead>
						<tbody>
                        <s:iterator value="appointments" var="appointment">  
                            <tr>
                                <td><s:property value="petName"/></td>
                                <td><s:property value="customer"/></td>
                                <td><s:property value="service"/></td>
                                <td><s:property value="schedule"/></td>
								<s:set var="stat" value="status"/>
								<s:if test='%{#stat == "pending"}'>
									<td><span class="status pending" ><s:property value="status"/></span></td>
								</s:if>
								<s:else>
									<td><span class="status completed" style="background-color: rgba(0, 128, 0, 0.831);"><s:property value="status"/></span></td>
								</s:else>
                            </tr>
                        </s:iterator>
						</tbody>
					</table>
				</div>
				
			</div>




  </div>
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
	
	
  
  </div>
  <script src="css/admincss/script.js"></script>
</s:if>
<s:else>
	<s:include value="/access-denied.jsp"></s:include>
</s:else>
</s:if>
<s:else>
	<jsp:forward page="login.jsp"> 
	<jsp:param name="noSession" value="Session Timeout. Please log in again." /> 
	</jsp:forward>
</s:else>
</body>
</html>