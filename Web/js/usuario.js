window.onload = function(){
    if(sessionStorage.getItem("usuario") != null){
        actualizarDatos();
        document.getElementById("botonSesion").style.display = 'block';
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
    http.send("nombre="+sessionStorage.getItem("usuario")+"&&contraseña="+sessionStorage.getItem("contraseña"));
}

function cerrarSesion(){
    sessionStorage.clear();
    window.location.href = "home.html";
}