var inputValidator = {
	createInputListners: function(topicNameCount, topicDescCount){
		$( "#topicName" ).keyup(function() {
			inputValidator.updateCounter(this,topicNameCount,"name");
		});
		$( "#topicDesc" ).keyup(function() {
			inputValidator.updateCounter(this,topicDescCount,"text");
		});
	},
	updateCounter: function(elm, limit, type){
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
	}	
}
function makeAjaxCall(type, url, data, successCallback, errorCallback){
	$.ajax({
	  type: type,
	  url: url,
	  data: data,
	  success: successCallback,
	  error: errorCallback
	});
}

var topicManager = {
	createTopic: function(){
		if(inputValidator.validateTopic()){
			var $topicName = $("#topicName");
			var $topicDesc = $("#topicDesc");
			var postData = {topicName:$topicName.val(),topicDesc:$topicDesc.val()};
			makeAjaxCall("POST","createTopic",postData, function(){
				$topicDesc.val("");
				$topicName.val("");
				topicManager.retrieveTopics();
			},
			function(){
				alert("An unexpected error has occured while creating the topic");
			});
		}
	},
	retrieveTopics: function(){
		$("#rightContainer").html("Loading...");
		makeAjaxCall("GET","getTopTopics",{}, function(result){
			var topicList = result.data;
			  if(topicList.length==0){
				  $("#rightContainer").html("There are no topics");
			  }else{
				  var str = "";
				  $("#rightContainer").html("");
				  for(var i=0;i<topicList.length;i++){
					  $("#rightContainer").append(topicManager.createTopicDisplay(topicList[i],(i+1)));
				  }
				  
			  }
		},
		function(){
			alert("An unexpected error has occured while retrieveing the topics");
		});
	},
	upvoteTopic: function(topicId){
		$("#rightContainer").html("Loading...");
		makeAjaxCall("PUT","upvoteTopic/"+topicId,{}, function(){
			topicManager.retrieveTopics();
		},
		function(){
			alert("An unexpected error has occured while upvoting the topic");
			topicManager.retrieveTopics();
		});
	},
	downvoteTopic: function(topicId){
		$("#rightContainer").html("Loading...");
		makeAjaxCall("PUT","downvoteTopic/"+topicId,{}, function(){
			topicManager.retrieveTopics();
		},
		function(){
			alert("An unexpected error has occured while downvoting the topic");
			topicManager.retrieveTopics();
		});
	},
	createTopicDisplay: function(topic, rank){
		var $card = $("<div />").addClass("card");
		var $cardContainer = $("<div />").addClass("cardContainer");
		
		var $voteButtonGroup = $("<div />").addClass("voteButtonGroup");

		var $voteCount = $("<p />").addClass("voteCount").text("#"+rank+" ("+topic.voteCount+" Votes)");
		$voteButtonGroup.append($voteCount);
		
		var $btnGroup = $("<div />").addClass("btn-group").attr("role","group");
		
		var $upvoteButton = $("<button />").addClass("btn btn-primary").attr({"aria-label":"Left Align","onclick":"topicManager.upvoteTopic('"+topic.topicId+"')"});
		var $upvoteButtonIcon = $("<span />").addClass("glyphicon glyphicon-triangle-top").attr("aria-label","Left Align");
		$upvoteButton.append($upvoteButtonIcon);
		$upvoteButton.append("Upvote");
		$btnGroup.append($upvoteButton);
		
		var $downvoteButton = $("<button />").addClass("btn btn-default").attr({"aria-label":"Left Align","onclick":"topicManager.downvoteTopic('"+topic.topicId+"')"});
		var $downvoteButtonIcon = $("<span />").addClass("glyphicon glyphicon-triangle-bottom").attr("aria-label","Left Align");
		$downvoteButton.append($downvoteButtonIcon);
		$downvoteButton.append("Downvote");
		$btnGroup.append($downvoteButton);
		
		$voteButtonGroup.append($btnGroup);
		$cardContainer.append($voteButtonGroup);
		
		var $cardContent = $("<div />").addClass("cardContent");
		
		var $topicName = $("<h4 />").append($("<b />").text(topic.topicName));
		$cardContent.append($topicName);
		$cardContent.append($("<p />").text(topic.topicDesc));
		
		var topicDate = new Date(topic.insertDatetime);
		var formattedDate = "Created on "+topicDate.getDate()+"/"+topicDate.getMonth()+"/"+topicDate.getFullYear()
							+" "+topicDate.getHours() + ":" 
							+ topicDate.getMinutes() + ":" + topicDate.getSeconds();
		
		$cardContent.append($("<small />").text(formattedDate).addClass("text-muted"));
		$cardContainer.append($cardContent);
		$card.append($cardContainer);
		return $card;
	}
};

