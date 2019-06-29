$(function(){

    var uphone = window.getCookie("uphone");
    if(uphone != null && uphone != "" && uphone.length > 8){
        $("#uphone").text(uphone.substring(0,3)+"****"+uphone.substring(8));
    }

    layui.use(['layer','element','form','laydate','table'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,laydate = layui.laydate
            ,table = layui.table

        //右侧固定客服
        $('.right_menu ul li').hover(function(){
            $(this).find('span').hide()
            $(this).find('em,div').css('display','block')
        },function(){
            $(this).find('em,div').hide()
            $(this).find('span').show()
            layer.close(layer.index);
        })

        /*//现货搜索
        form.on('submit(goods_in_stock_search)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        //求购搜索
        form.on('submit(want_to_buy_search)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });*/

        function resultPwd(data){
            if(data.ret == 1){
                layer.msg("修改成功");
                layer.closeAll('page');
            }else{
                layer.msg(data.msg);
            }
        }
        //修改密码
        form.on('submit(change_password)', function(data){
            // layer.msg(JSON.stringify(data.field));

            var redata = window.getObject();
            var data1 = new Object();
            data1.old_password = data.field.old_password;
            data1.new_password = data.field.new_password;
            data1.new_password1 = data.field.new_password1;
            redata.data = JSON.stringify(data1);

            if(new_password != new_password1){
                layer.msg("两次新密码输入不一致");
                return;
            }

            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/appuser/modifyPWD.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultPwd, data);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

            return false;
        });

        //打开弹窗
        $('#change_password').click(function(){
            layer.open({
                type:1,
                title :'修改密码',
                area: ['400px', '300px'],
                content:$('.change_password_content')
            })
        })

        //关闭弹窗
        $('#close_change_password').click(function(){
            layer.closeAll('page');
        })

    });
});
