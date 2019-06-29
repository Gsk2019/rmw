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
            margin-left: 13px
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
                        <label class="col-sm-4 control-label">发布人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUser.userName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">手机号码：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUser.phone}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">公司名称：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUser.company}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">公司电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUser.companyTel}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">品类：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.categoryTopName}-${info.categoryName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">产地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.productArea}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">品牌：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.brand}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">交割仓库地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.repertory}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">生产日期：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${info.productDate}" pattern="yyyy-MM-dd" /></label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">有效期：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${info.expiryDate}" pattern="yyyy-MM-dd" /> </label>

                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">价格：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.price}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">计量单位：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.unit}</label>
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">可供数量：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.count}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.contactName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系方式：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.contactTel}</label>
                    </div>
                </div>

                <c:if test="${info.status==1 }">
                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">交易员：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.traderName}</label>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">交易员电话：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.traderPhone}</label>
                        </div>
                    </div>
                </c:if>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">状态：</label>
                        <c:if test="${info.status==1 }">
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">已通过</label>
                        </c:if>
                        <c:if test="${info.status==2 }">
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">待审核</label>
                        </c:if>
                        <c:if test="${info.status==3 }">
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">已拒绝</label>
                        </c:if>
                    </div>
                    <c:if test="${info.status==3 }">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">拒绝原因：</label>
                            <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.reason}</label>
                        </div>
                    </c:if>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">备注：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.note}</label>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

</body>

</html>

