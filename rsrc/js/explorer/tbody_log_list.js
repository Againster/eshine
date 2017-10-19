


function resolve_file_suffix(string) {
    var index = string.lastIndexOf(".", 0);
    if (index == -1) {
        return "/";
    }
    return string.substring(index, string.length).toLowerCase();
}

function tbody_log_list(string) {
    var index = 1;
    var name;
    var url;
    var length;
    var created;
    var suffix;
    var content = "";
    $.each(string, function (i, items) {
        name = "";
        url = "";
        length = 0;
        created = "";
        suffix = "";
        content += "<tr>";
        $.each(items, function (j, item) {
            if (j == "name") {
                name = utf8to16($.base64.decode(item));
            } else if (j == "url") {
                url = utf8to16($.base64.decode(item));
            } else if (j == "length") {
                length = item;
            } else if (j == "created") {
                created = item;
            } else {
                console.log("can't resolve tbody_log_list json: " + item);
            }
        })
        suffix = resolve_file_suffix(name);
        if (suffix == "/") {
            suffix = "file";
        }
        content += "<td>" + index + "</td>";
        content += "<td><img src='img/theme_live/" + suffix + ".svg' height='39px' style='padding: 0px; border: 0px;'></td>";
        content += "<td><a href=" + url + ">" + name + "</a></td>";
        content += "<td>" + length + "</td>";
        index++;
        content += "</tr>";
    })
    document.getElementById("tbody_log_list").innerHTML = content;
}

function query_log_list() {
    $.getJSON(
        "explorer/doList",
        null,
        tbody_log_list
    )
}
