function shareNote(){
					//获取noteId
					$li=$("#note_list a.checked").parent();
					var noteId=$li.data("noteId");
					
					//发送ajax请求
					$.ajax({
						url:"http://localhost:8080/cloudnote/share/sharenote.do",
						type:"post",
						data:{"noteId":noteId},
						resultType:"json",
						success:function(result){
							if(result.status==0){
								alert(result.msg);
							}
							if(result.status==1){
								alert(result.msg);
							}
						},
						error:function(){
							alert("分享笔记异常");
						}
						
					});
					
				}

//将笔记移入回收站
function updateStatus(){
					//获取noteId
					$li=$("#note_list a.checked").parent();
					var noteId=$li.data("noteId");
					//发送ajax请求
					$.ajax({
						url:"http://localhost:8080/cloudnote/note/updatestatus.do",
						type:"post",
						data:{"noteId":noteId},
						resultType:"json",
						success:function(result){
							if(result.status==0){//删除成功
								//删除笔记列表数据
								$li.remove();
							//清空编辑区域
							$("#input_note_title").val("");
							um.setContent("");
								alert(result.msg);
								
								
							}
							
							
						},
						error:function(){
							alert("删除笔记异常");
						}
						
					});
					
				}

//显示笔记下拉菜单
function showNoteMenu(){
					//隐藏其他笔记菜单
					$("#note_list .note_menu").hide();
					//button父元素们下的li
					var $li=$(this).parents("li");//获取所点击的笔记li
					var $menu=$li.find(".note_menu");//获取菜单div
					//显示菜单
					$menu.show();
					
				}
//单击笔记本，加载笔记列表          
         function loadnotes(){
        	 
        	    $("#pc_part_2").show();
				$("#pc_part_6").hide();
        	 
					//给笔记本li设置选中样式
					$("#book_list li a").removeClass("checked");
					$(this).find("a").addClass("checked");
					
					//获取bookID,this为原有的js对象
					var bookId=$(this).data("bookId");
					//发送请求
					$.ajax({
						url:"http://localhost:8080/cloudnote/note/loadnotes.do",
						type:"post",
						data:{"bookId":bookId},
						resultType:"json",
						success:function(result){
							var notes=result.data;//获取回调结果
							//清除原有笔记列表
							$("#note_list").empty();
							for(var i=0;i<notes.length;i++){
								var noteId=notes[i].cn_note_id;
								var noteTitle=notes[i].cn_note_title;
								//拼成笔记li列表
								var s_li='<li class="online">';
								 s_li+=' <a> ';
								s_li+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								s_li+='</a>';
								s_li+='<div class="note_menu" tabindex="-1">';
								s_li+='<dl>';
								s_li+='   <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
								s_li+='   <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
								s_li+='    <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
								s_li+='</dl>';
								s_li+='</div>';
								s_li+='</li>';

								var $li=$(s_li);
								$li.data("noteId",noteId);//给li绑定笔记id
								
							//将笔记li添加到笔记ul中
							$("#note_list").append($li);
								
							}
						},
						error:function(){
							alert("加载笔记列表异常");
						}
					});
					
					
				}
         //弹出添加笔记本对话框
         function showAddBookWindow(){
				$(".opacity_bg").show();//显示背景
				//发出请求，将返回的结果加入发哦can的div中，参数（url,data,callback载入成功时回调函数）
				$("#can").load("alert/alert_notebook.html");
				
			}
         
         //弹出添加笔记对话框
         function showAddNoteWindow(){
				$(".opacity_bg").show();//显示背景
				//发出请求，将返回的结果加入发哦can的div中，参数（url,data,callback载入成功时回调函数）
				$("#can").load("alert/alert_note.html");
				
			}
         //关闭对话框
         function closeWindow(){
				$("#can").empty();
				$(".opacity_bg").hide();
			}
         /**
          * 创建笔记
          */
         function sureAddNote(){
				//获取笔记名
				var noteTitle=$("#input_note").val().trim();
				var ok=true;
				if(noteTitle==""){
					ok=false;
					alert("笔记名不能为空");
				}
				
				//获取笔记本id
				var $li=$("#book_list a.checked").parent();
				var bookId=$li.data("bookId");
				
				if(bookId==null){
					ok=false;
					alert("请选中一个笔记本");
					closeWindow();
				}
				
				if(ok){
				//发送ajax请求
				$.ajax({
					url:"http://localhost:8080/cloudnote/note/addnote.do",
					type:"post",
					data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
					resultType:"json",
					success:function(result){
						if(result.status==0){
							closeWindow();//关闭对话框
							var noteId=result.data;
							
							//拼接笔记对应的li列表
							var s_li='<li class="online">';
							 s_li+=' <a> ';
							s_li+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
							s_li+='</a>';
							s_li+='<div class="note_menu" tabindex="-1">';
							s_li+='<dl>';
							s_li+='   <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
							s_li+='   <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
							s_li+='    <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
							s_li+='</dl>';
							s_li+='</div>';
							s_li+='</li>';
							
							//将s_li字符串装换为jquery对象，藏bookId
							var $li=$(s_li);//转换为jquery
							$li.data("noteId",noteId);//藏值
							
							//将li添加到笔记本ul中
							$("#note_list").prepend($li);
							
						}
					},
					
					error:function(){
						alert("创建笔记异常");
					}
					
				});
				
				}
			
		}
         
         function showNote(){
				//设置选中样式
				$("#note_list li a").removeClass("checked");
				$(this).find("a").addClass("checked");
				//获取noteId
				
				var $li=$("#note_list a.checked").parent();
				var noteId=$li.data("noteId");
				
				//发送ajax请求
				$.ajax({
					url:"http://localhost:8080/cloudnote/note/shownote.do",
					type:"post",
					data:{"noteId":noteId},
					resultType:"json",
					success:function(result){
						if(result.status==0){
							//获取笔记标题以及内容
							var map=result.data;
							var noteTitle=map.cn_note_title;
							var noteBody=map.cn_note_body;
							
							//将标题和内容写入编辑框
							//val()沒有参数则为获取值，有参数为设置值
							$("#input_note_title").val(noteTitle);	
							um.setContent(noteBody);
							
						}
					},
					error:function(){
						alert("加载笔记信息异常");
					}
					
				});
				
			}
         
         function editNote(){
				//获取noteId
				var $li=$("#note_list a.checked").parent();
				var noteId=$li.data("noteId");
				
				//获取note标题以及内容
				var noteTitle=$("#input_note_title").val().trim();
				var noteBody=um.getContent();
				
				//格式检查
				
				//发送ajax请求
				$.ajax({
					url:"http://localhost:8080/cloudnote/note/editnote.do",
					type:"post",
					data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
					resultType:"json",
					success:function(result){
						if(result.status==0){
							alert(result.msg);
							//更改笔记标题列表中的名称
							var liTitle=$("#note_list a.checked").text().trim();
							
							if(liTitle!=noteTitle){//需要修改
								var s='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								$("#note_list a.checked").html(s);//替换选中的内容
							}
						}
					},
					error:function(){
						alert("编辑笔记异常");
					}
					
				});
				
			}