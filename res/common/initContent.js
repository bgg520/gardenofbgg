//使用语境状态类 panel-primary、panel-success、panel-info、panel-warning、panel-danger，来设置带语境色彩的面板.
var panelColor = ["panel-primary", "panel-success", "panel-info", "panel-warning", "panel-danger"];
var panelCurrentColor = null;
$(function () {
	$.getJSON("data.json",function(result){
		if(result.content.length>0){
			$.each(result.content, function(i, content){
				var str = '<div class="panel '+ getPanelColor() +'">';
				//panel-heading;
				var panelHeading = '<div class="panel-heading"><h3 class="panel-title">';
				panelHeading += '<a href="'+ content.url +'">'+ content.title +'</a></h3>' + '</div>'; 
				str += panelHeading;
				//panel-body
				var panelBody = '<div class="panel-body">';
				panelBody += '<div><p class="font" style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;简介：'+ content.desc +'</p></div>';
				panelBody += '</div>';
				str += panelBody;
				//panel-footer
				var panelFooter = '<div class="panel-footer">';
				panelFooter += '<div><a href="'+ content.url +'">'+ content.url +'</a></div>';
				panelFooter += getFooterLabel(content.type);
				panelFooter += '</div>';
				str += panelFooter + '</div>';
				$("#show-content").append(str);
			})
		}
	})
})
 
function getPanelColor(){
	if(panelCurrentColor==null){
		panelCurrentColor = Math.ceil(Math.random()*4);
	}
	if(panelCurrentColor==panelColor.length-1){
		panelCurrentColor = 0;
	} else {
		panelCurrentColor ++;
	}
	return panelColor[panelCurrentColor];
}
 
function getFooterLabel(type){
	//您可以使用修饰的 class label-default、label-primary、label-success、label-info、label-warning、label-danger 来改变标签的外观
	var str; 
	if(type=="info"){
		str = '<div><span class="label label-primary">资料</span></div>';
	} else if(type=="video"){
		str = '<div><span class="label label-success">视频</span></div>';
	} else if(type=="test"){
		str = '<div><span class="label label-info">测试题目</span></div>';
	} else {
		str = '<div><span class="label label-warning">其它</span></div>';
	}
	return str;
}