$(function(){

    var categoryarr = new Array()
    /*categoryarr['全脂奶粉'] = ['普通','热稳','速溶']
    categoryarr['黄油'] = ['含盐','无盐']
    categoryarr['乳清蛋白粉'] = ['wpc34','wpc60','wpc70','wpc80']
    categoryarr['乳糖'] = ['40目','100目','200目']
    categoryarr['乳清粉'] = ['高蛋白乳清粉','中蛋白乳清粉','低蛋白乳清粉','D40','D45','D50','D60','D70','D90']
    categoryarr['液态奶'] = ['全脂','脱脂']*/

    var placearr = new Array()
    /*
            placearr['中国'] = ['北京市','天津市','上海市','重庆市','河北省','山西省','辽宁省','吉林省','江苏省','浙江省','安徽省','福建省','江西省','山东省','河南省','湖北省','湖南省','广东省','海南省','四川省','贵州省','云南省','陕西省','甘肃省','青海省','台湾省','内蒙古自治区','广西壮族自治区','西藏自治区','宁夏回族自治区','新疆维吾尔自治区','香港特别行政区','澳门特别行政区']
            placearr['澳洲'] = ['澳大利亚','新西兰']
            placearr['欧洲'] = ['法国','德国','奥地利','比利时','波兰','丹麦','荷兰','瑞典','意大利','英国','智利']
            placearr['北美洲'] = ['加拿大','美国']
            placearr['南美洲'] = ['阿根廷']*/


    var redata = window.getObject();
    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/stock/home2.cs", //即上面的接口1
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

    function resultFree(data) {
        if (data.ret == 1) {
            var obj = data.data;
            //品种一级
            for (var i = 0; i < obj.allcategory.length; i++) {
                if (obj.allcategory[i].topId == '0') {
                    $('#categoryTopId').append("<option value='"+obj.allcategory[i].id+"'>"+obj.allcategory[i].categoryName+"</option>")
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
                    $('#productTopAreaId').append("<option value='"+obj.productAreas[i].id+"'>"+obj.productAreas[i].areaName+"</option>")
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
                $('#repertoryId').append("<option value='"+obj.rmwRepertorys[i].id+"'>"+obj.rmwRepertorys[i].name+"</option>")
            }
            //填充发布人信息
            $('#contactName').attr("value",obj.rmwUser.userName);
            $('#contactTel').attr("value",obj.rmwUser.phone);

            $('#reset').click();
        }
    }

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
            }else{
                layer.msg(data.msg);
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
            data.field.isSysTrade = $("#isSysTrade").find("option:selected").val();;
            data.field.expiryDate = getDay(parseInt($("#valday").find("option:selected").val()));

            var redata = window.getObject();
            redata.data = JSON.stringify(data.field);
            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/stock/addStocks.cs", //即上面的接口1
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