/**
 * 
 */

const excelDownload1 = document.querySelector('#excelDownload1');

document.addEventListener('DOMContentLoaded', ()=>{
    excelDownload1.addEventListener('click', exportExcel1);
});

function exportExcel1(){ 
  // step 1. workbook 생성
  let wb1 = XLSX.utils.book_new();

  // step 2. 시트 만들기 
  let newWorksheet1 = excelHandler1.getWorksheet();

  // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
  XLSX.utils.book_append_sheet(wb1, newWorksheet1, excelHandler1.getSheetName());

  // step 4. 엑셀 파일 만들기 
  let wbout1 = XLSX.write(wb1, {bookType:'xlsx',  type: 'binary'});

  // step 5. 엑셀 파일 내보내기 
  saveAs(new Blob([s2ab(wbout1)],{type:"application/octet-stream1"}), excelHandler1.getExcelFileName());
}

let excelHandler1 = {
    getExcelFileName : function(){
        return '지출_'+moment().format('YYYY-MM-DD')+'.xlsx';	//파일명
    },
    getSheetName : function(){
        return 'Sheet1';	//시트명
    },
    getExcelData1 : function(){
        return document.getElementById('excel_table1'); 	//TABLE id
    },
    getWorksheet : function(){
        return XLSX.utils.table_to_sheet(this.getExcelData1());
    }
}

function s2ab(s) { 
  let buf1 = new ArrayBuffer(s.length); //convert s to arrayBuffer
  let view1 = new Uint8Array(buf1);  //create uint8array as viewer
  for (let i=0; i<s.length; i++) view1[i] = s.charCodeAt(i) & 0xFF; //convert to octet
  return buf1;    
}









const excelDownload2 = document.querySelector('#excelDownload2');

document.addEventListener('DOMContentLoaded', ()=>{
    excelDownload2.addEventListener('click', exportExcel2);
});

function exportExcel2(){ 
  // step 1. workbook 생성
  let wb2 = XLSX.utils.book_new();

  // step 2. 시트 만들기 
  let newWorksheet2 = excelHandler2.getWorksheet();

  // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
  XLSX.utils.book_append_sheet(wb2, newWorksheet2, excelHandler2.getSheetName());

  // step 4. 엑셀 파일 만들기 
  let wbout2 = XLSX.write(wb2, {bookType:'xlsx',  type: 'binary'});

  // step 5. 엑셀 파일 내보내기 
  saveAs(new Blob([s2ab(wbout2)],{type:"application/octet-stream2"}), excelHandler2.getExcelFileName());
}

let excelHandler2 = {
    getExcelFileName : function(){
        return '수입_'+moment().format('YYYY-MM-DD')+'.xlsx';	//파일명
    },
    getSheetName : function(){
        return 'Sheet1';	//시트명
    },
    getExcelData2 : function(){
        return document.getElementById('excel_table2'); 	//TABLE id
    },
    getWorksheet : function(){
        return XLSX.utils.table_to_sheet(this.getExcelData2());
    }
}

function s2ab(s) { 
  let buf2 = new ArrayBuffer(s.length); //convert s to arrayBuffer
  let view2 = new Uint8Array(buf2);  //create uint8array as viewer
  for (let i=0; i<s.length; i++) view2[i] = s.charCodeAt(i) & 0xFF; //convert to octet
  return buf2;    
}


















