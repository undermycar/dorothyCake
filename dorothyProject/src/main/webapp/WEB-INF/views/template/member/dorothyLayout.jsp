<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page trimDirectiveWhitespaces="true" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/images/common/icon.png">
	
    <title><tiles:getAsString name="title" /></title>
	
    <!-- Bootstrap core CSS -->
    <link href="/resources/include/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/include/dist/css/sticky-footer-navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <link rel="stylesheet" type="text/css" href="/resources/include/css/top.css" />
	<link rel="stylesheet" type="text/css" href="/resources/include/css/header.css" />
	<link rel="stylesheet" type="text/css" href="/resources/include/css/footer.css" />
	<link rel="stylesheet" type="text/css" href="/resources/include/css/main.css" />
	
    <script type="text/javascript" src="/resources/include/js/jquery-1.12.4.min.js"></script>
  </head>

  <body>
	<!-- top -->
	<tiles:insertAttribute name="top" />
    <!-- header -->
    <tiles:insertAttribute name="header" />	<br /><br /><br /><br /><br /><br />
	<div class="container">
	 <div class="slidebox">
        <input type="radio" name="slide" id="slide01" checked>
        <input type="radio" name="slide" id="slide02">
        <input type="radio" name="slide" id="slide03">
        <input type="radio" name="slide" id="slide04">
        
        <ul class="slidelist">
            <li class="slideitem">
                <a href="/member/goods/goodsListLetter"><img src="/resources/images/main/mainimg1.jpg" border="0" width= 100% height="500"></a>
            </li>
            <li class="slideitem">
                <a href="/member/goods/goodsDetail?g_code=25"><img src="/resources/images/main/mainimg2.jpg" border="0" width= 100% height="500"></a>
            </li>
            <li class="slideitem">
                <a href="/member/goods/goodsDetail?g_code=41"><img src="/resources/images/main/mainimg3.jpg" border="0" width= 100% height="500"></a>
            </li>
            <li class="slideitem">
                <a href="/member/goods/goodsList"><img src="/resources/images/main/mainimg4.jpg" border="0" width= 100% height="500"></a>
            </li>
            
        </ul>
        <div class="slide-control">
            <div class="control01">
                <label for="slide04" class="prev"></label>
                <label for="slide02" class="next"></label>
            </div>
            <div class="control02">
                <label for="slide01" class="prev"></label>
                <label for="slide03" class="next"></label>
            </div>
            <div class="control03">
                <label for="slide02" class="prev"></label>
                <label for="slide04" class="next"></label>
            </div>
            <div class="control04">
                <label for="slide03" class="prev"></label>
                <label for="slide01" class="next"></label>
            </div>
        </div>
    </div>
   </div>
    <div class="img_top text-center"><img src="/resources/images/common/adminLogo.png"  alt="BRAND ??????" title="BRAND ??????"   /></div>
   <div class="company_textbox text-center">
        <p>Dorothy Cake??? ??????????????? ?????? ???????????? ???????????????.<br>??????, ????????? ????????? ?????? ??????????????? ???????????? ????????? ????????? ????????? ?????????<br>?????? ????????? ????????? ??????, ????????? ??????????????? ????????? ????????? ???????????????<br>????????????????????? ????????? ??????????????????.<br></p>
        <p><br>???????????? ??? ?????? ????????? ???????????? ????????????<br>?????? ???????????? ?????? ???????????? ???????????? ?????? ?????? ????????? ?????? ?????????.<br>?????? ????????? ?????? ?????? ?????????, ????????? ?????? ????????? ?????? ???????????????.</p>
    </div>
   <!--  <p class="text-center"><a class="btn btn-default" href="/admin/login" role="button">
				????????? ????????? &raquo;</a></p> -->
				<br /><br /><br /><br /><br /><br />

  
	
    <footer id="footer" class="footer">
      <tiles:insertAttribute name="footer" />
    </footer>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
    <script src="/resources/include/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/resources/include/dist/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
