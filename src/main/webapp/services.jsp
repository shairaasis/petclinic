<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Boxicons -->
      <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
      <!-- My CSS -->
      <link rel="stylesheet" href="css/admincss/style.css">
	  <link rel="stylesheet" href="css/admincss/modal.css">
	  <link href="assets/img/favicon-16x16.png" rel="icon">

      <title>Services</title>
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
		/* Modal Content */
		.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    border-radius: 10px;
    width: 30%;
    text-align: right;
  }
	  </style>
    </head>
<body>
	<!-- SIDEBAR -->
	<s:set var = "token" value = "#session.token"/>
	<s:if test="%{#token != null}">
	<s:if test="%{#session.accountType == 'admin'}">
	<section id="sidebar">
		<a href="#" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">PetClinic</span>
		</a>
		<ul class="side-menu top">
			<li>
				<s:url action="admin" var="admin">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${admin}">
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
				<s:url action="appointments" var="appointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${appointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li>
				<s:url action="approvedAppointments" var="approve">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${approve}">
					<span style="margin-left: 50px;" class="text">Approved</span>
				</a>
			</li>
			<li>
				<s:url action="listaccounts" var="listaccounts">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${listaccounts}">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Accounts</span>
				</a>
			</li>
			<li class="active">
				<s:url action="services" var="services">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${services}">
					<i class='bx bxs-clinic'></i>
					<span class="text">Services</span>
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
					<h1>Services</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">Services</a>
						</li>
					</ul>
				</div>
				
			</div>
			
            <!-- Trigger/Open The Modal -->
			<button id="myBtn" type="button"><i class='bx bx-plus-circle bx-s'></i> Add Service </button>

			<!-- The Modal -->
			<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<br>
							<h2 style="text-align: center;">Add Service</h2>
							<s:form action="addService" id="form" style="width: 100%;" method="GET">
								<s:hidden name="accountId" value="%{accountId}" />
                                <div class="control-group">
									<s:textfield name="serviceBean.service" label="Service Name" class="form-control p-4" placeholder="Service" required="required" data-validation-required-message="Please enter service"/>
									<p class="help-block text-danger"></p>
                                </div>
                                <div class="control-group">
									<s:textfield name="serviceBean.fee" label="Fee" class="form-control p-4" placeholder="Fee" />
									<p class="help-block text-danger"></p>
                                </div>
                                <s:submit id="myBtn" value="Save" class="btn btn-primary py-3 px-5"/>
							</s:form>	
				</div>
			</div>
			<h3 style="color: red;"><s:property value="error"></s:property></h3>


			<div class="table-data">
				<div class="order">
					<div class="head">
						<!-- <h3>Services</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
					</div>
					<table>
						<thead>
							<tr>
								<th>Service</th>
                                <th>Fee</th>
                                <th>Delete</th>
							</tr>
						</thead>
						<tbody>
                        <s:iterator value="services" var="service">  
                            <tr>
                                <td><s:property value="service"/></td>
                                <td><s:property value="fee"/></td>
                                <td>
                                    <!-- <s:url action="update" var="update">
                                        <s:param name="accountId" value="accountId"></s:param>
                                    </s:url>
                                    <s:a href="#"><button type="button" style="padding: 3px; background-color: #ED6436; border: none; border-radius: 5px; color: white;">
                                    <i class='bx bxs-edit bx-sm'></i></button></s:a>
                                     -->
                                    <s:url action="deleteService" var="deleteService">
										<s:param name="serviceId" value="serviceId"></s:param>
										<s:param name="accountId" value="%{accountId}"></s:param>
                                    </s:url>
                                    <s:a href="%{deleteService}"><button id="cancel" title="Delete" type="button" style="cursor: pointer;padding: 3px; background-color: #d22a2ae0; border: none; border-radius: 5px; color: white; ">
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
</s:if>
<s:else>
REDIRECT THIS TO ERROR PAGE. 
This page is only for Admins.
</s:else>
</s:if>
<s:else>
REDIRECT THIS TO ERROR PAGE. 
No Session.
</s:else>
</body>
</html>