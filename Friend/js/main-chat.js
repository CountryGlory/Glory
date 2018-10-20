if(app.mainPage=='chatMain'){
	mui.init({
		gestureConfig: {
			tap: true, //默认为true
			doubletap: true, //默认为false
			longtap: true, //默认为false
			swipe: true, //默认为true
			drag: true, //默认为true
			hold: false, //默认为false，不监听
			release: false //默认为false，不监听
		},
		pullRefresh: {
			container: '#tab-chat',
			down: {
				auto:true,
				style:'circle',
				callback: pulldownRefresh
			}
		}
	});
}
var thisChatId="";
var thisIndex=0;

document.getElementById("userhead").setAttribute("src",".."+app.user.headportrait);
/**
 * 下拉刷新具体业务实现
 */
function pulldownRefresh() {
	setTimeout(function() {
		
		pullPage();
		//chatMain.firstChild;
		mui('#tab-chat').pullRefresh().endPulldownToRefresh(); //refresh completed
		mui.toast("刷新成功");
	}, 1500);
}
//点击头像跳转到我的资料页面
mui(".mui-bar-nav").on("tap", "#user", function() {
	/*app.redirect(app.api.userData);*/
	app.redirect("../user/my.html");
});
var chatPage = null;
//自定义事件传值
mui(".mui-table-view").on("tap",".chat",function(){
	/*var username=$(this).find("#hname").text();
	if(!chatPage){
		chatPage=mui.preload({url:"../chat/chat.html",id:'chat.html'});
		console.log(1);
	}
	console.log(chatPage);
	//触发聊天页面的username事件
	mui.fire(chatPage,"username",{
		username:username
	});
	//打开聊天页面
	mui.openWindow({id:"chat/chat.html"});*/
	var friendId=$(this).attr("friendid");
	//var url=encodeURI('../chat/chat.html?friendeId='+friendId);
	//app.redirect(encodeURI(url));
	chat.friendId=friendId
	chat.chatPullPage(0);
	document.getElementById("main").style.display="none";
	document.getElementById("chat").style.display="inherit";
	app.mainPage="chat";
});
/*mui(".mui-table-view").on("tap", ".mui-media", function() {
	var friendName = this.querySelector('#hname').innerText;
	var url = encodeURI('../chat/chat.html?friendName=' + friendName);
	app.redirect(encodeURI(url));
}); */
/**
 * 长按会话弹出菜单框
 */
var mask=mui.createMask(function(){
		$(".press-tap").css("visibility","hidden");
	});
mui(".mui-table-view").on("longtap", ".mui-media", function() {
	thisIndex = $(this).attr("index");
	thisChatId=$(this).attr("chatid");
	var chatNumber=$(this).children("span");
	if(chatNumber.text()=="0" || chatNumber.text()==""){
		$("#sign").text("标为未读");
		$("#sign").attr("read","0");
	}else{
		$("#sign").text("标为已读");
		$("#sign").attr("read","1");
	}
	var topheight = $(this).outerHeight() * thisIndex;
	
	mask.show();
	$(".press-tap").css({"top":topheight,"visibility":"visible"});

});
/**
 * 页面刷新方法
 */
