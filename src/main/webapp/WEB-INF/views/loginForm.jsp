<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Controle Portaria</title>

<link
	href='<c:url value="/include/bootstrap-5.3.0-dist/css/bootstrap.min.css" />'
	rel="stylesheet">
<link
	href='<c:url value="/include/bootstrap-5.3.0-dist/css/bootstrap-custom.css" />'
	rel="stylesheet">
<script
	src='<c:url value="/include/bootstrap-5.3.0-dist/js/bootstrap.min.js" />'></script>
<script
	src='<c:url value="/include/bootstrap-5.3.0-dist/js/bootstrap.bundle.min.js" />'></script>
<script
	src='<c:url value="/include/jquery/code.jquery.com_jquery-3.7.0.min.js" />'></script>
</head>
<body>
	<div class="container">
		<div class="row">
				<form class="form-signin" name="usuario" action="efetuaLogin"
					method="post">
					<div class="row top-buffer25">
						<div class="col-4 text-center"></div>
						<div class="col-4 text-center error">
							<c:if test="${msgErrorPasword != null}">
								<span>${msgErrorPasword}</span>
							</c:if>
						</div>
					</div>
					<div class="row top-buffer25">
						<div class="col-4 text-center"></div>
						<div class="col-4 text-center">
							<label for="inputEmail" class="sr-only">Usuário</label> <input
								type="text" name="user" id="inputEmail" class="form-control"
								placeholder="Usuário" required="" autofocus=""> <label
								for="inputPassword" class="sr-only">Senha</label> <input
								type="password" name="senha" id="inputPassword"
								class="form-control" placeholder="Senha" required="">
							<button class="btn btn-lg btn-primary btn-block" type="submit">Acessar</button>
						</div>
					</div>
				</form>
	</div>
</body>
</html>
