<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản trị bàn ăn</title>
</head>
<body>
<h1>Danh sách bàn ăn</h1>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="list" method="get">
    Lọc sức chứa: <input name="cap" type="number" value="${capFilter}">
    <button type="submit">Tìm</button>
</form>

<br/>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Sức chứa</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="t" items="${tables}">
        <tr>
            <td>${t.id}</td>
            <td>${t.capacity}</td>
            <td>${t.status}</td>
            <td>
                <a href="view/${t.id}">Chi tiết</a>

                <c:choose>
                    <c:when test="${t.status == 'Còn trống'}">
                        <form action="update-status" method="post" style="display:inline">
                            <input type="hidden" name="id" value="${t.id}">
                            <input type="hidden" name="status" value="Đang dùng">
                            <button type="submit">Nhận bàn</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="update-status" method="post" style="display:inline">
                            <input type="hidden" name="id" value="${t.id}">
                            <input type="hidden" name="status" value="Còn trống">
                            <button type="submit">Trả bàn</button>
                        </form>
                    </c:otherwise>
                </c:choose>

                <form action="delete/${t.id}" method="post" style="display:inline">
                    <button type="submit" onclick="return confirm('Xóa bàn này?')">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${empty tables}">
    <p>Không tìm thấy bàn nào.</p>
</c:if>

</body>
</html>
