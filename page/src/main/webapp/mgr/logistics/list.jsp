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
            dataObj.title=$("#title").val();
            dataObj.type = 1;
            dataObj.status = 2;
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

        // 删除事件
        function del(){
            var ids = [];
            var param_id  = '';

            $($('#' + "tab_user").bootstrapTable('getSelections')).each(function() {
                ids.push(this.id);
                param_id += this.id + ',';
            });
            if(param_id.length == 0) return;
            param_id = param_id.substr(0, param_id.length - 1);
            if(ids.length == 0){
                return;
            }
            else if(ids.length == 1){
                param_id = ids[0];
            }
            else{
                param_id = JSON.stringify(ids);

                for(var i = 0; i < ids.length; i++){
                    param_id += ids[i] + ',';
                }
                param_id = param_id.substr(0, param_id.length - 1);
            }
            $.confirm("确定删除吗？ ", function(){
                $.post('del.cs?id=' + param_id,
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

            <%--<div class="ibox-content m-b-sm border-bottom">
                <div class="ibox-title">
                    <h5>查询条件</h5>
                </div>
                <form class="form-inline" id="myform">
                    <div class="form-group"><label>&nbsp;</label><input id="title" name="title" class="form-control" placeholder="关键字"></div>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i>搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>--%>

            <cs:toolbar title="物流" tableId="tab_user" width="70%" height="80%" menuCode="${menuCode }" type="1"></cs:toolbar>


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
                    <th data-field="linkMan">联系人</th>
                    <th data-field="phone" >联系方式</th>
                    <th data-field="startPoint" >起点</th>
                    <th data-field="endPoint" >终点</th>
                    <th data-field="createDate" data-formatter="format_time">创建时间</th>
                    <%--<th data-field="" data-formatter="updown">操作</th>--%>
                </tr>
                </thead>
            </table>

        </div>

    </div>

</div>
</body>
</html>
