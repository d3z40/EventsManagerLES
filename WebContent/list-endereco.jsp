<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<head>
    <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="js/bootstrap.min.js"></script>
    <script src="jquery/jquery-1.11.1.min.js"></script>

		<meta charset="UTF-8">
		<title>Endereços</title>
	</head>
	<body>
    <div class="container">
        <div class="row col-md-6 col-md-offset-2 custyle">
        <table class="table table-striped custab">
          <thead>
          	<form action="VisualizarEndereco" method="POST">
          		<div class="row">
          			<div class="col col-xs-6">
	                	<input id="txtFiltro" name="txtFiltro" type="text" placeholder="Informe algo a ser filtrado">
	              	</div>
	              	<div class="col col-xs-6 text-right">
	                	<button type="submit" id="operacao" name="operacao" value="VISUALIZAR" class="btn btn-sm btn-primary btn-create">Filtrar</button>
	              	</div>
	            </div>
	        </form>
            <br>
            <a href="cadastro-endereco.html" class="btn btn-primary btn-xs pull-right"><b>+</b> Novo Endereço</a>
            <tr>
                <th>Descrição</th>
                <th>Logradouro</th>
                <th>Nº</th>
                <th class="text-center">Ações</th>
            </tr>
          </thead>
          <c:forEach var="endereco" items="${listEndereco}">
            <tr>
                <td>${endereco.descricao}</td>
                <td>${endereco.logradouro}</td>
                <td>${endereco.numero}</td>
                <td class="text-center"><a class='btn btn-info btn-xs' href="ListarEndereco/operacao=VISUALIZAR&id=${endereco.id}"><span class="glyphicon glyphicon-edit"></span> Editar</a> <a class="btn btn-danger btn-xs" href="#"><span class="glyphicon glyphicon-remove"></span> Excluir</a></td>
            </tr>
          </c:forEach>
        </table>
        </div>
    </div>
  </body>
</html>
