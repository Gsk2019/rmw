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


        function getState(value,row,index){
            if(value == "1"){
                return "启用";
            }else if(value == "0"){
                return "停用";
            }
            return "未知";
        }

        $(function() {
            $('#btnSearch').click(function() {
                $('#tab_user').bootstrapTable('refresh');
            });

            $('#clearHref').click(function () {
                $('#myform')[0].reset();
            });
        });

        // 删除事件
        function downNews(val, id){
            var nam = "";
            if(val == 1){
                nam = "撤下";
            }else if(val == 2){
                nam = "显示";
            }
            $.confirm("确定要"+nam+"吗？ ", function(){
                $.post('updown.cs?val=' + val + '&id='+id,
                    function(result){
                        reflush('tab_user');
                    }
                );
            });
        }
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row row-lg">
        <div class="col-sm-12">
            <cs:toolbar title="新闻分类" tableId="tab_user" width="30%" height="60%" menuCode="${menuCode }" type="1">

            </cs:toolbar>
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
                    <th data-field="name" >名称</th>
                    <th data-field="state" data-formatter="getState">状态</th>
                    <th data-field="sort" >排序值</th>
                    <th data-field="modify_date" data-formatter="format_time">修改时间</th>
                    <th data-field="create_date" data-formatter="format_time">创建时间</th>
                </tr>
                </thead>
            </table>

        </div>

    </div>

    <!--               </div> -->
    <!--           </div> -->
</div>


</body>

</html>
