<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stayintouch - Welcome</title>
<link rel="stylesheet" type="text/css" href="/stayintouch/resources/css/style.css" media="screen">
</head>

<body>
<div id="main_container">
	<div id="header">
		<div class="copyright">
	    	<div id="logo"><a href="/stayintouch/main/"><img src="/stayintouch/resources/images/logo.gif" alt="" title="" border="0"></a></div>
	    	<div align="right" style="margin-right:20">
				<a href="/stayintouch/j_spring_security_logout" title="logout">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
	        <div id="menu">
	            <ul>                                        
	                <li><a class="current" href="#" title="">Home</a></li>
	                <li><a href="#" title="">Profile</a></li>
	                <li><a href="#" title="">Settings</a></li>
	                <li><a href="#" title="">Contact Us</a></li>
	                <sec:authorize access="hasRole('ADMINISTRATOR')">
	                	<li><a href="#" title="">Administrator</a></li>
	                </sec:authorize>
	            </ul>
	        </div>
	    </div>
    </div>
    
    <div class="green_box1">
    	   
        
    
    </div>
    
    
 

     <div id="footer">
     	<div class="copyright">
			<a href="home.html"><img src="/stayintouch/resources/images/footer_logo.gif" alt="" title="" border="0"></a>
        </div>
    	<div class="footer_links"> 
        <a href="#">About us</a>
        <a href="#">Privacy policy</a> 
        <a href="#">Contact us</a>

        </div>
    
    
    </div>  
 
   

</div> <!--end of main container-->


</body></html>