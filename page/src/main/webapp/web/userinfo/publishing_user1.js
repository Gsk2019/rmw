$(function(){

    //初始化现货信息
    var stockid = getUrlParam("infoid");

    var redata = window.getObject();
    var data1 = new Object();
    data1.id = stockid;
    redata.data =  JSON.stringify(data1);

    var info;
    $.ajax({
        type: 'post',
        url: window.requestUrl + "rest/stock/info.cs", //即上面的接口1
        data: redata,
        async:false,
        success: function(data, status) {
            window.resultOpera(resultInfo, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

    function resultInfo(data){
        if(data.ret == 1){
            info = data.data;
            //页面初始化
            $("#brand").val(info.brand);
            $("#id").val(info.id);
            $("#productDate").val(getDateDay(info.productDate));
            $("#count").val(info.count);
            $("#price").val(info.price);
            // $("#price").val(info.price);

           /* var options = document.getElementById('valday').children;
            for(var i=0;i<options.length;i++){
                if(options[i].value == info.valday){
                    options[i].selected=true;
                }
            }*/

            $("#unit").val(info.unit);
            $("#contactName").val(info.contactName);
            $("#contactTel").val(info.contactTel);

        }else{
            // layer.msg(data.msg)
        }
    }

    //初始化分类
    var categoryarr = new Array()
    //初始化区域
    var placearr = new Array()

    var redata = window.getObject();
    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/stock/home.cs", //即上面的接口1
        data:redata,
        async:false,
        success: function(data, status) {
            window.resultOpera(resultFree, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

    function resultFree(data) {
        if (data.ret == 1) {
            var obj = data.data;
            //品种一级
            for (var i = 0; i < obj.allcategory.length; i++) {
                if (obj.allcategory[i].topId == '0') {
                    if(info.categoryTopId == obj.allcategory[i].id){
                        $('#categoryTopId').append("<option value='"+obj.allcategory[i].id+"' selected>"+obj.allcategory[i].categoryName+"</option>")
                        $("#categoryTopName").val(obj.allcategory[i].categoryName);
                    }else{
                        $('#categoryTopId').append("<option value='"+obj.allcategory[i].id+"'>"+obj.allcategory[i].categoryName+"</option>")
                    }
                }
            }
            //品种二级
            for (var i = 0; i < obj.allcategory.length; i++) {
                var pca = obj.allcategory[i];
                if (pca.topId == '0') {
                    var pt = new Array()
                    for (var o = 0; o < obj.allcategory.length; o++) {
                        var nca = obj.allcategory[o];
                        if (nca.topId == pca.id) {
                            var t = new Object()
                            t.id=nca.id;
                            t.name=nca.categoryName;
                            pt.push(t)
                        }
                    }
                    categoryarr[pca.id] = pt;
                }
            }

            //产地一级
            for (var i = 0; i < obj.productAreas.length; i++) {
                if (obj.productAreas[i].topId == '0') {
                    if(info.productTopAreaId == obj.productAreas[i].id) {
                        $('#productTopAreaId').append("<option value='" + obj.productAreas[i].id + "' selected>" + obj.productAreas[i].areaName + "</option>")
                        $("#productTopArea").val(obj.productAreas[i].areaName);
                    }else{
                        $('#productTopAreaId').append("<option value='" + obj.productAreas[i].id + "'>" + obj.productAreas[i].areaName + "</option>")
                    }
                }
            }
            //产地二级
            for (var i = 0; i < obj.productAreas.length; i++) {
                var pca = obj.productAreas[i];
                if (pca.topId == '0') {
                    var pt = new Array()
                    for (var o = 0; o < obj.productAreas.length; o++) {
                        var nca = obj.productAreas[o];
                        if (nca.topId == pca.id) {
                            var t = new Object()
                            t.id=nca.id;
                            t.name=nca.areaName;
                            pt.push(t)
                        }
                    }
                    placearr[pca.id] = pt;
                }
            }

            //交割仓库一级
            for (var i = 0; i < obj.rmwRepertorys.length; i++) {
                if(info.repertoryId == obj.rmwRepertorys[i].id) {
                    $('#repertoryId').append("<option value='" + obj.rmwRepertorys[i].id + "' selected>" + obj.rmwRepertorys[i].name + "</option>")
                    $("#repertory").val(obj.rmwRepertorys[i].name);
                }else{
                    $('#repertoryId').append("<option value='" + obj.rmwRepertorys[i].id + "'>" + obj.rmwRepertorys[i].name + "</option>")
                }
            }

            // $('#reset').click();


        }
    }


    //品类和地址

    var cid = info.categoryTopId;
    if(cid != '' && categoryarr[cid] !== undefined){
        $('.varieties_1').html(function (){
            $(".varieties_1").html("<select name='categoryId' id='categoryId' lay-verify='required' class='varieties_1_select'></select>");
            for (var i = 0; i< categoryarr[cid].length; i++) {
                if(info.categoryId == categoryarr[cid][i].id){
                    $('.varieties_1_select').append("<option value="+ categoryarr[cid][i].id+" selected>"+ categoryarr[cid][i].name+"</option>")
                    $("#categoryName").val(categoryarr[cid][i].name);
                }else{
                    $('.varieties_1_select').append("<option value="+ categoryarr[cid][i].id+">"+ categoryarr[cid][i].name+"</option>")
                }
            }
        })
    }else{
        $('.varieties_1').html('')
    }
    // layui.form.render('select');

    var aid = info.productTopAreaId;
    if(aid != '' && placearr[aid]!==undefined){
        $('.place_of_origin_1').html(function (){
            $(".place_of_origin_1").html("<select name='productAreaId' id='productAreaId' lay-verify='required' class='place_of_origin_1_select'></select>");
            for (var i = 0; i< placearr[aid].length; i++) {
                if(info.productAreaId == placearr[cid][i].id) {
                    $('.place_of_origin_1_select').append("<option value=" + placearr[aid][i].id + " selected>" + placearr[aid][i].name + "</option>")
                    $("#productArea").val(placearr[aid][i].name);
                }else{
                    $('.place_of_origin_1_select').append("<option value=" + placearr[aid][i].id + ">" + placearr[aid][i].name + "</option>")
                }
            }
        })
    }else{
        $('.place_of_origin_1').html('')
    }
    // layui.form.render('select');

    layui.use(['layer','element','form','laydate'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,laydate = layui.laydate

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


        //品种联动
        form.on('select(varieties)', function(data){

            if(data.value!=' ' && categoryarr[data.value] !== undefined){
                $('.varieties_1').html(function (){
                    $(".varieties_1").html("<select name='categoryId' id='categoryId' lay-verify='required' class='varieties_1_select'></select>");
                    for (var i = 0; i< categoryarr[data.value].length; i++) {
                        $('.varieties_1_select').append("<option value="+ categoryarr[data.value][i].id+">"+ categoryarr[data.value][i].name+"</option>")
                    }
                })
            }else{
                $('.varieties_1').html('')
            }
            form.render('select');
        })

        //产地联动
        form.on('select(place_of_origin)', function(data){

            if(data.value!=' ' && placearr[data.value]!==undefined){
                $('.place_of_origin_1').html(function (){
                    $(".place_of_origin_1").html("<select name='productAreaId' id='productAreaId' lay-verify='required' class='place_of_origin_1_select'></select>");
                    for (var i = 0; i< placearr[data.value].length; i++) {
                        $('.place_of_origin_1_select').append("<option value="+ placearr[data.value][i].id+">"+ placearr[data.value][i].name+"</option>")
                    }
                })
            }else{
                $('.place_of_origin_1').html('')
            }
            form.render('select');
        })


        //生产日期
        laydate.render({
            elem: '#productDate' //指定元素
            ,theme: '#1E9FFF'
        });

        function addStock(data) {
            if (data.ret == 1) {
                layer.msg("添加成功");
                window.location.href = window.requestUrl + "web/userinfo/user_center.jsp";
            }
        }
        //发布现货
        form.on('submit(release_spot)', function(data){
            /*if(data.field.categoryId == ''){
                layer.msg("请选择发布产品的二级分类");
                return;
            }
            if(data.field.productAreaId == ''){
                layer.msg("请选择发布产品的二级产地");
                return;
            }*/
            data.field.categoryTopName = $("#categoryTopId").find("option:selected").text();;
            data.field.categoryName = $("#categoryId").find("option:selected").text();;
            data.field.productTopArea = $("#productTopAreaId").find("option:selected").text();;
            data.field.productArea = $("#productAreaId").find("option:selected").text();;
            data.field.repertory = $("#repertoryId").find("option:selected").text();;
            data.field.expiryDate = getDay(parseInt($("#valday").find("option:selected").val()));

            var redata = window.getObject();
            redata.data = JSON.stringify(data.field);
            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/stock/editStocks.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(addStock, data);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

            return false;
        });



        //重置清除二级联动数据
        $('#reset').click(function(){
            $('.varieties_1').html('')
            $('.place_of_origin_1').html('')
        })

    });
});