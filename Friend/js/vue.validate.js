Vue.prototype.validate = function() {
	var fields = this.$options.validate;
	var vue = this;
	var errors = [];
	for(var index in fields) {
		var rules = fields[index];
		if((typeof rules == 'object') && rules.constructor == Array) //数组
		{
			for(i = 0; i < rules.length; i++) {
				pushError(validateCheck(vue, rules[i], index));
			}
		} else {
			pushError(validateCheck(vue, rules, index));
		}
	}
	this.$options.$errors = errors;
	return errors.length == 0;

	function validateCheck(vue, rule, field) {
		if((typeof rule.test == 'function') && rule.test.constructor == Function) {
			if(!rule.test()) { //!eval(rule.test + "()")
				return rule.message;
			}
		} else {
			if(rule.test == "required") {
				rule.test = /\S+$/;
			}
			if(!new RegExp(rule.test).test(vue[field] || "")) {
				return rule.message;
			}
		}
	}

	function pushError(error) {
		if(error) {
			errors.push(error);
		}
	}
}