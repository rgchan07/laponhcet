<div class="container">
	<br>
	<div id="randomBibleVerse"></div>
</div>

<script>
	function getMessage() {
	var ar = new Array(5);
	ar[0] = "<p style='margin:30px 7% 20px; font-size: 1.4em; letter-height: 10px;text-align: justify;'>" +
    			"&quot;Like a shepherd He will tend His flock, in His arm He will gather the lambs and carry them in His bosom; He will gently lead the nursing ewes.&quot;" +
    			"<span class='pull-right navy'>- Isaiah 40:11</span></p>";
    	ar[1] = "<p style='margin:30px 7% 20px; font-size: 1.4em; letter-height: 10px;text-align: justify;'>" +
    			"Then God said, &quotLet the land produce vegetation: seed-bearing plants and trees on the land that bear fruit with seed in it, according to their various kinds.&quot And it was so." +
    			"<span class='pull-right navy'>- Genesis 1:11</span></p>";
    	ar[2] = "<p style='margin:30px 7% 20px; font-size: 1.4em; letter-height: 10px;text-align: justify;'>" +
    			"&quot;When you have eaten and are satisfied, praise the LORD your God for the good land he has given you. &quot;" +
    			"<span class='pull-right navy'>- Deuteronomy 8:10</span></p>";
    	ar[3] = "<p style='margin:30px 7% 20px; font-size: 1.4em; letter-height: 10px;text-align: justify;'>" +
    			"&quot;Land that drinks in the rain often falling on it and that produces a crop useful to those for whom it is farmed receives the blessing of God.&quot;" +
    			"<span class='pull-right navy'>- Hebrews 6:7</span></p>";
    	ar[4] = "<p style='margin:30px 7% 20px; font-size: 1.4em; letter-height: 10px;text-align: justify;'>" +
    			"&quot;Be sure you know the conditions of your flocks, give careful attention to your herds; for riches do not endure forever, and a crown is not secure for all generations.&quot;" +
    			"<span class='pull-right navy'>- Proverbs 27:34-35</span></p>";
    			
	var now = new Date();
	var sec = now.getSeconds();
	document.getElementById("randomBibleVerse").innerHTML = ar[sec % 5];
	}
	getMessage();
</script>