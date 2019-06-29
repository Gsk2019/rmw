$(function(){
    var redata = window.getObject();

    var cateid = getUrlParam("cateid");
    // //搜素关键字初始化
    // var varstr = getUrlParam("keyvalue");
    // if(varstr != null && varstr != "" && typeof(varstr) != "undefined"){
    //     $("#goods_in_stock_search").val(varstr);
    // }

    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/stock/home.cs", //即上面的接口1
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
            var h = 0;
            //品种一级
            $('.variety').parent().find("span").html("品种");
            for(var i=0;i<obj.allcategory.length;i++){
                if(obj.allcategory[i].topId == '0'){
                    if(cateid != '' && cateid != null && typeof(cateid) != "undefined"){
                        if(obj.allcategory[i].id == cateid){
                            $('.variety').append("<li mid='"+obj.allcategory[i].id+"' data-level='"+(i+1)+"'>"+obj.allcategory[i].categoryName+"</li>")
                        }else{
                            $('.variety').append("<li mid='"+obj.allcategory[i].id+"' data-level='"+(i+1)+"'>"+obj.allcategory[i].categoryName+"</li>")
                        }
                    }else{
                        if(h == 0){
                            $('.variety').append("<li mid='"+obj.allcategory[i].id+"' data-level='"+(i+1)+"'>"+obj.allcategory[i].categoryName+"</li>")
                        }else{
                            $('.variety').append("<li mid='"+obj.allcategory[i].id+"' data-level='"+(i+1)+"'>"+obj.allcategory[i].categoryName+"</li>")
                        }
                    }
                    h ++;
                }
            }
            //品种二级
            var catevalue = 0;
            for(var i=0;i<obj.allcategory.length;i++){
                var pca = obj.allcategory[i];
                if(pca.topId == '0'){
                    var ddiv = "";
                    if(catevalue == 0){
                        ddiv = ddiv +  "<div class='level' data-level='"+(i+1)+"'><p>";
                    }else{
                        ddiv = ddiv +  "<div class='level' data-level='"+(i+1)+"'><p>";
                    }

                    var c = 0;
                    for(var o=0;o<obj.allcategory.length;o++){
                        var nca = obj.allcategory[o];
                        if(nca.topId == pca.id){
                            if(c==0){
                                ddiv = ddiv + "<em class='level_active' mid='"+nca.id+"'>"+nca.categoryName+"</em>";
                            }else{
                                ddiv = ddiv + "<em mid='"+nca.id+"'>"+nca.categoryName+"</em>";
                            }
                            c++;
                        }
                    }
                    ddiv = ddiv + "</p></div>";
                    if(c > 0){
                        $('.variety').append(ddiv);
                        catevalue ++;
                    }
                }
            }

            //产地一级
            $('.place').parent().find("span").html("产地");
            var oo = 0;
            for(var i=0;i<obj.productAreas.length;i++){
                if(obj.productAreas[i].topId == '0'){
                    if(oo == 0){
                        $('.place').append("<li mid='"+obj.productAreas[i].id+"' data-level='"+(i+1)+"'>"+obj.productAreas[i].areaName+"</li>")
                    }else{
                        $('.place').append("<li mid='"+obj.productAreas[i].id+"' data-level='"+(i+1)+"'>"+obj.productAreas[i].areaName+"</li>")
                    }
                    oo ++;
                }
            }
            //产地二级
            var areavalue = 0;
            for(var i=0;i<obj.productAreas.length;i++){
                var pca = obj.productAreas[i];
                if(pca.topId == '0'){
                    var ddiv = "";
                    if(areavalue == 0){
                        ddiv = ddiv + "<div class='level' data-level='"+(i+1)+"'><p>";
                    }else{
                        ddiv = ddiv + "<div class='level' data-level='"+(i+1)+"'><p>";
                    }
                    var c = 0;
                    for(var o=0;o<obj.productAreas.length;o++){
                        var nca = obj.productAreas[o];
                        if(nca.topId == pca.id){
                            if(c==0){
                                ddiv = ddiv + "<em class='level_active' mid='"+nca.id+"'>"+nca.areaName+"</em>";
                            }else{
                                ddiv = ddiv + "<em mid='"+nca.id+"'>"+nca.areaName+"</em>";
                            }
                            c++;
                        }
                    }
                    ddiv = ddiv + "</p></div>";
                    if(c > 0){
                        $('.place').append(ddiv);
                        areavalue ++;
                    }
                }
            }

            //交割仓库一级
            $('.warehouse').parent().find("span").html("交割仓库地址");
            for(var i=0;i<obj.rmwRepertorys.length;i++){
                if(i == 0){
                    $('.warehouse').append("<li mid='"+obj.rmwRepertorys[i].id+"' data-level='"+(i+1)+"'>"+obj.rmwRepertorys[i].name+"</li>")
                }else{
                    $('.warehouse').append("<li mid='"+obj.rmwRepertorys[i].id+"' data-level='"+(i+1)+"'>"+obj.rmwRepertorys[i].name+"</li>")
                }
            }
            /*var is = $('.statistical_data i');
            for(var i=0;i<is.length;i++){
                if(i == 0){
                    $(is[i]).text(obj.count1);
                }else if(i == 1){
                    $(is[i]).text(obj.count2);
                }else if(i == 2){
                    $(is[i]).text(obj.count3);
                }
            }*/



            layui.use(['layer','laypage','element','form'], function(){

                var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
                    ,layer = layui.layer
                    ,laypage = layui.laypage
                    ,element = layui.element
                    ,form = layui.form

                //现货搜索
                form.on('submit(goods_in_stock_search)', function(data){
                    // layer.msg(JSON.stringify(data.field));
                    var ob = new Object();
                    ob.curr = 1;
                    ob.limit = 10;
                    laypagemy(ob);
                    return false;
                });

                //求购搜索
                form.on('submit(want_to_buy_search)', function(data){
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });

                //帮我找
                form.on('submit(help_find)', function(data){
                    // layer.msg(JSON.stringify(data.field));

                    var redata = window.getObject();
                    var data1 = new Object();
                    data1.content = data.field.content;
                    data1.type = 1;
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

                function resultStock(data){

                    if(data.ret == 1){
                        $("table tbody").html("");
                    }
                    var pager = data.data;

                    var token = window.getCookie("token");

                    for(var i=0;i<pager.results.length;i++){
                        var tr = "<tr>" ;
                        if(new Date().getTime() > parseInt(pager.results[i].expiry_date)+parseInt(86400000)){
                            tr = "<tr style='color: #c7c7c7'>" ;
                        }
                        if(pager.results[i].category_name==null||pager.results[i].category_name==""){
                            tr+="<td>"+pager.results[i].category_top_name+"</td>";
                        }else{
                            tr+="<td>"+pager.results[i].category_top_name+"-"+pager.results[i].category_name+"</td>" ;
                        }
                        tr+= "<td>"+pager.results[i].brand+"</td>" +
                            "<td>"+pager.results[i].product_area+"</td>" +
                            "<td>"+pager.results[i].unit+"</td>";
                        if(token != "" && token != null){
                            if(pager.results[i].price == null || pager.results[i].price == ""){
                                tr = tr + "<td class='red'><a class='red' href='javascript:;'>￥（不限价格）元/吨</a></td>";
                            }else{
                                tr = tr + "<td class='red'><a class='red' href='javascript:;'>￥"+pager.results[i].price+"元/吨</a></td>";
                            }
                        }else{
                            tr = tr + "<td class='red'><a class='red' href='javascript:;'><a class='log_show' href='"+window.requestUrl+"web/login.jsp'>登录可见</a></a></td>";
                        }

                        tr = tr +
                            "<td>"+pager.results[i].repertory+"</td>";
                        if(pager.results[i].product_date == null || pager.results[i].product_date == ""){
                            tr = tr + "<td>（不限）</td>";
                        }else{
                            tr = tr + "<td>"+getDateYM(pager.results[i].product_date)+"</td>";
                        }

                        tr = tr +  "<td>"+pager.results[i].count+"吨</td>" +
                            "<td>"+pager.results[i].company+"</td>" +
                            "<td>"+pager.results[i].t_name+"<br />"+pager.results[i].t_phone+"</td>" +
                            "<td>"+getDateDay(pager.results[i].create_date)+"</td>" +
                            "<td>"+getDateDay(pager.results[i].expiry_date)+"</td>" +
                            "<td><a class='layui-btn layui-btn-sm layui-btn-normal' href='javascript:userwt("+pager.results[i].id+");'>委托</a></td>" +
                            "</tr>";
                        $("table tbody").append(tr);
                    }

                }




                //分页
                function getParams(){
                    var variety = $('.variety').find('.active').attr("mid")
                    var place = $('.place').find('.active').attr("mid")
                    var warechouse = $('.warehouse').find('.active').attr("mid")
                    var variety_level,place_level
                    $('.variety .level').each(function(){
                        if($(this).css('display')=='block'){
                            variety_level = $(this).find('.level_active').attr("mid")
                        }
                    })
                    $('.place .level').each(function(){
                        if($(this).css('display')=='block'){
                            place_level = $(this).find('.level_active').attr("mid")
                        }
                    })

                    var ords = $('.select_list h2 span');
                    var ord = "1";
                    for(var i=0;i<ords.length;i++){
                        if($(ords[i]).find("i").text() == "↑"){
                            ord = $(ords[i]).find("i").attr("mid");
                        }else if($(ords[i]).find("i").text() == "↓"){
                            ord = parseInt($(ords[i]).find("i").attr("mid")) + 1;
                        }
                    }

                    var obj = new Object();
                    obj.category1 = variety;
                    obj.category2 = variety_level;
                    obj.area1 = place;
                    obj.area2 = place_level;
                    obj.rep = warechouse;
                    obj.ord = ord;
                    obj.name = $('#goods_in_stock_search').val();
                    return obj;
                }

                function laypagemy(obj){

                    var redata = window.getObject();
                    var data1 = getParams();
                    data1.page = obj.curr;
                    data1.pageSize = obj.limit;
                    // data1.ord = obj.ord;
                    data1.type = 1;
                    redata.data =  JSON.stringify(data1);

                    $.ajax({
                        type: 'post',
                        url: window.requestUrl + "rest/stock/getStocks.cs", //即上面的接口1
                        data:redata,
                        success: function(data, status) {
                            window.resultOpera(resultStock, data);
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

                //筛选菜单效果
                $('.select_search ul li ul li').click(function(){
                    $(this).parent().find('li').removeClass('active')
                    $(this).addClass('active')
                    var level = $(this).attr('data-level')
                    $(this).parent().find('.level').each(function(){
                        $(this).attr('data-level') == level ? $(this).show() : $(this).hide()
                    })
                    active()
                })
                $('.level p em').click(function(){
                    $(this).parent().find('em').removeClass('level_active')
                    $(this).addClass('level_active')
                    active()
                })
                function active(){

                    var ob = new Object();
                    ob.curr = 1;
                    ob.limit = 10;
                    laypagemy(ob);
                }

                //列表排序
                $('.select_list h2 span').click(function(){

                    var text = $(this).find('i')
                    if(text.text() == ''){
                        $('.select_list h2 span i').text('')
                        text.text('↑')
                    }
                    text.text() == '↑' ? text.text('↓') : text.text('↑')
                    laypagemy(ob);
                })

            });

        }else{
            layer.msg(data.msg);
        }
    }

});

//委托反馈处理
function userwt(id){

    var tk = window.getCookie("token");
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

    layer.open({
        content: '<input type="number" id="count" name="count" placeholder="请输入数量" lay-verify="required" autocomplete="off" class="layui-input">'
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            //按钮【按钮一】的回调
            var redata = window.getObject();
            var data1 = new Object();
            data1.infoId = id;
            data1.status = 1;

            data1.count = $("#count").val();
            if($("#count").val() == ""){
                layer.msg("请选择数量");
                return false;
            }

            redata.data = JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/userinfo/addUserInfo.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultWt, data);

                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

        }
        ,btn2: function(index, layero){
            //按钮【按钮二】的回调
            layer.close(index);
            //return false 开启该代码可禁止点击该按钮关闭
        }
        ,cancel: function(){
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }
    });




}

function resultWt(data) {
    if (data.ret == 1) {
        layer.msg("委托成功，业务员很快就会联系您");
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

function clickStock(data) {
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
        window.location.href = window.requestUrl + "web/gstock/publishing_spot_information.jsp";
    }
}