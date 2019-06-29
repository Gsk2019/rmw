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

        //获取分类
        function getCategory(value,row,index){
            return  row.category_top_name+"-"+row.category_name;
        }
        //获取状态
        function getStatus(value,row,index){
            if(value == 1){
                return "待处理";
            }else if(value == 2){
                return "已处理";
            }
            return "--";
        }

        function queryParams(params){
            var page = params.offset / params.limit + 1;
            var dataObj = new Object();
            dataObj.status=$("#status").val();
            dataObj.startDate=$("#startDate").val();
            dataObj.endDate=$("#endDate").val();
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

        // 审核
        function doCheck(){
            var data = getSelectedRow("tab_user");
            if(null == data){
                return;
            }
            $.openDlg({
                url:'transfer.cs?action=check&id=' + data.id,
                title:' 审核',
                width:'40%',
                height:'50%',
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
                    <div class="form-group">
                        <label>状态&nbsp;</label>
                        <select  class="form-control" id="status" >
                            <option value="">全部</option>
                            <option value="1">待处理</option>
                            <option value="2">已处理</option>
                        </select>
                        <label>委托时间&nbsp;</label>
                        <input id="startDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="background-color: #ffffff">--
                        <input id="endDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="background-color: #ffffff">
                    </div>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i>搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>

            <cs:toolbar title="信息" tableId="tab_user" width="80%" height="70%" menuCode="${menuCode }" type="1">
                <p:permission menuCode="${menuCode}" action="16">
                    <button class="btn btn-sm btn-success" type="button" onclick="doCheck()"><i class="fa fa-plus"></i>&nbsp;处理</button>
                </p:permission>
            </cs:toolbar>

            <table id="tab_user"
                   data-toggle="table"
                   data-url="list2.cs?type=1"
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
                    <th data-field="user_name">委托人</th>
                    <th data-field="uicount" >委托数量</th>
                    <th data-field="phone" >委托人手机</th>
                    <th data-field="status" data-formatter="getStatus">状态</th>
                    <th data-field="category_top_name" data-formatter="getCategory">品类</th>
                    <th data-field="count" >数量</th>
                    <th data-field="product_area" >产地</th>
                    <th data-field="repertory" >仓库交割地</th>
                    <th data-field="contact_name" >联系人</th>
                    <th data-field="contact_tel" >联系人电话</th>
                    <th data-field="trader_name" >交易员</th>
                    <th data-field="trader_phone" >交易员电话</th>
                    <th data-field="expiry_date" data-formatter="format_date">到期时间</th>
                    <th data-field="create_date" data-formatter="format_time">委托时间</th>
                    <%--<th data-field="" data-formatter="updown">操作</th>--%>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
