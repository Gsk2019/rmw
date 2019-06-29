<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addLogisticsForm"/>
            <form class="form-horizontal" action="check.cs" id="addLogisticsForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${newsComment.id }" name="id"/>
                <div class="form-group col-sm-12">

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">请选择：</label>
                        <div class="col-sm-8">
                            <label class="radio-inline"  >
                                <input type="radio"  value="1" name="state" <c:if test="${newsComment.state eq 1}">checked</c:if>>通过
                            </label>
                            <label class="radio-inline" >
                                <input type="radio"  value="3" name="state" <c:if test="${newsComment.state eq 3}">checked</c:if>>不通过
                            </label>
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

