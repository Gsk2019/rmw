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
    <script src="<%=basePath%>res/dist/js/city-picker.data.js"></script>
    <script src="<%=basePath%>res/dist/js/city-picker.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>res/dist/css/city-picker.css"/>

    <cs:initFile/>
    <script type="text/javascript">

    </script>
    <script>
        var categoryarr = new Array()
        var pparr = new Array()
        $(function (){
            initData();
        })

        function  initData(){
            $.ajax({
                type: "POST",
                url: "<%=basePath%>mgr/info/getCategoryByTopId.cs",
                data: {},
                dataType: "json",
                success: function(data){
                    categoryarr=data.categorylist;
                    pparr=data.palist;
                }
            });
        }

        function  tijiao(){

            var phone=$("#userPhone").val();
            if (phone==""){
                layer.alert("用户登录账号不能为空");
                return;
            }

            var categoryTopId=$("#categoryTopId").val();
            if (categoryTopId==-1){
                layer.alert("请选择品类");
                return;
            }
            var productTopAreaId=$("#productTopAreaId").val();
            if (productTopAreaId==-1){
                layer.alert("请选择产地");
                return;
            }

            var repertoryId=$("#repertoryId").val();
            if (repertoryId==-1){
                layer.alert("请选择仓库交割地");
                return;
            }

            var unit=$("#unit").val();
            if (unit==-1){
                layer.alert("请选择单件规格");
                return;
            }

            $.ajax({
                type: "POST",
                url: "<%=basePath%>mgr/info/isExitUser.cs",
                data: {"phone":phone},
                dataType: "json",
                success: function(data){
                    if(data.code==500){
                        layer.alert("用户不存在");
                    }else{
                        $("#addUserForm").submit();
                    }
                }
            });
        }

        function  getChange(){
            var str='<label class="col-sm-4 control-label">二级品类：</label><div class="col-sm-8"> <select class="form-control"> <option  value="">-无-</option> </select> </div>';
            var html='<label class="col-sm-4 control-label">二级品类：</label><div class="col-sm-8">'+
            '<select class="form-control" id="categoryName" name="categoryName">';
            var topid=$("#categoryTopId").val();
            var tip=1;
            var aaa = topid.split(',');
            for (var i = 0; i< categoryarr.length; i++) {
                if (aaa[0]==categoryarr[i].topId){
                    tip=2;
                    html+='<option value='+ categoryarr[i].id+','+categoryarr[i].categoryName+'>'+ categoryarr[i].categoryName+'</option>';
                }
            }
            html+='</select></div>';

            if (tip==2){
                $("#erji").html(html);
            }else {
                $("#erji").html(str);
            }
        }

        function  getChangeCd(){
            var str='<label class="col-sm-4 control-label">二级产地：</label><div class="col-sm-8"><select class="form-control"><option  value="">-请选择-</option></select></div>';
            var html='<label class="col-sm-4 control-label">二级产地：</label><div class="col-sm-8">'+
                '<select class="form-control" id="productArea" name="productArea">';
            var topid=$("#productTopAreaId").val();
            var aaa = topid.split(',');
            var tip=1;
            for (var i = 0; i< pparr.length; i++) {
                if (aaa[0]==pparr[i].topId){
                    tip=2;
                    html+='<option value='+ pparr[i].id+','+pparr[i].areaName+'>'+ pparr[i].areaName+'</option>';
                }
            }
            html+='</select></div>';
            if (tip==2){
                $("#erjiCd").html(html);
            }else {
                $("#erjiCd").html(str);
            }
        }


    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="add.cs" id="addUserForm" method="POST" onsubmit="return false;">

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">用户登录账号：</label>
                        <div class="col-sm-8">
                            <input id="userPhone" name="traderPhone" class="form-control"  validate="{required:true,maxlength:11}" validateMessage="{required:'请输入',maxlength:'输入的内容超出长度限制'}">
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">选择品类：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="categoryTopId" name="categoryTopName" onchange="getChange()">
                                <option  value="-1">-请选择-</option>
                                <c:forEach items="${categorylist}" var="category">
                                    <c:if test="${category.topId eq 0}">
                                        <option  value="${category.id},${category.categoryName}">${category.categoryName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" id="erji">
                        <label class="col-sm-4 control-label">二级品类：</label>
                        <div class="col-sm-8">
                            <select class="form-control">
                                <option  value="">-请选择-</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">选择产地：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="productTopAreaId" name="productTopArea" onchange="getChangeCd()">
                                <option  value="-1">-请选择-</option>
                                <c:forEach items="${palist}" var="pp">
                                    <c:if test="${pp.topId eq 0}">
                                        <option  value="${pp.id},${pp.areaName}">${pp.areaName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" id="erjiCd">
                        <label class="col-sm-4 control-label">二级产地：</label>
                        <div class="col-sm-8">
                            <select class="form-control">
                                <option  value="">-请选择-</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">品牌：</label>
                        <div class="col-sm-8">
                            <input id="brand" name="brand" class="form-control"  validate="{required:true,maxlength:20}" validateMessage="{required:'请输入品牌',maxlength:'输入内容超出长度限制'}">
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">仓库交割地：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="repertoryId" name="repertory">
                                <option  value="-1">-请选择-</option>
                                <c:forEach items="${rrlist}" var="rr">
                                    <option  value="${rr.id},${rr.name}">${rr.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">价格：</label>
                        <div class="col-sm-8">
                            <input id="price" name="price" class="form-control" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" validate="{required:true}" validateMessage="{required:'请输入价格'}" placeholder="请输入价格"  >
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">可供数量：</label>
                        <div class="col-sm-8">
                            <input id="count" name="count" class="form-control" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" validate="{required:true}" validateMessage="{required:'请输入数量'}" placeholder="请输入可供数量，单位：吨">
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">生产日期：</label>
                        <div class="col-sm-8">
                            <input id="productDate" name="productDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" validate="{required:true}" validateMessage="{required:'请输入生产日期'}" placeholder="请输入生产日期"  >
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">有效期：</label>
                        <div class="col-sm-8">
                            <input id="expiryDate" name="expiryDate"  readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" validate="{required:true}" validateMessage="{required:'请输入生产日期'}" placeholder="请输入生产日期"  >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">单件规格：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="unit" name="unit" required="true">
                                <option  value="-1">-请选择-</option>
                                <option  value="13.5kg/件">13.5kg/件</option>
                                <option  value="20kg/件">20kg/件</option>
                                <option  value="25kg/件">25kg/件</option>
                                <option  value="210kg/件">210kg/件</option>
                                <option  value="1L*12/件">1L*12/件</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">排序值：</label>
                        <div class="col-sm-8">
                            <input id="sort" name="sort" value="0" class="form-control" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入数字，从小到大排序" validate="{required:true,maxlength:5}" validateMessage="{required:'请输入排序值',maxlength:'输入内容超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系人：</label>
                        <div class="col-sm-8">
                            <input id="contactName" name="contactName" class="form-control"  validate="{required:true,maxlength:20}" validateMessage="{required:'请输入联系人',maxlength:'输入的内容超出长度限制'}">
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系方式：</label>
                        <div class="col-sm-8">
                            <input id="contactTel" name="contactTel"  class="form-control" validate="{required:true,maxlength:11}" validateMessage="{required:'请输入联系方式',maxlength:'输入内容超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">备注：</label>
                        <div class="col-sm-8">
                            <input id="note" name="note" class="form-control" value=""  validate="{required:false,maxlength:200}" placeholder="请输入备注" validateMessage="{required:'请输入备注',maxlength:'输入的备注超出长度限制'}">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="button" onclick="tijiao()" >提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>

</html>

