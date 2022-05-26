function actualizarDatos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("tablaPartidas").innerHTML = http.responseText
        }
    }

    http.open("GET", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.send();
}

function crearPartida(){
        let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+sessionStorage.getItem("usuario")+"&&contrasena="+sessionStorage.getItem("contrasena"));
}