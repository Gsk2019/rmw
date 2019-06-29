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
                    <label class="col-sm-2 control-label">标题：</label>
                    <div class="col-sm-10">
                        ${news.title}
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">摘要：</label>
                    <div class="col-sm-10">
                        ${news.summary}
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">类型：</label>
                    <div class="col-sm-10">
                        <c:if test="${news.type eq 1}">行业动态</c:if><c:if test="${news.type eq 2}">价格行情</c:if><c:if test="${news.type eq 3}">独家视角</c:if>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">发布人：</label>
                    <div class="col-sm-10">
                        ${news.publisher}
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">主图：</label>
                    <div class="col-sm-10">
                        <cs:file name="mainImage" url="${news.mainImage}" type="100" action="view"></cs:file>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="col-sm-2 control-label">新闻详情：</label>
                    <div class="col-sm-10">
                        ${news.content}
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

