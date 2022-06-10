var comprobarJugador;

function crearPartida(){
    let http = new XMLHttpRequest();
	console.log("partidaCreada");
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
		localStorage.setItem("idCombate", http.responseText);
		window.localStorage.setItem('join', '0');
        let popup = document.getElementById("popup")
        popup.style.display = "flex";
        
        comprobarJugador = setInterval(() => {
			comprobarJugador2()
		}, 1000);
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
}

function abrirCombate(){
	if(window.localStorage.getItem('join')=="1"){
		let popup = document.getElementById("popup")
		popup.style.display = "none";
		
		let combate = document.getElementById("popupCombates")
		combate.style.display = "flex";
		solicitarCombate = setInterval(() => {
			combate;
		}, 5000);
		clearInterval(comprobarJugador);
	}
}
2

function comprobarJugador2(){
    let http = new XMLHttpRequest();
    
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            window.localStorage.setItem('join', String(http.responseText));
            abrirCombate();
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/comprobarJugador2", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
}

function cancelarCombate(){
    let http = new XMLHttpRequest();
	
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            let popup = document.getElementById('popup');
            popup.style.display = "none";
            
            clearInterval(comprobarJugador);
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/cancelarPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
    
}

function actualizarCombatesDisponibles(){
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

function unirseCombate(idCombate){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            localStorage.setItem("idCombate", idCombate);     
            document.getElementById("popupCombates").style.display = "flex";
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/unirsePartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&idCombate="+idCombate);
}

const listaPokemon = document.querySelectorAll('.pokemonEquipo');

listaPokemon.forEach(elements => elements.addEventListener("click", event =>{
    if(!(event.target.id == "pokemonSeleccionado")){
        document.getElementById("pokemonSeleccionado").removeAttribute('id');
        if(event.target.className == "imagenPokemon"){
            event.target.parentNode.setAttribute('id', 'pokemonSeleccionado');
        }else{  
            event.target.setAttribute('id', 'pokemonSeleccionado');
        }
    }
    
}))

function obtenerImagenSeleccionado(){
    let resultado = "./";
    let i = 0;
    let seleccionado = false;

    while(!seleccionado){
        if(listaPokemon[i].id == "pokemonSeleccionado"){
            seleccionado = true;
            let rutaImagen = listaPokemon[i].firstChild.src;
            resultado += rutaImagen.substring(rutaImagen.indexOf("Imagenes"), rutaImagen.length);
        }else{
            i++;
        }
    }

    return resultado;
}


