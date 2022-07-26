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
      <title>Profile</title>
    </head>
<body>
	<!-- SIDEBAR -->
	<s:set var = "token" value = "#session.token"/>
	<s:if test="%{#token != null}">
	<section id="sidebar">
		<a href="#" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">PetClinic</span>
		</a>
		<ul class="side-menu top">
			<li class="">
				<a href="profile.jsp">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Dashboard</span>
				</a>
			</li>
			<li>
				<a href="<s:url action='appointments' />">
					<i class='bx bxs-calendar'></i>
					<span class="text">Appointments</span>
				</a>
			</li>
			<li class="active">
				<a href="<s:url action='listaccounts' />">
					<i class='bx bxs-user' ></i>
					<span class="text">Accounts</span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class='bx bxs-clinic'></i>
					<span class="text">Services</span>
				</a>
			</li>
		</ul>
		<ul class="side-menu">
			<li>
				<a href="#" class="logout">
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
			<form action="#">
				<div class="form-input">
					<input type="search" placeholder="Search...">
					<button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
				</div>
			</form>
			<input type="checkbox" id="switch-mode" hidden>
			<label for="switch-mode" class="switch-mode"></label>
			<a href="#" class="notification">
				<i class='bx bxs-bell' ></i>
				<span class="num">8</span>
			</a>
			<a href="#" class="profile">
				<img src="img/people.png">
			</a>
		</nav>
		<!-- NAVBAR -->
    <!-- MAIN -->
		<main>
			<div class="head-title">
				<div class="left">
					<h1>Accounts</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">Accounts</a>
						</li>
					</ul>
				</div>
				
			</div>



			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>All Accounts</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i>
					</div>
					<h3><s:property value="accountDeleted"></s:property></h3>
					<table>
						<thead>
							<tr>
								<th>Name</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Manage</th>
							</tr>
						</thead>
						<tbody>
				<s:iterator value="accounts" var="account">  
					<tr>
						<td><s:property value="firstName"/> <s:property value="lastName"/></td>
						<td><s:property value="address"/></td>
						<td><s:property value="contactNo"/></td>
						<td><s:property value="email"/>
						<td>
							<!-- <s:url action="update" var="update">
								<s:param name="accountId" value="accountId"></s:param>
							</s:url> -->
							<!-- <s:a href="%{update}"><button type="button" style="padding: 3px; background-color: #ED6436; border: none; border-radius: 5px; color: white;">
							<i class='bx bxs-edit bx-sm'></i></button></s:a> -->
                            <s:a href="%{update}"><button type="button" style="padding: 3px; background-color: #ED6436; border: none; border-radius: 5px; color: white;">
							<i class='bx bxs-edit bx-sm'></i></button></s:a>


							
							
							<s:url action="delete" var="delete">
								<s:param name="accountId" value="accountId"></s:param>
							</s:url>
							<s:a href="%{delete}"><button type="button" style="padding: 3px; background-color: #d22a2a; border: none; border-radius: 5px; color: white;">
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
	
  </div>

  <script src="css/admincss/script.js"></script>
  <script src="css/admincss/modal.js"></script>
</s:if>
<s:else><%
String redirectURL = "login.jsp";
response.sendRedirect(redirectURL);
%>
</s:else>
</body>
</html>