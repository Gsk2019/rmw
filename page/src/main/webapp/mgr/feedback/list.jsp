<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<html>
<head>

    <%@ include file="../../common/head.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link href="<%=basePath%>/res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
    <script src="<%=basePath%>/res/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="<%=basePath%>/res/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=basePath%>/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script>
        function queryParams(params){
            var page = params.offset / params.limit + 1;
            var dataObj = new Object();
            dataObj.name=$('#name').val();
            return  { page:page,
                pageSize:params.limit,
                search:JSON.stringify(dataObj),
                sort:params.sort,
                order:params.order,
                data:JSON.stringify(dataObj)
            };
        }

        function opera(){
            var row = getSelectedRow("dcuser");
            if(null == row){
                layer.msg("请选择要操作的数据。");
                return;
            }
            if(row.status !="1"){
                layer.msg("反馈已处理。");
                return;
            }

            layer.open({
                title:'处理反馈',
                content: $('#hideSelect').html()
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>mgr/feedback/result.cs",
                        data: {"id": row.id, "result":$(".layui-layer-content textarea[name='result']").val()},
                        dataType: "json",
                        success: function(data){
                            layer.msg(data.data);
                            if(data.code == 200){
                                // return false
                                reflush('dcuser');
                            }
                        }
                    });
                }
                ,btn2: function(index, layero){
                    //按钮【按钮二】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
                ,cancel: function(){
                    //右上角关闭回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        }


        function getState(value,row,index){
            if(value == "1"){
                return "未解决";
            }else if(value == "2"){
                return "已解决";
            }
            return "未知";
        }

        function getContent(value,row,index){
            if(value.length>25){
                return value.substring(0,25)+"......";
            }else{
                return value;
            }
        }

        function strForma(value,row,index){
            if(value == "" || value == null){
                return "--";
            }else {
                return value;
            }
        }


        function resultDate(value,row,index){
            if(row.status == "1"){
                return "--";
            }
            return format_time(value,row,index);
        }

        // 删除事件
        function del(){
            var ids = [];
            var param_id  = '';

            $($('#' + "dcuser").bootstrapTable('getSelections')).each(function() {
                param_id += this.id + ',';
            });

            if(param_id.length == 0) return;

            param_id = param_id.substr(0, param_id.length - 1);

            $.confirm("确定删除吗？ ", function(){
                $.post('del.cs?ids=' + param_id,
                    function(result){
// 						window.location.reload();
                        reflush('dcuser');
                    }
                );
            });

        }

        $(function() {
            $('#btnSearch').click(function() {
               /* if($('#name').val().length==0)
                {
                    $.tips("请输入检索条件！");
                    return false;
                }*/
                $('#dcuser').bootstrapTable('refresh');
            });

            $('#clearHref').click(function () {
                $('#myform')[0].reset();
            });
        });

        // 预览事件
        function view(){
            var data = getSelectedRow("dcuser");
            if(null == data){
                return;
            }
            $.openDlg({
                url:'transfer.cs?action=view&id=' + data.id,
                title:'查看反馈',
                width:'60%',
                height:'80%',
            });
        }
    </script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp">
    <!--           <div class="ibox " style=""> -->
    <!--               <div class="ibox-content"> -->
    <div class="row row-lg">

        <div class="form-group" id="hideSelect" style = "display:none;">
            <label>&nbsp;&nbsp;解决结果：</label>
            <br>
            <textarea rows="5" cols="22"  name="result" ></textarea>

        </div>

        <div class="col-sm-12">

            <div class="ibox-content m-b-sm border-bottom">
                <div class="ibox-title">
                    <h5>查询条件</h5>
                </div>
                <form class="form-inline" id="myform">
                    <div class="form-group"><label>&nbsp;</label><input id="name" name="name" class="form-control" placeholder="内容"></div>
                    <label><a href="#" id="btnSearch" class="btn btn-primary"><i class="icon-search"></i> 搜索</a></label>&nbsp;&nbsp;
                    <label><a href="#" id="clearHref" class="btn btn-primary"><i class="icon-search"></i> 清除搜索条件</a></label>
                </form>
            </div>

            <p:permission menuCode="${menuCode}" action="1">
                <button class="btn btn-sm btn-success" type="button" onclick="opera()"><i class="fa fa-plus"></i>&nbsp;处理</button>
            </p:permission>
            <p:permission menuCode="${menuCode}" action="2">
                <button class="btn btn-sm btn-danger" type="button" onclick="del()"><i class="fa fa-minus"></i> 删除</button>
            </p:permission>
            <p:permission menuCode="${menuCode}" action="4">
                <button class="btn btn-sm btn-info" type="button" onclick="view()"><i class="fa fa-minus"></i> 查看</button>
            </p:permission>
            <table id="dcuser"
                   data-toggle="table"
                   data-url="list2.cs"
                   data-method="get"
                   data-click-to-select="true"
                   data-pagination="true"
                   data-data-type="json"
                   data-show-refresh="true"
                   data-show-columns="true"
                   data-show-toggle="true"
                   data-single-select="true"
                   data-search-on-enter-key="true"
                   data-toolbar="#def_toolbar"
                   data-side-pagination="server"
                   data-query-params="queryParams"
                   data-mobile-responsive="true"
            >
                <thead>
                <tr>
                    <th data-checkbox="true" data-click-to-select="true"></th>
                    <th data-field="id" data-visible="false">id</th>
                    <th data-field="user_name" >反馈人</th>
                    <th data-field="phone" >电话</th>
                    <th data-field="content" class="col-md-4" data-formatter="getContent">反馈内容</th>
                    <th data-field="status" data-formatter="getState">反馈状态</th>
                    <th data-field="result" class="col-md-4">处理结果</th>
                    <th data-field="create_date" data-formatter="format_time">反馈时间</th>
                    <th data-field="modify_date" data-formatter="resultDate">解决时间</th>
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
