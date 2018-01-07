<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<title>test</title>
</head>
<body>

      <!--main content start-->

          <section class="wrapper">

              <div class="row">
              	
	  			<div class="container">
	  	

				      <form class="form-login" action="login.do">
				        <h2 class="form-login-heading">員工登入</h2>
				        <div class="login-wrap">
				        	<center>
				        		<img alt="維修中" src="resources/img/head.png"  class="img-circle">
				        	<center/>
				        	<br>
				            <input type="text" class="form-control" placeholder="User ID" autofocus name="emp_id">
				            <br>
				            <input type="password" class="form-control" placeholder="Password" name="emp_pwd">
				            <label class="checkbox">
				                <span class="pull-right">
				                    <a data-toggle="modal" href="login.html#myModal"> 忘記密碼?</a>
				
				                </span>
				            </label>
				            <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> SIGN IN</button>				           	           				
				        </div>
				
				          <!-- Modal -->
				          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
				              <div class="modal-dialog">
				                  <div class="modal-content">
				                      <div class="modal-header">
				                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                          <h4 class="modal-title">Forgot Password ?</h4>
				                      </div>
				                      <div class="modal-body">
				                      	  <p>輸入您的e-mail來重設您的帳號.</p>
				                          <p>Enter your e-mail address below to reset your password.</p>
				                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
				
				                      </div>
				                      <div class="modal-footer">
				                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
				                          <button class="btn btn-theme" type="button">Submit</button>
				                      </div>
				                  </div>
				              </div>
				          </div>
				          <!-- modal -->
				
				      </form>	  		  	
	  			</div>
                
              </div><!--/row -->
          </section>
</body>
</html>