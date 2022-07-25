<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
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
      <sx:head />
      <title>Pets</title>
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
			<li>
				<s:url action="customerAppointments" var="customerAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${customerAppointments}">
					<span style="margin-left: 50px;" class="text">Pending</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span style="margin-left: 50px;" class="text">Approved</span>
				</a>
			</li>
			<li class="active"><s:url action="pets" var="pets">
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
					<h1>Accounts</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">Pets</a>
						</li>
					</ul>
				</div>
				
			</div>
			
		<!-- Trigger/Open The Modal -->
		<button id="myBtn"><i class='bx bx-plus-circle bx-s'></i>Register Pet</button>

		<!-- The Modal -->
		<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
        <!-- register pet -->
        <h1>PET REGISTRATION</h1>
        <!-- <h1><s:property value="accountId"/></h1> -->
		
		<div class="w3-container">
			<s:form action="registerpet" id="form" style="width: 100%;">
				<div style="text-align: left; float: left;" class="control-group">
					<s:hidden name="petBean.ownerId" value="%{accountId}" />
					<!-- <s:textfield name="petBean.ownerId" class="form-control p-4" placeholder="Your ID" required="required" data-validation-required-message="Please enter your name" /> -->
					<p class="help-block text-danger"></p>
				</div>
				<div class="control-group">
					<s:textfield name="petBean.petName" label="Pet Name" class="form-control p-4" placeholder="Your Pet's name" required="required" data-validation-required-message="Please enter your name" />
					<p class="help-block text-danger"></p>
				</div>
				<div class="control-group">
					<sx:datetimepicker name="petBean.dateOfBirth" toggleType="fade" labelposition="left" label="Date of Birth (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="%{'2010-01-01'}"/>
					<p class="help-block text-danger"></p>
				</div>
				<div class="control-group">
					<s:textfield name="petBean.breed" label="Breed" class="form-control p-4" placeholder="Breed" required="required" data-validation-required-message="Please enter your name" />
					<p class="help-block text-danger"></p>
				</div>
				<div class="control-group">
					<s:textfield name="petBean.coatColor" label="Coat Color" class="form-control p-4" placeholder="Coat color" required="required" data-validation-required-message="Please enter your name" />
					<p class="help-block text-danger"></p>
				</div>
				<s:submit id="myBtn" value="Register" class="btn btn-primary py-3 px-5"/>
				
			</s:form>
		</div>
		</div>
		</div>


			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>All Pets</h3>
						<!-- <i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i> -->
					</div>
					<table>
						<thead>
							<tr>
								<th>Pet</th>
								<th> </th>
								<th></th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
				<s:iterator value="pets" var="pet">  
                <tr>
                  <td><s:property value="petName"/></td>
				  <td> </td>
				  <td> </td>
                  <td>
					<s:url action="deletePet" var="deletepet">
						<s:param name="accountId" value="accountId"></s:param>
						<s:param name="petId" value="petId"></s:param>
					</s:url>
					<s:a href="%{deletepet}"><button id="cancel" title="Delete" type="button" style="cursor: pointer;padding: 3px; background-color: #d22a2ae0; border: none; border-radius: 5px; color: white; ">
					<i class='bx bx-x bx-sm'></i></button></s:a>
                </tr>
          		</s:iterator>
						</tbody>
					</table>
				</div>
			
			</div>
		</main>
<!-- 			
			<h3><s:property value="successMessage" /></h3> -->
  
      


  </div>
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