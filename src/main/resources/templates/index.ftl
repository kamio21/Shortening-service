<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<form method="post" action="/">
		<input type="text" name="originUrl" placeholder="줄이려고 하는 링크를 붙여넣기 해주세요."/>
		<button type="submit">줄이기</button>
	</form>
	<div>
		<#if originUrl??>
			입력한 URL : ${originUrl}
		</#if>
	</div>
	<div>
		<#if shortUrl??>
			줄인 Url : ${shortUrl}
		</#if>
	</div>
</body>
</html>