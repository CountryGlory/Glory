var str='<div class="mui-slider">'
		+'<div class="mui-slider-group">';
for(var i=1;i<=91;i++){
	if(i==1){
		str+='<div class="mui-slider-item">'
				+'<div class="mui-row">'
					+'<div class="mui-col-sm-2 mui-col-xs-2">'
						+'<li  class="mui-table-view-cell biaoqin">'
							+'<img src="../images/qq/'+i+'.gif"/>'
						+'</li>'
					+'</div>';
		
	}else if(i%17==0){
		str+='<div class="mui-col-sm-2 mui-col-xs-2">'
				+'<li class="mui-table-view-cell biaoqin">'
					+'<img src="../images/qq/'+i+'.gif"/>'
				+'</li>'
			+'</div>'
			+'<div class="mui-col-sm-2 mui-col-xs-2">'
				+'<li class="mui-table-view-cell delbiaoqin">'
					+'<img src="../images/qq/del.png"/>'
				+'</li>'
			+'</div>'
		+'</div>'
	+'</div>';
	}else if(i%17==1){
		str+='<div class="mui-slider-item">'
				+'<div class="mui-row">'
					+'<div class="mui-col-sm-2 mui-col-xs-2">'
						+'<li class="mui-table-view-cell biaoqin">'
							+'<img src="../images/qq/'+i+'.gif"/>'
						+'</li>'
					+'</div>';
	}else if(i==91){
		str+='<div class="mui-col-sm-2 mui-col-xs-2">'
				+'<li class="mui-table-view-cell biaoqin">'
					+'<img src="../images/qq/'+i+'.gif"/>'
				+'</li>'
			+'</div>'
			+'<div class="mui-col-sm-2 mui-col-xs-2">'
				+'<li class="mui-table-view-cell delbiaoqin">'
					+'<img src="../images/qq/del.png"/>'
				+'</li>'
			+'</div>'
		+'</div>'
	+'</div>'
+'</div>'
+'<div class="mui-slider-indicator">';
	for(var j=0;j<Math.ceil(91/17);j++){
		if(j==0){
			str+='<div class="mui-indicator mui-active"></div>';
		}else{
			str+='<div class="mui-indicator"></div>';
		}
	}
	str+='</div>'
	+'</div>';

	}else{
		str+='<div class="mui-col-sm-2 mui-col-xs-2">'
				+'<li class="mui-table-view-cell biaoqin">'
					+'<img src="../images/qq/'+i+'.gif"/>'
				+'</li>'
			+'</div>';
	}
	
}
//console.log(str);
document.getElementById('chat_biaoqin').innerHTML=str;
document.getElementById('chat_biaoqin').style.display="none";
str="";

mui(".mui-col-sm-2").on('tap',".biaoqin",function(){
	var biaoqin=this.innerHTML;
	document.getElementById("chat_form_article").innerHTML+=biaoqin;
	document.getElementById("chat_send").style.visibility="inherit";
});
mui(".mui-col-sm-2").on("tap",".delbiaoqin",function(){
	var imgs=document.getElementById("chat_form_article").children;
	var content=document.getElementById("chat_form_article").innerHTML;
	console.log(content.substring(content.length-5,content.length));
	if(content.substring(content.length-5,content.length)=='gif">'){
		imgs[imgs.length-1].remove();
	}else{
		document.getElementById("chat_form_article").innerHTML=content.substring(0,content.length-1);
	}
	
});
