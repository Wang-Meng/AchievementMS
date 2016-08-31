/**
 * created by @zhanghaoran in 15.7.21
 */

/**
 * 防止浏览器不支持URL
 * @return {[type]} [description]
 */
(function () {
    var _createObjectURL = window.URL.createObjectURL;
    Object.defineProperty(window.URL, 'createObjectURL', {
        set: function (value) {
        console.trace('set createObjectURL');
                _createObjectURL = value;
        },
        get: function () {
        console.trace('get createObjectURL');
                return _createObjectURL;
        }
    })
})();
(function () {
    var _URL = window.URL;
    Object.defineProperty(window, 'URL', {
        set: function (value) {
        console.trace('set URL');
                _URL = value;
        },
        get: function () {
        console.trace('get URL');
                return _URL;
        }
    })
})();

/**
 * 创建alert元素
 */
(function () {
    var $al = $('<div id="alert"></div>');
    $al.data('alert', true);
    $al.css({
        'position': 'fixed',
        'width': '600px',
        'height': '50px',
        'margin': 'auto',
        'left': '0',
        'right': '0',
        'top': '0',
        'bottom': '0',
        'z-index': '100004',
        'display': 'none'
    });
    var div = $('<div class="alert message fade in hide"></div>');
    div.css({
        'margin-top': '5px',
        'margin-left': 'auto',
        'margin-right': 'auto',
        'z-index': '1989101500',
        'opacity': '0.7',
        'background-color': '#000',
        'border-color': '#000',
        'color': '#fff',
        'position': 'relative',
        'width': '90%',
        'line-height': '40px',
        'border-radius': '10px',
        'padding-left': '1em',
        'padding-right': '1em'
    });
    div.append('<a class="dismiss close" style = "line-height: 40px;" data-dismiss="alert">×</a><label></label>');
    $al.append(div);
    $('body').append($al);
})();


function alert(msg, func) {
    var $al = $('#alert');
    if (false == $al.data('alert')) return false;
    $al.children('div').children('label').text(msg);
    $al.data('alert', false);
    $al.show();
    $al.delay(2000).fadeIn(1000, function () {
        $al.data('alert', true);
    }).fadeOut(500, function () {
        if (typeof func != 'undefined')
            func();
    });
}

(function () {
    var yearSel = document.getElementById('yearSelect');
    var monSel = document.getElementById('monthSelect');
    var daySel = document.getElementById('daySelect');
    var year = parseInt((new Date()).format('yyyy')) - 10;
    var ouArr = [1, 3, 5, 7, 8, 10, 12];
    var jiArr = [4, 6, 9, 11];
    var monDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    for (var i = 1970; i < year + 1; i++) {
        yearSel.options.add(new Option(i, i));
    }
    for (var i = 0; i < 12; i++) {
        monSel.options.add(new Option(i + 1, i + 1))
    }

    yearSel.onchange = function () {
        monSel.firstChild.selected = true;
        $(monSel).trigger('change');
    }

    monSel.onchange = function () {
        var monVal = parseInt(monSel.value);
        var yearVal = parseInt(yearSel.value);
        daySel.innerHTML = '';
        console.log(monVal);
        if (2 != monVal) {
            var dayCount = monDay[monVal - 1];
            var options = daySel.options;
            for (var i = 0; i < dayCount; i++) {
                options.add(new Option(i + 1, i + 1));
            }
            // console.log(monVal);
            // if (dayCount < daySel.length) {
            //     for (var i = dayCount; i < 31; i++) {
            //         daySel.options.remove(i);
            //     }
            // } else {
            //     for (var i = daySel.length; i < dayCount; i++) {
            //         daySel.options.add(new Option(i + 1, i + 1));
            //     }
            // }
        } else {
            if ((yearVal % 4 == 0) && (yearVal % 100 != 0 || yearVal % 400 == 0)) {
                var options = daySel.options;
                for (var i = 0; i < 29; i++) {
                    options.add(new Option(i + 1, i + 1));
                }
                // console.log('闰年');
                // if (29 < daySel.length) {
                //     for (var i = 29; i <= 31; i++) {
                //         daySel.options.remove(i);
                //     }
                // } else {
                //     for (var i = daySel.length; i <= 28; i++) {
                //         daySel.options.add(new Option(i + 1, i + 1));
                //     }
                // }
            } else {
                var options = daySel.options;
                for (var i = 0; i < 28; i++) {
                    options.add(new Option(i + 1, i + 1));
                }
                // if (28 < daySel.length) {
                //     for (var i = 28; i <= 31; i++) {
                //         daySel.options.remove(i);
                //     }
                // } else {
                //     for (var i = daySel.length; i < 28; i++) {
                //         daySel.options.add(new Option(i + 1, i + 1));
                //     }
                // }
            }
        }
    };
})();

var Validation = {
    tel: function (tel) {
        return /^(13[0-9]|15[0-9]|17[0678]|18[0-9]|14[57])[0-9]{8}$/.test(tel);
    },
    idcard: function (idcard) {
        return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idcard);
    }
};

