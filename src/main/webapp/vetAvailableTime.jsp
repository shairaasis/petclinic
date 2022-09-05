<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vet Available Time</title>
</head>
<body>

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
    <s:form action="getTimeAvailable" id="form" style="width: 100%;">
            <s:hidden name="appointmentBean.clientId" value="%{accountId}" />
            <s:hidden name="appointmentBean.dateOfAppointment" value="%{appointmentBean.dateOfAppointment}" />
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
            <div class="control-group"></div>
            <s:select headerKey="-1" id="pet" headerValue="Select Pet"
                    list="listOfPets" 
                    name="appointmentBean.petName" />
                </div>
            <s:property value="appointmentBean.dateOfAppointment"></s:property>
            <s:property value="appointmentBean.veterinarian"></s:property>
            <!-- <select name="appointmentBean.veterinarian">
                <c:forEach items="${veterinarianId}" var="vet">
                    <option value="${vet.key}">${country.value}</option>
                </c:forEach>
            </select> -->

              <s:submit id="myBtn" value="Create" class="btn btn-primary py-3 px-5"/>
    </s:form>
</body>
</html>