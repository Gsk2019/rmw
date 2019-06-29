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
            dataObj.state=$("#state").val();
            dataObj.type=$("#type").val();
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
                return "显示中";
            }else if(value == "2"){
                return "已撤下";
            }
            return "未知";
        }

        function getLinkType(value,row,index){
            if(value == "1"){
                return "图文";
            }else if(value == "2"){
                return "链接";
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

        // 上架或下架新闻
        function updown(value,row,index){
            if(row.state == '1'){
                return '<button class="btn btn-sm btn-warning" type="button" onclick="downNews(1,\''+row.id+'\')">撤下</button>';
            }else if(row.state == '2'){
                return '<button class="btn btn-sm btn-warning" type="button" onclick="downNews(2,\''+row.id+'\')">显示</button>';
            }
        }

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
            <div class="ibox-content m-b-sm border-bottom">
                <%--<div class="ibox-title">
                    <h5>查询条件</h5>
                </div>--%>
                <form class="form-inline" id="myform">
                    <div class="form-group"><label>&nbsp;</label><input id="title" name="title" class="form-control" placeholder="标题"></div>
                    <label>状态&nbsp;</label>
                    <select  class="form-control" id="state" >
                        <option value="">全部</option>
                        <option value="1">显示中</option>
                        <option value="2">已撤下</option>
                    </select>
                    <label>类型&nbsp;</label>
                    <select  class="form-control" id="type" >
                        <option value="">全部</option>
                        <option value="1">行业动态</option>
                        <option value="2">价格行情</option>
                        <option value="3">独家视角</option>
                    </select>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i> 搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>


            <cs:toolbar title="图文" tableId="tab_user" width="100%" height="100%" menuCode="${menuCode }" type="1">

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
                    <th data-field="title" >标题</th>
                    <th data-field="publisher" >发布者</th>
                    <th data-field="typeName" >新闻分类</th>
                    <th data-field="link_type" data-formatter="getLinkType" >新闻类型</th>
                    <th data-field="state" data-formatter="getState">状态</th>
                    <%--<th data-field="view_total" data-formatter="veiwHis">阅读数</th>--%>
                    <th data-field="sort" >排序值</th>
                    <th data-field="create_date" data-formatter="format_time">创建时间</th>
                    <th data-field="" data-formatter="updown">操作</th>
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
