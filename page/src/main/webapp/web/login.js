/*
new Vue({
    el: '#sub1',
    data: {
        name: 'Hello Vue.js!'
    },
    methods: {
        greet: function (event) {
            // `this` 在方法里指当前 Vue 实例
            /!*alert('Hello ' + this.name + '!')
            // `event` 是原生 DOM 事件
            if (event) {
                alert(event.target.tagName)
            }*!/

            $("#form1").submit()
        }
    }
})*/

layui.use('form', function(){
    var form = layui.form;

    //登录
    form.on('submit(login)', function(data){

        $('#login').attr("disabled", true);

        var redata = window.getObject();
        var date = new Object();
        date.phone = $('#phone').val();
        date.pwd = $('#password').val();
        date.type = 1;
        redata.data = JSON.stringify(date);

        $.ajax({
            type: 'post',
            url:    window.requestUrl + "rest/appuser/appLogin.cs", //即上面的接口1
            data: redata ,
            success: function(data, status) {
                if (data.ret==1) {
                    var obj = data.data;
                    setUserCookie(obj);

                    window.location.href = window.requestUrl + "web/index.jsp";

                   /* alert(obj);
                    alert(JSON.stringify(data.data));*/
                }else{
                    layer.msg(data.msg);
                }
                $('#login').attr("disabled", false);
            },
            error: function(data, status) {
                // alert(status);
                console.log(status);
                layer.msg("请稍后再试");
                $('#login').attr("disabled", false);
            }
        });
        // layer.msg(JSON.stringify(data.field));

        return false;
    });

});