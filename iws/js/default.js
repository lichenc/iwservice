$(function () {
    InitLeftMenu();
    $('body').layout();
});

function InitLeftMenu() {
    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).text();
        var url = $(this).attr("ghref");
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
}

function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
	        $('#tabs').tabs('add', {
	            title: subtitle,
	            content: createFrame(url),
	            closable: true,
	            selected:true,
	            width: $('#mainPanle').width() - 10,
	            height: $('#mainPanle').height()
	        });
	        count++;
    	}else {
        $('#tabs').tabs('select', subtitle);
    }
}

function createFrame(url) {
    var s = '<iframe name="mainFrame" style="width:100%;height:100%;margin:0px;" scrolling="auto" frameborder="0" src="'
		+ url + '"></iframe>';
    return s;
}