function pullPage(){
	mui.get(app.api.chatMain,function(data){
		if(data!=null){
			var ul=document.getElementById("chatdialog_ul");
			var content="";
			var chatNumber=0;
			var friendChatNumber=parseInt(data.friendCahtCount||0);
			var lookChatNumber=parseInt(data.lookChatCount||0);
			for(var i=0;i<data.chatdialogs.length;i++){
				var chatdialog=data.chatdialogs[i];
				chatNumber+=chatdialog.unreadchat;
				content+='<li index="'+chatdialog.sortno+'" chatid="'+chatdialog.id+'" friendid="'+chatdialog.friends.id+'" class="mui-table-view-cell mui-media chat">'
					+'<a href="#chat">'
						+'<img class="mui-media-object mui-pull-left" src="..'+chatdialog.friends.user.headportrait+'">'
						+'<div class="mui-media-body">'
							+'<p class="hcenter">'
								+'<div id="hname">'+chatdialog.friends.remark+'</div>'
								+'<div id="htime">'+app.dateFormat(chatdialog.lastdt)+'</div>'
							+'</p>'
							+'<p class="mui-ellipsis">'+chatdialog.newchat+'</p>'
						+'</div>'
					+'</a>';
					if(chatdialog.unreadchat=="0"){
						content+='<span class="mui-badge mui-badge-red" style="visibility:hidden;">'+chatdialog.unreadchat+'</span>';
					}else{
						content+='<span class="mui-badge mui-badge-red" >'+chatdialog.unreadchat+'</span>';
					}
				content+='</li>';
			}
			content+='<div class="press-tap">'
						+'<div id="sign" read="0">标为未读</div>'
						+'<div id="settop">置顶该聊天</div>'
						+'<div id="delchat">删除该聊天</div>'
					+'</div>';
			ul.innerHTML=content;
			document.getElementById("chatNumber").innerText=chatNumber;
			document.getElementById("friendChatNumber").innerText=friendChatNumber;
			document.getElementById("lookChatNumber").innerText=lookChatNumber;
			if(chatNumber==0){
				document.getElementById("chatNumber").setAttribute("style","display: none;");
			}
			else if(friendChatNumber == 0){
				document.getElementById("friendChatNumber").setAttribute("style","display: none;");
			}
			else if(lookChatNumber==0){
				document.getElementById("lookChatNumber").setAttribute("style","display: none;");
			}
		}
	});
}
//pullPage();//刷新页面

/**
 * 未读已读单击事件
 */
mui(".mui-content").on("tap",".press-tap #sign",function(){

	var read=$(this).attr("read");
	var uri="";
	if(read=="1"){
		uri=app.api.chatSign;
	}else{
		uri=app.api.chatUnSign;
	}
	mui.get(uri+"?chatdialogId="+thisChatId,function(data){
		if(data=="1"){
			mui(".mui-table-view li").each(function(){
				var chat=this;
				if(chat.getAttribute("chatid")==thisChatId){
					var span=chat.querySelectorAll("span")[0];
					var thisChatNumber=parseInt(span.innerText);
					span.style.visibility="visible";
					span.innerText="1";
					var chatNumber=parseInt($("#chatNumber").text());
					chatNumber+=1;
					$("#chatNumber").text(chatNumber);
				}
			});
		}else if(data=="0"){
			mui(".mui-table-view li").each(function(){
				var chat=this;
				if(chat.getAttribute("chatid")==thisChatId){
					var span=chat.querySelectorAll("span")[0];
					var thisChatNumber=parseInt(span.innerText);
					span.innerText="0";
					span.style.visibility="hidden";
					var chatNumber=parseInt($("#chatNumber").text());
					chatNumber-=thisChatNumber;
					
					if(chatNumber<=0)
						$("#chatNumber").text("");
					else
						$("#chatNumber").text(chatNumber);
				}
			});
		}else{
			mui.toast("系统错误！请联系客服");
		}
	});
	$(".press-tap").css("visibility","hidden");
	mask.close();
});
/**
 * 页面重排
 */
function indexSort(){
	var i=1;
	mui(".mui-table-view li").each(function(){
		this.setAttribute("index",i);
		i++;
	});
}
/**
 * 置顶该聊天
 */
mui(".mui-content").on("tap",".press-tap #settop",function(){
	mui.getJSON(app.api.chatSettop+"?chatdialogId="+thisChatId,function(data){
		if(data==true){
			var ul=document.getElementsByClassName("mui-table-view")[0];
			var thisLi=ul.children[thisIndex-1];
			ul.insertBefore(thisLi,ul.childNodes[0]);
			indexSort();
			
		}else{
			mui.toast("系统异常，请联系客服");
		}
	});
	$(".press-tap").css("visibility","hidden");
	mask.close();
});
/**
 * 删除该聊天
 */
mui(".mui-content").on("tap",".press-tap #delchat",function(){
	mui.getJSON(app.api.chatDelchat+"?chatdialogId="+thisChatId,function(data){
		if(data==true){
			var ul=document.getElementsByClassName("mui-table-view")[0];
			var thisLi=ul.children[thisIndex-1];
			ul.removeChild(thisLi);
			indexSort();
			
		}else{
			mui.toast("系统异常，请联系客服");
		}
	});
	$(".press-tap").css("visibility","hidden");
	mask.close();
});

mui(".mui-table-view").on("tap","li",function(){
	
});
