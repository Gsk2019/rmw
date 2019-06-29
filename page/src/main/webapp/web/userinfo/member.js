$(function(){


    var redata1 = window.getObject();
    $.ajax({
        type: 'post',
        url:    window.requestUrl + "rest/appuser/getStatus.cs", //即上面的接口1
        data:redata1,
        async:false,
        success: function(data, status) {
            window.resultOpera(resultStatus, data);
        },
        error: function(data, status) {
            // alert(status);
            console.log(status);
            layer.msg("首页信息加载失败");
        }
    });

    function resultStatus(data){
        if(data.ret == 1){
            // layer.msg("提交成功");
            // layer.closeAll('page');
            window.setCookie("uidentity", data.data);
        }else{
            layer.msg(data.msg);
        }
    }
    var uidentity = window.getCookie("uidentity");

    //1待提交认证 2审核中 3认证成功 4认证未通过
    if(uidentity == 2){
        $("#a1").css("display", "block");
    }else if(uidentity == 3){
        $("#a2").css("display", "block");
    }else if(uidentity == 4){
        $("#a3").css("display", "block");
        $("#a4").css("display", "block");
        $("#a8").css("display", "block");
    }else{
        $("#a3").css("display", "block");
        $("#a4").css("display", "block");
    }

    layui.use(['layer','element','form','laydate','table','upload'], function(){

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            ,layer = layui.layer
            ,element = layui.element
            ,form = layui.form
            ,laydate = layui.laydate
            ,table = layui.table
            ,upload = layui.upload;

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

        //营业执照上传
        // var redata = ;
        var index2;
        var uploadInst = upload.render({
            elem: '#business_license'
            ,url: window.requestUrl + 'rest/file/uploadPic.cs'	//接口地址
            ,auto:true
            ,data: {
                tk:function(){return getCookie("token")},
                plat:function(){
                    return window.requestType
                }
            } //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
            ,bindAction:'#img_submit' //确认上传按钮
            ,before: function(obj){
                index2 = layer.load();
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#business_license').attr('src', result); //图片链接（base64）
                });
            }
            /*,choose: function(obj){		//本地预览图片
                obj.preview(function(index, file, result){
                    $('#business_license_img').attr('src', result).show(); //图片链接（base64）
                    $('#business_license').find('i,p').hide()

                });

            }*/
            ,done: function(res){ //上传操作
                //如果上传失败
                /*if(res.code > 0){
                    return layer.msg('上传失败');
                }*/
                window.resultOpera(resultImg1, res);
                layer.close(index2);
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#business_license_upload');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-warm layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

        function resultImg1(data){
            if(data.ret == 1){
                // layer.msg("提交成功");
                // layer.closeAll('page');
                $('#img1').val(data.data.path); //图片链接（base64）
            }else{
                layer.msg(data.msg);
            }
        }

        //许可证上传
        var index3;
        var uploadInst1 = upload.render({
            elem: '#licence'
            ,url: window.requestUrl + 'rest/file/uploadPic.cs'	//接口地址
            ,auto:true
            ,data: {
                tk:function(){return getCookie("token")},
                plat:function(){
                    return window.requestType
                }
            } //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
            ,before: function(obj){
                index3 = layer.load();
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#licence').attr('src', result); //图片链接（base64）
                });
            }
            ,bindAction:'#img_submit'	//确认上传按钮
            /*,choose: function(obj){		//本地预览图片
                obj.preview(function(index, file, result){
                    $('#licence_img').attr('src', result).show(); //图片链接（base64）
                    $('#licence').find('i,p').hide()
                });

            }*/
            ,done: function(res){	//上传操作
                //如果上传失败
                // if(res.code > 0){
                // return layer.msg('上传失败');
                window.resultOpera(resultImg2, res);
                layer.close(index3);
                // }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#license_upload');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-warm layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst1.upload();
                });
            }
        });

        function resultImg2(data){
            if(data.ret == 1){
                // layer.msg("提交成功");
                // layer.closeAll('page');
                $('#img2').val(data.data.path); //图片链接（base64）
            }else{
                layer.msg(data.msg);
            }
            // layer.close(index);
        }

        //身份证正面
        var index;
        var uploadInst2 = upload.render({
            elem: '#identity_front'
            ,url: window.requestUrl + 'rest/file/uploadPic.cs'
            ,auto:true
            ,data: {
                tk:function(){return getCookie("token")},
                plat:function(){
                    return window.requestType
                }
            } //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
            ,before: function(obj){
                index = layer.load();
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#identity_front').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                /*if(res.code > 0){
                    return layer.msg('上传失败');
                }*/
                //上传成功

                window.resultOpera(resultImg3, res);
                layer.close(index);
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#identity_front_upload');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-warm layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst2.upload();
                });
            }
        });

        function resultImg3(data){
            if(data.ret == 1){
                // layer.msg("提交成功");
                // layer.closeAll('page');
                $('#img3').val(data.data.path); //图片链接（base64）
            }else{
                layer.msg(data.msg);
            }
            // layer.close(index);
        }

        //身份证正面
        var index1;
        var uploadInst3 = upload.render({
            elem: '#identity_reverse'
            ,url: window.requestUrl + 'rest/file/uploadPic.cs'
            ,auto:true
            ,data: {
                tk:function(){return getCookie("token")},
                plat:function(){
                    return window.requestType
                }
            } //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
            ,before: function(obj){
                index1 = layer.load();
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#identity_reverse').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                /*if(res.code > 0){
                    return layer.msg('上传失败');
                }*/
                //上传成功

                window.resultOpera(resultImg4, res);
                layer.close(index1);
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#identity_reverse_upload');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-warm layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst3.upload();
                });
            }
        });

        function resultImg4(data){
            if(data.ret == 1){
                // layer.msg("提交成功");
                // layer.closeAll('page');
                $('#img4').val(data.data.path); //图片链接（base64）
            }else{
                layer.msg(data.msg);
            }
            // layer.close(index);
        }

        //提交审核
        form.on('submit(member)', function(data){
            // layer.msg(JSON.stringify(data.field));

            var b_l_img = $('#business_license').attr('src')
            var l_img = $('#licence').attr('src')

            if(!b_l_img){
                layer.msg('请上传营业执照')
                return false
            }
            if(!l_img){
                layer.msg('请上传食品经营许可证/食品生产许可证')
                return false
            }

            // index = layer.load(1);
            /*uploadInst.upload();
            uploadInst1.upload();*/


            var redata = window.getObject();
            var data1 = new Object();
            data1.businessLicence = $('#img1').val();
            if(data.field.licence == 'licence_jy'){
                data1.licenceJy = $('#img2').val();
            }else if(data.field.licence == 'licence_sc'){
                data1.licenceSc = $('#img2').val();
            }

            /*data1.identity = data.field.identity;
            data1.identityFront = $('#img3').val();
            data1.identityReverse = $('#img4').val();*/
            data1.companyTel = data.field.company_tel;
            data1.companyOwner = data.field.company_owner;
            data1.company = data.field.company;

            redata.data = JSON.stringify(data1);

            $.ajax({
                type: 'post',
                url:    window.requestUrl + "rest/appuser/identity.cs", //即上面的接口1
                data:redata,
                success: function(data, status) {
                    window.resultOpera(resultIdentity, data);
                },
                error: function(data, status) {
                    // alert(status);
                    console.log(status);
                    layer.msg("首页信息加载失败");
                }
            });

            return false;
        });



        /*$(".upload .left").click(function () {
            uploadInst.upload();
            // uploadInst1.upload();
        });
        $(".upload .right").click(function () {
            // uploadInst.upload();
            uploadInst1.upload();
        });*/

        //验证是否上传
        $('#is_img').click(function(){
            // $('#img_submit').click()
        })

        function resultIdentity(data){
            if(data.ret == 1){
                layer.msg("入住成功，请耐心等待审核");
                window.setCookie("uidentity", "2");
                window.location.href = window.location.href;
                // layer.closeAll('page');
            }else{
                layer.msg(data.msg);
            }
        }

    });
});
