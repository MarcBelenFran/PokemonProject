window.onload = function(){
    if(localStorage.getItem("usuario") != null){
        actualizarDatos();
        document.getElementById("botonSesion").style.display = 'block';
        document.getElementById("nombreUsuario").innerHTML = localStorage.getItem("usuario");
    }
}

function actualizarDatos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("avatarEntrenador").src = http.responseText;
            if(window.location.href.includes("home.html")){
                document.getElementById("botonesVisitante").style.display = 'none';
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+localStorage.getItem("usuario")+"&&contrasena="+localStorage.getItem("contrasena"));
}

function cerrarSesion(){
    localStorage.clear();
    window.location.href = "home.html";   
}