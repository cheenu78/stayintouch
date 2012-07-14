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

<c:url value="/j_spring_security_check" var="doLogin" />

<body>
	<div class="main_page_bg">
		<table border=0 align="right" style="width: 640px">
			<tr bgcolor="green" style="width: 640px">
				<td colspan=1 height=100px align="right">
					<form:form commandName="userValidator" modelAttribute="userValidator" id="login" name="login" action="${doLogin}" method="POST">
						<table border=0>
							<tr>
								<td class="login_font">Email</td>
								<td class="login_font">Password</td>
								<td class="login_font"> &nbsp; </td>
							</tr>
							<tr>
								<td class="login_controls"><form:input type="text" path="j_username" maxlength="45"/></td>
								<td class="login_controls"><form:password path="j_password" maxlength="45"/></td>
								<td class="login_controls"><div class="read_more"><a href="javaScript:login()">Login</a></div></td>
							</tr>
							<tr>
								<td class="login_font"><form:checkbox path="_spring_security_remember_me"/> Keep me logged in &nbsp; </td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
			<tr bgcolor="lightgreen" style="width: 640px">
				<td colspan=1 height=400px align="left" valign="top">
				<form:form commandName="userDetailsValidator" modelAttribute="userDetailsValidator" id="signup" name="signup" action="/stayintouch/app/spring/signup">
					<table border=0 width="100%">
						<tr>
							<td class="search_title" colspan=2>Sign Up</td>
						</tr>
						
						<tr>
							<td colspan=3 class="search_title1">Keep your knowledge updated always</td>
						</tr>
						<tr>
							<td colspan=3 class="search_title1"> <hr/> </td>
						</tr>
						<tr>
							<td class="search_title2" align="right" width="30%">First Name:</td>
							<td class="login_controls" align="left" width="100"><form:input type="text" path="firstName" size="45" maxlength="45" /></td>
						</tr>
						<tr>
							<td class="search_title2" align="right">Last Name:</td>
							<td class="login_controls" align="left" width=""><form:input type="text" path="lastName" size="45" maxlength="45" /></td>
						</tr>
						<tr>
							<td class="search_title2" align="right">Your Email:</td>
							<td class="login_controls" align="left" width=""><form:input type="text" path="entryEmail" size="45" onblur="checkEmail()" maxlength="45" /></td>
						</tr>
						<tr>
							<td class="search_title2" align="right">Password:</td>
							<td class="login_controls" align="left" width=""><form:password path="enterPassword" size="45" maxlength="45" /></td>
						</tr>
						<tr>
							<td class="search_title2" align="right">Password Again:</td>
							<td class="login_controls" align="left" width=""><form:password path="reenterPassword" size="45" maxlength="45" /></td>
						</tr>
						<tr>
							<td class="search_title2" align="right">I am:</td>
							<td class="login_controls" align="left" width="">
								<form:select path="gender">
									<form:option value = "0" label="Select Sex:" />
									<form:option value = "1" label="Male" />
									<form:option value = "2" label="Female" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="search_title2" align="right">Birthday:</td>
							<td class="login_controls" align="left" width="">
								<table>
									<tr>
										<td>
											<form:select path="year" onChange="yearChange()" multiple="false">
												<form:option value="0" label="Year:" selected="selected" />
												<form:options items="${userDetailsValidator.years}" />
											</form:select>
										</td>
										<td>
											<form:select path="month" onChange="monthChange()" multiple="false">
												<form:option value="0" label="Month:" selected="selected" />
												<form:options items="${userDetailsValidator.months}" />
											</form:select>
										</td>
										<td id="dayTableCell">
											<form:select path="day" multiple="false">
												<form:option value="0" label="Day:" selected="selected" />
												<form:options items="${userDetailsValidator.days}" />
											</form:select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="login_controls" align="left"><div class="signin"><a href="javaScript:signup()">Sign up</a></div></td>
						</tr>
						<tr>
							<td colspan="3" align="center" bordercolor="red" id="errorRow" ><form:errors path="signUpError"/></td>
						</tr>
					</table>
					</form:form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>