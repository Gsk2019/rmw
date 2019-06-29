$(function(){

    var p1 = 0;//1行业动态
    var p2 = 0;//2 价格行情
    var p3 = 0;//3独家视角
    var ptol1 = 0;
    var ptol2 = 0;
    var ptol3 = 0;

    //p:页数 ps：每页多少 ty：//1行业动态 2 价格行情 3独家视角 sta：1轮播图 2独家视角 3行业动态 4价格行情
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
            async:false,
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
            if(data.status == 1){
                // $('#div1').html("");
                var number = (parseInt(data.data.page)-1) * parseInt(data.data.pageSize);
                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<div><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=3"><span><img src="'+newi.mainImage+'"/></span><p>'+newi.title+'</p></a></div>';
                    $('#div1').append(l1)
                }

                layui.use(['carousel'], function() {

                    var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
                        , layer = layui.layer
                        , carousel = layui.carousel;
                    //新闻轮播
                    layui.carousel.render({
                        elem: '#news_banner'
                        , width: '100%' //设置容器宽度
                        , height: '300px'
                        , arrow: 'none' //始终不显示箭头
                    });
                });

            }else if(data.status == 2){
                // $('.news_list ul').html("");
                p3 = data.data.page;
                ptol3 = data.data.pageTotal;

                var number = (parseInt(p3)- 1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li>' +
                        '<a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=3">' +
                        '<span><img src="'+newi.mainImage+ '"/></span>' +
                        '<p class="title">'+newi.title+'</p>' +
                        '<p class="describe">'+newi.summary+'</p>' +
                        '<p class="time">'+getFullDayByLong(newi.createDate)+'</p>' +
                        '</a>' +
                        '</li>';
                    $('.news_list ul').append(l1)
                }
                if((p3+1) > ptol3){
                    $("#a1").text("没有更多了");
                }
            }else if(data.status == 3){//行业动态
                // $('.industry ul').html("");

                p1 = data.data.page;
                ptol1 = data.data.pageTotal;

                var number = (parseInt(p1)- 1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=1">'+newi.title+'</a></li>';
                    $('#industry1 ul').append(l1)
                }
                if((p1+1) > ptol1){
                    $("#a2").text("没有更多了");
                }
            }else if(data.status == 4){//价格行情
                // $('.quotation ul').html("");
                p2 = data.data.page;
                ptol2 = data.data.pageTotal;

                var number = (parseInt(p2) - 1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=2">'+newi.title+'</a></li>' ;
                    $('#industry2 ul').append(l1)
                }
                if((p2+1) > ptol2){
                    $("#a3").text("没有更多了");
                }
            }
        }
    }

    layui.use(['layer','element','form','laydate','table'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,laydate = layui.laydate
            ,table = layui.table;

        //右侧固定客服
        $('.right_menu ul li').hover(function(){
            $(this).find('span').hide()
            $(this).find('em,div').css('display','block')
        },function(){
            $(this).find('em,div').hide()
            $(this).find('span').show()
            layer.close(layer.index);
        })

    });

    //新闻轮播图
    getNews(1, 2, 3, 1);
    //独家视角
    getNews(1, 5, 3, 2);
    //行业动态
    getNews(1, 10, 1, 3);
    //价格行情
    getNews(1, 10, 2, 4);

    //点击更多
    $("#a1").click(function(){
        if((p3+1) <= ptol3){
            getNews((p3+1), 5, 3, 2);
        }
    });
    $("#a2").click(function(){
        if((p1+1) <= ptol1){
            getNews((p1+1), 10, 1, 3);
        }
    });
    $("#a3").click(function(){
        if((p2+1) <= ptol2){
            getNews((p2+1), 10, 2, 4);
        }

    });
});