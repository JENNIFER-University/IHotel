var config = {
    init: function () {
        $("div.vertical-tab-menu>div.list-group>a").click(function(e) {
            e.preventDefault();
            $(this).siblings('a.active').removeClass("active");
            $(this).addClass("active");
            var index = $(this).index();
            $("div.vertical-tab>div.vertical-tab-content").removeClass("active");
            $("div.vertical-tab>div.vertical-tab-content").eq(index).addClass("active");
        });

    },

    changeStatus: function (obj) {
        var problemId = $(obj).attr("data-id");
        var currentStatus = $(obj).attr("data-stat");
        var data = { id: problemId};
        $.ajax({
            url: 'updateConfig',
            dataType: 'json',
            data: data,
            success:function (result) {
                if ($(obj).is(":checked")) {
                    $("#problem-status-"+problemId).html('true');
                }else {
                    $("#problem-status-"+problemId).html('false');
                }
            },
            error: function (jqXHR, textStatus) {
                if (textStatus == 'parsererror') {

                    if ($(obj).is(":checked")) {
                        $("#problem-status-"+problemId).html('true');
                    }else {
                        $("#problem-status-"+problemId).html('false');
                    }

                }
            }
        });
    }


}


$(document).ready(function () {
    config.init();
});