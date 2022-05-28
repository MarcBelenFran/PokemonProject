const listaPokemon = document.querySelectorAll('.pokemonEquipo');

listaPokemon.forEach(elements => elements.addEventListener("click", event =>{
    if(!(event.target.id == "pokemonSeleccionado")){
        if(document.getElementById('pokemonSeleccionado') != null){
            document.getElementById("pokemonSeleccionado").removeAttribute('id');
        }
        if(event.target.className == "imagenPokemon"){
            event.target.parentNode.setAttribute('id', 'pokemonSeleccionado');
            document.getElementById("pokemonMovimiento").src = obtenerImagenSeleccionado();
            if(event.target.src.includes("pokemonNoElegido")){
                document.getElementById("menuMovimientos").style.display = "none";
            }else{
                document.getElementById("menuMovimientos").style.display = "flex";
            }

        }else{  
            event.target.setAttribute('id', 'pokemonSeleccionado');
            document.getElementById("pokemonMovimiento").src = obtenerImagenSeleccionado();
            document.getElementById("menuMovimientos").style.display = "flex";
            if(event.target.firstChild.src.includes("pokemonNoElegido")){
                document.getElementById("menuMovimientos").style.display = "none";
            }else{
                document.getElementById("menuMovimientos").style.display = "flex";
            }
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

