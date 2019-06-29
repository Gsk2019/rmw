$(function(){

    /*//搜素关键字初始化
    var varstr = getUrlParam("keyvalue");
    if(varstr != null && varstr != "" && typeof(varstr) != "undefined"){
        $("#want_to_buy_search").val(varstr);
    }*/

    var ii=0;
    var iis = new Array();
    var timeids = new Array();
    layui.use(['layer','laypage','element','form','util'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,laypage = layui.laypage
            ,element = layui.element
            ,form = layui.form
            ,util = layui.util

        //右侧固定客服
        $('.right_menu ul li').hover(function(){
            $(this).find('span').hide()
            $(this).find('em,div').css('display','block')
        },function(){
            $(this).find('em,div').hide()
            $(this).find('span').show()
            layer.close(layer.index);
        })

        //现货搜索
        form.on('submit(goods_in_stock_search)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        //求购搜索
        form.on('submit(want_to_buy_search)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        //帮我找
        form.on('submit(supply)', function(data){
            // layer.msg(JSON.stringify(data.field));

            var redata = window.getObject();
            var data1 = new Object();
            data1.content = data.field.content;
            data1.type = 2;
            redata.data = JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/feedback/feedback.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultFb, data);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });


            return false;
        });

        //列表筛选
        form.on('submit(select_search)', function(data){
            // layer.msg(JSON.stringify(data.field));
            var ob = new Object();
            ob.curr = 1;
            ob.limit = 10;
            laypagemy(ob);
            return false;
        });

        function resultSupply(data){

            if(data.ret == 1){
                $(".supply_list ul").html("");
            }
            var pager = data.data;

            ii = pager.results.length
            for(var i=0;i<pager.results.length;i++){
                var pr = pager.results[i];

                var priceStr='<span class="price"><i>'+pr.price+'</i>-<i>'+pr.end_price+'</i>元/吨</span>';
                iis[i] = pr.expiry_date;
                var price = "（不限）";
                var endprice = "（不限）";
                var productdate = "（不限）";
                if(pr.price == null){
                    priceStr='<span class="price"><i>价格不限</i></span>';
                }
                if(pr.end_price != null && pr.end_price != ""){
                    endprice = pr.end_price;
                }
                if(pr.product_date != null && pr.product_date != ""){
                    productdate = getDateDay(pr.product_date);
                }

                var li = '<li>' +
                    '<div class="left">' +
                    '<p class="tit"><span class="varieties">'+pr.category_top_name+'-'+pr.category_name+'</span>'+priceStr+'</p>' +
                    '<p>求购数量：<span>'+pr.count+'吨</span> 产地：<span>'+pr.product_area+'</span>仓库交割地：<span>'+pr.repertory+'</span>品牌：<span>'+pr.brand+'</span></p>' +
                    '<p>生产日期：<span>'+productdate+'</span>交易员：<span>'+pr.trader_name+'</span>电话：<span>'+pr.trader_phone+'</span></p>' +
                    '<p>备注：<span>'+pr.note+'</span></p>' +
                    '</div>' +
                    '<div class="right">' +
                    '<p class="release_time"><span>发布时间：<em>'+getFullDayByLong(pr.create_date)+'</em></span><span class="time">距结束：<em class="em'+i+'"></em></span></p>' +
                    '<p class="supply_of_material">买家比价中...<a class="layui-btn layui-btn-sm layui-btn-normal" href="javascript:userwygh('+pr.id+');">我要供货</a></p>' +
                    '</div>' +
                    '</li>';
                $(".supply_list ul").append(li);

            }

        }

        function getParams(){
            var obj = new Object();
            obj.category = $('#category').val();
            obj.brand = $('#brand').val();

            obj.name = $('#want_to_buy_search').val();
            return obj;
        }

        function laypagemy(obj){

            var redata = window.getObject();
            var data1 = getParams();
            data1.page = obj.curr;
            data1.pageSize = obj.limit;
            // data1.ord = obj.ord;
            data1.type = 2;
            redata.data =  JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url: window.requestUrl + "rest/supply/getSupply.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultSupply, data);

                    showCountDown();
                    //分页
                    laypage.render({
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

        var ob = new Object();
        ob.curr = 1;
        ob.limit = 10;
        laypagemy(ob);

        function showCountDown(){

            for(var i=0;i<timeids.length;i++){
                window.clearTimeout(timeids[i]);
            }

            timeids = new Array();

            for(var i1=0;i1<ii;i1++){
                //倒计时示例
                var endTime = iis[i1] //假设为结束日期
                    ,serverTime = new Date().getTime(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的
                if(i1 == 0){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em0').html(str);
                    }));
                }else if(i1 == 1){

                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em1').html(str);
                    }));
                }else if(i1 == 2){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em2').html(str);
                    }));
                }else if(i1 == 3){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em3').html(str);
                    }));
                }else if(i1 == 4){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em4').html(str);
                    }));
                }else if(i1 == 5){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em5').html(str);
                    }));
                }else if(i1 == 6){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em6').html(str);
                    }));
                }else if(i1 == 7){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em7').html(str);
                    }));
                }else if(i1 == 8){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em8').html(str);
                    }));
                }else if(i1 == 9){
                    timeids.push(util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em9').html(str);
                    }));
                }

            }
        }


        /*//分页
        laypage.render({
            elem: 'page'
            ,theme: '#1E9FFF'
            ,count: 100
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj,first){

                //首次不执行
                if(!first){
                    layer.msg('当前页：'+obj.curr+'每页显示条数：'+obj.limit)
                }

            }
        });*/



        //列表排序
