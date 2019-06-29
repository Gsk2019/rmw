
$(function(){
    layui.use(['form'], function(){
        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,form = layui.form

        //右侧固定客服
        $('.right_menu ul li').hover(function(){
            $(this).find('span').hide()
            $(this).find('em,div').css('display','block')
        },function(){
            $(this).find('em,div').hide()
            $(this).find('span').show()
            layer.close(layer.index);
        })

        //意见反馈
        form.on('submit(feedback)', function(data){
            var redata = window.getObject();
            var data1 = new Object();
            data1.content = data.field.help_find;
            data1.type = 3;
            redata.data = JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/feedback/feedback.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultFby, data);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });
            return false;
        });
    });

});

function resultFby(data) {
    if (data.ret == 1) {
        layer.msg("谢谢您的反馈，我们会以优质的服务换取您的满意");
        $("#content").val("");
        // window.location.href = window.requestUrl + "web/gstock/gstock.jsp";
    }else{
        layer.msg(data.msg);
    }
}