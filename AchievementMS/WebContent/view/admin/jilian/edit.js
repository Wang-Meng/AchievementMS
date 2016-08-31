/**
 * Created by lilin on 4/13/15.
 */

function intDate (str)
{
    if (parseInt(str) < 10) str = '0' + str;
    return str;
}

function noon (str) {
    if (parseInt(str) <= 12) return '上午';
    return '下午';
}

$(function () {

    $('#datetimepicker').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        startDate: new Date(),
        todayBtn:  1,
        autoclose: true,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
    var mon = ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二'];
    if ($('input[name=startTimeZH]').val()) {
        var b = new Date(parseInt($('input[name=startTimeZH]').val()) * 1000);
        var time = b.getFullYear()+"-"+intDate(parseInt(b.getMonth()) + 1)+"-"+intDate(b.getDay())+" "+intDate(b.getHours())+":"+intDate(b.getMinutes())+":"+intDate(b.getSeconds());
        var timeZH = intDate(b.getDay())+' '+mon[parseInt(b.getMonth())]+'月'+' '+b.getFullYear()+' - '+intDate(b.getHours())+':'+intDate(b.getMinutes())+' '+noon(b.getHours());
        $('input[name=startTimeZH]').val(timeZH);
        $('input[name=startTime]').val(time);
    }
    $('#datetimepicker').datetimepicker('update');

    $('#saveProduct').on('submit', function () {
        var data = $(this).serializeArray();
        var fields = [{'name': 'type', 'valid': /\d/, 'msg': '类型不符合格式'},
                        {'name': 'price', 'valid': /\d/, 'msg': '价格不符合格式'},
                        {'name': 'carNum', 'valid': /[0-9a-zA-Z]{5}/,'msg': '车牌号不符合格式'},
                        {'name': 'carType', 'valid': /[0-9a-zA-Z]{5}/,'msg': '车型号不符合格式'},
                        {'name': 'startLocation', 'valid': /^[\u0391-\uFFE5]{1,5}[\-]{1}[\u0391-\uFFE5]{2,5}$/,'msg': '开始地址不符合格式'},
                        {'name': 'startTimeZH', 'valid': undefined,'msg': 'startz不符合格式'},
                        {'name': 'startTime', 'valid': undefined,'msg': 'time不符合格式'},
                        {'name': 'endLocation', 'valid': /^[\u0391-\uFFE5]{1,5}[\-]{1}[\u0391-\uFFE5]{2,5}$/,'msg': '到达地址不符合格式'}];
        var isSend = true;
        if (data.length < fields.length) {
            $('#show-message').text('填写内容不完整').fadeIn(1000).fadeOut(1500);
            return false;
        }
        for (var i = 0; i < fields.length;i++)
        {
            if (typeof fields[i].valid == 'object') {
                if (data.length == fields.length + 1)
                    isSend = fields[i].valid.test(data[i + 1].value);
                else isSend = fields[i].valid.test(data[i].value);
                if (isSend == false) {
                    $('#show-message').text(fields[i].msg).fadeIn(1000).fadeOut(1500);
                    return false;
                }
            }
        }
        $.ajax({
            type: 'POST',
            data: data,
            url: this.action
        }).done(function (result) {
            console.log(result);
            if (result !== false) result = '保存成功';
            else result = '保存失败';
            //$('#show-message').text(result).show();
            $('#show-message').text(result).fadeIn(1000).fadeOut(1500, function () {
                window.location.href = $('#productIndex').val();
            });

        });
        return false;
    });



    $.getJSON('/zuoye/files/location.json', function (data) {
        var pa = [];
        for (var i = 0; i < data.length; i++)
        {
            //var pai = {};
            //pai['value'] = data[i].name;
            for (var j = 0;j<data[i].cities.length;j++)
            {
                var ci = {};
                ci.value = data[i].name + '-' + data[i]['cities'][j];
                pa.push(ci);
            }
            //pa.push(pai);
        }
        var engine = new Bloodhound({
            local: pa,
            datumTokenizer: function(d) {
                return Bloodhound.tokenizers.whitespace(d.value);
            },
            queryTokenizer: Bloodhound.tokenizers.whitespace
        });
        engine.initialize();
        $('.locallist').tokenfield({
            typeahead: [null, { source: engine.ttAdapter() }]
        });
        $('.locallist').on('tokenfield:createdtoken', function () {
            $(this).tokenfield('readonly');
        })
    });
});

