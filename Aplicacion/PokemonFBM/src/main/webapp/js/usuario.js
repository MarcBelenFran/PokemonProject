window.onload = function(){
    if(localStorage.getItem("usuario") != null && localStorage.getItem("id") != null && localStorage.getItem("contrasena") != null){ 
            validarSesion();
            actualizarDatos();
            if(document.getElementById("botonesVisitante") == null){
                imagenesEquipoPokemon();   
            }else{
                document.getElementById("botonesVisitante").style.display = 'none';
            }
            if(document.getElementById("tablaPartidas") != null){
                actualizarTablaPartidas();
            }     
            document.getElementById("nombreUsuario").innerHTML = localStorage.getItem("usuario");
            document.getElementById("botonSesion").style.display = 'block';
    }else if(document.getElementById("botonesVisitante") == null){
        window.location.href = "index.html";
    }
}

function actualizarDatos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("avatarEntrenador").src = http.responseText.split(',')[0];
            document.getElementById("numVictorias").innerHTML = http.responseText.split(',')[1];
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+localStorage.getItem("usuario")+"&&contrasena="+localStorage.getItem("contrasena"));
}

function imagenesEquipoPokemon(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        let elementosImagen = document.getElementsByClassName("imagenPokemon");
        if(http.readyState == 4 && http.status == 200){
            if(http.responseText != "{ \"array\":]}"){
                let arrayRutas = JSON.parse(http.responseText);
                for(let i = 0; i < arrayRutas.array.length;i++){
                    elementosImagen[i].src = arrayRutas.array[i];
                }
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/imagenesPokemon", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
}

function actualizarTablaPartidas(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("tablaPartidas").innerHTML = http.responseText
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarTablaPartidas", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
}

function cerrarSesion(){
        localStorage.clear();
        window.location.href = "index.html";
    }


function validarSesion(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            if(http.responseText == "error"){
                cerrarSesion();
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/validarSesion", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&contrasena="+localStorage.getItem("contrasena")+"&&nombre="+localStorage.getItem("usuario"));
}