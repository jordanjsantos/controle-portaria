<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro Tipo Pessoa</title>

<link rel="mask-icon"
	href='<c:url value="/images/bootstrap/safari-pinned-tab.svg" />'
	color="#712cf9">
<link
	href='<c:url value="/include/bootstrap-5.3.0-dist/css/bootstrap.min.css" />'
	rel="stylesheet">
<meta name="theme-color" content="#712cf9">
</head>
<body>
	<div class="container">
		<footer
			class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
			<p class="col-md-4 mb-0 text-body-secondary">© 2023 Company, Inc</p>

			<a href="/"
				class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
				<svg class="bi me-2" width="40" height="32">
					<use xlink:href="#bootstrap"></use></svg>
			</a>

			<ul class="nav col-md-4 justify-content-end">
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-body-secondary">Home</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-body-secondary">Features</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-body-secondary">Pricing</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-body-secondary">FAQs</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-body-secondary">About</a></li>
			</ul>
		</footer>
		<div class="row justify-content-center">
			<div class="col">
				<form id="formId" name="formName" method="post" action="">
					<div class="row top-buffer5">
						<div class="col-md-10 col-md-offset-1">
							<legend class="titulo">Lista de Tipo Pessoa</legend>
						</div>
					</div>
					<div class="row top-buffer15">
						<div class="col-md-1 col-md-offset-7 text-right">
							<input type="button" name="novo" id="idNovo" value="Novo"
								class="btn btn-sm btn-oldstyle" />
						</div>
					</div>
					<div class="row top-buffer25">
						<div class="col-md-5 col-md-offset-3">
							<table id="idContaLista" class="table table-bordered table-striped ">
<!-- 								class="table compact table-condensed table-striped"> -->
								<thead>
									<tr>
										<th class="display-th"><input type="checkbox"
											id="idSelecionaTodos"></th>
										<th class="display-th">Tipo Pessoa</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listTipoPessoa}" var="tipoPessoa">
										<tr>
											<td class="text-center"><input type="checkbox"
												name="cds" value="${tipoPessoa.descricao}" class="check"></td>
											<td><a
												href="/sisfin/carregarConta?cdConta=${tipoPessoa.id}">${tipoPessoa.descricao}</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>