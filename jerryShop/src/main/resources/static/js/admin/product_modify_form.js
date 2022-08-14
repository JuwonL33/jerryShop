$("#productModifyBtn").click(function(){
		
	let productFrm = $('#productModifyFrm').serializeObject();
	
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
	}else{
		$.ajax({
		type: 'POST',
		url: '/product/modify/'+productFrm["productId"],
	 	cache : false,
		headers: {'Content-Type': 'application/json'},
		data: JSON.stringify(productFrm),
		beforeSend: function (xhr){
           xhr.setRequestHeader(header, token);
		},
		success: function(result){
			console.log(result)
			window.location = "/product/detail/"+productFrm["productId"];
		},error:function(request,status,error){        
			console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리       
		},     complete : function(data) {                 
		//  실패했어도 완료가 되었을 때 처리        
		}

		});
	}
})

$("#productDeleteBtn").click(function(){
	productId = document.getElementById("productId").innerHTML;
	swal("확인", "정말 삭제 하시겠습니까?", "warning")	.then((OK) => {
		$.ajax({
			type: 'GET',
			url: '/product/delete/'+productId,
		 	cache : false,
			headers: {'Content-Type': 'application/json'},
			beforeSend: function (xhr){
	           xhr.setRequestHeader(header, token);
			},
			success: function(result){
				window.location = "/product/list";
			},error:function(request,status,error){        
				console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리       
			},     complete : function(data) {                   
			}
		});
	})
});
		