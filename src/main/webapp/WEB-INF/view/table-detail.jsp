<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Chi tiết bàn</title>
</head>
<body>
<h1>Chi tiết bàn</h1>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty table}">
    <table border="1" cellpadding="5" cellspacing="0">
        <tr><th>ID</th><td>${table.id}</td></tr>
        <tr><th>Sức chứa</th><td>${table.capacity}</td></tr>
        <tr><th>Trạng thái</th><td>${table.status}</td></tr>
    </table>
</c:if>

<br/>
<a href="${pageContext.request.contextPath}/tables/list">Quay lại danh sách</a>

</body>
</html>
