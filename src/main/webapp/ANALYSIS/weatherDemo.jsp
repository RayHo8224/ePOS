<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="weather" class="analysis.LoadWeatherRss" scope="page"/>
	
			今日天氣<input type="text" value="${weather.nowWeather}"><br>
			<h3>未來一週氣象預報</h3>
			<c:forEach items="${weather.weekWeather}" var="weekWeather">
			日期=${weekWeather.key} , 預測天氣=${weekWeather.value}<br>
			
			</c:forEach>
			
</body>
</html>