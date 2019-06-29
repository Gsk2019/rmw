$(function(){

    $('#sendCode').on('click',function(){
        codeClick($(this));
    });

    function codeClick(obj){

        $('#sendCode').unbind('click');

        var phone = $('#phone').val();
        if(phone == '' || phone == null){
            layer.msg("请输入接收验证码的手机号");
            return false;
        }

        //验证输入的电话号码是否是11位数字
        var phoneReg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
        if(!phoneReg.test($('#phone').val())){
            layer.msg('请输入正确的手机号码！');
            return false;
        }


        var redata = window.getObject();
        var data = new Object();
        data.type = 2;
        data.phone = $('#phone').val();

        redata.data = JSON.stringify(data);

        //发送验证码给手机
        $.ajax({
            type: 'post',
            url:    window.requestUrl + "rest/appuser/getCode.cs", //即上面的接口1
            data: redata,
            success: function(data, status) {
                if (data.ret==1) {

                    obj.attr("disabled", "disabled");
                    obj.css("background-color", "#b4b2b3");
                    obj.css("width", obj.css('width'));
                    //下面就是实现倒计时的效果代码
                    var d = new Date();
                    d.setSeconds(d.getSeconds() + 59);
                    var id = "#sendCode";
                    var end_time = d.getTime(),
                        //月份是实际月份-1
                        sys_second = (end_time - new Date().getTime()) / 1000;
                    var timer = setInterval(function() {
                            if (sys_second > 1) {
                                sys_second -= 1;
                                var second = Math.floor(sys_second % 60);
                                var time_text = '';
                                if (second > 0) {
                                    if (second < 10) {
                                        second = '0' + second;
                                    }
                                    time_text += second + '秒';
                                }
                                $(id).text(time_text);

                            } else {
                                obj.attr("disabled", false);
                                obj.text('获取验证码');
                                obj.css("background-color", "#FF5722");
                                clearInterval(timer);
                            }
                        },
                        1000);

                }else{
                    layer.msg(data.msg);
                }
            },
            error: function(data, status) {
                // alert(status);
                layer.msg("请稍后再试！");
                console.log(status);
                // layer.msg(status);
            }
        });

        $('#sendCode').bind('click',function(){
            codeClick($(this));
        });
    };

    layui.use(['form','layer'], function(){
        var form = layui.form,
            layer = layui.layer;
        //重置密码
        form.on('submit(recovery)', function(data){

            $('#recovery').attr("disabled", true);

            // console.log(data.field)

            var redata = window.getObject();
            var data = new Object();
            data.phone = $('#phone').val();
            data.code = $('#code').val();
            data.pwd = $('#new_password').val();

            redata.data = JSON.stringify(data);

            //发送验证码给手机,登录
            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/appuser/forgetPWD.cs", //即上面的接口1
                data: redata,
                success: function(data, status) {

                    window.resultOpera(resultFree, data);

                    $('#recovery').attr("disabled", false);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("请稍后再试");
                    $('#recovery').attr("disabled", false);
                }
            });

            return false;
        });

    });

    function resultFree(data){
        if (data.ret==1) {
            layer.open({
                content: '前往登录？',
                yes: function(index, layero){
                    //do something
                    window.location.href = window.requestUrl + "web/login.jsp";
                }
            });
            /*window.setUserCookie(data.data.data);*/
            /*layer.open({
                type: 1,
                title:'注册成功',
                content: $('.register_success'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });*/
        }else{
            layer.msg(data.msg);
        }
    }
});
