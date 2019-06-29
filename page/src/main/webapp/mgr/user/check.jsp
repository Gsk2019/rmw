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

        function check(){
            var item = $(":radio:checked");
            var len=item.length;
            if(!len>0){
                $.tips('请选择通过或不通过');
                return false;
            }
            $("#addUserForm").submit();
            return true;
        }

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
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal"  action="check.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${user.id}" name="id"/>
                <%--<div class="form-group col-sm-12">--%>
                    <%--<div class="form-group col-sm-6">--%>
                        <%--<label class="col-sm-4 control-label">头像：</label>--%>
                        <%--<label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><img src="${user.img}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/></label>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">账号：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.phone}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">姓名：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.userName}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">注册时间：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-DD HH:mm:ss"></fmt:formatDate></label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">认证状态：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><c:if test="${user.isIdentity eq 1}">未认证</c:if><c:if test="${user.isIdentity eq 2}">待审核</c:if><c:if test="${user.isIdentity eq 3}">已认证</c:if></label>
                    </div>
                </div>

                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">账号：</label>--%>
                <%--<div class="col-sm-6">--%>
                <%--${user.phone}--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">用户名：</label>--%>
                <%--<div class="col-sm-6">--%>
                <%--${user.userName}--%>
                <%--</div>--%>
                <%--</div>--%>

                <c:if test="${user.isIdentity != 1}">

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司名称：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.company}</label>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司法人：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.companyOwner}</label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">公司电话：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.companyTel}</label>
                        </div>
                        <%--<div class="form-group col-sm-6">--%>
                            <%--<label class="col-sm-4 control-label">身份证号：</label>--%>
                            <%--<label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${user.identity}</label>--%>
                        <%--</div>--%>
                    </div>

                    <div class="form-group col-sm-12">
                        <%--<div class="form-group col-sm-6">--%>
                            <%--<label class="col-sm-4 control-label">身份证正反面：</label>--%>
                            <%--<label class="col-sm-4 control-label" style="text-align: left;font-weight:normal;white-space: nowrap"><img src="${user.identityFront}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/>&nbsp;&nbsp;<img src="${user.identityReverse}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/></label>--%>
                        <%--</div>--%>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">营业执照：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><img src="${user.businessLicence}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/></label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">食品经营许可证：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><img src="${user.licenceJy}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/></label>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">食品生产许可证：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><img src="${user.licenceSc}" onclick="javascript:$.showPic(this.src)" style="width: 80px;height: 60px"/></label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">请选择：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">
                                <label class="radio-inline"  >
                                    <input type="radio"  value="3" name="isIdentity" <c:if test="${user.isIdentity eq 3}">checked</c:if>>通过
                                </label>
                                <label class="radio-inline" >
                                    <input type="radio"  value="4" name="isIdentity" <c:if test="${user.isIdentity eq 4}">checked</c:if>>不通过
                                </label>
                            </label>
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-3 pull-left">
                            <button class="btn btn-default btn-dlg-close">取消</button>
                            <button class="btn btn-primary" type="button" onclick="check()">提交</button>
                        </div>
                    </div>

                </c:if>
            </form>
        </div>
    </div>
</div>
</body>
</html>

