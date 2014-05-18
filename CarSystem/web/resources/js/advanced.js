window.setInterval(function() { advance(); }, 4000);
var bla = ["Advanced", "Enterprise", "Big Data", "Business Oriented", "Cloud",
           "Design Patterns", "Technical Database Procedures", "Apllication Servers", "Batch Processing",
           "Community Driven", "Integrated", "Dependency Injection", "Certified",
           "Electric", "Test-Driven", "Multi-Platform", "Global", "Dynamic", "Connectivity", "Message Oriented Modules",
           "Enterprise Java Broker"]
$(document).ready(function()
{
    var main = $("<div>").addClass("superawesomeMain");
    var divvy = $("<div>").addClass("superawesomeBoss");
    divvy.attr("id", "boss");
    divvy.css({ "width" : $(document).width() + "px", "height" : $(document).height() + "px" });
    main.append(divvy);
    $("body").append(main);
});
function advance()
{
    var rand = bla[Math.floor(Math.random() * bla.length)];
    var posx = Math.random() * 100;
    var posy = Math.random() * 100;

    var superawesome = $("<div>").html(rand).addClass("superawesome");
    superawesome.css({"left" : posx + "%", "top" : posy + "vh" });

    $("#boss").append(superawesome);
    superawesome.fadeIn("slow", function()
    {
       superawesome.fadeOut("slow", function()
       {
           superawesome.remove();
       });
    });
}