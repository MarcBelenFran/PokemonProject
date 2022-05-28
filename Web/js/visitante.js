window.onload = function () {
    if (localStorage.getItem("usuario") != null && localStorage.getItem("id") != null && localStorage.getItem("contrasena") != null) {
        window.location.href = "home.html";
    }
}

function registrar() {
    if (validacionRegistro()){
        let http = new XMLHttpRequest();

        http.onreadystatechange = function () {
            if (http.readyState == 4 && http.status == 200) {
                document.getElementById("resultado").innerHTML = http.responseText;

                if(document.getElementById("resultado").innerHTML == "Usuario Registrado correctamente"){
                    setTimeout(function(){      
                        window.location.href = "login.html";
                    }, 2000);
                }
            }
        }

        http.open("POST", "http://localhost:8080/PokemonFBM/registrar", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("nombre=" + document.getElementById("nombre").value + "&&correo=" + document.getElementById("correo").value + "&&contrasena=" + document.getElementById("contrasena").value);
    }
}

function login() {
    if (validacionLogin()) {
        let http = new XMLHttpRequest();

        http.onreadystatechange = function () {
            if (http.readyState == 4 && http.status == 200) {
                if (http.responseText == "conectado") {
                    document.getElementById("resultado").innerHTML = "Usuario ya conectado";
                } else if(http.responseText == "validacion") {
                    document.getElementById("resultado").innerHTML = "ERROR: Buen intento crack";
                }else if(http.responseText != ""){
                    window.location.href = "home.html";
                    localStorage.setItem("usuario", document.getElementById("nombre").value);
                    localStorage.setItem("contrasena", document.getElementById("contrasena").value);
                    localStorage.setItem("id", http.responseText);
                }
                else {
                    document.getElementById("resultado").innerHTML = "ERROR: Fallo en el nombre de usuario o contraseña";
                }
            }
        }

        http.open("POST", "http://localhost:8080/PokemonFBM/login", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("nombre=" + document.getElementById("nombre").value + "&&contrasena=" + document.getElementById("contrasena").value);
    }
}

function validacionRegistro() {
    let validacion = false;
    let nombre = document.getElementById("nombre");
    let contrasena = document.getElementById("contrasena");
    let correo = document.getElementById("correo");

    if (!/^[0-9a-zA-Z]{1,10}$/.test(nombre.value)) {
        nombre.setCustomValidity("Formato de nombre de usuario no valido (1-10 caracteres alfanumericos)");
        nombre.reportValidity();
    }

    else if (!/^\w{1,70}\@[a-zA-Z0-9]{2,64}\.[a-zA-Z]{2,64}$/.test(correo.value)) {
        correo.setCustomValidity("Formato de correo no valido (ejemplo@gmail.com)");
        correo.reportValidity();
    }

    else if (!/^[0-9a-zA-Z]{6,20}$/.test(contrasena.value)) {
        contrasena.setCustomValidity("Formato de nombre de contraseña no valido (6-20 caracteres alfanumericos)");
        contrasena.reportValidity();
    }
    else {
        validacion = true;
    }

    return validacion;
}

function validacionLogin() {
    let validacion = false;
    let nombre = document.getElementById("nombre");
    let contrasena = document.getElementById("contrasena");

    if (!/^[0-9a-zA-Z]{1,10}$/.test(nombre.value)) {
        nombre.setCustomValidity("Formato de nombre de usuario no valido (1-10 caracteres alfanumericos)");
        nombre.reportValidity();
    }

    else if (!/^[0-9a-zA-Z]{6,20}$/.test(contrasena.value)) {
        contrasena.setCustomValidity("Formato de nombre de contraseña no valido (6-20 caracteres alfanumericos)");
        contrasena.reportValidity();
    }
    else {
        validacion = true;
    }

    return validacion;
}

