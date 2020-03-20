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
				<h1 class="page-announce-text valign">Emprunter un livre</h1>
			</div>
			<div class="row">
				<div class="container">
					<h5>S�lectionnez le livre et le membre emprunteur</h5>
					<div class="row">
						<form action="/LibraryManager/emprunt_add" method="post"
							class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<select id="idLivre" name="idLivre" class="browser-default">
										<option value="" disabled selected>-- Livres --</option>
										<c:forEach var="livre" items="${livres}">
											<option value="<c:out value="${livre.id }"/>">"
												<c:out value="${livre.titre}" />", de
												<c:out value="${livre.auteur}" /></option>
										</c:forEach>
									</select>
								</div>
								<div class="input-field col s6">
									<select id="idMembre" name="idMembre" class="browser-default">
										<option value="" disabled selected>-- Membres --</option>
										<c:forEach var="membre" items="${membres}">
											<option value="<c:out value="${membre.id }"/>"><c:out
													value="${membre.prenom }" />
												<c:out value="${membre.nom }" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row center">
								<button class="btn waves-effect waves-light" type="submit">Enregistrer
									l'emprunt</button>
								<button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div style="text-align: center">
         <a href="emprunt_list" ><button class="btn waves-effect waves-light" ><i class="fa fa-arrow-left"></i> RETOUR</button></a>
        </div>
		</section>
	</main>
	<jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
