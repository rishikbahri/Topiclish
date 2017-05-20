$( "#topicName" ).keyup(function() {
	updateCounter(this,50,"name");
});
$( "#topicDesc" ).keyup(function() {
	updateCounter(this,255,"text");
});
function updateCounter(elm, limit, type){
	var countLeft = limit - $(elm).val().length;
	var charCounterId = "textCharCounter";
	var errorClass = "textError";
	
	if(type=="name"){
		charCounterId = "nameCharCounter";
		errorClass = "nameError";
	}
	if(countLeft==limit){
		$("#"+charCounterId).text("");
	}else{
		var $parent = $(elm).parent();
		if($parent.hasClass("has-error")){
			$parent.removeClass("has-error");
		}
		$("#"+errorClass).text("");
		$("#"+charCounterId).text("("+countLeft+" charecters remaining)");
	}
}
var topicManager = {
	createTopic: function(){
		if(this.validateTopic()){
			$.ajax({
			  type: "POST",
			  url: "createTopic",
			  data: {topicName:$("#topicName").val(),topicDesc:$("#topicDesc").val()},
			  success: function(){
				  topicManager.displayTopics();
			  }
			});
		}
	},
	validateTopic: function(){
		var validateTopic = true;
		var $topicName = $("#topicName");
		var topicName = $topicName.val();
		
		var $topicDesc = $("#topicDesc");
		var topicDesc = $topicDesc.val();
		
		if(topicName.length==0){
			$topicName.parent().addClass("has-error");
			$("#nameError").text("This field cannot be empty");
			validateTopic = false;
		}else if(topicName.length>50){
			$topicName.parent().addClass("has-error");
			$("#nameError").text("This field cannot be more than 50 charecters");
			validateTopic = false;
		}
		if(topicDesc.length==0){
			$topicDesc.parent().addClass("has-error");
			$("#textError").text("This field cannot be empty");
			validateTopic = false;
		}else if(topicDesc.length>255){
			$topicDesc.parent().addClass("has-error");
			$("#textError").text("This field cannot be more than 255 charecters");
			validateTopic = false;
		}
		return validateTopic;
	},
	displayTopics: function(){
		$.ajax({
			  type: "GET",
			  url: "getTopTopics",
			  success: function(result){
				  var topicList = result.data;
				  if(topicList.length==0){
					  $("#rightContainer").append("There are no topics");
				  }else{
					  var str = "";
					  for(i=0;i<topicList.length;i++){
						  str=str+"name="+topicList[i].topicName+" desc="+topicList[i].topicDesc+" <br/>";
					  }
					  $("#rightContainer").html(str);
				  }
			  }
			});
	}
}