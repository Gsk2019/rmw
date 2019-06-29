<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<html>
<head>

    <%@ include file="../../common/head.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="<%=basePath%>res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
    <script src="<%=basePath%>res/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>res/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=basePath%>res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script>


        function queryParams(params){
            var page = params.offset / params.limit + 1;
            var dataObj = new Object();
            return  { page:page,
                pageSize:params.limit,
                search:JSON.stringify(dataObj),
                sort:params.sort,
                order:params.order,
                data:JSON.stringify(dataObj)
            };
        }


        $(function() {
            $('#btnSearch').click(function() {
                $('#tab_user').bootstrapTable('refresh');
            });

            $('#clearHref').click(function () {
                $('#myform')[0].reset();
            });
        });

        function getImg(value,row,index){

                return '<img src="'+value+'" onclick="javascript:$.showPic(this.src)" style="height: 60px;width: 180px">';
        }


    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row row-lg">

        <div class="col-sm-12">

            <cs:toolbar title="轮播图" tableId="tab_user" width="50%" height="40%" menuCode="${menuCode }" type="1"></cs:toolbar>

            <table id="tab_user"
                   data-toggle="table"
                   data-url="list2.cs"
                   data-method="get"
                   data-click-to-select="true"
                   data-pagination="true"
                   data-data-type="json"
                   data-show-refresh="true"
                   data-show-columns="true"
                   data-show-toggle="true"
                   data-search-on-enter-key="true"
                   data-toolbar="#def_toolbar"
                   data-side-pagination="server"
                   data-query-params="queryParams"
                   data-mobile-responsive="true"
            >
                <thead>
                <tr>
                    <th data-checkbox="true"	data-click-to-select="true"></th>
                    <th data-field="id" data-visible="false">id</th>
                    <th data-field="contents" data-formatter="getImg">图片</th>
                    <th data-field="code" >名称</th>
                    <th data-field="modify_date" data-formatter="format_time">修改时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
