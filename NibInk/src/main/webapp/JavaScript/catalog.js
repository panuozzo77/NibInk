$(document).ready(function() {
  $("#searchbar").keyup(function() {
    var searchValue = $(this).val();
    //console.log(searchValue);
    if(searchValue===""){
		$("#searchResults").empty();
	}
    else if(searchValue){
		$.get('/NibInk/AjaxSearchServlet', {"value": searchValue}, 
    	function(resp){
			$("#searchResults").empty();
			//console.log("Ottengo: " + resp);
			var results=resp.split(",");
			//console.log(results);
			
			if(results!=null){
				for(var i=0; i<results.length; i++){
					if(results[i]!=""){
						showResults(results[i]);
					}
				}
			}
			$('#searchResults :last-child').css({"border-radius": "0 0 10px 10px"});
		})
		.fail(function(){
			console.log("ho fallito!");
		})
	}	
  });
});

function showResults(r){
	var a = r.split("::");
	var newP=$("<p>").html(a[0]);
	newP.css({
		"border": "1px solid black",
		"border-top": "0px",
		"padding": "3%"		
		});
		
	newP.hover(
		function() {
			$(this).css({
				"background-color": "#e9e9ed",
				"cursor": "pointer"
			});
		},
		function() {
			$(this).css({
				"background-color": "transparent",
				"cursor": "default"
			});
		}
	);
	
	newP.click(function(){
		 location.href="/NibInk/JSP/product.jsp?id="+a[1]; 
	});
	$("#searchResults").append(newP);
}