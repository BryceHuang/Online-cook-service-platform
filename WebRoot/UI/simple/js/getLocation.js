function getProvince(){	
	 $.ajax({
         url:"./../publicAction/getProvinceAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {
               $("#province").empty();
               for(var i=0;i<data.length;i++)
               {
               	var code=data[i].provinceCode;
               	var name=data[i].provinceName;
               	$("#province").append("<option value="+code+">"+name+"</option>");
               	//console.log($("#province").val());              	
               }
               if($("#province").val()==null||$("#province").val()=="")
               { $("#province").val(data[0].provinceCode);	}
			   getCity();	
			   						                   						                   
               },
               error:function(ajax) {
                   alert("获取省份信息失败！");
               }
           }) ; 
}


function getCity(){				
 $.ajax({
		url:"./../publicAction/getCityAction",
		   type:"post",
		   dataType:"json",
		   data:{"type":0,"provinceCode":$("#province").val()},
		   aysnc:true,
		   success:function(data) {
			$("#city").empty();
		   for(var i=0;i<data.length;i++)
		   {
			var cityCode=data[i].cityCode;
			var cityName=data[i].cityName;
			$("#city").append("<option value="+cityCode+">"+cityName+"</option>");
		   }
		   if($("#city").val()==null||$("#city").val()=="")
           { $("#city").val(data[0].cityCode);	}
			//$("#city").val(data[0].cityCode);
			getArea();													                   						                   
		   },
		   error:function(ajax) {
			   alert("获取城市信息失败！");
		   }
	   }) ;		
	}
	
	
function getArea(){
 $.ajax({
 url:"./../publicAction/getAreaAction",
	   type:"post",
	   dataType:"json",
	   data:{"type":0,"cityCode":$("#city").val()},
	   aysnc:true,
	   success:function(data) {
		 $("#area").empty();
	   for(var i=0;i<data.length;i++)
	   {
		var areaCode=data[i].areaCode;
		var areaName=data[i].areaName;
		$("#area").append("<option value="+areaCode+">"+areaName+"</option>");
	   }
	  
	   },
	   error:function(ajax) {
		   alert("获取城市信息失败！");
	   }
   }) ;
 
}