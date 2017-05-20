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
  					<div class="form-group">
  						<label for="topicName">Name</label>
    					<input type="text" class="form-control" id="topicName" placeholder="Name">
    					<br/>
    					<label for="topicName">Text</label>
    					<textarea class="form-control" id="topicDesc" placeholder="Text"></textarea>
    					 <br/>
    					 <button type="submit" class="btn btn-primary">Create</button>	
					</div>
				</div>
			</div>
			<div id="rightContainer">
				
			</div>
		</div>
		<script src="<c:url value="/resources/js/main.js" />"></script>
	</body>
</html>