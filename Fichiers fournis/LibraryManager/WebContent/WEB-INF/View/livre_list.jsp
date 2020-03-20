<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Liste des livres</h1>
      </div>
      <div class="row">
	        <div class="col s12">
	          <table class="striped no-padding">
                <thead>
                    <tr>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Code ISBN 13</th>
                        <th>D�tails</th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${!empty livres }">
								<c:forEach var="l" items="${livres}">
                    <tr>
                        <td><c:out value="${l.titre}" /></td>
                        <td><c:out value="${l.auteur}" /></td>
                        <td><c:out value="${l.isbn}" /></td>
                        <td class="center"><a href="livre_details?id=<c:out value="${l.id}" />"><ion-icon class="details" name="information-circle-outline"></ion-icon></a></td>
                    </tr>
                    </c:forEach>
                    </c:if>
                    
                   
                </tbody>
            </table>
          </div>
        </div>
        <div style="text-align: center">
         <a href="livre_add" ><button class="btn waves-effect waves-light orange" ><i class="fa fa-plus-square"></i> AJOUTER</button></a>
         <a href="dashboard" ><button class="btn waves-effect waves-light" ><i class="fa fa-arrow-left"></i> RETOUR</button></a>
        </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
