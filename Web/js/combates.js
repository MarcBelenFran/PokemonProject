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

function crearPartida(){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("popup").style.display = "flex";
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/crearPartida", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id"));
}

function comprobarJugador2(){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            return http.responseText;
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/comprobarJugador2", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("idUsuario"));
}

function cancelarCombate(){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            let popup = document.getElementById('popup');

            popup.style.display = "none";
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/cancelarPartida", true);
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
            console.log(resultado);
        }else{
            i++;
        }
    }

    return resultado;
}


