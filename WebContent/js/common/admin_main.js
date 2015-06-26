$(function() {
	// 为每个选项绑定openTab
	$(".easyui-accordion .panel-body a").click(function() {
		var tabTitle = $(this).text();
		var url = $(this).attr("url");
		var id = $(this).attr("id");
		if (url == '') {
			return false;
		}
		addTab(tabTitle, url, id);
		$('.easyui-accordion div').removeClass("selected");
        $(this).parent().addClass("selected");
	});
	
	// 添加tab页
	function addTab(subtitle, url, id) {
		if ($("#tabs").tabs("exists", subtitle)) {
			$('#tabs').tabs('select', subtitle);
		} else {
			$("#tabs").tabs("add", {
				title : subtitle,
				id : "p_" + id,
				closable : true,
				content : createFrame(url, id)
			});
		}
	}
	
});

// 创建iframe
function createFrame(url, id) {
	return '<iframe name="f_' + id + '" id="f_' + id
			+ '" scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
}

// 打开dialog
function showWin(url, options) {
	// 判断对象中id是否存在
	if (options.id) {
	} else {
		options.id = "windiv";
	}
	var d = document.getElementById(options.id);
	// 判断dom对象是否存在    创建div
	if (d) {
	} else {
		d = document.createElement("div");
		d.id = options.id;
		document.body.appendChild(d);
	}
	var f = createFrame(url, options.id); // iframe
	
		$(d).dialog({
			content : f, // 内容
			title : options.title,
			id : options.id,
			width : options.width,
			height : options.height,
			loadingMessage : "加载中..",
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			buttons : [ {
				text : "确定",
				iconCls : 'icon-ok',
				handler : function() {
					$(window.frames[0].document.getElementById("submit")).click();
				}
			}, {
				text : "取消",
				iconCls : 'icon-cancel',
				handler : function() {
					$(d).window('close', true); // 打开dialog
				}
			} ]
		});
	
}

// 关闭dialog
function closeWin(id)
{
	if(id){}
	else{
		id = "windiv";
	}
	var d = document.getElementById(id);
	$(d).window('close',true);
}