<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/calcu.css">
</head>

<body>

<div class="calculater">	
    <form name="forms">	
        <input type="text" name="output" readonly>	
        <input type="button" class="clear" value="C" onclick="document.forms.output.value=''">	
        <input type="button" value="/" onclick="document.forms.output.value+='/'">	
        <input type="button" value="1" onclick="document.forms.output.value+='1'">
        <input type="button" value="2"onclick="document.forms.output.value+='2'">	
        <input type="button" value="3" onclick="document.forms.output.value+='3'">	
        <input type="button" class="operator" value="*" onclick="document.forms.output.value+='*'">	
        <input type="button" value="4" onclick="document.forms.output.value+='4'">	
        <input type="button" value="5" onclick="document.forms.output.value+='5'">	
        <input type="button" value="6" onclick="document.forms.output.value+='6'">	
        <input type="button"  class="operator" value="+" onclick="document.forms.output.value+='+'">	
        <input type="button" value="7" onclick="document.forms.output.value+='7'">	
        <input type="button" value="8"onclick="document.forms.output.value+='8'">
        <input type="button" value="9"onclick="document.forms.output.value+='9'">	
        <input type="button"  class="operator" value="-" onclick="document.forms.output.value+='-'">	
        <input type="button"  class="dot" value="."onclick="document.forms.output.value+='.'">	
        <input type="button" value="0"onclick="document.forms.output.value+='0'">	
        <input type="button"  class="operator result" value="=" onclick="document.forms.output.value=eval(document.forms.output.value)">	
    </form>	
</div>
    
</body>
</html>