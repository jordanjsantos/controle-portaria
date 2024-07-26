<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-bs-theme="light">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Controle Portaria | Login</title>
		
		<link href='<c:url value="/include/bootstrap-5.3.0-dist/css/bootstrap.min.css" />' rel="stylesheet">
		<link href='<c:url value="/include/bootstrap-5.3.0-dist/css/bootstrap-custom.css" />' rel="stylesheet">
		<link href='<c:url value="/include/css/login.css" />' rel="stylesheet">
	</head>
	<body class="d-flex align-items-center py-4 bg-body-tertiary">
		<main class="w-100 m-auto form-container">
			<form name="usuario" action="efetuaLogin" method="post">
				<h1 class="h3 mb-3 fw-normal">Por favor, faça login</h1>
				<div class="form-floating">
					<input type="text" name="user" class="form-control mb-2" id="inputUser" placeholder="Usuário" autofocus required>
					<label for="inputUser">Usuário</label>
				</div>
				<div class="form-floating">
					<input type="password" name="senha" class="form-control mb-5" id="inputSenha" placeholder="Senha" required>
					<label for="inputSenha">Senha</label>
				</div>
		
				<button type="submit" class="btn btn-primary w-100 py-2 mb-5">Acessar</button>
				
			    <c:if test="${msgErrorPasword != null}">
			    	<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
						${msgErrorPasword}
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>
			</form>
		</main>
					
		<script src='<c:url value="/include/bootstrap-5.3.0-dist/js/bootstrap.min.js" />'></script>
		<script src='<c:url value="/include/bootstrap-5.3.0-dist/js/bootstrap.bundle.min.js" />'></script>
		<script src='<c:url value="/include/jquery/code.jquery.com_jquery-3.7.0.min.js" />'></script>
	</body>
</html>
