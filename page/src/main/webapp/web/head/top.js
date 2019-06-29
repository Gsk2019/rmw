
$(function(){

    var cname= window.getCookie("uname");
    $("#uanme1").html(cname + "，欢迎来到乳买网！");
});
function exitUser(){
    window.exit();
    window.location.href = window.requestUrl + "web/login.jsp";
}