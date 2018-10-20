/*点赞*/
var iffb=0;
mui('.content-bottom').on('tap','#fabulous',function(){
	if(this.querySelector('#fbimg1').style.display=='inline'){
		iffb=0;
	}else{
		iffb=1;
	}
	var fbNumberstr=this.querySelector('span').innerText;
	if(iffb===0){
		if(fbNumberstr.length>=4){
			fbNumberstr='999+';
		}else{
			var fbNumber=parseInt(fbNumberstr|| '0');
			fbNumberstr=fbNumber+1;
		}
		this.querySelector('#fbimg1').style.display='none';
		this.querySelector('#fbimg2').style.display='inline';
		iffb=1;
	}else{
		if(fbNumberstr.length>=4){
			
			fbNumberstr='999+';
		}else{
			var fbNumber=parseInt(fbNumberstr);
			fbNumberstr=fbNumber-1;
			if(fbNumberstr==0){
				fbNumberstr='';
			}
		}
		this.querySelector('#fbimg2').style.display='none';
		this.querySelector('#fbimg1').style.display='inline';
		iffb=0;
	}
	this.querySelector('span').innerText=fbNumberstr;
});

/*点击转发图标*/
var ifshare=0;
var friendPost;
mui('.content-bottom').on('tap','#share',function(e){
	e.stopPropagation?e.stopPropagation():e.cancelBubble=true;
	if(ifshare===0){
		friendPost=$(this).parents('.mui-look')[0];
		document.getElementById('foworetxt').style.visibility='inherit';
		var esrc =document.getElementById('form_article');
		console.log(esrc);
		app.cursorLast(esrc);
		ifshare=1;
	}else{
		document.getElementById('foworetxt').style.visibility='hidden';
		ifshare=0;
	}
	
});
/*点击屏幕任意处关闭转发样式*/
//window.addEventListener('tap',function(){
//		document.getElementById('foworetxt').style.visibility='hidden';
//		document.getElementById('page_emotion').style.display='none';
//});

document.getElementById('foworetxt').addEventListener('tap',function(e){
	e.stopPropagation?e.stopPropagation():e.cancelBubble=true;
});

/*点击表情，隐藏显示*/
var ifexp=0;
document.getElementById('expression').addEventListener('tap',function(){
	if(ifexp===0){
		document.getElementById('page_emotion').style.display='inherit';
		ifexp=1;
	}else{
		document.getElementById('page_emotion').style.display='none';
		ifexp=0;
	}
});

/*转发说说*/
document.getElementById("foworebtn").addEventListener('tap',function(){
	var friendName=friendPost.querySelector('#friend-name a').innerText;
	var friendContent=friendPost.querySelector('.look-content-txt').innerHTML;
	var friendImage=friendPost.querySelector('.look-content-img').innerHTML;
	var forwardContent=document.getElementById('form_article').innerHTML;
	var yLook=document.getElementsByClassName('content-bottom')[0].innerHTML;
	var newLook='<div class="mui-look">'
			+'<div class="look-header">'
				+'<div id="friend-header">'
					+'<a class="redirect">'
						+'<img src="../images/head_portrait/u=1097202646,3345048323&fm=27&gp=0.jpg" />'
					+'</a>'
				+'</div>'
				+'<div id="friend-name">'
					+'<a class="redirect">苦逼程序猿</a>'
					+'<p>'+app.dateFormat(app.getNewDate())+'</p>'
				+'</div>'
			+'</div>'
			+'<div class="look-content">'
				+'<div class="look-content-txt">'
					+'<p>'+forwardContent+'</p>'
					+'<a href="#">'+friendName+': </a>'
					+friendContent
				+'</div>'
				+'<div class="look-content-img">'
					+friendImage
				+'</div>'
			+'</div>'
			+'<div class="look-bottom">'
				+'<div class="look-icon">'
					+'<div class="mui-row">'
						+'<div class="mui-col-sm-4 mui-col-xs-4" id="fabulous">'
							+'<img src="../images/icon/f99515fea62a6e21c8100e876b2451e1.png" style="display: inline;" id="fbimg1" width="16px" height="16px" />'
							+'<img src="../images/icon/f99515fea62a6e21c8100e876b2451e2.png" style="display: none;" id="fbimg2" width="16px" height="16px" />'
							+'<span ></span>'
						+'</div>'
						+'<div class="mui-col-sm-4 mui-col-xs-4" id="comment">'
							+'<a herf="#"> <img src="../images/icon/9d6ec52a9985cd89d7f30d9d96d745a1.png" width="16px" height="16px" /></a>'
							+'<span ></span>'
						+'</div>'
						+'<div class="mui-col-sm-4 mui-col-xs-4" id="share">'
							+'<a href="#"><img src="../images/icon/e89033de8b55ffe041c906ad6aab83cc.png" width="16px" height="16px" /></a>'
							+'<span ></span>'
						+'</div>'
					+'</div>'
				+'</div>'
			+'</div>'
		+'</div>';
	document.getElementsByClassName('content-bottom')[0].innerHTML=newLook+yLook;
	document.getElementById('form_article').innerHTML='';
	document.getElementById('foworetxt').style.visibility='hidden';
	document.getElementById('page_emotion').style.display='none';
});