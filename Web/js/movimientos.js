const listaPokemon = document.querySelectorAll('.pokemonEquipo');
const listaMovimientos = document.querySelectorAll('.movimiento');

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
                document.getElementById("nombrePokemon").innerHTML = obtenerNombreSeleccionado(obtenerImagenSeleccionado());
            }

        }else{  
            event.target.setAttribute('id', 'pokemonSeleccionado');
            document.getElementById("pokemonMovimiento").src = obtenerImagenSeleccionado();
            document.getElementById("menuMovimientos").style.display = "flex";
            if(event.target.firstChild.src.includes("pokemonNoElegido")){
                document.getElementById("menuMovimientos").style.display = "none";
            }else{
                document.getElementById("menuMovimientos").style.display = "flex";
                document.getElementById("nombrePokemon").innerHTML = obtenerNombreSeleccionado(obtenerImagenSeleccionado());
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

function obtenerNombreSeleccionado(rutaImagen){
    let nombre = rutaImagen.replaceAll("../Imagenes/", "");
    nombre = nombre.substring(0, nombre.indexOf("."));
    return nombre;
}

function agregarMovimiento(idMovimiento){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            let contador = 0;
            while(contador < listaMovimientos.length){
                if(listaMovimientos[0].innerHTML == ""){
                    listaMovimientos[0].innerHTML = http.responseText;
                    contador = listaMovimientos.length;
                }else{
                    contador++;
                }
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarDatos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("usuario")+"&&imagenPokemon="+obtenerImagenSeleccionado()+"&&idMovimiento="+idMovimiento);
}

function tablaMovimientos(tipoTabla){
    document.getElementById("popup").style.display = "flex";

    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("menuTabla").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/tablaMovimientos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado()+"&&tipoTabla="+tipoTabla);
}

function cerrarMenuTabla(){
    document.getElementById("popup").style.display = "none";
}

function actualizarMovimientos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("menuMovimientos").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/actualizarMovimientos", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado());
}