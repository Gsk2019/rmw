$(function(){

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


        function laypagemy(obj){

            var redata = window.getObject();
            var data1 = new Object();
            data1.page = obj.curr;
            data1.pageSize = obj.limit;
            // data1.ord = obj.ord;
            data1.type = 2;
            redata.data =  JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url: window.requestUrl + "rest/supply/getMySupply.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultSupply, data);

                    //分页
                    layui.laypage.render({
                        elem: 'page'
                        ,theme: '#1E9FFF'
                        ,count: data.data.total
                        ,curr: data.data.page
                        ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                        ,jump: function(obj,first){

                            //首次不执行
                            if(!first){
                                // layer.msg('当前页：'+obj.curr+'每页显示条数：'+obj.limit)
                                laypagemy(obj);

                            }
                        }
                    });
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

        }

        function resultSupply(data){

            if(data.ret == 1){
                $(".table tbody").html("");
            }
            var pager = data.data;

            for(var i=0;i<pager.results.length;i++){
                var pr = pager.results[i];

                var showmsg = "";
                if(pr.status == '1'){
                    showmsg="展示中";
                }else if(pr.status == "4"){
                    showmsg ="已过期";
                }else if(pr.status == "2"){
                    showmsg ="待审核";
                }else if(pr.status == "3"){
                    showmsg ="审核失败";
                }
                var li = '<tr>' +
                    '<td>'+pr.category_top_name+'-'+pr.category_name+'</td>' +
                    '<td class="red">'+pr.price+'-'+pr.end_price+'元</td>' +
                    '<td>'+pr.count+'吨</td>' +
                    '<td>'+getFullDayByLong(pr.create_date)+'</td>' +
                    '<td>'+showmsg+'</td>' +
                    '<td><a class="layui-btn layui-btn-xs layui-btn-normal" href="'+window.requestUrl+'web/userinfo/publish_user1.jsp?infoid='+pr.id+'">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" href="javascript:dels('+pr.id+')">删除</a></td>' +
                    '</tr>';
                $(".table tbody").append(li);

            }

        }

        var ob = new Object();
        ob.curr = 1;
        ob.limit = 10;
        laypagemy(ob);

    });




});

function dels(arr){
    var redata = window.getObject();
    var data1 = new Object();
    data1.ids = arr;
    redata.data =  JSON.stringify(data1);

    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/stock/dels.cs", //即上面的接口1
        data:redata,
        success: function(data, status) {
            window.resultOpera(resultGen, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });
}

function resultGen(data){
    if(data.ret == 1){
        layer.msg("操作成功");
        window.location.href = window.location.href;
    }else{
        layer.msg(data.msg);
    }
}