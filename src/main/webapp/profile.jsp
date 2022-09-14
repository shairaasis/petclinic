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
      <title>Profile</title>
      <style>
        #myBtn, #updateBtn{
			padding: 10px;
			color: white;
			background-color: #13a052e5;
			border-radius: 5px;
			border: none;
			cursor: pointer;
		}
		#myBtn:hover, #updateBtn:hover{
			background-color: #0e783e!important;
		}
        .input{
            padding: 5px;
            width: 100%;
        }.formButton{
            width: 100%;
            display: grid;
        }.container{
            margin-top: 2em;
        }
      </style>
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
            <s:set var="accountType" value="accountBean.accountType"/>
			<li>
                <s:if test='%{#accountType == "3"}'>
				<s:url action="client" var="client">
					<s:param name="accountId" value="%{accountId}"></s:param>
				</s:url>
				<a href="${client}">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Dashboard</span>
				</a>
                </s:if>
                <s:elseif test='%{#accountType == "2"}'>
                    <s:url action="veterinarian" var="veterinarian">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${veterinarian}">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </s:elseif>
                <s:else>
                    <s:url action="admin" var="admin">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${admin}">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </s:else>
                
			</li>
			<li class="active">
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
				<s:if test='%{#accountType == "3"}'>
                    <s:url action="customerAppointments" var="customerAppointments">
                        <s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${customerAppointments}">
                        <span style="margin-left: 50px;" class="text">Pending</span>
                    </a>
                </s:if>
                <s:elseif test='%{#accountType == "2"}'>
                    <s:url action="vetPendingAppointments" var="vetPendingAppointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${vetPendingAppointments}">
                        <span style="margin-left: 50px;" class="text">Pending</span>
                    </a>
                </s:elseif>
                <s:else>
                    <s:url action="appointments" var="appointments">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${appointments}">
                        <span style="margin-left: 50px;" class="text">Pending</span>
                    </a>
                </s:else>
			</li>
			<li>
				<s:if test='%{#accountType == "3"}'>
                    <s:url action="approvedClientAppointments" var="approve">
                    <s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${approve}">
                        <span style="margin-left: 50px;" class="text">Approved</span>
                    </a>
                </s:if>
                <s:elseif test='%{#accountType == "2"}'>
                    <s:url action="vetApprovedAppointments" var="vetApprovedAppointments">
                    <s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${vetApprovedAppointments}">
                    <span style="margin-left: 50px;" class="text">Approved</span>
                    </a>
                </s:elseif>
                <s:else>
                    <s:url action="approvedAppointments" var="approve">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${approve}">
                        <span style="margin-left: 50px;" class="text">Approved</span>
                    </a>
                </s:else>
			</li>
            <s:if test='%{#accountType == "1"}'>
                <li>
                    <s:url action="listaccounts" var="listaccounts">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${listaccounts}">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Accounts</span>
                    </a>
                </li>
                <li>
                    <s:url action="services" var="services">
					<s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${services}">
                        <i class='bx bxs-clinic'></i>
                        <span class="text">Services</span>
                    </a>
                </li>
            </s:if>
            <s:elseif test='%{#accountType == "3"}'>
                <li>
                    <s:url action="pets" var="pets">
                        <s:param name="accountId" value="%{accountId}"></s:param>
                    </s:url>
                    <a href="${pets}">
                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAATRJREFUSEvtluExBEEQhb+LgBCIgBCIgAwQASJABkRABogAESADIRAB9antq63Znptbbv3Sf652Z9687vfebN2MiWs28fn0CW6Awwah6+6zHoFPYHcRpk/gZsFHFYCHX8O8qXdgDdgE3mokJYH7MpI43PXA3AF7wCVwVSPJCFq2BGYDeOmmCIzPp5183+9+QyBeEifYKYj0RY9SgjDtoWurfK4lbx04B46Bp450pQQhk2GZNz9Wog/ATrPy/RlwArwC26VEGrTVcNjR1fy2sS/1YH9JoB0az6zuu5gb4UGK4g4cVMAXgMDnBd0PApAlwsjZpb+W0pkONfYm1zwoJU8niOY0yIMtiZyq9Z0aRRBRa3g5WF5KIlF/ShBd9Ulr9+dHE/wTpEHJ9F6pB2PjWd0/+b+KL5dQRBmTzVIhAAAAAElFTkSuQmCC" style="width: 16px; height: 16px; margin: 10px;"/>
                        <span class="text">Pets</span>
                    </a>
                </li>
            </s:elseif>
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
					<h1>Profile Page</h1>
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="client.jsp">Profile</a>
						</li>
					</ul>
				</div>
				
			</div>

			<ul class="box-info">
				<li>
					<i class='bx bxs-user'></i>
						<span class="text">
						<h1><s:property value="accountBean.username"></s:property></h1>
                        <p>You are logged in as <s:property value="activeAccount"></s:property></p>
                        
					</span>
				</li>
				
			</ul>
            <h3 style="color: green; 
            color: green;
            text-align: center;
            margin: 1em;
            background: rgba(211, 211, 211, 0.748);
            padding: 5px;"><s:property value="%{updateStatus}"></s:property></h3>
			
            <div class="table-data">
                <div class="order">
                    <div class="head">
						<h3></h3>
						<button id="myBtn" type="button"><i class='bx bx-edit'></i>Update</button>
                        <!-- The Modal -->
                    	<div id="myModal" class="modal">

                        <!-- Modal content -->
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            
                        <!-- Add account -->
                        <div class="container">
                            <h1>Update Account</h1>
                            <s:actionmessage />
                            <s:form action="updateAccount" id="form" style="width: 100%;">
                                    <s:hidden name="accountId" value="%{accountId}" />
                                    <s:hidden name="accountBean.accountType" value="%{accountType}"/>
									<s:password name="accountBean.password" value="password" label="Password" required="required" data-validation-required-message="Please enter your password" />
                                    <s:textfield name="accountBean.username" label="Username" required="required" data-validation-required-message="Please enter your username" />
                                    <s:textfield name="accountBean.firstName" label="First Name" required="required" data-validation-required-message="Please enter your first name" />
                                    <s:textfield name="accountBean.lastName" label="Last Name" required="required" data-validation-required-message="Please enter your last name" />
                                    <s:textfield name="accountBean.email" label="Email" required="required" data-validation-required-message="Please enter your email" />
                                    <s:textfield name="accountBean.address" label="Address" required="required" data-validation-required-message="Please enter your address" />
                                    <s:textfield name="accountBean.contactNo" label="Contact No." required="required" data-validation-required-message="Please enter your contact number" />                        
                                    <s:submit value=" Update " id="updateBtn" class="btn btn-primary py-3 px-5"/>
                            </s:form>
                        </div>
                        </div>
                    	</div>
					</div>
					<table>
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<s:property value="accountBean.firstName"></s:property>
								</td>
								<td><s:property value="accountBean.lastName"></s:property></td>
							</tr>
						</tbody>
					</table>
                    <br>
                    <table>
						<thead>
							<tr>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<s:property value="accountBean.email"></s:property>
								</td>
							</tr>
						</tbody>
					</table>
                    <br>
                    <table>
						<thead>
							<tr>
								<th>Address</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<s:property value="accountBean.address"></s:property>
								</td>
							</tr>
						</tbody>
					</table>
                    <br>
                    <table>
						<thead>
							<tr>
								<th>Contact No.</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<s:property value="accountBean.contactNo"></s:property>
								</td>
							</tr>
						</tbody>
					</table>
				<!-- </div>
            <div class="control-group">
                <s:textfield name="accountBean.username" class="form-control p-4" value="%{accountBean.username}" required="required" data-validation-required-message="Please enter your name" />
                <p class="help-block text-danger"></p>
              </div> -->
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
	<%
	String redirectURL = "login.jsp";
	response.sendRedirect(redirectURL);
%>
</s:else>
</body>
</html>