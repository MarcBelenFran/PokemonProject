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
                actualizarMovimientos();
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
                actualizarMovimientos();
            }
        }
    }
}))

listaMovimientos.forEach(elements => elements.addEventListener("click", event =>{
    if(event.target.id != "movimientoSeleccionado"){
        if(document.getElementById('movimientoSeleccionado') != null){
            document.getElementById("movimientoSeleccionado").removeAttribute('id');
        }
        event.target.id = "movimientoSeleccionado";
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

    return resultado
}

function obtenerNombreSeleccionado(rutaImagen){
    let nombre = rutaImagen.replaceAll("./Imagenes/", "");
    nombre = nombre.substring(0, nombre.indexOf("."));
    return nombre;
}



function agregarMovimiento(idMovimiento){
    let numeroMovimiento = document.getElementById("movimientoSeleccionado").classList[1];
    let http = new XMLHttpRequest();

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("movimientoSeleccionado").innerHTML  = http.responseText;
            cerrarMenuTabla();
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/agregarMovimiento", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado()+"&&idMovimiento="+idMovimiento
    +"&&numeroMovimiento="+numeroMovimiento);
}

function quitarMovimiento(){
    if(document.getElementById("movimientoSeleccionado") != null){
        let numeroMovimiento = document.getElementById("movimientoSeleccionado").classList[1];
        let http = new XMLHttpRequest();
    
        http.onreadystatechange = function(){
            if(http.readyState == 4 && http.status == 200){
                document.getElementById("movimientoSeleccionado").innerHTML  = "";
                cerrarMenuTabla();
            }
        }

        http.open("POST", "http://localhost:8080/PokemonFBM/quitarMovimiento", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado()+"&&numeroMovimiento="+numeroMovimiento);
    }else{
        alert("Selecciona el movimiento que quieres modificar!!!");
    }

}


function tablaMovimientos(){
    if(document.getElementById("movimientoSeleccionado") != null){
        document.getElementById("popup").style.display = "flex";

        let http = new XMLHttpRequest();
    
        http.onreadystatechange = function(){
            if(http.readyState == 4 && http.status == 200){
                document.getElementById("menuTabla").innerHTML = http.responseText;
            }
        }

        http.open("POST", "http://localhost:8080/PokemonFBM/tablaMovimientos", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado());
    }else{
        alert("Selecciona el movimiento que quieres modificar!!!");
    }
}


function actualizarMovimientos(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        listaMovimientos.forEach(elements => elements.innerHTML = "")
        if(http.readyState == 4 && http.status == 200){
            try{
                let movimientos = JSON.parse(http.responseText);

                for(let i = 0; i < movimientos.array.length; i++){
                    if(movimientos.array[1] == "null"){
                        listaMovimientos[i].innerHTML = "";
                    }else{
                        listaMovimientos[i].innerHTML = movimientos.array[i];
                    }
                }
            }catch(error){
                console.log("No hay movimientos");
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/movimientosPokemon", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("idUsuario="+localStorage.getItem("id")+"&&imagenPokemon="+obtenerImagenSeleccionado());
}

function cerrarMenuTabla(){
    document.getElementById("popup").style.display = "none";
}