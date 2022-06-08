function enviarTurno(){
    let http = new XMLHttpRequest();
    let JSONobjects;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            JSONobjects = JSON.parse(http.responseText);

        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(
        "idUsuario="+localStorage.getItem("id") +
        "&idCombate="+localStorage.getItem("idCombate") +
        "&numeroTurno="+localStorage.getItem("turno") +
        "&idMovimiento="+localStorage.getItem("idMovimiento") +
        "&imagenPokemon="+ sacarImagenPokemon() +
        "&cambioPokemon="+localStorage.getItem("cambioPokemon") 
        );
}

function rendirse(){
    document.getElementById("popupCombates").style.display = "none";
}

function sacarImagenPokemon(imagenPokemon){
    let imagen;

    if(localStorage.getItem("numeroJugador") == 1){
        imagen = document.getElementById("imagenPokemon1");
    }else{
        imagen = document.getElementById("imagenPokemon2");
    }
    
    return imagen;
}