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
                <input type="hidden" value="${rmwRepertory.id}" name="id"/>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">仓库交割地址：</label>
                        <div class="col-sm-10">
                            <input id="name" name="name" value="${rmwRepertory.name}" class="form-control" placeholder="请输入仓库交割地址" validate="{required:true,maxlength:20}" validateMessage="{required:'请输入仓库交割地址',maxlength:'输入的仓库交割地址超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">排序值：</label>
                        <div class="col-sm-10">
                            <input id="sort" name="sort" class="form-control" value="${rmwRepertory.sort}" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入',maxlength:'输入的超出长度限制'}" />
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">详细地址：</label>
                        <div class="col-sm-10">
                            <textarea id="addr" name="addr"   placeholder="请输入详细地址" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入详细地址',maxlength:'输入的详细地址超出长度限制'}" >${rmwRepertory.addr}</textarea>
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

