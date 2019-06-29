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
            dataObj.state=$("#state").val();
            return  { page:page,
                pageSize:params.limit,
                search:JSON.stringify(dataObj),
                sort:params.sort,
                order:params.order,
                data:JSON.stringify(dataObj)
            };
        }

        function getState(value,row,index){
            if(value == "1"){
                return "已通过";
            }else if(value == "2"){
                return "待审核";
            }
            return "未通过";
        }


        $(function() {
            $('#btnSearch').click(function() {
                $('#tab_user').bootstrapTable('refresh');
            });

            $('#clearHref').click(function () {
                $('#myform')[0].reset();
            });
        });

        // 审核
        function doCheck(){
            var data = getSelectedRow("tab_user");
            if(null == data){
                return;
            }
            $.openDlg({
                url:'transfer.cs?action=check&id=' + data.id,
                title:' 审核',
                width:'20%',
                height:'35%',
            });
        }

    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row row-lg">
        <div class="col-sm-12">
            <div class="ibox-content m-b-sm border-bottom">
                <%--<div class="ibox-title">
                    <h5>查询条件</h5>
                </div>--%>
                <form class="form-inline" id="myform">
                    <%--<div class="form-group"><label>&nbsp;</label><input id="title" name="title" class="form-control" placeholder="标题"></div>--%>
                    <label>状态&nbsp;</label>
                    <select  class="form-control" id="state" >
                        <option value="">全部</option>
                        <option value="1">已通过</option>
                        <option value="2">待审核</option>
                        <option value="2">审核失败</option>
                    </select>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i> 搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>

            <cs:toolbar title="评论" tableId="tab_user" width="20%" height="30%" menuCode="${menuCode }" type="1">
                <p:permission menuCode="${menuCode}" action="16">
                    <button class="btn btn-sm btn-success" type="button" onclick="doCheck()"><i class="fa fa-plus"></i>&nbsp;审核</button>
                </p:permission>
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
                    <th data-field="title" >新闻标题</th>
                    <th data-field="user_name" >评论者</th>
                    <th data-field="content" >评论内容</th>
                    <th data-field="state" data-formatter="getState">状态</th>
                    <th data-field="create_date" data-formatter="format_time">评论时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