function sexEdit (that) {
    var $self = $(that);
    if (!$self.hasClass('btnEnable')) {
        editSetting({param: 'sex', val: $self.text()}, function (re) {
            $self.parent().children().removeClass('btnEnable').end().end().addClass('btnEnable');
            window.location.reload(true);
        });
    }
    return false;
}


function editSetting (da, func) {
    $.ajax({
        url: APP + 'My/modifySet',
        type: 'POST',
        data: da,
        success: function (res) {
            if ('undefined' != typeof func) func(res);
        }
    });
}

$(function () {
    $('.setting_header_li').on('click', function () {
        var $self = $(this);
        $self.parent().children('li').removeClass('nav_active').end().end().addClass('nav_active');
        $('.settingBox').hide();
        $('#' + $self.attr('target')).show();
    });


    $($('.setting_header_li')[0]).trigger('click');

    var $img = $('#uploadImg2 > img');
    var cropperOptions = {
        aspectRatio: 1 / 1,
        preview: '.img-preview',
        strict: true,
        dragMode: 'crop',
        crop: function (data) {
        }
    };
    Tool.loading();
    $.ajax({
        url: APP + 'My/getUserInfo',
        type: 'POST',
        data: {},
        success: function (result) {
            console.log(result);
            if (0 == result.code) {
                //这个是img
                result = result.result;
                // $img.parent().parent().children().addClass('no_show');
                // $img[0].src = result.avatar;
                // $img.parent().removeClass('no_show');
                // $img.cropper(cropperOptions);
                //输出到页面
                var fields = ['nickname', 'sex', 'telephone', 'idcard', 'birthday'];
                var len = fields.length;
                var field = null;
                for (var i = 0; i < len; i++) {
                    field = fields[i];
                    switch(field){
                        case 'birthday':
                            var date = (new Date(parseFloat(result[field]))).format('yyyy.MM.dd');
                            $('[data-name = "' + field + '"]').append(date);
                            date = date.split('.');
                            var yearSel = document.getElementById('yearSelect').options[parseInt(date[0]) - 1970];
                            var monSel = document.getElementById('monthSelect').options[parseInt(date[1]) - 1];
                            if ('undefined' != typeof yearSel) yearSel.selected = true;
                            if ('undefined' != typeof monSel) {monSel.selected = true;$('#monthSelect').trigger('change');}
                            var daySel = document.getElementById('daySelect').options[parseInt(date[2]) - 1];
                            if ('undefined' != typeof daySel) daySel.selected = true;
                            break;
                        case 'sex':
                            var elem = '';
                            if ('男' == result['sex']) {
                                elem = '<a onclick = "sexEdit(this)" class = "btn btnEnable">男</a><a onclick = "sexEdit(this)" class = "btn">女</a>';
                            } else {
                                elem = '<a onclick = "sexEdit(this)" class = "btn">男</a><a onclick = "sexEdit(this)" class = "btn btnEnable">女</a>';
                            }
                            $('#sex').append(elem);
                            $('[data-name = "' + field + '"]').append(result[field]).next('.editArea').children('div').append('');
                            break;
                        default:
                            $('[data-name = "' + field + '"]').append(result[field]);
                            break;
                    }
                }
            }
            Tool.loaded();
        }
    });

    $('.showEdit').on('click', function () {
        var $self = $(this);
        var $editArea = $self.parent().next('.editArea');
        if ($editArea.is(':hidden')) {
            $self.addClass('hideEdit').text('收起').parent().children(':first-child').addClass('showEdit').end().css('color', 'white');
            $editArea.slideDown();
        } else {
            $self.removeClass('hideEdit').text('编辑').parent().children(':first-child').removeClass('showEdit').end().css('color', 'rgb(154,154,154)');
            $editArea.slideUp();
        }
    });

    $('.closeEdit').on('click', function () {
        $(this).parent().parent().prev().children('a').trigger('click');
    });

    $('.submit').on('click', function () {
        var $self = $(this);
        var $input = $self.parent().prev().children('input.ajaxInput');
        if ('undefined' != typeof $input.attr('valid')) {
            var valid = $input.attr('valid');
            if (!Validation[valid]($input[0].value)) {
                alert('输入的数据不合法');
                return false;
            }
        }
        // var set = {};
        editSetting({param: $input[0].name, val: $input[0].value}, function (re) {
            console.log(re);
            window.location.reload(true);
        });
        return false;
    });

    $('.birthSubmit').on('click', function () {
        var yearSel = document.getElementById('yearSelect').value;
        var monSel = document.getElementById('monthSelect').value;
        var daySel = document.getElementById('daySelect').value;
        var birth = document.getElementById('yearSelect').value + '-' + document.getElementById('monthSelect').value + '-' + document.getElementById('daySelect').value;
        var set = {};
        editSetting(
            {
                param: 'birthday',
                val: document.getElementById('yearSelect').value +
                    '-' +
                    document.getElementById('monthSelect').value +
                    '-' +
                    document.getElementById('daySelect').value
            },
            function (re) {
                console.log(re);
                window.location.reload(true);
            });
        return false;
    });

    $('#modifyPass').on('click', function () {
        var $form = $(this).parent().parent();
        var pass = $('#pass').val(),
        newpass = $('#newpass').val(),
        renewpass = $('#renewpass').val();
        if ('' == pass) {
            alert('请输入密码');
            return false;
        }
        if (6 > newpass.length) {
            alert('密码的长度过短,请最少输入六位数密码');
            return false;
        }
        if (newpass != renewpass) {
            alert('两次输入的密码不一样');
            return false;
        }
        $.ajax({
            url: $form[0].action,
            type: 'POST',
            data: $form.serializeObject(),
            success: function (res) {
                if (0 == res.code) window.location.href = APP + 'login/logout';
                else alert(res.msg);
            }
        });
        return false;
    });

    var fileTypes = ['image/jpg', 'image/jpeg', 'image/gif', 'image/png'];

    var picw_uuid = Math.uuid().replace(/-/g,"").toLowerCase();

    var uploader = new QiniuJsSDK().uploader({
        runtimes: 'html5,flash,html4',    //上传模式,依次退化
        browse_button: 'uploadButton',       //上传选择的点选按钮，**必需**
        uptoken_url: STATIC + '/qiniu_ueditor/php/getToken.php?key='+picw_uuid+'&plupload=1',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
        domain: 'http://7xjrjb.com1.z0.glb.clouddn.com/',   //bucket 域名，下载资源时用到，**必需**
    //    container: 'uploadArea',           //上传区域DOM ID，默认是browser_button的父元素，
        max_file_size: '5mb',           //最大文件体积限制
        flash_swf_url: STATIC + '/qiniu_plupload/plupload/Moxie.swf',  //引入flash,相对路径
        max_retries: 3,                   //上传失败最大重试次数
        dragdrop: true,                   //开启可拖曳上传
        drop_element: 'uploadArea',        //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
        chunk_size: '4mb',                //分块上传时，每片的体积
        auto_start: false,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
        filters: {
            mime_types: [{title : 'Image files', extensions : 'jpg,gif,png,jpeg'}]
        },
        init: {
            'FileFiltered': function (up, file) {
                Tool.loading();
                if (5242880 < file.size) {
                    alert('图片大小不能超过5M');
                } else if (-1 == fileTypes.indexOf(file.type)) {
                    alert('上传文件类型必须为指定类型');
                }else {
                    // 文件添加进队列后,通过url直接显示在页面
                    var URL = window.URL || window.webkitURL, blobURL;
                    if (URL) {
                        if (/^image\/\w+$/.test(file.type)) {
                            blobURL = URL.createObjectURL(file.getNative());
                            if ('undefined' == typeof $img.attr('src') || '' == $img.attr('src')) {
                                $img.parent().parent().children().addClass('no_show');
                                $img[0].src = blobURL;
                                $img.parent().removeClass('no_show');
                                $img.cropper(cropperOptions);
                            } else {
                                $img.one('built.cropper', function () {
                                    URL.revokeObjectURL(blobURL); // Revoke when load complete
                                }).cropper('reset').cropper('replace', blobURL);
                            }
                            // Tool.loaded();
                        }
                    }
                }
                 Tool.loaded();
            },
            'FilesAdded': function(up, files) {
                // console.log(files);
                if (1 < files.length) files = files.splice(files.length - 1, 1);
            },
            'BeforeUpload': function(up, file) {
                Tool.loading();
            },
            'UploadProgress': function(up, file) {
            },
            'FileUploaded': function(up, file, info) {
                Tool.loaded();
                var res = $.parseJSON(info);
                var sourceLink = res.url; //获取上传成功后的文件的Url
                console.log(sourceLink);
                editSetting({param: 'avatar', val: sourceLink}, function (re) {
                    console.log(re);
                    window.location.reload(true);
                });
            },
            'Error': function(up, err, errTip) {
            },
            'UploadComplete': function() {
            },
            'Key': function(up, file) {
                return picw_uuid;
            }
        }
    });

    $('#leftRote').on('click', function () {
        $img.cropper('rotate', 30);
    });

    $('#rightRote').on('click', function () {
        $img.cropper('rotate', -30);
    });
    $('#imgSave').on('click', function () {
        uploader.addFile(convertCanvasToBlob($img.cropper("getCroppedCanvas")));
        uploader.start();
    });
});

// function getImgParam ($img) {
//     var param = '?imageMogr2/crop/!';
//     var imgData = $img.cropper('getData');
//     param += Math.round(imgData.width) + 'x' + Math.round(imgData.height) + 'a' + Math.round(imgData.x) + 'a' + Math.round(imgData.y) + '/rotate/' + Math.round(imgData.rotate);
//     return param;
// }

// function convertCanvasToImage(canvas) {
//     var image = new Image();
//     image.src = canvas.toDataURL("image/jpg");
//     return image;
// }

function convertCanvasToBlob(canvas) {
    var dataurl = canvas.toDataURL('image/jpg');
    return dataURLtoBlob(dataurl);
}

function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}