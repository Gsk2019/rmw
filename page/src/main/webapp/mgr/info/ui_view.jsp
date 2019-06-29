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
                        <label class="col-sm-4 control-label">委托人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUser.userName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系方式：</label>
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
                        <label class="col-sm-4 control-label">委托状态：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><c:if test="${rmwUserInfo.status ==1}">待处理</c:if><c:if test="${rmwUserInfo.status ==2}">已处理</c:if>  </label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">委托时间：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${rmwUserInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">处理备注：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${rmwUserInfo.note }</label>
                    </div>
                </div>

                <div class="form-group col-sm-12" style="text-align: center;font-weight: bolder">
                    委托现货信息
                    <hr>
                </div>
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
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${saleUser.company}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">公司电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${saleUser.companyTel}</label>
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

            </form>
        </div>
    </div>

</div>

</body>

</html>

