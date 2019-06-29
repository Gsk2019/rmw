<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="../res/js//plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="../res/css/demo/webuploader-demo.css">
    <script type="text/javascript" src="../res/js/plugins/webuploader/webuploader.js"></script>
    <script type="text/javascript" src="../res/js/plugins/webuploader/uploader.js"></script>
	<cs:initFile/>
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="addUserForm"/>
                  <form class="form-horizontal" action="add.cs" id="addUserForm" method="POST">
<!--                      <div class="form-group"> -->
<!--                           <label class="col-sm-3 control-label">类型：</label> -->
<!--                           <div class="col-sm-6"> -->
<!--                               <input id="type" name="type"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入类型'}" > -->
<!--                           </div> -->
<!--                       </div> -->
                      <div class="form-group">
                          <label class="col-sm-3 control-label">工号：</label>
                          <div class="col-sm-6">
                              <input id="code" name="code"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入code,不能重复'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">登录账号：</label>
                          <div class="col-sm-6">
                              <input id="account" name="account" class="form-control" validate="{required:true}" validateMessage="{required:'请输入账号'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">密码：</label>
                          <div class="col-sm-6">
                              <input id="pwd" name="pwd" class="form-control" validate="{required:true}" validateMessage="{required:'请输入密码'}" >
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-sm-3 control-label">名称：</label>
                          <div class="col-sm-6">
                              <input id="name" name="name" class="form-control" validate="{required:true}" validateMessage="{required:'请输入名称'}" >
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-sm-3 control-label">手机号码：</label>
                          <div class="col-sm-6">
                              <input id="phone" name="phone" class="form-control" validate="{required:true}" validateMessage="{required:'请输入手机号码'}" >
                          </div>
                      </div>

                     <div class="form-group">
                          <label class="col-sm-3 control-label">性别：</label>
                          <div class="col-sm-6">
                          		<select  class="form-control" name="sex">
                          			<option value="1">男</option>
                          			<option value="2">女</option>
                          		</select>
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">头像：</label>
                          <div class="col-sm-6">
                              <cs:file name="licessePic" type="100"></cs:file>
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">描述：</label>
                          <div class="col-sm-6">
                              <input id="remark" name="remark" class="form-control"  >
                          </div>
                      </div>
                     
<!--                       <div class="form-group"> -->
<!--                           <label class="col-sm-3 control-label">：</label> -->
<!--                           <div class="col-sm-6"> -->
<!--                               <input id="weight" name="weight"  class="form-control" > -->
<!--                           </div> -->
<!--                       </div> -->
                      <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3 pull-left">
                                    <button class="btn btn-default btn-dlg-close">取消</button>
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                       </div>
                      
                  </form>
            </div>
        </div>

    </div>

</body>

</html>

