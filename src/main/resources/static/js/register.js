$(function() {
	$(".register").click(function() {
		var username = $(".username input").val();
		var password = $(".password input").val();
		var phone = $(".phone input").val();
		var formJson = {"username": username, "password": password, "phone": phone};
		console.log($("#memberForm").serializeJson());
		$.ajax({
			url : "/"+ contextPath +"registerMember",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify($("#memberForm").serializeJson()),
			success : function(data) {
				console.log("注册返回的信息："+ data);
			},
			error : function(e) {
				console.log(e);
			}
		});
    });
});