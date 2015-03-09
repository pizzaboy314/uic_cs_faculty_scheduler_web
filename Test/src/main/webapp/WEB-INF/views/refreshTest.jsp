<script type="text/javascript">
	$(document).ready(
		function() {
			setInterval(function() {
				var randomnumber = Math.floor(Math.random() * 100);
				$('#show').text('I am getting refreshed every 3 seconds..! Random Number ==> ' + randomnumber);
			}, 3000);
		}
	);
</script>