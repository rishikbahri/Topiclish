<!DOCTYPE html>
<%@ include file="taglib_includes.jsp"%>

<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />"/>
		<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	</head>
	<body>
		<div id="container">
			<div id="leftContainer">
				<p id="title">Topiclish</p>
				<div>
					<h3>Create Topic</h3>
    				<div class="form-group" id="textGroup">
    					<p class="formLabel">Topic Text <span id="textCharCounter"></span></p> 
    					<label class="control-label" for="topicName"><small id="textError"></small></label>
    					<textarea class="form-control" id="topicDesc" placeholder="Text" rows="3" maxlength="${supportedDescLen}"></textarea>
    					 <br/>
    					 <button type="submit" id="submitBttn" class="btn btn-primary" onclick="topicManager.createTopic()">Create</button>	
					</div>
				</div>
			</div>
			<div id="rightContainer">
				
			</div>
		</div>
		<script src="<c:url value="/resources/js/main.js" />"></script>
		<script>
			$(document).ready(function() { 
				inputValidator.createInputListners("${supportedDescLen}")
				topicManager.retrieveTopics();
			});

		</script>
	</body>
</html>