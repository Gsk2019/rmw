<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="editMenuForm"/>
            <form class="form-horizontal" id="editMenuForm" action="edit.cs" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">上级菜单：</label>
                    <div class="col-sm-6">
                        <select class="form-control m-b" id="topId" name="topId" readonly>
                            <option value="0">请选择</option>
                            <c:forEach items="${menuList }" var="superMenu">
                                <option value="${superMenu.id }"
                                        <c:if test="${superMenu.id == category.topId }"> selected</c:if>
                                >${superMenu.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">名称：</label>
                    <div class="col-sm-6">
                        <input id="id" name="id" value="${category.id }" class="form-control" type="hidden"  >
                        <input id="categoryName" name="categoryName" value="${category.categoryName }" readonly class="form-control" validate="{required:true, minlength:1}" validateMessage="{required:'请输入品类名称'}"  >
                    </div>
                </div>


            </form>
        </div>
    </div>

</div>


</body>

</html>

