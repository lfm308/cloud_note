//注意浏览器会缓存js文件，更改js文件的话要清理下浏览器的缓存才行
$(function(){
		//给登录按钮追加单击处理
		$('#login').click(function(){
			
			//清除上次数据,提示信息
			$("#count_msg").html("");
			$("#pwd_msg").html("");
			
			//获取请求数据
			var name=$("#count").val().trim();
			var pwd=$("#password").val().trim();
			
			//检查数据格式,为空的话不发送ajax请求
			var ok=true;
			if(name==""){
				$("#count_msg").html("用户名不能为空");
				ok=false;
			}
			if(pwd==""){
				$("#pwd_msg").html("密码不能为空");
				ok=false;
			}
			
			//发送Ajax请求
			if(ok){
			$.ajax({
				url:"http://localhost:8080/cloudnote/user/login.do",
				type:"post",
				data:{"name":name,"pwd":pwd},
				dataType:"json",
				success:function(result){
					//result服务器返回的json结果，返回类型为json对象
					
					if(result.status==0){//成功
						//获取用户id写入cookie
						var userId=result.data;
					//用js文件中提供的方法写入cookie,session有绘画状态，必须用同一个服务器处理，为了
						//避免这个问题，所以把数据保存到cookie中
						addCookie("uid",userId,2);
						
						window.location.href="edit.html";
					}
					if(result.status==1){//用户名错误
						$("#count_msg").html(result.msg);
					}
					if(result.status==2){//密码错
						$("#pwd_msg").html(result.msg);
					}
				},
				error:function(){
					alert("登录异常");
				}
			});		
		}//end if
		});
	});