$(function(){
	
	(function ready(){
		//判断是否有错误内容，没有则隐藏，有则显示
		if($("#loginError").html() == "")
			$("#loginError").parent().css("display","none");
		else
			$("#loginError").parent().css("display","block");
		// 用户名和密码 
		$("#username").focusin(inputFocusin);
		$("#username").focusout(changeToop);
		$("#username").keyup(changeBorder);
		$("#password").focusin(inputFocusin);
		$("#password").focusout(changeToop);
		$("#password").keyup(changeBorder);
		
		function inputFocusin(){
			$(this).prev().css("display","none");
		}
		function changeToop(){
			if($(this).val() != "")
				$(this).prev().css("display","none");
			else
				$(this).prev().css("display","inline");
		}
		function changeBorder(){
			if($(this).val() != ""){
				$(this).parent().css("border-color","");
				if($("#loginError").text().toString() == "请将登录信息填写完整")
					$("#loginError").parent().css("display","none");
			}
		}
		
		//下次自动登录的文本
		$("#rememberMe").next().click(function(){
			$("#rememberMe").prop("checked",!$("#rememberMe").prop("checked"));
		});
		
		//点击登录，进行判断
		$("#login_btn").click(function(event){
			event.stopPropagation();
			event.preventDefault();
			/*var passwordVali=/^(?=.{6,16}$)(?![0-9]+$)(?!.*(.).*\1)[0-9a-zA-Z.,_]+$/;
			if(!passwordVali.test($("#password").val())){
				$("#loginError").html("密码格式错误");
			}*/
			var flag = true;
			if($("#username").val().replace(/[ ]/g,"") == ""){
				$("#loginError").html("请将登录信息填写完整");
				$("#loginError").parent().css("display","block");
				$("#username").parent().css("border-color","#ff0000");
				flag = false ;
			}
			if($("#password").val().replace(/[ ]/g,"") == ""){
				$("#loginError").html("请将登录信息填写完整");
				$("#loginError").parent().css("display","block");
				$("#password").parent().css("border-color","#ff0000");
				flag = false ;
			}
			if(!flag)
				return ;
			
			$("#loginForm").submit();
		});
	})();
	
});