var addid=0;
mui(".friend_group").on('tap','.mui-friend',function(){
	if(addid===0){
		this.querySelector('.friends').removeAttribute('hidden');
		var icon=this.querySelector('.mui-icon');
		icon.removeAttribute('class');
		icon.setAttribute('class','mui-icon mui-icon-arrowdown');
		
		addid++;
	}else{
		this.querySelector('.friends').setAttribute('hidden','hidden');
		var icon=this.querySelector('.mui-icon');
		icon.removeAttribute('class');
		icon.setAttribute('class','mui-icon mui-icon-arrowright');
		addid=0
	}	
})
//点击好友跳好友信息页面
mui(".mui-table-view").on("tap",".friend",function(){
	var friendName=this.querySelector('#hname').innerText;
	var url=encodeURI('../contacts/friend.html?friendName='+friendName);
	app.redirect(encodeURI(url));
});
//点击头像跳转到我的资料页面
mui(".mui-bar-nav").on("tap","#user",function(){
	/*app.redirect(app.api.userData);*/
	app.redirect("../user/my.html");
});
//点击添加跳转到添加好友页面
mui(".mui-bar-nav").on("tap","#add",function(){
	app.redirect("../contacts/friendsadd.html");
});
//点击新朋友跳转到新朋友页面
mui(".mui-table-view").on("tap",".mui-navigate-right",function(){
	app.redirect("../contacts/newfriend.html");
});
//屏幕长按事件
mui(".friend_group").on("longtap",".mui-friend",function(){
	
	
});
mui(".mui-table-view").on("tap",".friend",function(){
	var frdName=this.querySelector('#hname').innerText;
	var url=encodeURI('../contacts/friend.html?frdName='+frdName);
	app.redirect(encodeURI(url));
});