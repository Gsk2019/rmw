<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
    <script type="text/javascript">

        $(function () {
            var status=$("#statusValue").val();
            if (status==1){
                $("#noteTab").hide();
            }else if(status==2){
                $("#noteTab").show();
            }
        })

        function  getTab(value){
            if (value==1){
                $("#noteTab").hide();
            }else {
                $("#noteTab").show();
            }
        }
    </script>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <cs:form_validate formId="addLogisticsForm"/>
            <form class="form-horizontal" action="check.cs" id="addLogisticsForm" method="POST" onsubmit="return false;">
                <input type="hidden" value="${info.id }" name="id"/>
                <div class="form-group col-sm-12">

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">请选择：</label>
                        <div class="col-sm-8">
                            <label class="radio-inline" onclick="getTab(2)"  >
                                <input type="radio"  value="2" name="status" <c:if test="${info.status eq 2}">checked</c:if>>已处理
                            </label>
                            <label class="radio-inline" onclick="getTab(1)" >
                                <input type="radio"  value="1" name="status" <c:if test="${info.status eq 1}">checked</c:if>>未处理
                            </label>
                        </div>
                    </div>

                    <input type="hidden"  id="statusValue" value="${info.status}">
                    <div class="form-group col-sm-6" id="noteTab">
                        <label class="col-sm-4 control-label">处理备注：</label>
                        <div class="col-sm-8">
                            <input id="note" name="note" value="${info.note}" class="form-control"  >
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

