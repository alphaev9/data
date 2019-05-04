<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/2
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${backlogAmount!=null}">
    <p>the address count is: ${backlogAmount}</p>
</c:if>

<c:if test="${addressInStreet!=null}">
    <p>there are some address corresponding the designated street:</p>
    <c:forEach items="${addressInStreet}" var="address">
        <p>${address.street} : ${address.number}</p>
    </c:forEach>
</c:if>

</body>
</html>
