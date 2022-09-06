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
      <title>Accounts</title>
	  <style>
		#cancel:hover{
			background-color: #d22a2a!important;
		}
		
		#myBtn, #updateBtn{
			padding: 5px;
			color: white;
			background-color: #13a052e5;
			border-radius: 5px;
			border: none;
			cursor: pointer;
		}
		#myBtn:hover, #updateBtn:hover{
			background-color: #0e783e!important;
		}
	  </style>
    </head>
<body>
  <!-- SIDEBAR -->
	<section id="sidebar">
		<a href="#" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">PetClinic</span>
		</a>
		<ul class="side-menu top">
			<li class="">
				<a href="admin.jsp">
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
			<li>
				<s:url action="appointments" var="appointments">
				</s:url>
				<a href="${appointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li>
				<s:url action="approvedAppointments" var="approve">
				</s:url>
				<a href="${approve}">
					<span style="margin-left: 50px;" class="text">Approved</span>
				</a>
			</li>
			<li class="active">
				<a href="<s:url action='listaccounts' />">
					<i class='bx bxs-user' ></i>
					<span class="text">Accounts</span>
				</a>
			</li>
			<li>
				<a href="<s:url action='services' />">
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
			<h3 style="color: green;"><s:property value="status"></s:property></h3>
			<!-- Trigger/Open The Modal -->
			<button id="myBtn" type="button"><i class='bx bx-plus-circle bx-s'></i> Register Account </button>

			<!-- The Modal -->
			<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<h1>ADD ACCOUNT</h1>
			<!-- Add account -->
			<div class="container">
			  <s:form action="register" id="form" style="width: 100%;">
				  <!-- <s:checkbox name="veterinarian" fieldValue="true" label="Veterinarian"/>
				  <s:checkbox name="client" fieldValue="true" label="Client"/> -->
				  <s:radio name="typeOfAccount" list="#{'1':'Admin','2':'Veterinarian','3':'Client'}" value="3" />
				  <div class="control-group">
					  <s:textfield name="accountBean.username" class="form-control p-4" placeholder="Username" required="required" data-validation-required-message="Please enter your username" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:password name="accountBean.password" class="form-control p-4" placeholder="Password" required="required" data-validation-required-message="Please enter your password" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.firstName" class="form-control p-4" placeholder="First Name" required="required" data-validation-required-message="Please enter your first name" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.lastName" class="form-control p-4" placeholder="Last Name" required="required" data-validation-required-message="Please enter your last name" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.address" class="form-control p-4" placeholder="Address" required="required" data-validation-required-message="Please enter your address" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.contactNo" class="form-control p-4" placeholder="Contact Number" required="required" data-validation-required-message="Please enter your contact number" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.email" class="form-control p-4" placeholder="Email" required="required" data-validation-required-message="Please enter your email" />
					  <p class="help-block text-danger"></p>
					</div>
					<s:submit value=" Add account " id="myBtn" class="btn btn-primary py-3 px-5"/>
					
			  </s:form>
			</div>
			</div>
			</div>




			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>All Accounts</h3>
						<!-- <form action="#">
							<div class="form-input">
								<input type="search" placeholder="Search...">
								<button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
							</div>
						</form>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
						<s:form action="filterAccount">
							<select name="filter" id="filter" onchange='if(this.value != 0) { this.form.submit(); }'> 
								<option value="0">Filter By</option> 
								<option value="1">Admin</option> 
								<option value="2">Veterinarian</option> 
								<option value="3">Client</option> 
							</select>
						
						</s:form>
					</div>
					<h3><s:property value="accountDeleted"></s:property></h3>
					<table>
						<thead>
							<tr>
								<th>Name</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Delete</th>
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
							</s:url>
							<s:a href="%{update}"><button type="button" style="padding: 3px; background-color: #ED6436; border: none; border-radius: 5px; color: white;">
							<i class='bx bxs-edit bx-sm'></i></button></s:a>
							 -->

							<!-- Trigger/Open The Modal -->
							<button id="updateBtn">Update</button>

							<!-- The Modal -->
							<div id="myModal" class="modal">

							<!-- Modal content -->
							<div class="modal-content">
								<span class="close">&times;</span>
								<p>UPDATE ACCOUNT</p>
								<div class="container-fluid pt-5">
									<div class="row justify-content-center">
									<div class="col-12 col-sm-8 mb-5">
										<div class="contact-form">
											<s:form action="update" id="form" style="width: 100%;">
												<div class="control-group">
													<s:hidden name="accountBean.accountId" value="%{accountId}"/>
													<s:textfield name="accountBean.username" class="form-control p-4" placeholder="Your Username" required="required" data-validation-required-message="Please enter your name" value="%{username}"/>
													<p class="help-block text-danger"></p>
												  </div>
												  <div class="control-group">
													<s:textfield name="accountBean.firstName" class="form-control p-4" placeholder="First Name" required="required" data-validation-required-message="Please enter your name" value="%{firstName}"/>
													<p class="help-block text-danger"></p>
												  </div>
												  <div class="control-group">
													<s:textfield name="accountBean.lastName" class="form-control p-4" placeholder="Last Name" required="required" data-validation-required-message="Please enter your name" value="%{lastName}"/>
													<p class="help-block text-danger"></p>
												  </div>
												  <div class="control-group">
													<s:textfield name="accountBean.address" class="form-control p-4" placeholder="Address" required="required" data-validation-required-message="Please enter your name" value="%{address}"/>
													<p class="help-block text-danger"></p>
												  </div>
												  <div class="control-group">
													<s:textfield name="accountBean.contactNo" class="form-control p-4" placeholder="Contact Number" required="required" data-validation-required-message="Please enter your name" value="%{contactNo}"/>
													<p class="help-block text-danger"></p>
												  </div>
												  <div class="control-group">
													<s:textfield name="accountBean.email" class="form-control p-4" placeholder="Email" required="required" data-validation-required-message="Please enter your name" value="%{email}" />
													<p class="help-block text-danger"></p>
												  </div>
												  <s:submit value="Update" class="btn btn-primary py-3 px-5"/>
											</s:form>
										</div>
									</div>
									</div>
								</div>
							</div>
							</div>



							
							<s:url action="delete" var="delete">
								<s:param name="accountId" value="accountId"></s:param>
							</s:url>
							<s:a href="%{delete}"><button id="cancel" title="Delete" type="button" style="cursor: pointer;padding: 3px; background-color: #d22a2ae0; border: none; border-radius: 5px; color: white; ">
								<i class='bx bx-x bx-sm'></i></button></s:a>					
						</td>
					</tr>
				</s:iterator>
						</tbody>
					</table>
				</div>
				
			</div>
			<!-- 
			<h1>ADD ACCOUNT</h1>
			Add account
			<div class="w3-container">
			  <s:form action="register" id="form" style="width: 100%;">
				   <s:checkbox name="veterinarian" fieldValue="true" label="Veterinarian"/>
				  <s:checkbox name="client" fieldValue="true" label="Client"/> 
				  <s:radio name="typeOfAccount" list="#{'1':'Admin','2':'Veterinarian','3':'Client'}" value="3" />
				  <div class="control-group">
					  <s:textfield name="accountBean.username" class="form-control p-4" placeholder="Your Username" required="required" data-validation-required-message="Please enter your username" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:password name="accountBean.password" class="form-control p-4" placeholder="Your Password" required="required" data-validation-required-message="Please enter your password" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.firstName" class="form-control p-4" placeholder="First Name" required="required" data-validation-required-message="Please enter your first name" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.lastName" class="form-control p-4" placeholder="Last Name" required="required" data-validation-required-message="Please enter your last name" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.address" class="form-control p-4" placeholder="Address" required="required" data-validation-required-message="Please enter your address" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.contactNo" class="form-control p-4" placeholder="Contact Number" required="required" data-validation-required-message="Please enter your contact number" />
					  <p class="help-block text-danger"></p>
					</div>
					<div class="control-group">
					  <s:textfield name="accountBean.email" class="form-control p-4" placeholder="Email" required="required" data-validation-required-message="Please enter your email" />
					  <p class="help-block text-danger"></p>
					</div>
					<s:submit value="Add account" class="btn btn-primary py-3 px-5"/>
					
			  </s:form>
			  <h3 style="color: green;"><s:property value="successMessage" /></h3>
			-->
				
		  
		  
			</div>
		</main>
		<!-- MAIN -->

		     
	</section>
	<!-- CONTENT -->
<!--   
  <div class="w3-container w3-teal">
    <h1>Create an Account</h1>
  </div>

  <div class="table-data">
    <div class="order">
      <div class="head">
        <h3>All accounts</h3>
        <i class='bx bx-search' ></i>
        <i class='bx bx-filter' ></i>
      </div>

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
          <s:iterator value="accounts">  
                <tr>
                  <td><s:property value="lastName"/>, <s:property value="firstName"/></td>
                  <td><s:property value="address"/></td>
                  <td><s:property value="contactNo"/></td>
                  <td><s:property value="email"/></td>
                  <td><span class="status pending">Delete</span></td>
                </tr>
          </s:iterator>
        </tbody>
      </table>
    </div>
  
  </div> -->


  </div>

  <script src="css/admincss/script.js"></script>
  <script src="css/admincss/modal.js"></script>

</body>
</html>