<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <s:set var = "token" value = "#session.token"/>
	<s:if test="%{#token != null}">
    <h1>Error...</h1>
    <h1><s:property value="%{error}"></s:property></h1>
    <h1><s:property value="5{accountId}"></s:property></h1>
    
    <h1><s:property value="%{adminId}"></s:property></h1>
    <h1><s:property value="%{accountIdToDelete}"></s:property></h1>
    </s:if>
    <s:else> 
        <%
	String redirectURL = "login.jsp";
	response.sendRedirect(redirectURL);
%>
    </s:else>
</body>
</html>