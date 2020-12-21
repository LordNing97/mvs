/**
Demo script to handle the theme demo
**/
var Theme = function () {

    // Handle Theme Settings
    var handleTheme = function () {
        var panel = $('.theme-panel');
        if ($('body').hasClass('page-boxed') === false) {
            $('.layout-option', panel).val("fluid");
        }
        $('.sidebar-option', panel).val("default");
        $('.page-header-option', panel).val("fixed");
        if ($('.sidebar-pos-option').attr("disabled") === false) {
            $('.sidebar-pos-option', panel).val(Metronic.isRTL() ? 'right' : 'left');
        }

       

        $('.theme-colors > li').click(function () {
            var color = $(this).attr("data-style");
            //setColor(color);
            $('ul > li', panel).removeClass("current");
            $(this).addClass("current").siblings().removeClass("current");
        });


    };

    // handle theme colors
    function setColor(color) {
        var color_ = (Metronic.isRTL() ? color + '-rtl' : color);
        $('#style_color').attr("href", Layout.getLayoutCssPath() + 'themes/' + color_ + ".css");
        if (color == 'light2') {
            $('.page-logo img').attr('src', Layout.getLayoutImgPath() + 'logo.png');
        } else {
            $('.page-logo img').attr('src', Layout.getLayoutImgPath() + 'logo.png');
        }
    };

    // handle theme style
    var setThemeStyle = function (style) {
        var file = (style === 'rounded' ? 'components-rounded' : 'components');
        file = (Metronic.isRTL() ? file + '-rtl' : file);

        $('#style_components').attr("href", Metronic.getGlobalCssPath() + file + ".css");

        if ($.cookie) {
            $.cookie('layout-style-option', style);
        }
    };

    return {

        //main function to initiate the theme
        init: function () {
            // handles style customer tool
            //handleTheme();

            //$(function () {
            //    $('.btn').blur();
            //    $('.btn').click(function () {
            //        $(this).blur();
            //    })
            //});

            // handle layout style change
            //$('.theme-panel .layout-style-option').change(function () {
            //    setThemeStyle($(this).val());
            //});

        },
        setThemeColor: function (color) {
            setColor(color);
        }
    };

}();