<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="assets/img/favicon-16x16.png" rel="icon">

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->

    <!-- Google Web Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <title>Login</title>
    <style>
        ul.actionMessage li {
            list-style-type: none;
            text-align: center;
        }
    </style>
</head>

<body>
    <!-- Topbar Start -->    
    <div class="container-fluid" style="background-color:rgb(204 189 189 / 20%);">
        
        <div class="row py-3 px-lg-5">
            <div class="col-lg-4">
                <a href="" class="navbar-brand d-none d-lg-block">
                    <h1 class="m-0 display-5 text-capitalize"><span class="text-primary">Pet</span>Clinic</h1>
                </a>
            </div>
            <div class="col-lg-8 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <div class="d-inline-flex flex-column text-center pr-3 border-right">
                        <h6>Opening Hours</h6>
                        <p class="m-0">8.00AM - 7.00PM</p>
                    </div>
                    <div class="d-inline-flex flex-column text-center px-3 border-right">
                        <h6>Email Us</h6>
                        <p class="m-0">petclinic@gmail.com</p>
                    </div>
                    <div class="d-inline-flex flex-column text-center pl-3">
                        <h6>Call Us</h6>
                        <p class="m-0">+6399 501 14219</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <!-- <div class="container-fluid p-0">
        <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-lg-5">
            <a href="" class="navbar-brand d-block d-lg-none">
                <h1 class="m-0 display-5 text-capitalize font-italic text-white"><span class="text-primary">Safety</span>First</h1>
            </a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between px-3" id="navbarCollapse">
                <div class="navbar-nav mr-auto py-0">
                    <a href="index.jsp" class="nav-item nav-link active">Home</a>
                    <a href="about.html" class="nav-item nav-link">About</a>
                    <a href="#" class="nav-item nav-link">Services</a>
                    <a href="#" class="nav-item nav-link">Products</a>
                    <a href="#" class="nav-item nav-link">Contact</a>
                </div>
                
                
            </div>
        </nav>
    </div> -->
    <!-- Navbar End -->

  
    <div class="container-fluid pt-5">
      <div class="d-flex flex-column text-center mb-5 pt-5">
        <h4 class="text-secondary mb-4">Verification</h4>
      </div>
      <div class="row justify-content-center">
        <div class="col-12 col-sm-8 mb-5">
          <div class="contact-form">
            <s:if test="hasActionMessages()">
            <div class="loggedOut">
                <s:actionmessage/>
                
            </div>
            </s:if>
            <p style="text-align: center;"><s:property value="status"/></p>
            <p style="text-align: center; color: red;"><s:property value="errorMessage"/></p>
            
          <s:form action="verify" style="width: 100%;">
            <div class="control-group">
                <s:textfield name="accountBean.username" class="form-control p-4" placeholder="Your Username" required="required" data-validation-required-message="Please enter your username" />
                <p class="help-block text-danger"></p>
              </div> 
            <div class="control-group">
              <s:textfield name="fourDigitCode" value ="" class="form-control p-4" placeholder="Your 4-Digit Code" required="required" data-validation-required-message="Please enter your 4-Digit Code" />
              <p class="help-block text-danger"></p>
            </div>
            <s:submit value="Verify Account" class="btn btn-primary py-3 px-5" style="float: right;"/>
            
          </s:form>
          </div>
        </div>
      </div>
    </div>
</body>
</html>
