(function(mui,chat){
if(app.mainPage == 'chat') {
	mui.init({
		pullRefresh: {
			container: '.center',
			down: {
				//auto: true,
				style: 'circle',
				callback: chatPulldownRefresh
			}
		}
	});
}
//				window.addEventListener('username',function(e){
//					var username=e.detail.username;
//					$(".mui-title").text(username);
//				});
//当输入框有值时显示发送按钮，当输入框为空时隐藏发送按钮
//返回
mui(".mui-bar-nav").on("tap", ".mui-icon-back", function() {
	document.getElementById("main").style.display="inherit";
	document.getElementById("chat").style.display="none";
	app.mainPage="main";
});
var addhidden = 0;
var expressionhidden = 0;
var pageindex = 0;
var friendHeader="";
chat.friendId="";
var ws;
if(typeof(ws)!='undefined'&&ws!=''){
ws.onopen=function(){
	console.log("webSocket连接成功！");
};
ws.onmessage=function(){
	var ul = document.getElementsByClassName('center-ul')[0];
	var li = document.createElement('li');
	li.innerHTML = "<div class='head'><img src='"+friendHeader+"'/></div>" +
		"<div class='content'>" + content + "</div>"+
		"<div class='null'></div>" ;
	li.setAttribute('class', 'left');
	ul.appendChild(li);
	document.getElementsByClassName('center')[0].scrollTop = document.getElementsByClassName('center')[0].scrollHeight;
}
ws.onerror=function(){
	console.log("连接错误！");
	mui.toast("WebSocket连接失败！");
}
ws.onclose=function(){
	console.log("WebSocket连接关闭！");
}
}
chat.chatPullPage=function(index) {
	if(typeof(ws)=='undefined'||ws==''||ws.readyState==0||ws.readyState==3){
		ws= new WebSocket("ws://localhost:8772/websocket");
	}
	mui.get(app.api.chatPage + "?friendId=" + chat.friendId, function(data) {
		if(data.length == 2) {
			var chatRecords = data[0][0];
			var friendUser = data[1];
			var myUser = app.user;
			var lis = "";
			friendHeader=friendUser.headportrait;
			for(var i = 0; i < chatRecords.length; i++) {
				var chatRecord = chatRecords[i];
				if(chatRecord.sendUserId == myUser.id) {
					lis += '<li class="right">' +
						'<div class="head"><img src="..' + myUser.headportrait + '" /></div>' +
						'<div class="content">' + chatRecord.msg + '</div>' +
						'</li>';
				} else if(chatRecord.recUserId == myUser.id) {
					lis += '<li class="left">' +
						'<div class="head"><img src="..' + friendUser.headportrait + '" /></div>' +
						'<div class="content">' + chatRecord.msg + '</div>' +
						'<div class="null"></div>' +
						'</li>';
				}
			}
			document.getElementById('chat_title').innerText = friendUser.nickname;
			document.getElementById("center-ul").innerHTML = lis;
			return true;
		} else if(data == 'error') {
			mui.toast("系统错误！");
		}
		return false;
	});
}
/**
 * 下拉刷新
 */
function chatPulldownRefresh() {
	setTimeout(function() {

		if(chatPullPage()) {
			mui.toast("刷新成功");
		}
		//chatMain.firstChild;
		mui('.center').pullRefresh().endPulldownToRefresh(); //refresh completed
	}, 1500);
}
//当点击输入框时关闭表情和附加功能
mui('.bottom').on("tap","#chat_form_article", function() {
	$(".center").css({
		"bottom": "50px",
		"padding-top": "98px"
	});
	$(".bottom").css("bottom", "0px");
	$(".bottom").css("height", "50px");
	$("#chat_biaoqin").css("display", "none");
	$("#chat_additional").css("visibility", "hidden");
});
//单击+图标出现附加功能
mui(".bottom").on("tap", "#chat_add", function() {
	if(addhidden == 0) {
		$(".center").css({
			"bottom": "50px",
			"padding-top": "98px"
		});
		$(".bottom").css("bottom", "0px");
		$("#chat_biaoqin").css("display", "none");
		$(".center").css({
			"bottom": "120px",
			"padding-top": "168px"
		});
		$(".bottom").css("height", "120px");
		$("#chat_additional").css("visibility", "inherit");

		addhidden += 1;
	} else {
		$(".center").css({
			"bottom": "50px",
			"padding-top": "98px"
		});
		$(".bottom").css("height", "50px");
		$("#chat_additional").css("visibility", "hidden");
		addhidden = 0;
	}

});
//点击表情
document.getElementById("chat_expression").addEventListener("tap", function() {
	if(expressionhidden == 0) {
		$(".center").css({
			"bottom": "50px",
			"padding-top": "98px"
		});
		$(".bottom").css("height", "50px");
		$("#chat_additional").css("visibility", "hidden");
		$(".mui-center>.center").css({
			"bottom": "230px",
			"padding-top": "278px"
		});
		$(".mui-center>.bottom").css("bottom", "183px");
		$("#chat_biaoqin").css("display", "inherit");
		expressionhidden += 1;
	} else {
		$(".center").css({
			"bottom": "50px",
			"padding-top": "98px"
		});
		$(".bottom").css("bottom", "0px");
		$("#chat_biaoqin").css("display", "none");
		expressionhidden = 0;
	}
});
document.getElementById('chat_form_article').addEventListener('keyup', function(even) {
	var articlehtml = document.getElementById('chat_form_article').innerHTML;
	if(document.getElementById('chat_send') != null) {
		if(articlehtml != '') {
			document.getElementById('chat_send').style.visibility = "visible";
		} else {
			document.getElementById('chat_send').style.visibility = "hidden";
		}
	}
});
//发送消息
var send = document.getElementById('chat_send');
send.addEventListener('tap', function() {
	var content = document.getElementById('chat_form_article').innerHTML;
	if(content != "") {
		var message={};
		message.sendUserId=app.user.id;
		message.recUserId=app.friendId;
		message.msg=content;
		ws.send(message);
		var ul = document.getElementsByClassName('center-ul')[0];
		var li = document.createElement('li');
		li.innerHTML = "<div class='head'><img src='"+app.user.headportrait+"'/></div>" +
			"<div class='content'>" + content + "</div>";
		li.setAttribute('class', 'right');
		ul.appendChild(li);
		document.getElementById('chat_form_article').innerHTML = '';
		this.style.visibility = "hidden";
		document.getElementsByClassName('center')[0].scrollTop = document.getElementsByClassName('center')[0].scrollHeight;
	}
});
mui.plusReady(function() {
	//发送消息
	var send = document.getElementById('send');
	send.addEventListener('tap', function() {
		var content = vm.content;
		var ul = document.getElementsByClassName('content-ul')[0];
		ul.innerHTML += "<li class='right'>" +
			"<div class='head'><img src='../images/head_portrait/u=1097202646,3345048323&fm=27&gp=0.jpg'/></div>" +
			"<div class='center'>" + content + "</div>" +
			"</li>";
		vm.content = '';
	});
	//发送图片
	document.getElementById('sendImg').addEventListener('tap', function() {
		app.galleryImg();
	});
	//拍照
	document.getElementById('sendPhoto').addEventListener('tap', function() {
		app.getImage();
	});
});
}(mui,window.chat = {}));
