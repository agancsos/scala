var ViewService = {
	getCopyrightYear: function() {
		var fullDate = new Date();
		return fullDate.getFullYear();
	},

	findGetParameter: function(parameterName) {
		var result = null, tmp = [];
    	location.search
        	.substr(1)
        	.split("&")
        	.forEach(function (item) {
          		tmp = item.split("=");
          		if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });
		return result;
	},

	getConfiguration: function() {
		return JSON.parse($.ajax(
			{
				beforeSend: function(xhr){
    				if (xhr.overrideMimeType) {
      					xhr.overrideMimeType("application/json");
    				}
  				},
				url : "./configuration.json", 
				async: false, 
				dataType: 'json'
			}).responseText);    
	},
}

var EventService = {
    loadBody: function() {
        document.getElementById("footer-inner").innerHTML = "&copy; "
            + ViewService.getCopyrightYear() + " Gancsos Labs<br/>All Rights Reserved";
		LetsLeaf.initialize(ViewService.getConfiguration().nodes, document.getElementById("container"), 120, "green");
		LetsLeaf.generate();
    }
};
