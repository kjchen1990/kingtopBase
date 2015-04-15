$(function(){
	$("#headerNav").load("header.html",function(data){
		onHeaderLoad();
	});
	//当头部加载完成
	function onHeaderLoad(){
		//控制主菜单的子菜单的显示或隐藏
		$("#header_menu ul li").each(function(index){
			$(this).mouseover(function(){
				if(typeof($("#subMenu div:eq(" + index + ")").html()) != "undefined" && $("#subMenu div:eq(" + index + ")").html() != ""){
					$("#subMenu").css("display","block");
					$("#subMenu div:eq(" + index + ")").css("display","block");
				}
			});
			$(this).mouseout(function(){
				$("#subMenu").css("display","none");
				$("#subMenu div:eq(" + index + ")").css("display","none");
			});
		});
		//点击登录，弹出登录框
		$("#loginBtn").click(function(event){
			$("#login-form").fadeIn(500);
			event.stopPropagation();
		});
		//点击其他地方，隐藏登录框
		$(document).click(function(event){
			var elem = event.target;  
	                  
	        while(elem)  
	        {   
	            if(elem.id == "login-form")  
	            {  
	                    return;  
	            }  
	            elem = elem.parentNode;       
	        }  
	        //隐藏div的方法  
	        $("#login-form").fadeOut(500);
		});
	}
});

