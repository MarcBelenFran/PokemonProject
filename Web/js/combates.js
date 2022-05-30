function actualizarDatosPartida(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("tablaPartidas").innerHTML = http.responseText
        }
    }

    http.open("GET", "http://localhost:8080/PokemonFBM/tablaPartidas", true);
    http.send("nombre"+localStorage.getItem("nombre"));
}

function crearPartida(){
    let popup = document.getElementById('popup');

    popup.style.display = "flex";
    
}

function cancelarCombate(){
    let popup = document.getElementById('popup');

    popup.style.display = "none";
}

function eliminarPartida(){
        let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+sessionStorage.getItem("usuario"));
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
    let resultado = "../";
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

    return resultado
}
