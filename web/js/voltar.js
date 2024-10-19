window.onscroll = function() {
    var voltarTopo = document.getElementById("voltarTopo");
    if (document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
        voltarTopo.classList.add("show");
    } else {
        voltarTopo.classList.remove("show");
    }
};

document.getElementById("voltarTopo").onclick = function() {
    window.scrollTo({top: 0, behavior: 'smooth'});
};


