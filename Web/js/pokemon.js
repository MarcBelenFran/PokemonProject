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
            console.log(contador);
            continuar = true;
        }
        continuar++; 
    }
}))


function actualizarTabla(){
    let http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if (http.readyState==4 && http.status==200){
            document.getElementById("infoPokemons").innerHTML = http.responseText;
        }
    }


    http.open("POST","http://localhost:8080/PokemonFBM/tablaPokemon", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("filtroNombre="+document.getElementById("inputNombre").value);
}