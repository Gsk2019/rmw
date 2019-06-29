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
                <div class="form-group col-sm-12" style="text-align: center;font-weight: bolder">
                    供货信息
                    <hr>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.contactName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货人电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.contactTel}</label>
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
                        <label class="col-sm-4 control-label">供货品类：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.categoryTopName} ${info.categoryName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货产地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.productTopArea}${info.productArea}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货价格：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.price}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货数量：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.count}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货品牌：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.brand}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货仓库交割地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.repertory}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">生产日期：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${info.productDate}" pattern="yyyy-MM-dd" /></label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货状态：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><c:if test="${info.status ==1}">待处理</c:if><c:if test="${info.status ==2}">已处理</c:if>  </label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">供货时间：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal"><fmt:formatDate value="${info.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">处理备注：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${info.note }</label>
                    </div>
                </div>

                <div class="form-group col-sm-12" style="text-align: center;font-weight: bolder">
                   求购信息
                    <hr>
                </div>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购人：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.contactName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购人电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.contactTel}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">公司名称：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qgUser.company}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">公司电话：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qgUser.companyTel}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购品类：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.categoryTopName}-${qginfo.categoryName}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购产地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.productArea}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购价格：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.price}-${qginfo.endPrice}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购数量：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.count}</label>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购品牌：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.brand}</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购交割仓库地：</label>
                        <label class="col-sm-4 control-label" style="text-align: left;font-weight:normal">${qginfo.repertory}</label>
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

