function loadNoteBooks(){
	$.ajax({
		url:"http://localhost:8080/cloudnote/notebook/loadbooks.do",
		type:"post",
		data:{"userId":userId},
		//async:false,//默认为异步，设值为false之后为同步
		dataType:"json",
		success:function(result){
			if(result.status==0){//
				var books=result.data;//笔记本集合
				//循环集合生成笔记本li列表
				for(var i=0;i<books.length;i++){
					//获取每个元素的笔记本名称
					var bookName=books[i].cn_notebook_name;
					//获取每个笔记本id
					var bookId=books[i].cn_notebook_id;
					//拼成li元素
					var s_li='<li class="online"><a>';
					s_li+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
					s_li+='</i>'+bookName+'</a></li>';
					
					//将s_li字符串装换为jquery对象，藏bookId
					var $li=$(s_li);//转换为jquery
					$li.data("bookId",bookId);//藏值
					
					//将li添加到笔记本ul中
					$("#book_list").append($li);
					
				}
			}
		}
		
	});

}

function sureAddBook(){
	
	//获取笔记本名
	var bookName=$("#input_notebook").val().trim();
	//检测笔记本名是否为空
	var ok=true;
	if(bookName==""){
		ok=false;
		alert("笔记本名不能为空");
	}
	
	//发送ajax请求
	if(ok){
	$.ajax({
		url:"http://localhost:8080/cloudnote/notebook/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId},
		resultType:"json",
		success:function(result){
			if(status==0){
				closeWindow();//关闭对话框
				//添加一个笔记本li
				var bookId=result.data;//获取返回的bookId
				
				var s_li='<li class="online"><a>';
				s_li+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
				s_li+='</i>'+bookName+'</a></li>';
				
				//将s_li字符串装换为jquery对象，藏bookId
				var $li=$(s_li);//转换为jquery
				$li.data("bookId",bookId);//藏值
				
				//将li添加到笔记本ul中
				$("#book_list").prepend($li);//加到列表的最前面，就是第一个位置
				
			}
		}
		
	});
	}
}