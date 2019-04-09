<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	body {background: #555; width: 400px; border: 1px solid #fff; padding: 0px;}
    div {padding: 5px; margin: 0px;}
    h1, h2, p {padding: 0px; margin: 0px;}
	#banner-style {background: #3B3E37;}
	#title-style {background: #665845;}
	#subtitle-style {background: #9F8158;}
	#primary-style {background: #EBC785;}
	#footer-style {background: #733027;}
</style>

<title>Insert title here</title>
</head>
<body>

<div id="banner-style">
	<h1>Pets Squad Banner</h1>
</div>

<div id="title-style">
	<h2>Pet Type</h2>
</div>

<div id="primary-style">
	<p>A pet is an animal kept for companionship and enjoyment or a household animal, as opposed to 
wild animals or to livestock, laboratory animals, working animals or sport animals, which are kept for 
economic or productive reasons. The most popular pets are noted for their loyal or playful 
characteristics, for their attractive appearance, or for their song. Pets also generally seem to 
provide their owners with non-trivial health benefits;[1] keeping pets has been shown to help 
relieve stress to those who like having animals around. There is now a medically-approved class of 
"therapy animals," mostly dogs, that are brought to visit confined humans. Walking a dog can 
provide both the owner and the dog with exercise, fresh air, and social interaction.</p>

	<c:url var="imageUrl" value="/resources/220px-Trillium_Poncho_cat_dog.jpg" />
	<img src="${imageUrl}"/>
	
	<p>Source: Wikipedia</p>
</div>

<div id="footer-style">
	<p>Color theme from "Horses made of sticks" by piahr from Adobe Kuler</p>
</div>

</body>
</html>