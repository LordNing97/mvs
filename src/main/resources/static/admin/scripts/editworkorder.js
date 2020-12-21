/*工单js*/
var WorkOrder = function () {
    var productdata = "";//产品数据

    //初始化工单页面事件
    var initPage = function () {
        //初始化自定义字段中日期类型字段
        initDataPicker();

        //初始化客户选择
        initCustomer();

        //选择产品类别
        $("#SelProductCategoryID").change(function () {
            var proCategory = $(this).val();//获取产品类别
            var serviceSpaceId = $("#ServiceSpace_ID").val();//服务空间id
            $("#ProductCategory_ID").val($(this).val()).blur();
            $("#Product_ID").val("");
            $("#SelProduct").empty();
            $("#SelProduct").append("<option value=''>选择产品</option>");
            if (proCategory) {
                $.ajax({
                    url: "/servicespace/workorder/GetProduct",
                    type: "Get",
                    data: { "serviceSpaceId": serviceSpaceId, "serviceCategoryId": $(this).val() },
                    success: function (data) {                    
                        if (data) {
                            $.each(data, function (i, item) {
                                $("#SelProduct").append("<option value=" + item.ID + ">" + item.Name+"（"+item.Type + "）</option>");//产品类型赋值
                            });                      
                        }
                    },
                    error: function (data) { showException(data); }
                });
            } 
        });

        //选择产品
        $("#SelProduct").change(function () {
            $("#Product_ID").val($(this).val()).blur();
        });

        //选择工单类型
        $("#select-ServiceCategory").change(function () {
            $("#ServiceCategoryID,#WorkOrder_ServiceCategoryID").val($(this).val());
            $("#ServiceCategoryName,#WorkOrder_ServiceCategoryName").val($(this).find("option:selected").text());
        });

        //选择联系人
        $("#SelContact").change(function () {
            $("#popContact").val($(this).find("option:selected").text()).focus().blur();
            $("#popContactNumber").val($(this).find("option:selected").attr("data-phone")).focus().blur();
            $("#popContactMail").val($(this).find("option:selected").attr("data-mail"));
            $("#popContactArea").val($(this).find("option:selected").attr("data-area"));
            $("#popContactAddress").val($(this).find("option:selected").attr("data-address"));
        })

        //初始化客户区域
        //if ($("#Customer_Area")) {
        //    initArea("#sel_pro", "#sel_city", "#sel_distr", $("#Customer_Area").val());//初始化省市区域
        //    $("#sel_distr").blur(function () { getAreaVal(); });
        //}

        Metronic.initSlimScroll('.scroller');//滚动条
        $("#editWorkOrderForm").removeData("validator");//删除验证器
        $.validator.unobtrusive.parse("#editWorkOrderForm");//添加验证器
        initUniform();

        //初始化单选列表
        if ($(".radio-list").length>0) {
            $(".radio-list").each(function () {
                var radio = $(this).find(".radio-inline:first-child input");
                if (radio.attr("checked")) {
                    radio.change();
                }
            })
        }

        //初始化图片上传
        if ($("#filePortrait")) {
            initFileInput($("#uploadImgUrl").val());
            fileImgLimit();
        }
    };

    //初始化选择客户控件
    var initCustomer = function selectCustomer() {
        var indexLi = 0;
        var scrHeight = 34;
        $("#popCustomer").keyup(function (e) {
            var keycode = e.keyCode;
            var totalLi = $(this).parent().find(".dropdown-menu li").length - 1;

            //排除键盘中不需要搜索数据的键值
            var array = new Array();
            array = [16, 17, 18, 19, 20, 27, 33, 35, 36, 37, 39, 34, 35, 36, 45, 91, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 145, 187, 189, 192, 219, 220, 221, 255];
            for (i = 0; i < array.length; i++) {
                if (keycode == array[i]) { return false; break; }
            }

            //当触发回车键时
            if (keycode == 13) {
                $(this).blur();
                var curr = $(this).parent().find(".dropdown-menu li.active a");
                curr.click();
            }

            //触发上键
            if (keycode == 38) {
                indexLi--;
                if (indexLi <= 0) {
                    indexLi = 0;
                }
            }
            else if (keycode == 40) {
                //触发下键
                indexLi++;
                if (indexLi >= totalLi) {
                    indexLi = totalLi;
                }
            }

            var ulPar = $(this).parent().find(".dropdown-menu");
            //根据ul的高度计算当键盘上下键时是否需要滚动
            if (ulPar.length > 0) {
                ulPar.find("li:eq(" + indexLi + ")").addClass("active").siblings().removeClass("active");
            }

            //当键盘为回车、上键和下键时不去请求数据
            if (keycode != 38 && keycode != 40 && keycode != 13) {
                var a = $(this).attr("data-bind")
                var urlCus = '/servicespace/workorder/GetCustomerList';
                searchCustomers("#" + a, urlCus);
                $("#" + a).parent().attr("aria-expanded", "true").parent().addClass("open");
                indexLi = 0;
            }

        }).change(function () {
            $("#popCustomer_hidden").attr("data-bind", $(this).val());
            var a = $("#popCustomer_hidden").val();
            var b = $(this).val();
            if (b != a) { $(this).val(a) }
        }).keydown(function (e) {
            if (e.keyCode == 38 || e.keyCode == 40) {
                e.preventDefault && e.preventDefault();
            }
        })
    }

    //获取省市区域的值
    var getAreaVal = function getAreaVal() {
        //获取省市区的值
        var area = $("#sel_pro").find("option:selected").text() + "-" + $("#sel_city").find("option:selected").text() + "-" + $("#sel_distr").find("option:selected").text();
        if ($("#sel_pro").val() && $("#sel_city").val() && $("#sel_distr").val()) {
            $("#Customer_Area").val(area).focus().blur();
        }
        else { $("#Customer_Area").val(""); }
    }

    return {
        //传入查询客户数据的url
        init: function () {
            initPage();
        }
    };
}();

