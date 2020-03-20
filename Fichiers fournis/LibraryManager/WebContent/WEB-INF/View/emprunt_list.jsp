<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page='menu.jsp'></jsp:include>
	<main>
		<section class="content">
			<div class="page-announce valign-wrapper">
				<a href="#" data-activates="slide-out"
					class="button-collapse valign hide-on-large-only"><i
					class="material-icons">menu</i></a>
				<h1 class="page-announce-text valign">Liste des emprunts</h1>
			</div>
			<div class="row">
				<div class="container">
					<div class="col s12">
						<table class="striped">
							<thead>
								<tr>
									<th>Livre</th>
									<th>Membre emprunteur</th>
									<th>Date d'emprunt</th>
									<th>Retour</th>
								</tr>
							</thead>
							<tbody id="results">
								
									<c:if test="${!empty emprunts }">
										<c:forEach var="emprunt" items="${emprunts}">
											<tr>
												<td><c:out value="${emprunt.idLivre.getTitre()}" /> <em>de
														<c:out value="${emprunt.idLivre.getAuteur()}" />
												</em></td>
												<td><c:out value="${emprunt.idMembre.getPrenom()}" />
													<c:out value="${emprunt.idMembre.getNom()}" /></td>
												<td><c:out value="${emprunt.dateEmprunt}" /></td>
												<td><c:if test="${emprunt.dateRetour != null}">
													${emprunt.dateRetour}
												</c:if> <c:if test="${emprunt.dateRetour == null }">
														<a
															href="emprunt_return?id=<c:out value="${emprunt.id}" />"><ion-icon
																class="table-item" name="log-in"></a>
													</c:if></td>
											</tr>
										</c:forEach>
									</c:if>
								
	

								
								<!-- TODO : rajouter un btn ou un truc qui permet d'afficher tout en postant on pourrait avoir la request avec show=all dedans -->
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div style="text-align: center">
			<a href="emprunt_add" ><button class="btn waves-effect waves-light orange" ><i class="fa fa-plus-square"></i> AJOUTER</button></a>
         <a href="dashboard" ><button class="btn waves-effect waves-light" ><i class="fa fa-arrow-left"></i> RETOUR</button></a>
        </div>
		</section>
	</main>
	<jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
