<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/stayintouch/resources/css/style.css" media="screen" />
 <link rel="SHORTCUT ICON" href="/stayintouch/resources/images/srini-web.ico"/>
<script type="text/javascript"
	src="/stayintouch/resources/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="/stayintouch/resources/js/home.js"></script>

</head>
<body>
	<div class="main_page_bg">
		<table border=0 align="center">
			<tr>
				<td colspan=1 height=100px align="right">
					<table border=0 class="loginTable" bgcolor="green">
						<tr>
							<td colspan=2 class="login_font_big">
								Login to Stay<i>In</i>Touch
							</td>
						</tr>
						<tr>
							<td class="login_font" colspan="2">
								<hr>
							</td>
						</tr>
						<tr>
							<td class="login_font" colspan="2">
							</td>
						</tr>
						<c:url value="j_spring_security_check" var="doLogin" />
						<form:form commandName="userValidator" modelAttribute="userValidator" id="login" name="login" action="${doLogin}" method="POST">
							<tr>
								<td class="login_controls" align="center" colspan="2"></td>
							</tr>
						
							<tr>
								<td class="login_font" align="right">Email</td>
								<td class="login_controls"><form:input type="text" path="j_username" size="45" maxlength="45" /></td>
							</tr>
							<tr>
								<td class="login_font" align="right">Password</td>
								<td class="login_controls"><form:password type="password" path="j_password" size="45" maxlength="45" /></td>
							</tr>
							<tr>
								<td class="login_font" align="left" colspan="2">
									<form:checkbox path="_spring_security_remember_me"/> Keep me logged in.
								</td>
								
							</tr>
							<tr>
								<td class="login_font" align="left"><div class="read_more"><a href="javascript:login()">Login</a></div></td> 
								<td class="login_font"><a href="/stayintouch" class="sign_up"> or Sign up to Stay in Touch </a></td>
							</tr>
							<td align="center" colspan="3">
								<c:if test="${userValidator.authSuccess eq 'failed'}">
								<table bgcolor="rgb(252,128,97)">
									<tr>
										<td colspan="3" align="center">Incorrect Credentials / Details Provided</td>
									</tr>
								</table>
								</c:if>
								<c:if test="${userValidator.authSuccess eq 'successful'}">
								<table bgcolor="rgb(134,163,215)">
									<tr>
										<td colspan="3" align="center">User Created Successfully, please login now.</td>
									</tr>
								</table>
								</c:if>
						</form:form>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>