<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>jQuery Tutorial</title>
<style>
	a.test {
		font-weight: bold;
	}
</style>
</head>
<body>
	<a href="http://jquery.com">jQuery</a>
<!-- 	<script src="bootstrap/js/jquery-2.1.0.min.js"></script> -->
	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			$("a").click(function(event) {
				alert("Preventing default behavior...");
				event.preventDefault();
				$(this).hide("slow");
			});
			
			$("a").addClass("test");
		});
	</script>
</body>
</html>