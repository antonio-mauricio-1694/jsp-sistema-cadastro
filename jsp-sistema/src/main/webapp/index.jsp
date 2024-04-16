<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<style>

* { 
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    background-color: #C1CED9;
}


form {
	position: absolute;
	top: 40%;
	left: 25%;
	right: 20%;
	
}

label{

color: #BF9075;
}

h1{
	position: absolute;
	color: white;
	position: absolute;
	top: 30%;
	left: 35%;
	
	
	
}
h5{
	position: absolute;
	top: 88%;
	left: 35%;
	font-size: 9px;
	color: #F50505;
	
}
button {
	color: #021F59;
}

</style>
<head>
<meta charset="ISO-8859-1">

<title>Sistema de cadatro</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
   crossorigin="anonymous">
</head>
<body>
<h1>Isluc Sistemas </h1>

<form class="row g-3 needs-validation" novalidate action="ServletLogin" method="post" >
<input type="hidden" value="<%= request.getParameter("url") %>" name="url">


 <div class="mb-3">
<label>login</label>
<input type="text"  class="form-control" name="login"  required>
<div class="valid-feedback">
      ok!
    </div>
<div class="invalid-feedback">
     informe o login!
    </div>
</div>


<div class="mb-3">
<label>senha</label>
<input type="password"  class="form-control" name="senha"  required>
<div class="valid-feedback">
      ok!
    </div>
<div class="invalid-feedback">
     informe a senha!
    </div>
</div>


 
<button type="submit" class="btn btn-primary" >Sign in</button>

</form>
<h5>${msg}</h5>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
 integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
  crossorigin="anonymous"></script>
  
  <script type="text/javascript">
  (() => {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  const forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
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