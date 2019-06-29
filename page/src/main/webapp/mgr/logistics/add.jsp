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

    <script src="<%=basePath%>res/dist/js/city-picker.data.js"></script>
    <script src="<%=basePath%>res/dist/js/city-picker.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>res/dist/css/city-picker.css"/>

    <cs:initFile/>
    <script type="text/javascript">

    </script>
    <script>
        function  tijiao(){
            $("#addLogisticsForm").submit();
        }
    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addLogisticsForm"/>
            <form class="form-horizontal" action="add.cs" id="addLogisticsForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="2" name="status"/>
                <input type="hidden" value="1" name="type"/>
                <div class="form-group col-sm-12">

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系人：</label>
                        <div class="col-sm-8">
                            <input id="linkMan" name="linkMan" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入联系人',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">手机号码：</label>
                        <div class="col-sm-8">
                            <input id="phone" name="phone" class="form-control" validate="{required:true,maxlength:11}" validateMessage="{required:'请输入手机号码',maxlength:'手机号码超出长度限制'}" >
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">价格：</label>
                            <div class="col-sm-8">
                                <input id="price" name="price" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入价格" class="form-control" validate="{required:true,min:0,maxlength:8}" validateMessage="{required:'请输入价格',maxlength:'价格超出长度限制'}" >
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">起点：</label>
                        <div class="col-sm-8" style="position: relative;">
                                <input id="startPoint" name="startPoint" readonly type="text" data-toggle="city-picker">
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">起点：</label>
                        <div class="col-sm-8" style="position: relative;">
                            <input id="endPoint" name="endPoint" readonly type="text" data-toggle="city-picker">
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="submit" >提交</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>
</body>

</html>

