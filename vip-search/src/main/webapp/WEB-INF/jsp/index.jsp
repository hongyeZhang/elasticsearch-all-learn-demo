<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello Lucene</h1>
<div style="height: auto;	margin-top: 10px;">
    <div style="height: 40px;	margin-left: 140px; padding-top: 30px;">
        <form action="/search" method="get">
            <input type="text" name="keyword" value="">
            <input type="submit" value="搜索">
        </form>
    </div>
</div>
<div>
    <div style="float: left; margin: 0; padding: 0;" id="vmMain">
        <c:forEach items="${docList}" var="doc">
            ${doc.content}
            <hr/>
        </c:forEach>
    </div>
</div>
</body>
</html>
