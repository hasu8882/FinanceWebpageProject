/**
 * 
 */


function opencalc(){
	let options = 
	"toolbar=no,scrollbars=no,resizable=no,status=no,menubar=no,width=300, height=300";
	var url = "calc.do" ;
	window.open(url, "_black_1", "_blank",options);
}
// 탭 메뉴
let vHas = location.hash;
if(vHas==""){
    location.hash = "#tab1";
}

// 지출 날짜 선택(연도-월-일)
document.getElementById('date1').value = 
	new Date().toISOString().substring(0, 10);;

// 수입 날짜 선택(연도-월-일)
document.getElementById('date2').value = 
	new Date().toISOString().substring(0, 10);;
	
// 지출표 동적 테이블 생성
function tableCreate1(){	
	let html = '';
				
	let num = $("#num").val();
	
	if(num == null || num == ''){
		num = 1;
	}
	
	let use = $("#use").val();
	let cash = $("#cash").val();
	let card = $("#card").val();
	let used_disposal = $("#used_disposal").val();
	let date1 = $("#date1").val();
	let memo1 = $("#memo1").val();
	let delete1 = $("delete1").val();
	
	if(use == null || use == ''){
		alert("사용내역을 입력해주세요");
		return true;
	}
	
	if((card == null || card == '') && (cash == null || cash == '')){
		alert("현금 또는 카드 금액을 입력해주세요");
		return true;
		}	
	
	if(used_disposal == null || used_disposal == ''){
		alert("사용처 분류를 선택해주세요");
		return true;
	}
	
	if(date1 == null || date1 == ''){
		alert("날짜를 선택해주세요");
		return true;
	}
	
		
// 지출 입력사항들의 입력 값을 받아 동적 테이블 생성		
	html += '<tr>';
	html += '<td><input type="text" name="num_reg" value="'+num+'" min="1" max="20" readonly /></td>';
	html += '<td><input type="text" name="use1_reg" value="'+use+'" readonly /></td>';
	html += '<td><input type="text" name="cash_reg" value="'+cash+'" readonly /></td>';
	html += '<td><input type="text" name="card_reg" value="'+card+'" readonly /></td>';
	html += '<td><input type="text" name="used_disposal_reg" value="'+used_disposal+'" readonly /></td>';
	html += '<td><input type="text" name="date1_reg" value="'+date1+'" readonly /></td>';
	html += '<td><input type="text" name="memo1_reg" value="'+memo1+'" readonly /></td>';
	html += '<td><button onclick="tableDelete1(this)" id="delete1">삭제</button></td>';
	html += '</tr>';
				
	$("#table1").append(html);
	
	$("#num").val(parseInt(num)+1);
	$("#use").val('');
	$("#cash").val('');
	$("#card").val('');
	$("#used_disposal").val('');
	$("#date1").val('');
	$("#memo1").val('');
	
	}
	
// 지출표에 생성된 동적 테이블 삭제
function tableDelete1(obj){
	let tr = $(obj).parent().parent();
	
	tr.remove();
	}
	
	
// 수입표 동적 테이블 생성
function tableCreate2(){	
	let html = '';
				
	let num2 = $("#num2").val();	
	
	if(num2 == null || num2 == ''){
		num2 = 1;
	}
	
	let use2 = $("#use2").val();
	let amount = $("#amount").val();
	let bankbook = $("#bankbook").val();
	let import_disposal = $("#import_disposal").val();
	let date2 = $("#date2").val();
	let memo2 = $("#memo2").val();
	let delete2 = $("#delete2").val();
	
	if(use2 == null || use2 == ''){
		alert("사용내역을 입력해주세요");
		return true;
	}
	
	if(amount == null || amount == ''){
		alert("금액을 입력해주세요");
		return true;
	}
	
	if(bankbook == null || bankbook == ''){
		alert("입금통장 은행명을 입력해주세요");
		return true;
	}
	
	if(import_disposal == null || import_disposal == ''){
		alert("수입원 분류를 선택해주세요");
		return true;
	}
	
	if(date2 == null || date2 == ''){
		alert("날짜를 선택해주세요");
		return true;
	}	
	

// 수입 입력사항들의 입력 값을 받아 동적 테이블 생성
	html += '<tr>';
	html += '<td><input type="text" name="num_reg" value="'+num2+'" min="1" max="20" readonly /></td>';
	html += '<td><input type="text" name="use2_reg" value="'+use2+'" readonly /></td>';
	html += '<td><input type="text" name="amount_reg" value="'+amount+'" readonly /></td>';
	html += '<td><input type="text" name="bankbook_reg" value="'+bankbook+'" readonly /></td>';
	html += '<td><input type="text" name="import_disposal_reg" value="'+import_disposal+'" readonly /></td>';
	html += '<td><input type="text" name="date2_reg" value="'+date2+'" readonly /></td>';
	html += '<td><input type="text" name="memo2_reg" value="'+memo2+'" readonly /></td>';
	html += '<td><button onclick="tableDelete2(this)" id="delete2">삭제</button></td>';
	html += '</tr>';
				
	$("#table2").append(html);
			
	$("#num2").val(parseInt(num2)+1);
	$("#use2").val('');
	$("#amount").val('');
	$("#bankbook").val('');
	$("#import_disposal").val('');
	$("#date2").val('');
	$("#memo2").val('');
	}
	
// 수입표에 생성된 동적 테이블 삭제
function tableDelete2(obj){
	let tr = $(obj).parent().parent();
	
	tr.remove();
	}
