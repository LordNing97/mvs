//加载品牌信息
function user_brand() {
    $.post("/brand/selectAllBrand", function (result) {
        if (result.status == 200) {
            let brands = result.brandList;
            let options = "<option value='-1'>请选择品牌</option>";
            for (let i = 0; i < brands.length; i++) {
                options += "<option value='" + brands[i].id + "'>" + brands[i].name + "</option>"
            }
            $("#user-brand").html(options);
            $("#user-brand").selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}

//加载大类信息
function user_category() {
    $.post("/category/selectAllCategory", function (result) {
        if (result.status == 200) {
            let categorys = result.categoryList;
            let options = "<option value='-1'>请选择大类</option>";
            for (let i = 0; i < categorys.length; i++) {
                options += "<option value='" + categorys[i].id + "'>" + categorys[i].name + "</option>"
            }
            $("#user-category").html(options);
            $("#user-category").selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}

//加载产品线信息
function user_productLine() {
    $.post("/productline/selectAllProductline", function (result) {
        if (result.status == 200) {
            let productLines = result.productlineList;
            let options = "<option value='-1'>请选择产品线</option>";
            for (let i = 0; i < productLines.length; i++) {
                options += "<option value='" + productLines[i].id + "'>" + productLines[i].name + "</option>"
            }
            $("#user-productLine").html(options);
            $("#user-productLine").selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}

//加载区域信息
function user_region() {
    $.post("/region/listRegion", function (result) {
        if (result.status == 200) {
            let regions = result.regions;
            let options = "<option value='-1'>请选择区域</option>";
            for (let i = 0; i < regions.length; i++) {
                options += "<option value='" + regions[i].id + "'>" + regions[i].name + "</option>"
            }
            $("#user-region").html(options);
            $("#user-region").selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}

let userPage = 1, userSize = 10, userTotal = 0;

//加载工程师
function search_user() {
    let brandId = $("#user-brand").val();
    let categoryId = $("#user-category").val();
    let productLineId = $("#user-productLine").val();
    let regionId = $("#user-region").val();
    let name = $("#user-name").val();
    $.post("/user/listUserAll", {
        name: name,
        brandId: brandId,
        categoryId: categoryId,
        productLineId: productLineId,
        regionId: regionId,
        page: userPage,
        size: userSize
    }, function (result) {
        if (result.status == 200) {
            $("#paginate2").show();
            let html = "";
            $(result.users).each(function (index, val) {
                html += '<tr v-for="item in users" class="odd gradeX">\n' +
                    '                                            <td>' +
                    '                                                <button class="btn btn-default btn-xs black"\n' +
                    '                                                        onclick="chooseUser(' + val.id + ',\'' + val.name + '\')">\n' +
                    '                                                    <i class="fa fa-check"></i>\n' +
                    '                                                    选择\n' +
                    '                                                </button>\n' +
                    '                                            </td>\n' +
                    '                                            <td>' + isNull(val.name) + '</td>' +
                    '                                            <td>' + isNull2(val.roleNames) + '</td>' +
                    '                                            <td>' + isNull(val.phone) + '</td>' +
                    '                                            <td>' + isNull(val.regionNames) + '</td>' +
                    '                                            <td>' + isNull(val.brandNames) + '</td>' +
                    '                                            <td>' + isNull(val.categoryNames) + '</td>' +
                    '                                            <td>' + isNull(val.productLineNames) + '</td>' +
                    '                                        </tr>';
            });
            $("#userBody").html(html);
            userTotal = result.total;
            userShowYema();
        } else {
            growlAlert.danger(result.message);
            userShowYema();
        }
    });
}

function isNull2(value1,value2) {
    if (value1 == null && value2 != null){
        if(value2.length > 15){
            return value2.substr(0,14)+"...";
        }else{
            return value2;
        }
    } else if (value2==null && value1!=null) {
        if(value1.length > 15){
            return value1.substr(0,14)+"...";
        }else{
            return value1;
        }
    }else if (value1 !=null && value2!=null) {
        if(value2.length > 15){
            return value2.substr(0,14)+"...";
        }else{
            return value2;
        }
    }else{
        return "";
    }

}
function isNull(value) {
    if (value == null) {
        return "";
    }else{
        if(value.length > 15){
            return value.substr(0,14)+"...";
        }else{
            return value;
        }
    }
}
//生成分页按钮及绑定事件
function userShowYema() {
    var str = '<li class="prev2"><a href="#"><i class="ace-icon fa fa-angle-left"></i></a></li>';

    if (userPage == 1) {
        str += '<li class="active"><a class="yeshu2"> 1 </a></li>';
    } else {
        str += '<li><a class="yeshu2"> 1 </a></li>';
    }
    if (Math.ceil(userTotal / userSize) > 1) {
        if (userPage > 4)
            str += '<li class="paginItem"><a >...</a></li>';
        for (var i = 2; i < Math.ceil(userTotal / userSize); i++) {
            if (i > userPage - 3 && i < userPage + 3) {
                if (userPage == i) {
                    str += '<li class="paginItem active"><a class="yeshu2"> '
                        + i + ' </a></li>';
                } else {
                    str += '<li class="paginItem"><a class="yeshu2"> ' + i + ' </a></li>';
                }
            }
        }
        if (userPage < Math.ceil(userTotal / userSize) - 3)
            str += '<li class="paginItem"><a >...</a></li>';

        if (userPage == Math.ceil(userTotal / userSize)) {
            str += '<li class="paginItem active"><a class="yeshu2"> '
                + Math.ceil(userTotal / userSize) + ' </a></li>';
        } else {
            str += '<li class="paginItem"><a class="yeshu2"> '
                + Math.ceil(userTotal / userSize) + ' </a></li>';
        }
    }
    str += '<li class="next2"><a href="#"><i class="ace-icon fa fa-angle-right"></i></a></li>';
    $("#pagination2").html(str);
    $(".prev2").click(
        function () {
            if (userPage > 1)
                userPage--;
            search_user();
        });
    $(".next2").click(
        function () {
            if (userPage < Math.ceil(userTotal / userSize))
                userPage++;
            search_user();
        });
    $(".yeshu2").click(
        function () {
            if (!isNaN($(this).html().trim())) {
                userPage = parseInt($(this).html());
                search_user();
            }
        });
    $("#count2").html(userTotal);
}

function changeUserPageSize(e) {
    $(e).parent().addClass("active").siblings().removeClass("active");
    let currItem = $(e).children();
    userSize = $(currItem).attr("data-val");
    let pageSizeTxt = $(currItem).text();
    userPage = 1;
    $("#userCurrentPageSize").attr("data-val", userSize).html(pageSizeTxt);
    search_user();
}

function chooseUser(id, name) {
    //选择工程师
    $("#userDialog").modal('hide');
    $("#search-user").attr("data-val", id);
    $("#search-user").focus();
    $("#search-user").val(name);
    $("#search-user").blur();
    changeUrl(navUrl, index);

}

function searchUser() {
    user_brand();
    user_category();
    user_productLine();
    user_region();
    search_user();
}

function listBrand(id) {
    $.post("/brand/selectAllBrand", function (result) {
        if (result.status == 200) {
            let brands = result.brandList;
            let options = "<option value='-1'>请选择品牌</option>";
            for (let i = 0; i < brands.length; i++) {
                options += "<option value='" + brands[i].id + "'>" + brands[i].name + "</option>"
            }
            $("#" + id).html(options);
            $("#" + id).selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}

function listCategory(id) {
    $.post("/category/selectAllCategory", function (result) {
        if (result.status == 200) {
            let categorys = result.categoryList;
            let options = "<option value='-1'>请选择大类</option>";
            for (let i = 0; i < categorys.length; i++) {
                options += "<option value='" + categorys[i].id + "'>" + categorys[i].name + "</option>"
            }
            $("#" + id).html(options);
            $("#" + id).selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}
/*

function listProductLine(id, brand_id, category_id) {
    let brandId = $("#"+ brand_id).val();
    let categoryId = $("#" + category_id).val();
    $.post("/productline/selectAllProductline", {brandId: brandId, categoryId: categoryId}, function (result) {
        if (result.status == 200) {
            let productLines = result.productlineList;
            let options = "<option value='-1'>请选择产品线</option>";
            for (let i = 0; i < productLines.length; i++) {
                options += "<option value='" + productLines[i].id + "'>" + productLines[i].name + "</option>"
            }
            $("#" + id).html(options);
            $("#" + id).selectpicker('refresh');
        } else {
            growlAlert.danger(result.message);
        }
    });
}
*/

function listProductLine(brandId, categoryId, id, element) {
    $.post("/productline/selectAllProductline", {brandId: brandId, categoryId: categoryId}, function (result) {
        if (result.status == 200) {
            let options = "<option value='-1'>" + "请选择产品线" + "</option>";;
            for (let i = 0; i < result.productlineList.length; i++) {
                options += "<option value='" + result.productlineList[i].id + "'>" + result.productlineList[i].name + "</option>"
            }
            $("#" + element).html(options);
            $("#" + element).selectpicker('refresh');
            if (id != 0) {
                $("#" + element).selectpicker('val', id);
            }
        } else {
            growlAlert.danger("请求产品线信息失败")
        }
    });
}

$(function () {
    initSelect();
    let brandId1 = $("#select-brand").val();
    let categoryId1 = $("#select-category").val();
    listProductLine(brandId1,categoryId1,0, 'select-productLine');
    $("#user-brand").bind("change", function () {
        let brandId = $("#user-brand").val();
        let categoryId = $("#user-category").val();
        listProductLine(brandId, categoryId, 0, 'user-productLine');
    });

    $("#user-category").bind("change", function () {
        let brandId = $("#user-brand").val();
        let categoryId = $("#user-category").val();
        listProductLine(brandId, categoryId, 0, 'user-productLine');
    });

});


