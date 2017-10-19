
function check_on_server() {
    var selector = this;
    var parameter = {
        command: "verify",
        subject: "user",
        object: selector.value
    };
    $.getJSON(
        "access/doCheck",
        JSON.stringify(parameter),
        function (data) {
            $.each(data, function (i, item) {
                var tooltip = $("#input_group_user");
                if (item == "invalid") {
                    tooltip.get(0).setAttribute("data-content", "The user name has been taken.");
                    tooltip.popover("show");
                } else {
                    tooltip.get(0).setAttribute("data-content", "The user name is valid.");
                    tooltip.popover("show");
                }
                setTimeout(function () {
                    tooltip.popover("hide");
                }, 2000)
            })
        }
    );
}

function check_register() {
    $("#input_user").keyup( check_on_server )
}