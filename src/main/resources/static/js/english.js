$(function() {
	init();
	
	$(".english").click(function() {
		$(".chinese").removeClass("hide");
	});
	
	$(".btn-next").click(function() {
		if (index < total) { index ++; }
		change();
	});
	
	$(".btn-pre").click(function() {
		if (index > 1) { index --; }
		change();
	});
	
	$(".btn-random").click(function() {
		index = Math.floor((Math.random()*total)+1);
		change();
	});
	
	$(".play").click(function() {
		var audio = $("#audio")[0];
		if (audio.paused) {
			audio.play();
		} else {
			audio.pause();
		}
	});
	
	function init() {
		total = pageData.length;
		wordData = pageData;
		change();
	}
	
	
	function change() {
		$(".chinese").addClass("hide");
		var eng = wordData[index-1].english;
		if (eng.length > 14) {
			$(".english p").addClass("middle-font");
		}
		$(".english p").text(eng);
		$(".english p").attr("id", wordData[index-1].id);
		$(".chinese p").text(wordData[index-1].chinese);
		if (undefined != wordData[index-1].voice && null != wordData[index-1].voice) {
			$("#audio").attr("src", "data:audio/wav;base64,"+ wordData[index-1].voice);
			$("#audio")[0].load();
		} else {
			getEnglishVoice();
		}
	}
	
	function getEnglishVoice() {
		var id = $(".english p")[0].id;
		var english = $(".english p").text();
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