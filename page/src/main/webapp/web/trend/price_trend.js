var firstcid;
var line;
var lineTrend;

$(function(){

    getCategory();
    getLineTrend('', '');

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
        //按钮效果
        $('.layui-btn-group button').click(function(){
            $('.layui-btn-group button').removeClass('layui-btn-normal')
            $(this).addClass('layui-btn-normal')

            var mt = $(this).attr("val");
            var id= $("#c-ul .layui-this").attr("val");
            getLineTrend(id, mt);
            getTrend(1, 6, id);
        })

    });



    getTrend(1, 6, '');
});

function categoryClick(obj){

    var id= $(obj).attr("val");
    getLineTrend(id, $("#group1 .layui-btn-normal").attr("val"));
    getTrend(1, 6, id);
}

function getTrend(p, ps, id){
    var redata = window.getObject();

    var data1 = new Object();
    data1.page = p;
    data1.pageSize = ps;
    if(id== ''){
        data1.categoryId = firstcid;
    }else{
        data1.categoryId = id;
    }
    redata.data = JSON.stringify(data1);

    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/trend/getTrend.cs", //即上面的接口1
        data: redata,
        success: function(data, status) {
            window.resultOpera(resultTrend, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });
}
function resultTrend(data) {
    if (data.ret == 1) {
        // $('#div1').html("");
        $('.table tbody').html("");
        for(var i=0; i<data.data.results.length; i++){
            var newi = data.data.results[i];
            /*var l1= '<div><a href="news_content.jsp"><img src="'+window.requestUrl+'web/img/news_banner.jpg"/><p>中国乳制品工业协会第24次年会在西安隆重召开</p></a></div>';*/
            var l1= '<tr><td>'+newi.categoryName+'</td><td class="bold">'+newi.avePrice+'元/吨</td><td class="bold">'+getDateDay(newi.createDate)+'</td></tr>';
            $('.table tbody').append(l1)
        }

        /*//新闻轮播
        layui.carousel.render({
            elem: '#news_banner'
            ,width: '100%' //设置容器宽度
            ,height:'300px'
            ,arrow: 'none' //始终不显示箭头
        });*/

    }
}

function getLineTrend(id, mt){
    var redata = window.getObject();
    var data1 = new Object();
    if(id == ''){
        data1.categoryTopId = firstcid;
    }else{
        data1.categoryTopId = id;
    }

    if(mt == ''){
        data1.mt = "1";
    }else{
        data1.mt = mt;
    }
    redata.data = JSON.stringify(data1);

    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/trend/getBrokenTrend.cs", //即上面的接口1
        data: redata,
        async:false,
        success: function(data, status) {
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
        line = data.data.dline;
        lineTrend = data.data.aline;

        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                top:'3%',
                left: '3%',
                right: '3%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                axisLine:{show:false},
                axisTick:{show:false},
                // data: ['8/20','8/21','8/22','8/23','8/24','8/25','8/26'],
                data:line,
            },
            yAxis: {
                axisLine:{show:false},
                axisTick:{show:false}
            },
            series: lineTrend
            /*[

            /!*{
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
            }*!/
        ]*/
        };


        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
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

            }else{
                $("#c-ul").append('<li onclick="categoryClick(this)" val="'+obj[i].id+'">'+obj[i].categoryName+'</li>');

            }

            /*if(i < 9){
                $("#pcate1").append('<li><a href="'+window.requestUrl +'web/gstock/gstock.jsp?cateid='+obj[i].id+'">'+obj[i].categoryName+'</a></li>');
            }*/
        }
        // $("#c-ul").css("overflow", "hidden");
        gundong();
    }else{
        layer.msg(data.msg);
    }
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