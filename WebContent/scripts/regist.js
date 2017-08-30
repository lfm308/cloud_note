$(function(){
		
		$("#regist_button").click(function(){
			//清除表单信息
			$("#warning_1 span").html("");
			$("#warning_2 span").html("");
			$("#warning_3 span").html("");
			
			
			//获取表单信息
			var name=$("#regist_username").val().trim();
			var nickname=$("#nickname").val().trim();
			var password=$("#regist_password").val().trim();
			var final_pwd=$("#final_password").val().trim();
			
			//检测数据格式
			var ok=true;
			if(name==""){
				ok=false
				$("#warning_1 span").html("用户名不能为空");
				$("#warning_1").show();
			}
			
			if(password==""){
				ok=false
				$("#warning_2 span").html("密码不能为空");
				$("#warning_2").show();
			}
			if(final_pwd==""){
				ok=false
				$("#warning_3 span").html("密码不能为空");
				$("#warning_3").show();
			}else if(final_pwd!=password){
				ok=false
				$("#warning_3 span").html("输入密码不一致");
				$("#warning_3").show();
			}
			
			
			//发送ajax请求
			if(ok){
			$.ajax({
				url:"http://localhost:8080/cloudnote/user/regist.do",
				type:"post",
				data:{"name":name,"pwd":password,"nickname":nickname},
				dataType:"json",
				success:function(result){//回调
					if(result.status==0){
						alert(result.msg);
						$("#back").click();//出发返回按钮的单击，也会调用绑定的function
					}else if(result.status==1){//用户名被占用
						$("#warning_1 span").html(result.msg);
						
						$("#warning_1").show();//显示提示信息，jquery中的hide（）为隐藏
					}
				
				}
				
			});
			}
		});
		
	});