//搜索客户数据
function searchCustomers(o, url) {
    var a = $(o).attr("data-bind");//控制区域
    var b = $(o).attr("data-id");//文本id

    var name = $("#" + b).val();
    $.ajax({
        url: url,
        type: "Get",
        data: { "name": name },
        success: function (data) {
            $("#" + a).empty();
            $("#" + a).append(data);
        },
        error: function (data) {
            showException(data)
        }
    });

}

//绑定客户数据
function bindCustomerData(o) {
    var cusVal = $(o).attr("data-val");
    var cusId = $(o).attr("data-id");

    var a = $(o).attr("data-bind");//获取当前客户id
    var b = $(o).attr("data-content");//获取当前客户姓名
    var c = $(o).attr("data-mobile");//获取当前客户手机

    $("#" + cusVal).val(b).focus().blur();
    $("#" + cusVal + "_hidden").val(b);
    $("#" + cusId).val(a);

    var customerId = a;
    $("#SelContact").empty();
    if (customerId) {
        $.ajax({
            url: "/ServiceSpace/Customer/GetContacts",
            type: "Get",
            data: { "customerId": customerId },
            success: function (data) {
                if (data) {
                    $.each(data, function (i, item) {
                        if (i == 0) {
                            $("#SelContact").append("<option value=\"" + item.ID + "\" data-phone=\"" + item.Telephone + "\" data-mail=\"" + item.Mail + "\" data-area=\"" + item.Area + "\" data-address=\"" + item.Address + "\" selected=\"selected\">" + item.Name + "</option>");

                            $("#popContact").val(item.Name).focus().blur();
                            $("#popContactNumber").val(item.Telephone).focus().blur();
                            $("#popContactMail").val(item.Mail);
                            $("#popContactArea").val(item.Area);
                            $("#popContactAddress").val(item.Address);
                        }
                        else {
                            $("#SelContact").append("<option value=\"" + item.ID + "\" data-phone=\"" + item.Telephone + "\" data-mail=\"" + item.Mail + "\" data-area=\"" + item.Area + "\" data-address=\"" + item.Address + "\">" + item.Name + "</option>");
                        }
                    });
                }
            },
            error: function (data) { showException(data); }
        });
    }
}

//搜索按钮点击事件函数
function btnLoadCustomerClick(o, url, input) {
    searchCustomers(o, url);
    $("#" + input).focus();
}

