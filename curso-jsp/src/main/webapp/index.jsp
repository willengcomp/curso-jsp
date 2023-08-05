<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 33%;
}

h3 {
	position: absolute;
	top: 25%;
	left: 37%;
}

.msg {
	position: absolute;
	top: 70%;
	left: 37%;
	font-size: 20px;
	color: red;
}
</style>

<!-- Incluir os arquivos CSS do Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h3 class="mt-5">Bem-vindo ao Curso de JSP</h3>

		<form action="ServletLogin" method="post"
			class="row g-3 needs-validation" novalidate>
			<!-- ... Seu formulário ... -->
			<div class="col-md-6">
				<label class="form-label" for="login">Login:</label> <input
					name="login" type="text" class="form-control" id="login"
					placeholder="login" required>
				<div class="invalid-feedback">Por favor informe o login.</div>
				<div class="valid-feedback">Ok!</div>
			</div>

			<div class="col-md-6">
				<label class="form-label" for="senha">Senha:</label> <input
					name="senha" type="password" class="form-control" id="senha"
					placeholder="senha" required>
				<div class="invalid-feedback">Por favor informe a senha.</div>
				<div class="valid-feedback">Ok!</div>
			</div>

			<input type="submit" value="Acessar" class="btn btn-primary mt-3">
		</form>
		<h4 class="msg">${msg}</h4>
	</div>

	<!-- Incluir os arquivos JavaScript do Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
		
		<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()
		</script>
</body>
</html>