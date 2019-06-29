<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>res/js//plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/demo/webuploader-demo.css">
    <script type="text/javascript" src="<%=basePath%>res/js/plugins/webuploader/webuploader.js"></script>
    <script type="text/javascript" src="<%=basePath%>res/js/plugins/webuploader/uploader.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>res/js/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>res/js/plugins/ueditor/ueditor.all.js"> </script>
    <cs:initFile/>
    <script type="text/javascript">

    </script>
    <script>
        function initUe(){

            var mainImage = $("input[name='mainImage']");
            var num = 0;
            for(var i=0;i<mainImage.length;i++){
                if($(mainImage[i]).val() != ''){
                    num ++;
                }
            }
            if(0==num){
                layer.msg("请上传您的主图");
                return false;
            }

            $("#content").val(ue2.getContent());

            $("#addUserForm").submit();
            return true;
        }

        /*function gcChange(name, obj){
            $("#"+name).val();
        }*/
    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="edit.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="1" name="type"/>
                <input type="hidden" value="${news.id}" name="id"/>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">标题：</label>
                        <div class="col-sm-8">
                            <input id="title" name="title" value="${news.title}" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>

                   <%-- <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">分类：</label>
                        <div class="col-sm-8">
                            <input id="gc" name="gc" value="${news.gc}" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>--%>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">作者：</label>
                        <div class="col-sm-8">
                            <input id="publisher" name="publisher" value="${news.publisher}" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>
                </div>


                <div class="form-group col-sm-12">


                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">权重：</label>
                        <div class="col-sm-8">
                            <input id="weight" name="weight" value="${news.weight}" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入数字，从小到大排序" class="form-control" validate="{required:true,min:0,maxlength:8}" validateMessage="{required:'请输入权重，内容为数字',maxlength:'输入的权重超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">


                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">摘要：</label>
                        <div class="col-sm-10">
                            <textarea id="summary" name="summary"  placeholder="请输入摘要" class="form-control" validate="{required:true,min:0,maxlength:40}" validateMessage="{required:'请输入摘要',maxlength:'输入的摘要超出长度限制'}" >${news.summary}</textarea>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">

                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">主图：</label>
                        <div class="col-sm-10">
                            <cs:file name="mainImage" url="${news.mainImage}" type="100"></cs:file>
                            <%--<textarea id="mainImage" name="mainImage"  placeholder="请输入摘要" class="form-control" validate="{required:true,min:0,maxlength:40}" validateMessage="{required:'请输入摘要',maxlength:'输入的摘要超出长度限制'}" ></textarea>--%>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">新闻详情：</label>
                    <div class="col-sm-10">
                        <div class="content">
                            <script id="editor2" type="text/plain" style="width:100%;height:500px;">
                            </script><script type="text/javascript">

                            //实例化编辑器
                            //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                            var ue2 = UE.getEditor('editor2');
                            ue2.ready(function() {
                                ue2.setContent('${news.content}');  //赋值给UEditor
                            });
                            /*  ue2.addListener('selectionchange',function(){
                                    $("#productBody").val(ue2.getContent());
                                 console.log("选区已经变化！");
                            }) */
                        </script>
                            <textarea rows="" cols="" id="content" style="display:none;" name="content">${news.content}</textarea>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="button" onclick="initUe();">提交</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>

</body>

</html>

