const botonNav = document.getElementById('botonNav');
const listaMenu = document.getElementById('ulNav');
const nav = document.getElementById('menu');

botonNav.addEventListener('click', () => {
    listaMenu.style.display='flex';
    nav.style.flexDirection = 'column'
    listaMenu.className = 'desplegable';
    nav.style.height = '100%';
})


document.addEventListener('click', (e) => {
    if (e.target.id != 'centroBoton' && e.target.id != 'ulNav' && window.innerWidth <= 768) {
        listaMenu.style.display = 'none';
        nav.style.height = '50px';
        nav.style.flexDirection = 'row'
    }
})



window.addEventListener("resize", ()=>{
    if(window.innerWidth > 768){
        listaMenu.style.display ='flex';
        nav.style.flexDirection = 'column'
        listaMenu.className = 'listaMenu';
        nav.style.height = '50px';
        nav.style.alignItems = 'center';
    }

    if(window.innerWidth <= 768 && listaMenu.className == 'listaMenu'){
        nav.style.flexDirection = 'row';
    }
})