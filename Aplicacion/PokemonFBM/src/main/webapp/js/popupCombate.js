var JSONobjects;
let solicitarCombate = setInterval(combate, 5000);
const myTimeout = setTimeout(turnoRandom, 60000);

// TURNO Y TURNO RANDOM SON LOS INSERTS QUE SE VAN A REALIZAR EN LA TABLA TURNO DE LA BASE DE DATOS (turnoRandom solo se realizará en caso de que el jugador no realize un movimiento)
function turno() {
    clearTimeout(myTimeout);
    let http = new XMLHttpRequest();

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idCombate="+localStorage.getItem("idCombate")
                +"&idMovimiento="+localStorage.getItem("idMovimiento")
                +"&turno="+localStorage.getItem("turno")
                +"&imagenPokemon="+localStorage.getItem("imagenPokemon")
                +"&idJugador="+localStorage.getItem("idUsuario")
                +"&cambio="+localStorage.getItem("cambioPokemon")
    )
};

function turnoRandom() {
    let movimientos = document.getElementsByClassName("botonMovimientos");
    let movimiento = Math.floor(Math.random()*movimientos.length);
    let idMovimiento = movimientos[movimiento].id;

    let http = new XMLHttpRequest();

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idCombate="+localStorage.getItem("idCombate")
                +"&idMovimiento="+idMovimiento
                +"&turno="+localStorage.getItem("turno")
                +"&imagenPokemon="+localStorage.getItem("imagenPokemon")
                +"&idJugador="+localStorage.getItem("idUsuario")
                +"&cambio="+localStorage.getItem("cambioPokemon")
    )
};

//Activa el servlet Combate cada X segundos
function combate (){
    let http = new XMLHttpRequest();
    http.onreadystatechange = function(){
        if (http.readyState==4 && http.status==200){
            JSONobjects = JSON.parse(responseText);
        }

    }

    http.open("POST", "http://localhost:8080/PokemonFBM/BuscadorCombates", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idCombate="+localStorage.getItem("idCombate"));
};


function rendirse(){
    document.getElementById("popupCombates").style.display = "none";
}



function sacarImagenPokemon(){
    let imagen;

    if(localStorage.getItem("numeroJugador") == 1){
        imagen = document.getElementById("imagenPokemon1");
    }else{
        imagen = document.getElementById("imagenPokemon2");
    }
    
    return imagen;
}