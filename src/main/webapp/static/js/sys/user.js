function login(){
	var xm=$("#ctx").val();
	
	var username=$("#username").val();
	var password=$("#password").val();
	var googleCode=$("#googleCode").val();
	
	if(username==null||username==''){
		alert("请输入用户名");
		return;
	}
	if(password==null||password==''){
		alert("请输入密码");
		return;
	}
	if(googleCode==null||googleCode==''){
		alert("请输入验证码");
		return;
	}
	var url = xm+"/user/checkCode";
	var params = {
	   'username':username,
	   'googleCode':googleCode
	};
	$.post(url, params, checkCodeResult, 'json');
}

function checkCodeResult(json){
	console.log(json);
	var result = json.res;
	result='yes';
	if(result=='yes'){
		$("#login_form").submit();
	}else{
		$("#msg").text(json.msg);
	}
}
//检验用户名是否重复
function checkUsername1(){
	var xm=$("#ctx").val();
	var str=$("#username").val();
	
	if(str==null||str==''){
		alert("请输入用户名");
		return;
	}
	var url = xm+"/user/checkUsername";
	var params = {
	   'username':str
	};
	$.ajax({
        "type":"post",
        "data":JSON.stringify(params),
        "contentType": "application/json; charset=utf-8",
        "url" : url,
        "dataType":"json",
        "success":checkResult
    });

	//$.post(url, params, checkResult, 'json');
}

function checkResult(json){
	console.log(json);
	if(json.res!='ok'){
		alert("用户名重复，请换一个！");
		$("#username").val("");
	}
}

function register1(){
	var xm=$("#ctx").val();
	
	var username=$("#username").val();
	var password=$("#password").val();
	var password2=$("#password2").val();
	
	if(username==null||username==''){
		alert("请输入用户名");
		return;
	}
	if(password==null||password==''){
		alert("请输入密码");
		return;
	}
	if(password2==null||password2==''){
		alert("请输入重复密码");
		return;
	}
	
	if(password!=password2){
		alert("两次输入的密码不相同");
		return;
	}
	
	$("#register_form").submit();
}
