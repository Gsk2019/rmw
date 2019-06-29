// window.requestUrl = "http://47.92.195.202:8080/page/";
// window.requestUrl = "http://localhost:8080/";
window.requestUrl = window.location.protocol+"//" + window.location.host +"/";
// window.requestUrl = "http://localhost:8081/";

window.requestType = "web";

window.setCookie = function(pro, value){
    var expiresDate= new Date();
    expiresDate.setTime(expiresDate.getTime() + (60 * 60 * 1000));
    //?替换成分钟数如果为60分钟则为 60 * 60 *1000
    //cookie的作用域
    $.cookie(pro, value, {
        path : "/",
        expires : expiresDate
    });
}

window.setUserCookie = function(user){
    window.setCookie("token", user.tk);
    window.setCookie("uimg", user.img);
    window.setCookie("uname", user.name);
    window.setCookie("uphone", user.phone);
    window.setCookie("uidentity", user.identity);
}

window.getCookie = function(pro){
    return $.cookie(pro);
}

window.getObject = function(){
    var obj = {
        tk:getCookie("token"),
        plat:window.requestType
    };
    return obj;
}

window.resultOpera = function(fn, data){

    var tk = data.tk;
    if(tk != null && tk != ''){
        window.setCookie("token", tk);
    }
    if(data.ret == 1000){
        window.setCookie("token", "");
        window.setCookie("uimg", "");
        window.setCookie("uname", "");
        window.setCookie("uphone", "");
        window.setCookie("uidentity", "");

        layer.confirm('需要先登录？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            // var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
            window.location.href = window.requestUrl + "web/login.jsp";
            //此处请求后台程序，下方是成功后的前台处理……
        });
        return false;
    }
    fn(data);

}

window.exit = function(){
    window.setCookie("token", "");
    window.setCookie("uimg", "");
    window.setCookie("uname", "");
    window.setCookie("uphone", "");
    window.setCookie("uidentity", "");
}


function getDay(day){
    var today = new Date();

    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;

 /*   today.setTime(targetday_milliseconds); //注意，这行是关键代码

    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);*/
    return targetday_milliseconds;
}

function getAllDay(day){
    var today = new Date();

    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;

    today.setTime(targetday_milliseconds); //注意，这行是关键代码

       var tYear = today.getFullYear();
       var tMonth = today.getMonth();
       var tDate = today.getDate();
       tMonth = doHandleMonth(tMonth + 1);
       tDate = doHandleMonth(tDate);

       var thour = today.getHours();
       var tminu = today.getMinutes();
       var tsec = today.getSeconds();
    return tYear + "-" + tMonth + "-" + tDate + " "+ thour + ":" +tminu +":"+tsec;
}

function doHandleMonth(month){
    var m = month;
    if(month.toString().length == 1){
        m = "0" + month;
    }
    return m;
}

function getFullDayByLong(day){
    if(day == null){
        return "--";
    }
    var today = new Date();
    today.setTime(day); //注意，这行是关键代码

    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);

    var thour = today.getHours();
    var tminu = today.getMinutes();
    var tsec = today.getSeconds();
    return tYear + "-" + tMonth + "-" + tDate + " "+ thour + ":" +tminu +":"+tsec;
}

function getDateDay(day){
    var today = new Date();


    today.setTime(day); //注意，这行是关键代码

    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);

    return tYear + "-" + tMonth + "-" + tDate;
}

function getDateYM(day){
    var today = new Date();


    today.setTime(day); //注意，这行是关键代码

    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);

    return tYear + "-" + tMonth;
}

function getStr(len, str){
    if(str == null || str == ""){
        return "--";
    }
    var slen = str.length;
    if(len <= slen){
        return str.substring(0, len - 1);
    }else{
        return str;
    }
}

function getUrlParam(name) {

    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象

    var r = window.location.search.substr(1).match(reg);  //匹配目标参数

    if (r != null) return unescape(r[2]);

    return ''; //返回参数值

}

function toUnicodeFun(data){
    if(data == '' || typeof data == 'undefined') return '请输入汉字';
    var str ='';
    for(var i=0;i<data.length;i++){
        str+="\\u"+data.charCodeAt(i).toString(16);
    }
    return str;
}
