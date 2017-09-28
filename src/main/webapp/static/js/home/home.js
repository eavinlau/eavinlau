function submitForm(){
	var str=$("#search").val();
	if(str==null||str==''){
		alert("请输入查询内容！");
		return;
	}
	$("#search_form").submit();
}

function saveForm(){
	var str=$("#title").val();
	if(str==null||str==''){
		alert("请输入标题！");
		return;
	}
	$("#save_form").submit();
}
function checkTitle(){
	var str=$("#title").val();
	var xm=$("#ctx").val();
	var url = xm+"/home/checkTitle";
	var params = {
	   'title':str
	};
	$.post(url, params, checkResult, 'json');
}

function checkResult(json){
	console.log(json);
	if(json.res!='ok'){
		alert("标题重复，请换一个！");
		$("#title").val("");
	}
}