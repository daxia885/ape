$(function() {
	init();
	
	/**
	 * 点击按钮我认识，显示中文释义
	 */
	$(".ape-btn-remember").click(function() {
		$(this).addClass("ape-hide");
		$(".ape-btn-forget").addClass("ape-hide");
		$(".ape-chinese").removeClass("ape-hide");
		$(".ape-btn-next").removeClass("ape-hide");
		setWordStudied(1);
	});
	
	/**
	 * 点击按钮想不起来，显示中文释义
	 */
	$(".ape-btn-forget").click(function() {
		$(this).addClass("ape-hide");
		$(".ape-btn-remember").addClass("ape-hide");
		$(".ape-chinese").removeClass("ape-hide");
		$(".ape-btn-next").removeClass("ape-hide");
		setWordStudied(2);
	});
	
	/**
	 * 点击按钮下一个
	 */
	$(".ape-btn-next").click(function() {
		if (index < total) { index ++; }
		change();
		tnum += 1;
		$(".ape-process .ape-left span").text(tnum +"个");
		$(this).addClass("ape-hide");
		$(".ape-chinese").addClass("ape-hide");
		$(".ape-btn-remember").removeClass("ape-hide");
		$(".ape-btn-forget").removeClass("ape-hide");
	});

	/**
	 * 点击收藏按钮
	 */
	$(".ape-book").click(function() {
		$(this).toggleClass("ape-book-collected");
	});
	
	/**
	 * 点击播放按钮，播放音频
	 */
	$(".ape-play").click(function() {
		var audio = $("#audio")[0];
		if (audio.paused) {
			audio.play();
		} else {
			audio.pause();
		}
	});
	
	/**
	 * 1分钟定时统计一下学习时间
	 */
	var countTime = setTimeout(function() {
		tmin += 1;
		$(".ape-process .ape-right span").text(tmin +"min");
	}, 60000);
	
	/**
	 * 初始化
	 */
	function init() {
		total = pageData.length;
		wordData = pageData;
		$(".ape-process .ape-left span").text(tnum +"个");
		$(".ape-process .ape-right span").text(tmin +"min");
		change();
	}
	
	/**
	 * 将后台数据展示到页面
	 */
	function change() {
		$(".ape-chinese").addClass("hide");
		var eng = wordData[index-1].english;
		if (eng.length > 14) {
			$(".ape-english p").addClass("ape-middle-font");
		}
		$(".ape-english p").text(eng);
		$(".ape-english p").attr("id", wordData[index-1].id);
		$(".ape-chinese p").text(wordData[index-1].chinese);
		if (undefined != wordData[index-1].voice && null != wordData[index-1].voice) {
			$("#audio").attr("src", "data:audio/wav;base64,"+ wordData[index-1].voice);
			$("#audio")[0].load();
		} else {
			getEnglishVoice();
		}
	}
	
	function setWordStudied(level) {
		var wid = $(".ape-english p").attr("id");
		var dataJson = {"member": {"id":memberId}, "word":{"id":wid}, "level":level};
		$.ajax({
			url : "/"+ contextPath +"setWordStudied",
			type : "post",
			async : false,
			contentType : "application/json",
			data : JSON.stringify(dataJson),
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	/**
	 * 从接口获取该英文的发音
	 */
	function getEnglishVoice() {
		var id = $(".ape-english p")[0].id;
		var english = $(".ape-english p").text();
		var dataJson = {"id": id, "english": english};
		$.ajax({
			url : "/"+ contextPath +"getEnglishVoice",
			type : "post",
			async : false,
			contentType : "application/json",
			data : JSON.stringify(dataJson),
			success : function(data) {
				$("#audio").attr("src", "data:audio/wav;base64,"+ data);
				$("#audio")[0].load();
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	/**
	 * 获取下一页
	 */
	function nextPage() {
		$.ajax({
			url : "/"+ contextPath +"pageWordList",
			type : "post",
			contentType : "application/json",
			data : "{\"pageNumber\":1,\"pageSize\":30}",
			success : function(data) {
				wordData = wordData.concat(data.content);
				total += data.content.length;
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
});

var wordData;
var index = 1;
var total = 0;
var tnum = 0; 	//今日单词个数
var tmin = 0; 	//今日学习时间