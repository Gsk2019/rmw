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


        function getCategory(value,row,index){
            if (row.category_name==null||row.category_name==""){
                return  row.category_top_name;
            }else {
                return  row.category_top_name+"-"+row.category_name;
            }
        }
        function getPrice(value,row,index){
            return  row.price+"-"+row.endPrice+'元';
        }

        function getState(value,row,index){
            if(value == "1"){
                return "已通过";
            }else if(value == "2"){
                return "待审核";
            }
            return "审核失败";
        }

        function queryParams(params){
            var page = params.offset / params.limit + 1;
            var dataObj = new Object();
            dataObj.publisher=$("#publisher").val();
            dataObj.categoryTopSelect=$("#categoryTopSelect").val();
            dataObj.categorySelect=$("#categorySelect").val();
            dataObj.status=$("#status").val();
            dataObj.startDate=$("#startDate").val();
            dataObj.endDate=$("#endDate").val();
            dataObj.type = 2;
            return  { page:page,
                pageSize:params.limit,
                search:JSON.stringify(dataObj),
                sort:params.sort,
                order:params.order,
                data:JSON.stringify(dataObj)
            };
        }

        $(function() {
            $('#categorySelect').hide();
            $('#btnSearch').click(function() {
                $('#tab_user').bootstrapTable('refresh');
            });
            $('#clearHref').click(function () {
                $('#myform')[0].reset();
                $('#categorySelect').hide();
                $('#categorySelect').html('<option value=""></option>');
            });
        });

        function getCategoryChange(){
            var value=$("#categoryTopSelect").val();
            var arr = new Array()
            arr['全脂奶粉'] = ['普通','热稳','速溶']
            arr['黄油'] = ['含盐','无盐']
            arr['乳清蛋白粉'] = ['wpc34','wpc60','wpc70','wpc80']
            arr['乳糖'] = ['40目','100目','200目']
            arr['乳清粉'] = ['高蛋白乳清粉','中蛋白乳清粉','低蛋白乳清粉','D40','D45','D50','D60','D70','D90']
            arr['液态奶'] = ['全脂','脱脂']

            if(value!=''&&arr[value]!=undefined){
                $('#categorySelect').show();
                var str='<option value="">全部</option>';
                for (var i = 0; i<arr[value].length; i++) {
                    str+="<option value="+arr[value][i]+">"+arr[value][i]+"</option>";
                }
                $('#categorySelect').html(str);
            }else{
                $('#categorySelect').hide();
            }
        }

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
                    <div class="form-group"><label>品类&nbsp;</label>
                        <select name="" class="form-control" id="categoryTopSelect" onchange="getCategoryChange()">
                            <option value="">全部</option>
                            <option value="全脂奶粉">全脂奶粉</option>
                            <option value="脱脂奶粉">脱脂奶粉</option>
                            <option value="黄油">黄油</option>
                            <option value="无水奶油">无水奶油</option>
                            <option value="淡奶油">淡奶油</option>
                            <option value="乳清蛋白粉">乳清蛋白粉</option>
                            <option value="乳糖">乳糖</option>
                            <option value="乳清粉">乳清粉</option>
                            <option value="液态奶">液态奶</option>
                        </select>
                        <select  class="form-control" id="categorySelect" >
                            <option value=""></option>
                        </select>
                        <label>状态&nbsp;</label>
                        <select  class="form-control" id="status" >
                            <option value="">全部</option>
                            <option value="1">已通过</option>
                            <option value="2">待审核</option>
                            <option value="3">审核拒绝</option>
                        </select>
                        <label>发布时间&nbsp;</label>
                        <input id="startDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="background-color: #ffffff">--
                        <input id="endDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="background-color: #ffffff">
                    </div>
                    <div class="form-group"><label>发布人&nbsp;</label><input id="publisher"  class="form-control" placeholder="发布人名字/账号"></div>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i>搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>

            <cs:toolbar title="信息" tableId="tab_user" width="70%" height="80%" menuCode="${menuCode }" type="2">
                <p:permission menuCode="${menuCode}" action="16">
                    <button class="btn btn-sm btn-success" type="button" onclick="doCheck()"><i class="fa fa-plus"></i>&nbsp;审核</button>
                </p:permission>
            </cs:toolbar>
            <table id="tab_user"
                   data-toggle="table"
                   data-url="list2.cs?type=2"
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
                    <th data-field="user_name">发布人</th>
                    <th data-field="phone" >发布账号</th>
                    <th data-field="category_top_name" data-formatter="getCategory">求购品类</th>
                    <th data-field="price" data-formatter="getPrice">求购价格</th>
                    <%--<th data-field="unit" >计量单位</th>--%>
                    <th data-field="repertory" >求购仓库交割地</th>
                    <th data-field="contact_name" >联系人</th>
                    <th data-field="contact_tel" >联系人电话</th>
                    <th data-field="trader_name" >交易员</th>
                    <th data-field="trader_phone" >交易员电话</th>
                    <th data-field="expiry_date" data-formatter="format_time">到期时间</th>
                    <th data-field="status" data-formatter="getState">状态</th>
                    <th data-field="sort" >排序值</th>
                    <th data-field="create_date" data-formatter="format_time">发布时间</th>
                    <%--<th data-field="" data-formatter="updown">操作</th>--%>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