//自定义字段值改变
function changeCustomFieldValue(id, type, field) {
    var value = "";
    $("#customField_" + id).html("");
    //数据格式检验
    if (type == 1) {//单行文本
        value = $(field).val();
    }
    else if (type == 2) {//多行文本
        value = $(field).val();
    }
    else if (type == 3) {//纯数字
        value = $(field).val();
        var p = /^[0-9]*$/;
        if (value && !p.test(value)) {
            $("#customField_" + id).html("请输入正确的数值");
        }
        if (value.length > 7) {
            $("#customField_" + id).html("请输入小于10000000的数值");
        }
    }
    else if (type == 4) {//邮箱
        value = $(field).val();
        if (value && !validEmail(value)) {
            $("#customField_" + id).html("请输入正确的邮箱");;
        }
    }
    else if (type == 5) {//勾选项
        value = $(field).attr('checked') ? true : false;
    }
    else if (type == 6) {//下拉框
        value = $(field).val();
    }
    else if (type == 7) {//日期
        value = $(field).val();
    }
    else if (type == 8) {//小数
        value = $(field).val();
        var p = /^[1-9](\d+(\.\d{1,9})?)?$/;
        var p1 = /^[0-9](\.\d{1,9})?$/;
        if (value && !p.test(value) && !p1.test(value)) {
            $("#customField_" + id).html("请输入正确的小数");
        }

        if (value.length > 9) {
            $("#customField_" + id).html("请输入长度小于10位的小数");
        }
    }
    else if (type == 9) {//radio list
        $("#customField_" + id + "_hide").val($(field).val());
        value = $("#customField_" + id + "_hide").val();
    }
    else if (type == 10) {//checkk list
        var checks = $("#customField_" + id + "_hide").parent().find("input[type=checkbox]");
        var subData = "";
        $.each(checks, function (i, o) {
            if ($(o).attr('checked')) {
                subData += $(o).val() + ",";
            }
        });

        subData = subData.substring(0, subData.length - 1)
        $("#customField_" + id + "_hide").val(subData);
        value = $("#customField_" + id + "_hide").val();
    }

    //json数组存放自定义字段id value
    var jsonarrayNewCustomFields = new Array();
    var arr =
         {
             "id": id,
             "value": value
         }
    jsonarrayNewCustomFields.push(arr);
    var jsonstr;
    if ($("#CustomFields")) {
        jsonstr = $("#CustomFields").val();
        if (jsonstr) {
            var jsonarrayOldCustomFields = eval('(' + jsonstr + ')');
            for (i = 0; i < jsonarrayOldCustomFields.length; i++) {
                if (jsonarrayOldCustomFields[i].id != id) {
                    jsonarrayNewCustomFields.push(jsonarrayOldCustomFields[i]);
                }
            }
        }
        $("#CustomFields").val(JSON.stringify(jsonarrayNewCustomFields));
    }

    if ($("#WorkOrder_CustomFields")) {
        var jsonstr = $("#WorkOrder_CustomFields").val();
        if (jsonstr) {
            var jsonarrayOldCustomFields = eval('(' + jsonstr + ')');
            for (i = 0; i < jsonarrayOldCustomFields.length; i++) {
                if (jsonarrayOldCustomFields[i].id != id) {
                    jsonarrayNewCustomFields.push(jsonarrayOldCustomFields[i]);
                }
            }
        }
        $("#WorkOrder_CustomFields").val(JSON.stringify(jsonarrayNewCustomFields));
    }
}


//初始化fileinput
function initFileInput(uploadUrl) {
    var control = $("#filePortrait");
    control.fileinput({
        language: 'zh',        //设置语言
        uploadUrl: uploadUrl,  //上传的地址
        dropZoneEnabled: false,//是否显示拖拽区域
        showPreview: false,     //是否显示预览
        uploadAsync: true,
        showUpload: false,     //是否显示上传按钮
        showCaption: false,    //是否显示标题
        showRemove: false,      //是否显示删除/清空按钮
        showCancel: false,     //是否显示取消文件上传按钮

        allowedPreviewTypes: ['image'],
        allowedFileExtensions: ['jpg', 'png'],//接收的文件后缀
        showClose: false,
        showUploadedThumbs: true,
        autoReplace: true,
        browseClass: "btn default", //按钮样式
        enctype: 'multipart/form-data',
        validateInitialCount: true,
        maxFileCount: 4, //表示允许同时上传的最大文件个数
        browseIcon: "<i class='fa fa-camera'></i>",
        maxFileSize: 2000
    }).on("filebatchselected", function (event, files) {
        $(control).parents(".btn-file").siblings(".kv-upload-progress").fadeIn(500);
       
        control.fileinput('upload');
    }).on("fileuploaded", function (event, data) {
        if (data.response) {
            $("#ulimg li img").attr("src", data.response);
            $("#ulimg li").find("input[name=ImgPaths]").val(data.response);
            $(control).parents(".btn-file").siblings(".kv-upload-progress").fadeOut(500);
            $("#filePortrait_ul").append($("#ulimg").html());
            fileImgLimit();
            
        };
    }).on('filepreupload', function (event, data, previewId, index) {
    });
}

//删除图片
function delelteImgPaths(obj) {
    $(obj).parent().remove();
    fileImgLimit();
}

//限制图片上传个数
function fileImgLimit() {
    if ($("#filePortrait_ul li").length >= 4) {
        $("#filePortrait_ul").parent().siblings().addClass("hidden");
    } else {
        $("#filePortrait_ul").parent().siblings().removeClass("hidden");
    }
}

//提交工单表单
function submitWorkOrderForm() {
    var isvalid = true;
    if (!$("#editWorkOrderForm").valid()) {
        isvalid = false;
    }

    var inputs = $("#editWorkOrderForm").find("input[data-bind='required'],select[data-bind='required'],textarea[data-bind='required']");
    if (inputs) {
        $.each(inputs, function () {
            if (!$(this).val()) {
                $(this).parent().find("span.esh-error-lable").html("输入项不能为空！");
                isvalid = false;
            }
            else {
                $(this).parent().next().html("");
            }
        });
    }

    if (isvalid) {
        //图片
        var ImgPaths = new Array();
        $("#filePortrait_ul li").each(function () {
            ImgPaths.push($(this).find("input[name=ImgPaths]").val());
        });
        $("#WorkOrder_UploadImgs").val(JSON.stringify(ImgPaths));
        $("#editWorkOrderForm").submit();
    }
}