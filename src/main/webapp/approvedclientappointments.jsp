<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-dojo-tags" %>

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
      <title>Approved Appointments</title>
	  <sx:head />
	  <style>
		#cancel:hover{
			background-color: #d22a2a!important;
		}
		.status{
			background-color: #13a053!important;;
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
			<li>
				<s:url action="customerAppointments" var="customerAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${customerAppointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li class="active">
				<s:url action="approvedClientAppointments" var="approve">
                    <s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${approve}">
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
							<a class="active" href="#">Appointments</a>
						</li>
					</ul>
				</div>
				
			</div>

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
							<p><s:property value="%{numOfAppointments}"/></p>
                        <s:iterator value="appointments" var="appointment">  
                            <tr>
								<td><s:property value="petName"/></td>
                                <td><s:property value="veterinarian"/></td>
                                <td><s:property value="service"/></td>
                                <td><s:property value="schedule"/></td>
								<td><span class="status"><s:property value="status"/></span></td>
                                <td>
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