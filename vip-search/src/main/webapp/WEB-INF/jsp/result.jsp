<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="org.apache.lucene.document.Document" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索结果</title>
</head>

<body>
<div style="height: auto;	margin-top: 10px;">
    <div style="height: 40px;	margin-left: 140px; padding-top: 30px;">
        <form action="/search" method="get">
            <input type="text" name="keyword" value="">
            <input type="submit" value="搜索">
        </form>
    </div>
</div>
<div>
    <div id="vmMain">
        <c:forEach items="${docList}" var="doc">${doc.content}<hr/></c:forEach>
    </div>
</div>
</body>
</html>