<@layout.layout>
	<form method="post" action="/">
		<input type="text" name="originUrl" placeholder="줄이려고 하는 링크를 붙여넣기 해주세요."/>
		<button type="submit">줄이기</button>
	</form>
	<br/>
	<br/>
	<#if shortUrl??>
	<div class="panel">
		입력한 URL : ${originUrl}
		<br/>
		줄인 URL : ${shortUrl}
	</div>
	</#if>
</@layout.layout>