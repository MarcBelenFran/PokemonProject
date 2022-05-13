function registrar(){
    var http = new XMLHttpRequest
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("resultado").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/registrar", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("nombre").value+"&&correo="+document.getElementById("correo").value+"&&contraseña="+document.getElementById("contraseña").value);

}