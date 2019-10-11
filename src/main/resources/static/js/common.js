var contextPath = "ape/";

$.fn.extend({
	serializeJson: function() {
		var json = {};
		var arr = this.serializeArray();
		$.each(arr, function() {
			if (json[this.name] !== undefined) {
				if (!json[this.name].push) {
					json[this.name] = [json[this.name]];
				}
				json[this.name].push(this.value || '');
			} else {
				json[this.name] = this.value || '';
			}
		});
		return json;
	}
});