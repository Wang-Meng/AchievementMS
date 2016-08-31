function collageChanged(selected){
	var collageSel = document.getElementById('collageSelect');
    var majorSel = document.getElementById('majorSelect');
//    for (var i = 1970; i < year + 1; i++) {
//        yearSel.options.add(new Option(i, i));
//    }
    for (var i = 0; i < 12; i++) {
        major.options.add(new Option(i + 1, i + 1));
    }

    yearSel.onchange = function () {
        monSel.firstChild.selected = true;
        $(monSel).trigger('change');
    }
}

$(function(){
	var collageSel = document.getElementById('collageSelect');
	$.getJSON('../collage.json', function(data) {
		var pa = [];
		for (var i = 0; i < data.length; i++) {
			for (var j = 0; j < data[i].major.length; j++) {
				var ci = {};
				ci.value = data[i].name + '--' + data[i]['major'][j];
				pa.push(ci);
			}
		}
	});
	alert(pa);
});
