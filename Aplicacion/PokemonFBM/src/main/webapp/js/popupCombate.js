var JSONobjects;
const myTimeout = setTimeout(turnoRandom, 60000);
var tiempoTurno = setInterval(() => {
	timer()
}, 1000);
var time = 60;

 /*
 			FALTA:
 				- APRENDER A USAR OBJETOS JS
 				- FUNCION CREAR BOTONES DES DE UNA STRING LOS BOTONES NECESARIOS SOLO FALTA JAVASCRIPT EN JAVA: BuscadorCombates/sacarMovimientos (EJEMPLO DE LA STRING "1/HIDROBOMBA/3/HOJA AFILADA/")
 				- FUNCION SACAR POKEMON DE ULTIMO MOVIMIENTO DE CADA JUGADOR JAVASCRIPT, EN JAVA SE LLAMA: BuscadorCombates/ultimoPokemon (EJEMPLO DE LA STRING "idUsuario/idPokemon/rutaImagen/")
 				- FUNCION CREAR BOTONES A PARTIR DE ARRAY DE POKEMONS (Yo haria solo los que esten vivos) 
 */


	// ESTILO
function timer() {
	var time = time-1;
	document.getElementById("clock").innerHTML = time;
};

// TURNO Y TURNO RANDOM SON LOS INSERTS QUE SE VAN A REALIZAR EN LA TABLA TURNO DE LA BASE DE DATOS (turnoRandom solo se realizará en caso de que el jugador no realize un movimiento)
function turno() {
    clearTimeout(myTimeout);
    let http = new XMLHttpRequest();
    
    if(http.readyState==4 && http.status==200){
		localStorage.setItem("turno", localStorage.getItem("turno")+1);
		time=60;
	}

    http.open("POST", "http://localhost:8080/PokemonFBM/insertarMovimiento", true);
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
    
    if(http.readyState==4 && http.status==200){
		localStorage.setItem("turno", localStorage.getItem("turno")+1);
		time=60;
	}

    http.open("POST", "http://localhost:8080/PokemonFBM/insertarMovimiento", true);
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

    http.open("POST", "http://localhost:8080/PokemonFBM/combate", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idCombate="+localStorage.getItem("idCombate"));
};


function rendirse(){
    document.getElementById("popupCombates").style.display = "none";
    // funcion terminar combate en java
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