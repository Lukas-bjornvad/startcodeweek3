/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("btn").onclick = function (e) {
    e.preventDefault();
    var inp = document.getElementById("in").value;
    var url = document.location.origin+"/jpareststarter/api/movie/" + inp;
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById("d1").innerHTML = "Id: " + data.id + "<br>Year: " + data.year
                        + "<br>Title: " + data.name
                        + "<br>Actors: " + data.actors;

            })
}
document.getElementById("btn1").onclick = function (e) {
    e.preventDefault();
    var inp = document.getElementById("in1").value;
    var url = document.location.origin+"/jpareststarter/api/movie/title/" + inp;
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById("d1").innerHTML = "Id: " + data.id + "<br>Year: " + data.year
                        + "<br>Title: " + data.name
                        + "<br>Actors: " + data.actors;

            })
}

document.getElementById("btn2").onclick = function (e) {
    e.preventDefault();
    var url = document.location.origin+"/jpareststarter/api/movie/all";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById("d1").innerHTML = "<table class='table'>"
                +"<thead>"
                        + "<tr>"
                        + "<th>Name</th>"
                        + "<th>Year</th>"
                        + "</tr>"
                        + "</thead>"
                        + data.map(function (item) {
                            return "<tr>"
                                    + "<td>" + item.name + "</td>"
                                    + "<td>" + item.year + "</td>"
                                    + "</tr>";
                        }).join('')
                        + "</table>";
            })
}