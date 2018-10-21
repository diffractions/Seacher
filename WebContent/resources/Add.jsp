<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="item.Paper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	New
	<b style="color: red">${new_papers_count}</b> papers add:
	<br>

	<table>
		<c:forEach var="paper" items="${new_papers}">
			<tr>
				<td><a href="http://localhost:8071/${paper.getName()}"
					target="pdfs"> ${paper.getName()} </a></td>
			</tr>
		</c:forEach>

	</table>






	<%
		request.getSession().removeAttribute("find_papers");
	%>

	<%
		String scheme = request.getScheme();
		String server = request.getServerName();
		int port = request.getServerPort();
		String path = request.getContextPath();
		StringBuffer sb = new StringBuffer();
		sb.append(scheme);
		sb.append("://");
		sb.append(server);
		if ((port != -1) && (port != 80)) {
			sb.append(":");
			sb.append(port);
		}
		sb.append(path);
		sb.append("/");
		String baseURL = sb.toString();
	%>

	<hr>
	<form method="get" action="<%=baseURL%>all"><input type="submit"
			value="Show all">
	</form>

	<form method="get" action="<%=baseURL%>search">
		<input type="text" name="search"><input type="submit"
			value="search">
	</form>


	<form method="post" enctype="multipart/form-data"
		action="<%=baseURL%>add">
		<input type="file" multiple accept="application/pdf" name="file"><input
			type="submit" value="add">
	</form>
</body>
</html>