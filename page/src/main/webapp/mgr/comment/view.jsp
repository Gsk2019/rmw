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


    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="addUserForm" method="POST" onsubmit="return false;">
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">标题：${news.title}</label>
                    </div>
                </div>
                    <div class="form-group col-sm-12">

                        <div class="form-group col-sm-12">
                            <label class="col-sm-2 control-label">摘要：${news.summary}</label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">类型：<c:if test="${news.type eq 1}">行业动态</c:if><c:if test="${news.type eq 2}">价格行情</c:if></label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">发布人：${news.publisher}</label>
                        </div>
                    </div>

                    <div class="form-group col-sm-12">
                        <label class="col-sm-2 control-label">主图：</label>
                        <div class="col-sm-10">
                            <img alt="" style="height:100px; width:100px; cursor: pointer;" src="${news.mainImage}" class="show-img">
                        </div>
                    </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">新闻详情：</label>
                    <div class="col-sm-10">
                        <div class="content">
                                ${news.content}
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