//		  $('.select_list h2 span').click(function(){
//
//		  		var text = $(this).find('i')
//		  		if(text.text() == ''){
//		  			$('.select_list h2 span i').text('')
//		  			text.text('↑')
//		  			layer.msg($(this).text())
//		  			return
//		  		}
//		  		text.text() == '↑' ? text.text('↓') : text.text('↑')
//		  		layer.msg($(this).text())
//		  })

    });
});

//委托反馈处理
function userwygh(id){

   /* var redata = window.getObject();
    var data1 = new Object();
    data1.infoId = id;
    data1.status = 2;
    redata.data = JSON.stringify(data1);*/

    /*$.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/userinfo/addUserInfo.cs", //即上面的接口1
        data:redata,
        success: function(data, status) {
            window.resultOpera(resultGh, data);

        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });*/

    var tk = window.getCookie("token");
    var uidentity = window.getCookie("uidentity");

    if(tk == null || tk == "" || typeof (tk) == "undefined"){
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

    if(uidentity != '3'){
        layer.confirm('需要先完成入驻认证？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            // var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
            window.location.href = window.requestUrl + "web/userinfo/member.jsp";
            //此处请求后台程序，下方是成功后的前台处理……
        });
    }else{
        window.location.href = window.requestUrl + "web/supply/publishing3.jsp?infoid="+id;
    }

}

function resultGh(data) {
    if (data.ret == 1) {
        layer.msg("供货请求发起成功");
        // window.location.href = window.requestUrl + "web/gstock/gstock.jsp";
    }else{
        layer.msg(data.msg);
    }
}

function resultFb(data) {
    if (data.ret == 1) {
        layer.msg("提交成功,客服会尽快和您联系");
        $("#content").val("");
        // window.location.href = window.requestUrl + "web/gstock/gstock.jsp";
    }else{
        layer.msg(data.msg);
    }
}

function clickSupply(data) {
    var tk = window.getCookie("token");
    var uidentity = window.getCookie("uidentity");

    if(tk == null || tk == "" || typeof (tk) == "undefined"){
        layer.confirm('需要先登录？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            // var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
            window.location.href = window.requestUrl + "web/login.jsp";
            //此处请求后台程序，下方是成功后的前台处理……
        });
        return false;
    }if(uidentity != '3'){
        layer.confirm('需要先完成入驻认证？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            // var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
            window.location.href = window.requestUrl + "web/userinfo/member.jsp";
            //此处请求后台程序，下方是成功后的前台处理……
        });
    }else{
        window.location.href = window.requestUrl + "web/supply/publish_purchase_message.jsp";
    }
}
