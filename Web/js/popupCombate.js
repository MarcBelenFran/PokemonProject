function enviarTurno(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(
        "idUsuario="+localStorage.getItem("id") +
        "idCombate="+localStorage.getItem("idCombate") +
        "&numeroTurno="+localStorage.getItem("turno") +
        "&idMovimiento="+localStorage.getItem("idMovimiento") +
        "&imagenPokemon="+localStorage.getItem("imagenPokemon") +
        "&cambioPokemon="+localStorage.getItem("cambioPokemon") 
        );
}