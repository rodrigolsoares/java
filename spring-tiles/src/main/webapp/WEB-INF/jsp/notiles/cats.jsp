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

<div id="subtitle-style">
	Felines
</div>

<div id="primary-style">
	<p>The cat (Felis catus), also known as the domestic cat or housecat[5] to distinguish it from other felines
 and felids, is a small furry domesticated carnivorous mammal that is valued by humans for its companionship
  and for its ability to hunt vermin and household pests. Cats have been associated with humans for 
  at least 9,500 years,[6] and are currently the most popular pet in the world.[7] Owing to their close
   association with humans, cats are now found almost everywhere on Earth.</p>

	<c:url var="imageUrl" value="/resources/250px-Collage_of_Six_Cats-02.jpg" />
	<img src="${imageUrl}"/>

	<p>Source: Wikipedia</p>
</div>

<div id="footer-style">
	<p>Color theme from "Horses made of sticks" by piahr from Adobe Kuler</p>
</div>

</body>
</html>