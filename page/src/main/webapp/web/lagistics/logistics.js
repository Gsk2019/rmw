var len1 = 0;
var len2 = 0;
var tol1 = 0;
var tol2 = 0

function searchLogistics(p, ps, ty, isinit){
    var redata = window.getObject();

    var data1 = new Object();
    data1.startPoint = $("#startPoint").val();
    data1.endPoint = $("#endPoint").val();
    data1.page = p;
    data1.pageSize = ps;
    data1.type = ty;
    redata.data = JSON.stringify(data1);

    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/logistics/getLogistics.cs", //即上面的接口1
        data: redata,
        success: function(data, status) {
            data.isinit = isinit;
            if(ty == 1){
                window.resultOpera(resultLogistics1, data);
            }else{
                window.resultOpera(resultLogistics2, data);
            }
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });
}

function resultLogistics1(data) {
    if (data.ret == 1) {

        if(data.isinit == 1){
            $('#logistics1 ul').html("");
            len1 = data.data.results.length;
            tol1 = data.data.total;
        }
        for(var i=0; i<data.data.results.length; i++){
            var logi = data.data.results[i];
            var l1= '<li>' +
                '<div>'+getFullDayByLong(logi.createDate)+'</div>' +
                '<div title="'+logi.startPoint+'">'+getStr(8,logi.startPoint)+'</div>' +
                '<div title="'+logi.endPoint+'">'+getStr(8,logi.endPoint)+'</div>' +
                '<div>'+logi.price+'</div>' +
                '<div>'+logi.tonnage+'</div>' +
                '<div>'+logi.phone+'</div>' +
                '</li>';
            $('#logistics1 ul').append(l1)
        }

    }
}

function resultLogistics2(data) {
    if (data.ret == 1) {

        if(data.isinit == 1){
            $('#logistics2 ul').html("");
            len2 = data.data.results.length;
            tol2 = data.data.total;
        }

        for(var i=0; i<data.data.results.length; i++){
            var logi = data.data.results[i];
            var l1= '<li>' +
                '<div>'+getFullDayByLong(logi.createDate)+'</div>' +
                '<div title="'+logi.startPoint+'">'+getStr(8, logi.startPoint)+'</div>' +
                '<div title="'+logi.endPoint+'">'+getStr(8, logi.endPoint)+'</div>' +
                '<div title="'+logi.consignment+'">'+getStr(8, logi.consignment)+'</div>' +
                '<div>'+logi.tonnage+'</div>' +
                '<div>'+logi.phone+'</div>' +
                '</li>';
            $('#logistics2 ul').append(l1)
        }
    }
}

$(function(){

    searchLogistics(1, 8 ,1,1);
    searchLogistics(1, 8 ,2,1);

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
            searchLogistics(1, 8 ,1,1);
            searchLogistics(1, 8 ,2,1);
            return false;
        });



        //物流资源有效期
        laydate.render({
            elem: '#expiryDate' //指定元素
            ,theme: '#1E9FFF'
        });

        //物流资源有效期
        laydate.render({
            elem: '#expiryDate2' //指定元素
            ,theme: '#1E9FFF'
        });

        //打开发布物流报价
        $('#open_issuing_logistics_quotes').click(function(){
            layer.open({
                type:1,
                title:'发布物流报价',
                content:$('.issuing_logistics_quotes')
            })
        })

        //发布物流报价
        form.on('submit(issuing_logistics_quotes)', function(data){
            // layer.msg(JSON.stringify(data.field));
            data.field.type = 1;
            addLogistics(JSON.stringify(data.field))
            return false;
        });


        //打开发布物流委托
        $('#open_issuing_logistics_commission').click(function(){
            layer.open({
                type:1,
                title:'发布物流委托',
                area:'600px',
                content:$('.issuing_logistics_commission')
            })
        })

        //发布物流委托
        form.on('submit(issuing_logistics_commission)', function(data){
            // layer.msg(JSON.stringify(data.field));
            data.field.type = 2;
            addLogistics(JSON.stringify(data.field))
            return false;
        });


        //关闭所有页面层
        $('.close').click(function(){
            layer.closeAll('page');
        })

    });

    //物流信息滚动 定时滚动
    setInterval('autoScroll(".maquee")', 10000);
});
//物流信息滚动
function autoScroll(obj){
    $(obj).find("ul").animate({
        marginTop : "-46px"
    },500,function(){

        if("ul1" == $(this).attr("id")){
            if($("#ul1 li").length > 8){
                $("#ul1").css({marginTop : "0px"}).find("li:first").remove();
            }
            if((len1 + 1) <= tol1){
                searchLogistics((len1 + 1), 1 ,1,0);
                len1 ++;
            }else{
                len1 = 0;
                searchLogistics((len1 + 1), 1 ,1,0);
                len1 ++
            }
        }

        if("ul2" == $(this).attr("id")){
            if($("#ul2 li").length > 8){
                $("#ul2").css({marginTop : "0px"}).find("li:first").remove();
            }
            if((len2 + 1) <= tol2){
                searchLogistics((len2 + 1), 1 ,2,0);
                len2 ++;
            }else{
                len2 = 0;
                searchLogistics((len2 + 1), 1 ,2,0);
                len2 ++
            }
        }

    })
}

function addLogistics(dat){
    var redata = window.getObject();
    redata.data = dat;

    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/logistics/addLogistics.cs", //即上面的接口1
        data: redata,
        success: function(data, status) {
            window.resultOpera(resultAdd, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });
}

function resultAdd(data) {
    if (data.ret == 1) {
        layer.msg("保存成功");
        layer.closeAll('page');
    }else{
        layer.msg(data.msg);
    }
}

