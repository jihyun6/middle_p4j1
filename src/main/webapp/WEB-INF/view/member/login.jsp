<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	path = function(){
		let frm = $('#frm');
		postPath('/login.do', frm);
	}
})
</script>

<style type="text/css">
#frm {
/* 	padding-left: 100px; */
}

</style>

</head>
<body>
	<div class="d-flex justify-content-center">
		<div class="col-md-10 mx-auto col-lg-5">
			<form id="frm" class="p-4 p-md-5 border rounded-3 bg-light">
				<div class="form-floating mb-3">
					<input type="email" class="form-control" id="id" name="id"
						placeholder="name@example.com"> <label for="floatingInput">Username</label>
				</div>
				<div class="form-floating mb-3">
					<input type="password" class="form-control" id="pw" name="pw"
						placeholder="Password"> <label for="floatingPassword">Password</label>
				</div>
				<button class="w-100 btn btn-lg btn-primary" type="button" onclick="path()">Login</button>
				<p><p>
				<div class="col-12">
                  <p class="m-0 text-secondary text-center">회원정보가 없으십니까? 
                  <a href="#" onclick="getPath('/memberInsert.do')" class="link-primary text-decoration-none">Sign up</a></p>
                </div>
			</form>
		</div>
	</div>
</body>
</html>