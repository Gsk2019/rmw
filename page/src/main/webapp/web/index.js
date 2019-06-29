//初始化加载的品类
var firstcid;

function categoryClick(obj){
    var id = $(obj).attr("val");
    var redata = window.getObject();
    var data1 = new Object();
    data1.category1 = id;
    if(typeof (data1.category1) == "undefined"){
        data1.category1 = firstcid;
    }
    data1.page = 1;
    data1.pageSize = 6;
    data1.type = 1;
    data1.ord = 2;
    redata.data = JSON.stringify(data1);

    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/stock/getStocks.cs", //即上面的接口1
        data:redata,
        async:false,
        success: function(data, status) {
            window.resultOpera(resultIndexStock, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

    var redata = window.getObject();
    var data1 = new Object();
    data1.categoryTopId = id;
    if(typeof (data1.categoryTopId) == "undefined"){
        data1.categoryTopId = firstcid;
    }
    redata.data = JSON.stringify(data1);
    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/trend/getBrokenTrend.cs", //即上面的接口1
        data: redata,
        async:false,
        success: function(data, status) {
            data.data.cname = $(obj).text();
            window.resultOpera(resultLineTrend, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

}

function resultLineTrend(data) {
    if (data.ret == 1) {
        // $('#div1').html("");
        // line = data.data.dline;
        // lineTrend = data.data.aline;

        var cname = data.data.cname;

        if(cname == "" || cname== null || typeof (cname) == "undefined"){
            cname = $("#c-ul .layui-this").text();
        }

        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            title: {
                text: cname + '价格走势',
                textStyle:{
                    fontWeight:'normal',
                    color:'#333',
                    fontSize:15,
                    //lineHeight:30,
                    rich:{}
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                axisLine:{show:false},
                axisTick:{show:false},
                data: data.data.dline,
            },
            yAxis: {
                axisLine:{show:false},
                axisTick:{show:false}
            },
            series:  data.data.aline
        };


        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }

    }
}

function resultIndexStock(data){
    if (data.ret==1) {
        var is = data.data.results;
        $("#sd" + firstcid).html("");

        var token = window.getCookie("token");
        $("#sd" + firstcid).append('<tr><td>品类</td><td>品牌</td><td>价格</td><td>供应量</td><td>产地</td><td>仓库交割地</td><td>交易员</td><td>联系方式</td></tr>');
        for(var i=0;i<is.length;i++){
            if(token != "" && token != null){
                $("#sd" + firstcid).append('<tr onclick="toNewPage()">' +
                    '<td>'+is[i].category_top_name+'-'+is[i].category_name +'</td>' +
                    '<td>'+is[i].brand+'</td>' +
                    '<td class="red">￥'+is[i].price+'/吨</td>/吨' +
                    '<td>'+is[i].count+'吨</td>' +
                    '<td>'+is[i].product_area+'</td>' +
                    '<td>'+is[i].repertory+'</td>' +
                    /*'<td>品牌供应</td>' +*/
                    '<td>'+is[i].trader_name+'</td>' +
                    '<td>'+is[i].trader_phone+'</td>' +
                    '</tr>');
            }else{
                $("#sd" + firstcid).append('<tr onclick="toNewPage()">' +
                    '<td>'+is[i].category_top_name+'-'+is[i].category_name +'</td>' +
                    '<td>'+is[i].brand+'</td>' +
                    '<td class="red"><a class="log_show red" href="'+window.requestUrl+'web/login.jsp">登录可见</a></td>' +
                    '<td>'+is[i].count+'吨</td>' +
                    '<td>'+is[i].product_area+'</td>' +
                    '<td>'+is[i].repertory+'</td>' +
                    /*'<td>品牌供应</td>' +*/
                    '<td>'+is[i].trader_name+'</td>' +
                    '<td>'+is[i].trader_phone+'</td>' +
                    '</tr>');
            }

            /*if(i == 0){
                $(is[i]).text(obj.count1);
            }else if(i == 1){
                $(is[i]).text(obj.count2);
            }else if(i == 2){
                $(is[i]).text(obj.count3);
            }*/
        }
        /* alert(obj);
         alert(JSON.stringify(data.data));*/
    }else{
        // layer.msg(data.msg);
    }
}

function toNewPage(){
    window.open('/web/gstock/gstock.jsp')
}

$(function(){

    //轮播图
    var tk = window.getCookie("token");
    if(tk != null && tk != "" ){
        $("#ui1").html("Hi，"+ window.getCookie("uname"));
        // $("#ui2").css("display", "none");
        $("#ui3").css("display", "block");
    }else{
        $("#ui1").html("Hi，您好");
        $("#ui2").css("display", "block");
    }

    //求购倒计时
    var ii=0;
    var iis = new Array();
    var timeids = new Array();

    var redata = window.getObject();

    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/home/page.cs", //即上面的接口1
        data:redata,
        success: function(data, status) {
            window.resultOpera(resultFree, data);

        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

    function resultFree(data){
        if (data.ret==1) {
            var obj = data.data;
            var is = $('.statistical_data i');
            for(var i=0;i<is.length;i++){
                if(i == 0){
                    $(is[i]).text(obj.count1);
                }else if(i == 1){
                    $(is[i]).text(obj.count2);
                }else if(i == 2){
                    $(is[i]).text(obj.count3);
                }
            }
            /* alert(obj);
             alert(JSON.stringify(data.data));*/
        }else{
            layer.msg(data.msg);
        }
    }

    function getCategory(){
        var redata = window.getObject();
        var data1 = new Object();
        data1.topid = 0;
        redata.data = JSON.stringify(data1);

        $.ajax({
            type: 'post',
            url: window.requestUrl + "rest/category/category.cs", //即上面的接口1
            data: redata,
            async:false,
            success: function(data, status) {
                window.resultOpera(resultCategory, data);
            },
            error: function(data, status) {
                // alert(status);
                console.log(status);
                layer.msg("首页信息加载失败");
            }
        });
    }

    function resultCategory(data){
        if (data.ret==1) {
            var obj = data.data.category;
            for(var i=0;i<obj.length;i++){
                if(i == 0){
                    firstcid = obj[i].id;
                    $("#c-ul").append('<li onclick="categoryClick(this)" val="'+obj[i].id+'" class="layui-this">'+obj[i].categoryName+'</li>');
                    $("#stockdiv1").append('<div  class="layui-tab-item layui-show"><div class="layui-tab-item layui-show">' +
                        '<table class="table" id="sd'+obj[i].id+'"><tr>' +
                        '<th>细分品种</th>' +
                        '<th>品牌</th>' +
                        '<th>价格</th>' +
                        '<th>产地</th>' +
                        '<th>交割仓库地</th>' +
                        '<th>可供数量</th>' +
                        '<th>供应商</th>' +
                        '<th>交易员</th>' +
                        '</tr></table></div></div>');
                }else{
                    $("#c-ul").append('<li onclick="categoryClick(this)" val="'+obj[i].id+'">'+obj[i].categoryName+'</li>');
                    $("#stockdiv1").append('<div  class="layui-tab-item"><div class="layui-tab-item layui-show">' +
                        '<table class="table" id="sd'+obj[i].id+'"><tr>' +
                        '<th>细分品种</th>' +
                        '<th>品牌</th>' +
                        '<th>价格</th>' +
                        '<th>产地</th>' +
                        '<th>交割仓库地</th>' +
                        '<th>可供数量</th>' +
                        '<th>供应商</th>' +
                        '<th>交易员</th>' +
                        '</tr></table></div></div>');
                }

                if(i < 9){
                    $("#pcate1").append('<li><a href="'+window.requestUrl +'web/gstock/gstock.jsp?cateid='+obj[i].id+'">'+obj[i].categoryName+'</a></li>');
                }
            }
            // $("#c-ul").css("overflow", "hidden");
            gundong();
        }else{
            layer.msg(data.msg);
        }
    }

    //首页分类 一级
    getCategory();

    categoryClick();

    function getNews(p, ps, ty, sta){
        var redata = window.getObject();

        var data1 = new Object();
        data1.page = p;
        data1.pageSize = ps;
        data1.type = ty;
        redata.data = JSON.stringify(data1);

        $.ajax({
            type: 'post',
            url: window.requestUrl + "rest/news/getNews.cs", //即上面的接口1
            data: redata,
            success: function(data, status) {
                data.status = sta;
                window.resultOpera(resultNews, data);
            },
            error: function(data, status) {
                // alert(status);
                console.log(status);
                layer.msg("首页信息加载失败");
            }
        });
    }

    function resultNews(data) {
        if (data.ret == 1) {
            if(data.status == 3){//行业动态
                // $('.industry ul').html("");

                p1 = data.data.page;

                var number = (parseInt(p1)-1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=1">'+newi.title+'</a><span>'+getDateDay(newi.createDate)+'</span></li>';
                    $('.industry ul').append(l1)
                }
            }else if(data.status == 4){//价格行情
                // $('.quotation ul').html("");
                p2 = data.data.page;

                var number = (parseInt(p2)-1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=2">'+newi.title+'</a><span>'+getDateDay(newi.createDate)+'</span></li>' ;
                    $('.quotation ul').append(l1)
                }
            }else if(data.status == 5){//价格行情
                // $('.quotation ul').html("");
                p1 = data.data.page;

                var number = (parseInt(p1)-1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=1">'+newi.title+'</a></li>';
                    $('#new5').append(l1)
                }

            }
        }
    }

    //行业动态
    getNews(1, 5, 1, 3);
    //价格行情
    getNews(1, 5, 2, 4);

    //轮播图上的新闻
    getNews(1, 4, 1, 5);

    layui.use(['layer','element','form','carousel','util'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,carousel = layui.carousel
            ,util = layui.util

        /* //现货搜索
         form.on('submit(goods_in_stock_search)', function(data){
             layer.msg(JSON.stringify(data.field));
             return false;
         });

         //求购搜索
         form.on('submit(want_to_buy_search)', function(data){
             layer.msg(JSON.stringify(data.field));
             return false;
         });*/


        //物流找车
        form.on('submit(find_car)', function(data){
            // layer.msg(JSON.stringify(data.field));
            window.location.href = window.requestUrl + "web/lagistics/logistics.jsp?startplace=" + data.field.starting_place + "&endplace=" + data.field.destination;
            return false;
        });

        //帮我找
        form.on('submit(help_find)', function(data){
            //layer.msg(JSON.stringify(data.field));//help_find
            var redata = window.getObject();
            var data1 = new Object();
            data1.content = data.field.help_find;
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

        //banner
        carousel.render({
            elem: '#banner'
            ,width: '100%' //设置容器宽度
            ,height:'400px'
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        /*//倒计时示例
        var endTime = new Date(2018,10,5).getTime() //假设为结束日期
            ,serverTime = new Date().getTime(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的

        util.countdown(endTime, serverTime, function(date, serverTime, timer){
            var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
            layui.$('.time span').html(str);
        });*/

        function resultSupply(data){

            if(data.ret == 1){
                $(".supply_list ul").html("");
            }
            var pager = data.data;

            ii = pager.results.length
            for(var i=0;i<pager.results.length;i++){
                var pr = pager.results[i];

                iis[i] = pr.expiry_date;

                /* var li = '<li>' +
                     '<div class="left">' +
                     '<p class="tit"><span class="varieties">'+pr.category_top_name+'-'+pr.category_name+'</span><span class="price"><i>'+pr.price+'</i>-<i>'+pr.end_price+'</i>元/'+pr.unit+'</span></p>' +
                     '<p>求购数量：<span>'+pr.count+'吨</span>产地：<span>'+pr.product_top_area+'-'+pr.product_area+'</span>仓库交割地：<span>'+pr.repertory+'</span>品牌：<span>'+pr.brand+'</span></p>' +
                     '<p>生产日期：<span>'+getFullDayByLong(pr.product_date)+'</span>交易员：<span>'+pr.trader_name+'</span></p>' +
                     '<p>备注：<span>'+pr.note+'</span></p>' +
                     '</div>' +
                     '<div class="right">' +
                     '<p class="release_time"><span>发布时间：<em>'+getFullDayByLong(pr.create_date)+'</em></span><span class="time">距结束：<em class="em'+i+'"></em></span></p>' +
                     '<p class="supply_of_material">买家比价中<a class="layui-btn layui-btn-sm layui-btn-normal" href="javascript:userwygh('+pr.id+');">我要供货</a></p>' +
                     '</div>' +
                     '</li>';*/

                var pprice=pr.price+'</em>&nbsp;-&nbsp;<em>'+pr.end_price+'</em><i>元/吨</i></span>';
                if (pr.price==null) {
                    pprice='价格不限</em><i></i></span>';
                }
                var li = '<li>' +
                    '<p class="tit">'+pr.category_top_name+'-'+pr.category_name+'<span>买家比价中...</span></p>' +
                    '<p class="supplier">联系交易员：<span>'+pr.trader_phone+'</span></p>' +
                    '<p class="number">求购数量：<span>'+pr.count+'</span>吨</p>' +
                    '<p class="time">距结束：<span class="em'+i+'"></span></p>' +
                    '<span class="price"><em>'+pprice +
                    '<a class="layui-btn layui-btn-radius layui-btn-normal" href="javascript:userwygh('+pr.id+');">我要供货</a>' +
                    '</li>';
                $(".supply_list ul").append(li);

                if(i < 3){
                    var str='<span>价格不限</span>';
                    if (pr.price!=null){
                        str='<span>'+pr.price+'-'+pr.end_price+'/吨</span>';
                    }

                    $("#fsup").append('<li>' +
                        '<p class="name">'+pr.category_top_name+'-'+pr.category_name+str+'</p>' +
                        '<p class="price">'+pr.count+'吨<span>'+ getDateDay(pr.create_date) +'</span></p>' +
                        '</li>');
                }
            }
        }


        function laypagemy(){

            var redata = window.getObject();
            var data1 = new Object();
            data1.page = 1;
            data1.pageSize = 4;
            // data1.ord = obj.ord;
            data1.type = 2;
            redata.data =  JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url: window.requestUrl + "rest/supply/getSupply.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultSupply, data);
                    //倒计时
                    showCountDown();
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

        }

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
                    timeids.push(layui.util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em0').html(str);
                    }));
                }else if(i1 == 1){
                    timeids.push(layui.util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em1').html(str);
                    }));
                }else if(i1 == 2){
                    timeids.push(layui.util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em2').html(str);
                    }));
                }else if(i1 == 3){
                    timeids.push(layui.util.countdown(endTime, serverTime, function(date, serverTime, timer){
                        var str = '<i>'+date[0]+'</i>天<i>'+date[1]+'</i>时<i>'+date[2]+'</i>分<i>'+date[3]+'</i>秒';
                        layui.$('.time .em3').html(str);
                    }));
                }
            }
        }

        //供货初始化
        laypagemy();
        getLunbotu();


        //打开发布物流委托
        $('#open_issuing_logistics_commission').click(function(){
            layer.open({
                type:1,
                title:'发布物流委托',
                content:$('.issuing_logistics_commission')
            })
        })

        //发布物流委托
        form.on('submit(issuing_logistics_commission)', function(data){
            // layer.msg(JSON.stringify(data.field));
            window.location.href = window.requestUrl + "web/lagistics/logistics.jsp?startplace=" + data.field.starting_place + "&endplace=" + data.field.destination;
            return false;
        });


        //关闭所有页面层
        $('.close').click(function(){
            layer.closeAll('page');
        })

        //banner上左侧菜单切换
        $('.menu li,.menu_content>div').hover(function(){
            var index = $(this).index()
            $('.menu li').eq(index).css('background','#1E9FFF')
            $('.menu_content>div').eq(index).show()
        },function(){
            var index = $(this).index()
            $('.menu_content>div').eq(index).hide()
            $('.menu li').eq(index).css('background','none')
        })



    });


    /*var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: '全脂奶粉价格走势',
            textStyle:{
                fontWeight:'normal',
                color:'#333',
                fontSize:15,
                //lineHeight:30,
                rich:{}
            }
        },
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            axisLine:{show:false},
            axisTick:{show:false},
            data: ['8/20','8/21','8/22','8/23','8/24','8/25','8/26'],
        },
        yAxis: {
            axisLine:{show:false},
            axisTick:{show:false}
        },
        series: [

            {
                name:'液态奶',
                type:'line',
                color:'#1e9fff',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'乳清粉',
                type:'line',
                color:'#ffb800',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };


    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }*/

    $("#banner1").click(function(){
        window.location.href = window.requestUrl + "web/gstock/gstock.jsp";
    });

});


