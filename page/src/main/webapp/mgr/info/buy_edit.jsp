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
    <cs:initFile/>
    <script type="text/javascript">

        var categoryarr2 = new Array()
        var pparr2 = new Array()
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
                    categoryarr2=data.categorylist;
                    pparr2=data.palist;
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
            for (var i = 0; i< categoryarr2.length; i++) {
                if (aaa[0]==categoryarr2[i].topId){
                    tip=2;
                    html+='<option value='+ categoryarr2[i].id+','+categoryarr2[i].categoryName+'>'+ categoryarr2[i].categoryName+'</option>';
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
            for (var i = 0; i< pparr2.length; i++) {
                if (aaa[0]==pparr2[i].topId){
                    tip=2;
                    html+='<option value='+ pparr2[i].id+','+pparr2[i].areaName+'>'+ pparr2[i].areaName+'</option>';
                }
            }
            html+='</select></div>';
            if (tip==2){
                $("#erjiCd").html(html);
            }else {
                $("#erjiCd").html(str);
            }
        }

        function  tijiao(){

            var categoryTopId=$("#categoryTopId").val();
            if (categoryTopId==-1){
                layer.alert("请选择品类");
                return;
            }

            var repertoryId=$("#repertoryId").val();
            if (repertoryId==-1){
                layer.alert("请选择仓库交割地");
                return;
            }
            var productTopAreaId=$("#productTopAreaId").val();
            if (productTopAreaId==-1){
                layer.alert("请选择产地");
                return;
            }
            $("#addUserForm").submit();
        }
    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="editBuy.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${info.id}" name="id"/>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购品类：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="categoryTopId" name="categoryTopName" onchange="getChange()">
                                <option value="-1">-请选择-</option>
                                <c:forEach items="${categorylist}" var="category">
                                    <c:if test="${category.topId eq 0}">
                                        <option <c:if test="${info.categoryTopId eq category.id}">selected="selected"</c:if> value="${category.id},${category.categoryName}">${category.categoryName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" id="erji">
                        <label class="col-sm-4 control-label">二级品类：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="categoryName" name="categoryName">
                                <c:if test="${info.categoryId ==null}">
                                    <option  value="">-无-</option>
                                </c:if>
                                <c:if test="${info.categoryId !=null}">
                                    <c:forEach items="${categorylist}" var="category">
                                        <c:if test="${category.topId eq info.categoryTopId}">
                                            <option <c:if test="${info.categoryId==category.id}">selected="selected"</c:if> value="${category.id},${category.categoryName}">${category.categoryName}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购产地：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="productTopAreaId" name="productTopArea" onchange="getChangeCd()">
                                <option  value="-1">-请选择-</option>
                                <c:forEach items="${palist}" var="pp">
                                    <c:if test="${pp.topId eq 0}">
                                        <option <c:if test="${pp.id eq info.productTopAreaId }">selected</c:if> value="${pp.id},${pp.areaName}">${pp.areaName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-6" id="erjiCd">
                        <label class="col-sm-4 control-label" id="productArea" name="productArea">二级产地：</label>
                        <div class="col-sm-8">
                            <select class="form-control">
                                <option  value="">-请选择-</option>
                                <c:forEach items="${palist}" var="pp">
                                    <c:if test="${pp.topId eq info.productTopAreaId}">
                                        <option <c:if test="${pp.id eq info.productAreaId }">selected</c:if> value="${pp.id},${pp.areaName}">${pp.areaName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购品牌：</label>
                        <div class="col-sm-8">
                            <input id="brand" name="brand" value="${info.brand}" class="form-control"  validate="{required:true,maxlength:20}" validateMessage="{required:'请输入品牌',maxlength:'输入内容超出长度限制'}">
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">仓库交割地：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="repertoryId" name="repertory">
                                <option  value="-1">-请选择-</option>
                                <c:forEach items="${rrlist}" var="rr">
                                    <option <c:if test="${rr.id eq info.repertoryId}">selected</c:if>  value="${rr.id},${rr.name}">${rr.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购数量：</label>
                        <div class="col-sm-8">
                            <input id="count" name="count" value="${info.count}" class="form-control" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" validate="{required:true}" validateMessage="{required:'请输入数量'}" placeholder="请输入求购数量，单位：吨">
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">求购价格：</label>
                        <div class="col-sm-8">
                            <input id="price" name="price" value="${info.price}" class="form-control" style="width: 47%;float:left" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" validate="{required:true}" validateMessage="{required:'请输入价格'}" placeholder="最低价"  ><span style="float: left">--</span> <input id="endPrice" name="endPrice" value="${info.endPrice}" class="form-control" style="width: 47%;float:left" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" validate="{required:true}" validateMessage="{required:'请输入价格'}" placeholder="最高价"  >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">有效期：</label>
                        <div class="col-sm-8">
                            <input id="expiryDate" name="expiryDate" value="${info.expiryDate}" readonly class="form-control" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" validate="{required:true}" validateMessage="{required:'请输入有效期'}" placeholder="请输入信息有效期"  >
                        </div>
                    </div>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">排序值：</label>
                        <div class="col-sm-8">
                            <input id="sort" name="sort" value="${info.sort}" class="form-control" oninput='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入数字，从小到大排序" validate="{required:true,maxlength:5}" validateMessage="{required:'请输入排序值',maxlength:'输入内容超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系人：</label>
                        <div class="col-sm-8">
                            <input id="contactName" name="contactName" value="${info.contactName}" class="form-control"  validate="{required:true,maxlength:20}" validateMessage="{required:'请输入联系人',maxlength:'输入的内容超出长度限制'}">
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">联系方式：</label>
                        <div class="col-sm-8">
                            <input id="contactTel" name="contactTel" value="${info.contactTel}" class="form-control" validate="{required:true,maxlength:20}" validateMessage="{required:'请输入联系方式',maxlength:'输入内容超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <c:if test="${info.status==1}">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-4 control-label">交易员：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="traderId" name="traderId">
                                    <c:forEach items="${tlist}" var="obj">
                                        <option  value="${obj.id}" <c:if test="${info.traderId eq obj.id}">selected="selected"</c:if>   >${obj.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">备注：</label>
                        <div class="col-sm-8">
                            <input id="note" name="note" value="${info.note}" class="form-control" placeholder="请输入备注" validate="{required:true,maxlength:255}" validateMessage="{required:'请输入备注',maxlength:'输入内容超出长度限制'}" >
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

