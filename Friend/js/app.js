/**
 * 演示程序当前的 “注册/登录” 等操作，是基于 “本地存储” 完成的
 * 当您要参考这个演示程序进行相关 app 的开发时，
 * 请注意将相关方法调整成 “基于服务端Service” 的实现。
 **/
(function($, owner) {
	//版本
	owner.version = "1.0.0";
	
	//main页面当前div
	owner.mainPage="chatMain";
	
	var apiAddr = "local"; //local,remote,remotetest
	if(apiAddr == "local") {
		owner.baseUri = "http://127.0.0.1:8781/";
	}
	if(apiAddr == "remotetest") {
		owner.baseUri = owner.hrUrl + ":8781/";
	}
	
	owner.api = {
		//用户
		userLogin: owner.baseUri + "api-user/login",
		userData: owner.baseUri + "api-user/my",
		//消息
		chatMain: owner.baseUri + "api-chat/cm",
		chatUnSign: owner.baseUri + "api-chat/ur",
		chatSign: owner.baseUri + "api-chat/sr",
		chatSettop: owner.baseUri + "api-chat/stc",
		chatDelchat: owner.baseUri + "api-chat/rc",
		chatPage: owner.baseUri + "api-chat/chatindex"
	}

	mui.ajaxSettings.error=function(xhr,type,errorThrown){
		if(type=="abort"){
			mui.alert("请求超时，请稍后再试！","提示");
		}
		else if(xhr.status=="404"){
			//owner.redirect("../error/404.html");
		}
		else if(xhr.status=="401"){
			owner.redirect("../user/login.html");
		}
		else if(xhr.status=="500"){
			mui.alert("系统错误，请联系客服！","提示");
		}
	}
	mui.ajaxSettings.timeout=10000;
	mui.ajaxSettings.dataType='json';
	mui.ajaxSettings.async=true;
	mui.ajaxSettings.xhrFields={
		withCredentials: true
	};
	mui.ajaxSettings.crossDomain=true;
	
	owner.zidongLogin=function(url){
		var status=owner.getStatus();
		if(status===2){
			var password=owner.getPassword();
			var userCode=user.namenumber();
			owner.login(owner.api.userLogin,userCode,password);
		}
		
	}
	
	/**
	 * 用户登录
	 **/
	owner.login = function(url, userCode,password) {
		if(!userCode) {
			$.toast("用户名不能为空！");
		} else if(!password) {
			$.toast("密码不能为空！");
		} else {
			//本地储存登录
			//var users = JSON.parse(localStorage.getItem("$users") || '[]');

			/*var ifUserid = users.some(function(user) {
				return vue.userid == user.account;
			});

			var ifPassword = users.some(function(user) {
				return vue.password == user.password;
			});
			if(!ifUserid) {
				mui.toast("用户名不存在！");
			} else if(!ifPassword) {
				mui.toast("密码错误！");
			} else if(ifUserid && ifPassword) {
				owner.setState(1);
				owner.redirect("../main/main.html");
			}*/
			//后台登录
			mui.post(url,{
				userCode:userCode,
				password:password
			},function(r){
				var newuser=r;
				if(newuser.id!=null){
					owner.clearStatus();
					owner.setUser(newuser);
					owner.setPassword(password);
					owner.redirect("../main/main.html");
				}
				else { //登录失败
					if(r=="该账号不存在！"||r=="密码错误！") {
						mui.alert(r);
					} else {
						owner.clearStatus();
						mui.alert("系统错误!");
					}
				}
			});
		}

	};

	owner.createState = function(name, callback) {
		var state = owner.getState();
		state.account = name;
		state.token = "token123456789";
		owner.setState(state);
		return callback();
	};

	mui(document).on('tap', '.redirect', function() {
		var url = this.getAttribute('data-url');
		var data = this.getAttribute('data-data');
		mui.openWindow({
			url: url,
			extras: {
				data: data
			}
		});
	})

	//页面跳转
	owner.redirect = function(url, data) {
		//访问:plus.webview.currentWebview().data
		mui.openWindow({
			url: url,
			extras: {
				data: data
			}
		});
		return false;
	}

	// 获取url中的参数
	owner.getUrlParam = function(name) {
		var url = decodeURI(decodeURI(location.search));
		console.log(url);
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		console.log(reg);
		var r = url.substr(1).match(reg);
		if(r != null) {
			return unescape(r[2]);
		} else {
			return null;
		}
	}

	/**
	 * 新用户注册
	 **/
	owner.reg = function(regInfo, callback) {
		callback = callback || $.noop;
		regInfo = regInfo || {};
		regInfo.account = regInfo.account || '';
		regInfo.password = regInfo.password || '';
		if(regInfo.account.length < 5) {
			return callback('用户名最短需要 5 个字符');
		}
		if(regInfo.password.length < 6) {
			return callback('密码最短需要 6 个字符');
		}
		if(!checkEmail(regInfo.email)) {
			return callback('邮箱地址不合法');
		}
		var users = JSON.parse(localStorage.getItem('$users') || '[]');
		users.push(regInfo);
		localStorage.setItem('$users', JSON.stringify(users));
		return callback();
	};
	
	/**
	 * 获取当前状态
	 **/
	owner.getStatus = function() {
		var status = localStorage.getItem('$status') || "";
		return status;
	};

	/**
	 * 设置当前状态
	 **/
	owner.setStatus = function(status) {
		status = status || 0;
		localStorage.setItem('$status', status);
		//var settings = owner.getSettings();
		//settings.gestures = '';
		//owner.setSettings(settings);
	};

	var checkEmail = function(email) {
		email = email || '';
		return(email.length > 3 && email.indexOf('@') > -1);
	};

	/**
	 * 找回密码
	 **/
	owner.forgetPassword = function(email, callback) {
		callback = callback || $.noop;
		if(!checkEmail(email)) {
			return callback('邮箱地址不合法');
		}
		return callback(null, '新的随机密码已经发送到您的邮箱，请查收邮件。');
	};

	/**
	 * 设置应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
	}

	/**
	 * 获取应用本地配置
	 **/
	owner.getSettings = function() {
		var settingsText = localStorage.getItem('$settings') || "{}";
		return JSON.parse(settingsText);
	}
	/**
	 * 获取本地是否安装客户端
	 **/
	owner.isInstalled = function(id) {
		if(id === 'qihoo' && mui.os.plus) {
			return true;
		}
		if(mui.os.android) {
			var main = plus.android.runtimeMainActivity();
			var packageManager = main.getPackageManager();
			var PackageManager = plus.android.importClass(packageManager)
			var packageName = {
				"qq": "com.tencent.mobileqq",
				"weixin": "com.tencent.mm",
				"sinaweibo": "com.sina.weibo"
			}
			try {
				return packageManager.getPackageInfo(packageName[id], PackageManager.GET_ACTIVITIES);
			} catch(e) {}
		} else {
			switch(id) {
				case "qq":
					var TencentOAuth = plus.ios.import("TencentOAuth");
					return TencentOAuth.iphoneQQInstalled();
				case "weixin":
					var WXApi = plus.ios.import("WXApi");
					return WXApi.isWXAppInstalled()
				case "sinaweibo":
					var SinaAPI = plus.ios.import("WeiboSDK");
					return SinaAPI.isWeiboAppInstalled()
				default:
					break;
			}
		}
	}
	//注销,清除所有状态
	owner.clearStatus = function() {
		localStorage.removeItem("$user");
		localStorage.removeItem("$pwd");
		localStorage.removeItem("$status");
	}

	//判断是否登录
	owner.isLogined = function() {
		return !!app.user.id;
	}

	//用户
	owner.user = JSON.parse(localStorage.getItem("$user")) || {};

	//获取用户
	owner.getUser = function() {
		var usersText = localStorage.getItem("$users") || '';
		if(usersText != '') {
			usersText = usersText.replace('[', '');
			usersText = usersText.replace(']', '');
			var users = JSON.parse(usersText);
			return users
		} else {
			return '';
		}
	}
	//设置用户信息
	owner.setUser = function(user) {
		localStorage.setItem("$status", user.status);
		user.status=""
		localStorage.setItem("$user", JSON.stringify(user));
	}
	//更新用户
	owner.updateUser = function(name, value) {
		var user = app.user;
		user[name] = value;
		localStorage.setItem("$user", JSON.stringify(user));
	}

	//设置密码
	owner.setPassword = function(pwd) {
		localStorage.setItem("$pwd", pwd);
	}
	//获取密码
	owner.getPassword = function() {
		return localStorage.getItem("$pwd") || "";
	}
	//清除本地密码
	owner.clearPassword = function() {
		localStorage.removeItem("$pwd");
	}
	
	//owner.zidongLogin(owner.api.userZjLogin);
	//返回当前时间
	owner.getNewDate = function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var week = date.getDay();
		var hour = date.getHours();
		var minute = date.getMinutes();

		return year + '-' + month + '-' + day + ' ' + hour + ':' + minute

	}
	//yyyy-MM-dd格式的字符转日期
	owner.getDate=function(strDate) {
		if(strDate!=null)
		{
			var indexBegin=strDate.indexOf("T")||0;
			var hour=0;
			if(indexBegin>0){
				strDate=strDate.replace(/T/g," ").replace(".000+0000","");
				var hour=8;
			}
			var regEx = new RegExp("\\-", "gi");
			strDate = strDate.replace(regEx, "/");
			var milliseconds = Date.parse(strDate);
			var date=new Date();
			//var date = new Date(strDate).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
			date.setTime(milliseconds);
			date.setHours(date.getHours()+hour);
			return date;
		}else{
			return new Date();
		}
	}
	//时间转换
	owner.dateFormat = function(date) {
		var newDate = new Date();
		var newday = newDate.getDate();
		var date = owner.getDate(date);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var week = date.getDay();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var hourstr = '';
		if(hour >= 6 && hour <= 12) {
			hourstr = '上午 ' + hour + ':' + minute;
		} else if(hour > 12 && hour < 18) {
			hourstr = '下午 ' + (hour - 12) + ':' + minute;
		} else if(hour >= 18 && hour <= 23) {
			hourstr = '晚上 ' + (hour - 12) + ':' + minute;
		} else if(hour >= 0 && hour < 6) {
			hourstr = '凌晨 ' + hour + ':' + minute;
		}
		var weekstr = '';
		if((newday - day) < 7 && (newday - day) > 1) {
			switch(week) {
				case 1:
					weekstr = "星期一 ";
					break;
				case 2:
					weekstr = "星期二";
					break;
				case 3:
					weekstr = "星期三";
					break;
				case 4:
					weekstr = "星期四";
					break;
				case 5:
					weekstr = "星期五";
					break;
				case 6:
					weekstr = "星期六";
					break;
				case 0:
					weekstr = "星期天";
					break;
			}
			return weekstr + " " + hourstr;
		} else if((newday - day) == 1) {
			return '昨天 ' + hourstr;
		} else if((newday - day) == 0) {
			return hourstr;
		} else {
			return year + '-' + month + '-' + day + ' ' + hourstr;
		}

	}

	//可编辑DIV获得焦点并且光标跳至末尾
	owner.cursorLast = function(esrc) {
		esrc.focus();
		if(esrc.createTextRange) {
			var rtextRange = esrc.createTextRange();
			rtextRange.moveStart('character', esrc.value.length);
			rtextRange.collapse(true);
			rtextRange.select();
		} else if(esrc.selectionStart) {
			esrc.selectionStart = esrc.value.length;
		} else if(window.getSelection) {
			var sel = window.getSelection();
			var tempRange = document.createRange();
			tempRange.setStart(esrc.firstChild, esrc.firstChild.length);
			sel.removeAllRanges();
			sel.addRange(tempRange);
			//obj.focus();
		}
	}

	//日期选择组件
	owner.optionDate = function(thisbtn) {
		var _self = thisbtn;
		var result="";
		if(_self.picker) {
			_self.picker.show(function(rs) {
				result=rs.text;
				_self.picker.dispose();
				_self.picker = null;
			});
		} else {
			var optionsJson = thisbtn.getAttribute('data-options') || '{}';
			var options = JSON.parse(optionsJson);
			var id = thisbtn.getAttribute('id');
			/*
			 * 首次显示时实例化组件
			 * 示例为了简洁，将 options 放在了按钮的 dom 上
			 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
			 */
			_self.picker = new mui.DtPicker(options);
			_self.picker.show(function(rs) {
				/*
				 * rs.value 拼合后的 value
				 * rs.text 拼合后的 text
				 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
				 * rs.m 月，用法同年
				 * rs.d 日，用法同年
				 * rs.h 时，用法同年
				 * rs.i 分（minutes 的第二个字母），用法同年
				 */
				result=rs.text;
				/* 
				 * 返回 false 可以阻止选择框的关闭
				 * return false;
				 */
				/*
				 * 释放组件资源，释放后将将不能再操作组件
				 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
				 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
				 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
				 */
				_self.picker.dispose();
				_self.picker = null;
			});
		}
		return result;
	}

	//WebSocket消息通信
	owner.testSocket = function() {
		if(plus.os.name == "Android") {
			var Socket = plus.android.importClass("java.net.Socket");
			var PrintWriter = plus.android.importClass("java.io.PrintWriter");
			var BufferedWriter = plus.android.importClass("java.io.BufferedWriter");
			var OutputStreamWriter = plus.android.importClass("java.io.OutputStreamWriter");
			var BufferedReader = plus.android.importClass("java.io.BufferedReader");
			var InputStreamReader = plus.android.importClass("java.io.InputStreamReader");

			var socket = new Socket("192.168.8.160", 8020);

			var outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			var bufferWriter = new BufferedWriter(outputStreamWriter);
			var out = new PrintWriter(bufferWriter, true);
			out.println("Conneted...");

			var inputStreamReader = new InputStreamReader(socket.getInputStream());
			var br = new BufferedReader(inputStreamReader);
			var msg = br.readLine();

			//while(true)
			{
				if(msg != null) {
					console.log(msg);
				}
				//msg = br.readLine();      
			}
		}
		//console.log(mac);
		alert("Done");
	}
	//打开相机功能，拿到照片路径
	owner.getImage = function() {
		var c = plus.camera.getCamera();
		c.captureImage(function(e) {
			plus.io.resolveLocalFileSystemURL(e, function(entry) {
				var imgSrc = entry.toLocalURL() + "?version=" + new Date().getTime(); //拿到图片路径
				owner.setChatContentImage(imgSrc);
			}, function(e) {
				console.log("读取拍照文件错误:" + e.message);
			});
		}, function(s) {
			console.log("error" + s);
		}, {
			filename: "_doc/camera/"
		});
	}

	//从相册中选择图片
	owner.galleryImg = function() {
		plus.gallery.pick(function(e) {
			for(var i in e.files) {
				var fileSrc = e.files[i]
				owner.setChatContentImage(fileSrc);
			}
		}, function(e) {
			console.log("取消选择图片");
		}, {
			filter: "image",
			multiple: true,
			maximum: 5,
			system: false,
			onmaxed: function() {
				plus.nativeUI.alert('最多只能选取五张图片');
			}
		});
	}
	//显示上传的图片
	owner.setChatContentImage = function(imgSrc) {
		var ul = document.getElementsByClassName('content-ul')[0];
		ul.innerHTML += "<li class='right'>" +
			"<div class='head'><img src='../images/head_portrait/u=1097202646,3345048323&fm=27&gp=0.jpg'/></div>" +
			"<div class='center'><img src='" + imgSrc + "' style='max-height:200px;'/></div>" +
			"</li>";
	}

	// 上传的方法
	function upload() {
		var files = document.getElementById('testImg')
		MicroTaskGUID = common.guid();

		var rul = "服务器地址"

		var imgsArr = mui(".task-img");

		mui.each(imgsArr, function(index, item) {
			// 		console.log(index)
			// 		console.log(item.src)
			createUp(item)
		})

		function createUp(files) {
			var task = plus.uploader.createUpload(url, {
					method: "POST"
				},
				function(t, status) { //上传完成
					if(status == 200) {
						console.log("上传成功：" + t.responseText);
					} else {
						console.log("上传失败：" + status);
					}
				}
			);
			//添加其他参数
			//	    task.addData("name","test");
			task.addFile(files.src, {
				key: files.src
			});
			task.start();
		}
	}

}(mui, window.app = {}));