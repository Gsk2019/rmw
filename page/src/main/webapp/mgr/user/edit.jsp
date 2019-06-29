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

</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="edit.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${user.id}" name="id"/>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">用户账号：</label>
                        <div class="col-sm-8">
                            <input id="phone" name="phone" class="form-control" value="${user.phone}" readonly validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-10">
                            <input id="userName" name="userName" class="form-control" value="${user.userName}" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入名称',maxlength:'输入的名称超出长度限制'}" >
                        </div>
                    </div>
                </div>
                <c:if test="${user.isIdentity != 1}">
                    <div class="form-group col-sm-12">
                        <%--<div class="form-group col-sm-6">--%>
                            <%--<label class="col-sm-4 control-label">身份证号：</label>--%>
                            <%--<div class="col-sm-8">--%>
                                <%--<input id="identity" name="identity" value="${user.identity}" class="form-control" validate="{required:true,maxlength:19}" validateMessage="{required:'请输入身份证号',maxlength:'输入的身份证号超出长度限制'}" >--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司名称：</label>
                            <div class="col-sm-8">
                                <input id="company" name="company" value="${user.company}"  class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入',maxlength:'输入的超出长度限制'}" >
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司法人：</label>
                            <div class="col-sm-8">
                                <input id="companyOwner" name="companyOwner" value="${user.companyOwner}" class="form-control" validate="{required:true,maxlength:19}" validateMessage="{required:'请输入',maxlength:'输入的超出长度限制'}" >
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司电话：</label>
                            <div class="col-sm-8">
                                <input id="companyTel" name="companyTel" value="${user.companyTel}"  class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入',maxlength:'输入的超出长度限制'}" >
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">营业执照：</label>
                            <div class="col-sm-8">
                                <cs:file name="businessLicence" url="${user.businessLicence}" type="100"></cs:file>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">食品生产许可证：</label>
                            <div class="col-sm-8">
                                <cs:file name="licenceSc" url="${user.licenceSc}" type="100"></cs:file>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">食品经营许可证：</label>
                            <div class="col-sm-8">
                                <cs:file name="licenceJy" url="${user.licenceJy}" type="100"></cs:file>
                            </div>
                        </div>
                    </div>

                </c:if>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="submit">提交</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>

