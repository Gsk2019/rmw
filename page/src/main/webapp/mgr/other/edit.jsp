<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <cs:form_validate formId="addUserForm"/>
            <form class="form-horizontal" action="edit.cs" id="addUserForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${other.id}" name="id"/>
                <div class="form-group col-sm-12">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">数值：</label>
                        <div class="col-sm-8">
                            <input id="num" name="num" value="${other.num }" class="form-control" validate="{required:true,maxlength:50}" validateMessage="{required:'请输入价格',maxlength:'输入超出长度限制'}" >
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="submit" >提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

