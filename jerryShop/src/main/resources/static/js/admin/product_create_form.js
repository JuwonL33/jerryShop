
let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

$('#imageUploadBtn').click(function(){
	let formData = new FormData();
	
	let inputFile = $("input[type='file']");

	let files = inputFile[0].files;
	
	if (files.length == 0){
		swal("데이터 없음", "한 개 이상의 이미지를 추가해주세요.", "warning");
	}
	
	for (var i = 0; i < files.length; i++){
		console.log(files[i]);
		formData.append("files", files[i]);
	}
		$.ajax({
			url: '/uploadFiles',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			dataType: 'json',
			beforeSend: function (xhr){
		       xhr.setRequestHeader(header, token); 
			},
			success: function(result){
				console.log(result);
				showUploadedImages(result);
			}
			
			});
});
	
function showUploadedImages(arr){
	console.log(arr);
	
	let divArea = $("#thumbnail-area");

	let str = "";
			
	for(let i = 0; i < arr.length; i++){
		str += "<div>";
		str += "<img src='/display?fileName="+arr[i].folderPath+"/s_"+arr[i].uuid+arr[i].fileName+"'>";
		str += "<button class='removeBtn btn btn-dark btn-sm btn-block' data-name='"+arr[i].imageURL+"'>REMOVE</button>"
		str += "<div name='imageName' id='imageName' value='"+arr[i].fileName+"' style='display:none'></div>";
		str += "<div name='imagePath' id='imagePath' value='"+arr[i].folderPath+"/"+arr[i].uuid+arr[i].fileName+"' style='display:none'></div>";
		str += "</div>"
	}
	divArea.append(str);
}

$("#thumbnail-area").on("click", ".removeBtn", function(e){
	let target = $(this);
	let fileName = target.data("name");
	let targetDiv = $(this).closest("div");

	$.ajax({
		type: 'POST',
		url: '/removeFile',
		data: {"fileName" : fileName},
		beforeSend: function (xhr){
           xhr.setRequestHeader(header, token);
		},
		success: function(result){
			if(result === true){
				targetDiv.remove();
			}
		}
		});
});

/*
 * form 데이터 json 형태로 변환하는 소스. 매우유용
 */
jQuery.fn.serializeObject = function() { 
  var obj = null; 
  try { 
      if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
          var arr = this.serializeArray(); 
          if(arr){ obj = {}; 
          jQuery.each(arr, function() { 
              obj[this.name] = this.value; }); 
          } 
      } 
  }catch(e) { 
      alert(e.message); 
  }finally {} 
  return obj; 
}

$("#productCreateBtn").click(function(){
		
	let productFrm = $('#productCreatForm').serializeObject();
	
	if($("#imageName").attr("value") && $("#imagePath").attr("value") ){
		productFrm["imageName"] = $("#imageName").attr("value");
		productFrm["imagePath"] = $("#imagePath").attr("value");
	}
	
	if(productFrm.productName == ""){
		swal("필수입력", "상품명을 입력해주세요.", "warning");
	}else if(!productFrm.price){
		swal("필수입력", "가격을 입력해주세요.", "warning");
	}else if(!productFrm.category1){
		swal("필수입력", "대분류를 선택해주세요.", "warning");
	}else if(!productFrm.category2){
		swal("필수입력", "소분류를 선택해주세요.", "warning");
	}else{
		$.ajax({
		type: 'POST',
		url: '/product/create',
	 	cache : false,
		headers: {'Content-Type': 'application/json'},
		data: JSON.stringify(productFrm),
		beforeSend: function (xhr){
           xhr.setRequestHeader(header, token);
		},
		success: function(result){
			console.log(result)
			window.location = "/product/list";
		},error:function(request,status,error){        
			console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리       
		},     complete : function(data) {                 
		//  실패했어도 완료가 되었을 때 처리        
		}

		});
	}
})
