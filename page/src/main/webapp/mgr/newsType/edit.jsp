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
            $("#addUserForm").submit();
            return true;
        }
    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="edit.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${newsType.id}" name="id"/>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">名称：</label>
                        <div class="col-sm-8">
                            <input id="name" name="name" class="form-control" value="${newsType.name}" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">排序值：</label>
                        <div class="col-sm-8">
                            <input id="sort" name="sort" value="${newsType.sort}" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入数字，从小到大排序" class="form-control" validate="{required:true,min:0,maxlength:8}" validateMessage="{required:'请输入权重，内容为数字',maxlength:'输入的权重超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-8">
                            <input type="radio" name="state" value="1" <c:if test="${newsType.state==1}">checked</c:if> >启用
                            <input type="radio" name="state" value="0" <c:if test="${newsType.state==0}">checked</c:if> >停用
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