function resultFb(data) {
    if (data.ret == 1) {
        layer.msg("提交成功,客服会尽快和您联系");
        $("#content").val("");
        // window.location.href = window.requestUrl + "web/gstock/gstock.jsp";
    }else{
        layer.msg(data.msg);
    }
}
//我要供货
function userwygh(id){

    window.location.href = window.requestUrl + "web/supply/publishing3.jsp?infoid="+id;
}

function exitUser(){
    window.exit();
    window.location.href = window.requestUrl + "web/login.jsp";
}

//最新现货tab左右滑动
function gundong() {
    var $li = $("#c-ul > li");
    var screenWidth = $('.layui-tab').width(); //容器的宽度
    var index = 0;
    var oneWidth = 0; //移动的宽度
    var scrollSum = 0; //卷进去的总宽度
    var liSum = 0; //li总长度

    if($li.length<7){
        $('#left').hide()
    }

    $("#right").click(function() {
        $("#left").show();
        index = index - 1;
        oneWidth = oneWidth + $li.eq(index).width();
        $("#c-ul").stop(true, false).animate({
            "margin-left": oneWidth
        }, 100);
        scrollSum = scrollSum - $li.eq(index).width();
        if (index <= 0) {
            $("#right").hide();
            return false;
        }
    })
    for (var i = 0; i < $li.length; i++) {
        liSum = $li.eq(i).width() + 2 + liSum;
    }
    $("#left").click(function() {

        $("#right").show();
        oneWidth = oneWidth - $li.eq(index).width();
        $("#c-ul").stop(true, false).animate({
            "margin-left": oneWidth
        }, 100);
        scrollSum = scrollSum + $li.eq(index).width();
        index = index + 1;
        if (liSum - scrollSum < screenWidth + 520) {
            $("#left").hide();
            return false;
        }
    })
}


//获取轮播图
function getLunbotu(){

    var redata = window.getObject();
    var html="";
    $.ajax({
        type: 'post',
        url:   window.requestUrl + "rest/stock/getLunbotu.cs",
        data: redata,
        success: function(data) {

            $('#img1').attr('src', data.data[0].contents);
            $('#img2').attr('src', data.data[1].contents);
            $('#img3').attr('src', data.data[2].contents);
        },
        error: function(data, status) {
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });
}
