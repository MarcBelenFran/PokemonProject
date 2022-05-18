

function registrar(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("resultado").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/registrar", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("nombre").value+"&&correo="+document.getElementById("correo").value+"&&contraseña="+document.getElementById("contraseña").value);

}

function login(){
    let http = new XMLHttpRequest();
 
    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            if(http.responseText == "true"){
                window.location.href = "home.html";
                sessionStorage.setItem("usuario", document.getElementById("nombre").value);
                sessionStorage.setItem("contraseña", document.getElementById("contraseña").value);
            }else{
                document.getElementById("resultado").innerHTML = "ERROR: Fallo en el nombre de usuario o contraseña"; 
            }
        }
    }

    http.open("POST", "http://localhost:8080/PokemonFBM/login", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("nombre").value+"&&contraseña="+document.getElementById("contraseña").value);

}

