$(function(){


    var p1 = 0;//1行业动态
    var p2 = 0;//2 价格行情
    // var p3 = 0;//3独家视角
    var ptol1 = 0;
    var ptol2 = 0;
    // var ptol3 = 0;

    //p:页数 ps：每页多少 ty：//1行业动态 2 价格行情 3独家视角 sta：1轮播图2独家视角3行业动态 4价格行情
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
            if(data.status == 3){//行业动态
                // $('.industry ul').html("");

                p1 = data.data.page;
                ptol1 = data.data.pageTotal;

                var number = (parseInt(p1)-1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=1">'+newi.title+'</a></li>';
                    $('#industry1 ul').append(l1)
                }
                if((p1+1) > ptol1){
                    $("#a1").text("没有更多了");
                }
            }else if(data.status == 4){//价格行情
                // $('.quotation ul').html("");
                p2 = data.data.page;
                ptol2 = data.data.pageTotal;

                var number = (parseInt(p2)-1) * parseInt(data.data.pageSize);

                for(var i=0; i<data.data.results.length; i++){
                    var newi = data.data.results[i];
                    var l1= '<li><a href="'+window.requestUrl+'web/news/news_content.jsp?id='+newi.id+'&num='+(number + i + 1)+'&type=2">'+newi.title+'</a></li>' ;
                    $('#industry2 ul').append(l1)
                }
                if((p2+1) > ptol2){
                    $("#a2").text("没有更多了");
                }
            }
        }
    }

    //当前的页面内容
    function currentPage(){
        var redata = window.getObject();

        var data1 = new Object();
        data1.id = getUrlParam("id");
        data1.num = getUrlParam("num");
        data1.type = getUrlParam("type");
        redata.data = JSON.stringify(data1);

        $.ajax({
            type: 'post',
            url: window.requestUrl + "rest/news/detail.cs", //即上面的接口1
            data: redata,
            async:false,
            success: function(data, status) {
                window.resultOpera(resultNewsDetail, data);
            },
            error: function(data, status) {
                // alert(status);
                console.log(status);
                layer.msg("首页信息加载失败");
            }
        });
    }

    function resultNewsDetail(data) {
        if (data.ret == 1) {
            var nows = data.data.now;
            var pre = data.data.pre;
            var next = data.data.next;
            var type = data.data.type;
            var num = data.data.num;

            $('.content').html(nows.content);
            $("#id").val(nows.id);
            $('.title').html(nows.title);
            $('#time1').html("时间：" + getFullDayByLong(nows.createDate));
            $('#time2').html("阅读量：" + nows.viewCount);
            $('#time3').html("来源：" + nows.publisher);

            $(document).attr("title", nows.title);
            document.querySelector('meta[name="description"]').setAttribute('content', nows.title)
            document.querySelector('meta[name="keywords"]').setAttribute('content', nows.keywords)

            if(pre != null){
                $('#dm1').append('<p>上一篇：<a href="'+window.requestUrl+'web/news/news_content.jsp?id='+pre.id+'&num='+(parseInt(num)-1)+'&type='+type+'">'+pre.title+'</a></p>');
            }else{
                $('#dm1').append('<p>上一篇：<a href="javascript:;">没有上一篇资讯了</a></p>');
            }

            if(next != null){
                $('#dm1').append('<p>上一篇：<a href="'+window.requestUrl+'web/news/news_content.jsp?id='+next.id+'&num='+(parseInt(num)+1)+'&type='+type+'">'+next.title+'</a></p>');
            }else{
                $('#dm1').append('<p>下一篇：<a href="javascript:;">没有下一篇资讯了</a></p>');
            }
        }
    }

    function resultAddContents(data) {
        if (data.ret == 1) {
           layer.msg("评论成功");
           window.location.href = window.location.href;
        }else{
            layer.msg(data.msg);
        }
    }

    layui.use(['layer','element','form','laydate','table','carousel'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,laydate = layui.laydate
            ,table = layui.table
            ,carousel = layui.carousel;

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

        //文章评论
        form.on('submit(desc)', function(data){
            var redata = window.getObject();
            var data1 = new Object();
            data1.newid = $("#id").val();
            data1.content = data.field.desc;
            redata.data = JSON.stringify(data1);
            $.ajax({
                type: 'post',
                url: window.requestUrl + "rest/news/addNewsComments.cs", //即上面的接口1
                data: redata,
                success: function(data, status) {
                    window.resultOpera(resultAddContents, data);
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

    window._bd_share_config = {
        common : {
//			bdText : '自定义分享内容',
//			bdDesc : '自定义分享摘要',
//			bdUrl : '自定义分享url地址',
//			bdPic : '自定义分享图片'
        },
        share : [{
            "bdSize" : 16
        }],
        image : [{
            viewType : 'list',
            viewPos : 'top',
            viewColor : 'black',
            viewSize : '16',
            viewList : ["qzone","tsina","tqq","renren","weixin"]
        }],
        selectShare : [{
            "bdselectMiniList" : ["qzone","tsina","tqq","renren","weixin"]
        }]
    }
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];

    //行业动态
    getNews(1, 10, 1, 3);
    //价格行情
    getNews(1, 10, 2, 4);

    $("#a1").click(function(){
        if((p1+1) <= ptol1){
            getNews((p1+1), 10, 1, 3);
        }
    });
    $("#a2").click(function(){
        if((p2+1) <= ptol2){
            getNews((p2+1), 10, 2, 4);
        }
    });

    currentPage();

    //p:页数 ps：每页多少
    function getNewsContents(p, ps){
        var redata = window.getObject();

        var data1 = new Object();
        data1.page = p;
        data1.pageSize = ps;
        data1.newid = $("#id").val();
        redata.data = JSON.stringify(data1);

        $.ajax({
            type: 'post',
            url: window.requestUrl + "rest/news/getNewsComments.cs", //即上面的接口1
            data: redata,
            success: function(data, status) {
                window.resultOpera(resultNewsContents, data);
            },
            error: function(data, status) {
                // alert(status);
                console.log(status);
                layer.msg("首页信息加载失败");
            }
        });
    }

    var ncp = 0;
    var ncps =10;
    var ncptol = 0;

    function resultNewsContents(data) {
        if (data.ret == 1) {
            // $('.industry ul').html("");

            ncp = data.data.pager.page;
            ncptol = data.data.pager.pageTotal;

            for(var i=0; i<data.data.pager.results.length; i++){
                var newi = data.data.pager.results[i];
                var l1= '<li>' +
                    '<p><span class="name">'+newi.user_name+'</span><span class="time">'+getFullDayByLong(newi.create_date)+'</span></p>' +
                    '<p class="desc">'+newi.content+'</p>' +
                    '</li>';
                $('#ncul').append(l1)
            }
            if((ncp+1) > ncptol){
                $("#bm1").html("没有更多了");
            }
        }
    }

    getNewsContents(1, 10);

    $("#bm1").click(function(){
        if(ncp >= ncptol){

        }else{
            getNewsContents((ncp +1), ncps);
        }
    });
});

