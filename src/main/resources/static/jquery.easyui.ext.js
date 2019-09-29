/**
 * jquery.easyui扩展
 *
 * @author：yu8home
 * @date：2018年8月21日 下午6:10:45
 */
;(function ($) {
    function A(F, target) {
        var form = $(target);
        var opts = $.data(target, 'form').options;
        for (var i = opts.fieldTypes.length - 1; i >= 0; i--) {
            var type = opts.fieldTypes[i];
            var field = form.find('.' + type + '-f');
            if (field.length && field[type]) {
                field[type](F);
            }
        }
    }

    $.extend($.fn.form.methods, {
        enable: function (jq) {
            return jq.each(function () {
                A('enable', this);
            });
        },
        disable: function (jq) {
            return jq.each(function () {
                A('disable', this);
            });
        }
    });
})(jQuery);