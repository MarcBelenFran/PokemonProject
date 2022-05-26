const listaPokemon = document.querySelectorAll('.pokemonEquipo');

listaPokemon.forEach(elements => elements.addEventListener("click", event =>{
    if(!(event.target.id == "pokemonSeleccionado")){
        document.getElementById("pokemonSeleccionado").removeAttribute('id');
        if(event.target.className == "imagenPokemon"){
            event.target.parentNode.setAttribute('id', 'pokemonSeleccionado');
        }else{  
            console.log("hola");
            event.target.setAttribute('id', 'pokemonSeleccionado');
        }
    }
    
    let contador = 1;
    let continuar = false;
    while(!continuar){
        if(listaPokemon[contador].id == "pokemonSeleccionado"){
            continuar = true;
        }
        continuar++; 
    }

    obtenerImagenSeleccionado();
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

function agregarPokemon(Pokemon){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if (http.readyState==4 && http.status==200){
            document.getElementById("pokemonSeleccionado").firstChild.src = http.responseText;
            document.querySelector('table').remove();
        }
    }


    http.open("POST","http://localhost:8080/PokemonFBM/agregarPokemon", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&nombrePokemon="+Pokemon);
}


function actualizarTabla(){
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if (http.readyState==4 && http.status==200){
            document.getElementById("infoPokemons").innerHTML = http.responseText;
        }
    }


    http.open("POST","http://localhost:8080/PokemonFBM/tablaPokemon", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("filtroNombre="+document.getElementById("inputNombre").value+"&&idUsuario="+sessionStorage.getItem("id")+"&&rutaImagen="+obtenerImagenSeleccionado());
}