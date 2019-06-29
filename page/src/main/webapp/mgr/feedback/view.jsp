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
    <script type="text/javascript">

    </script>
    <style type="text/css">
        .form-group {
             margin-bottom: 0px;
        }
    </style>
</head>
<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="addUserForm" method="POST" onsubmit="return false;">

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">反馈人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.userName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.phone}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">反馈内容：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal;word-break:break-all">${user.content } </label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">反馈时间：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">处理状态：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><c:if test="${user.status ==1}">待处理</c:if><c:if test="${user.status ==2}">已处理</c:if>  </label>
                    </div>
                </div>


                <c:if test="${user.status==2 }">
                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">处理备注：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.result}</label>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
    </div>

</div>
</body>
</html>

