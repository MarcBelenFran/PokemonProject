window.onload = function(){
    if(sessionStorage.getItem("usuario") != null){
        actualizarDatos();
        document.getElementById("botonSesion").style.display = 'block';
        document.getElementById("nombreUsuario").innerHTML = sessionStorage.getItem("usuario");
    }
}

function actualizarDatos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("avatarEntrenador").src = http.responseText;
            document.getElementById("botonesVisitante").style.display = 'none';
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+sessionStorage.getItem("usuario")+"&&contrasena="+sessionStorage.getItem("contrasena"));
}

function cerrarSesion(){
    sessionStorage.clear();
    window.location.href = "home.html";
}