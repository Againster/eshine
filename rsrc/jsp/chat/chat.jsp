<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang=${lang}>
<head>
    <meta charset="UTF-8">
    <base href="${baseurl}"/>
    <title>Chat</title>
    <link rel="icon" href="img/${img_theme}/favicon.ico" type="image/x-icon"/>

    <link href="lib/animate.css/animate.min.css" rel="stylesheet">
    <link href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="lib/jquery.base64/jquery.base64.js"></script>
    <script src="lib/jquery.media/jquery.media.js"></script>

    <script src="js/so/so.js?ver=2"></script>

    <link href="lib/sprite.icon/cus-sprite.css" rel="stylesheet">
    <link href="lib/sprite.icon/cus-sprite.png" rel="image/png">
    <link href="css/bubbles.css?ver=3" rel="stylesheet">


    <style type="text/css">
        .speech-container {
            height: 60vh;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            overflow-y: auto;
            overflow-x: hidden;
        }
    </style>
</head>
<body>

<c:import url="/jsp/so/navigator.jsp"></c:import>

<div class="container">
    <div class="col-xs-12" id="content" style="display: block;">
        <div class="container-fluid speech-container" id="websocket_output">
        </div>
        <br>

        <div id="decoration-container" style="overflow: hidden;">

            <div id="decoration-container-font" style="float:left;">
                <button id="decoration-font-button" type="button" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon cus-font" aria-hidden="true"></span>
                </button>

                <div id="decoration-enhance-font" class="popover bottom" style="">
                    <div class="arrow" style="left:50%;"></div>
                    <h3 class="popover-title">设置字体样式</h3>
                    <div class="popover-content">

                        <div class="row">
                            <div class="col-xs-6" style="padding: 0px;">
                                <div class="btn-group" style="width:100%;">

                                    <button id="decoration-font-bold-button" type="button" class="btn btn-default"
                                            style="width:50%;" data-toggle="button" aria-pressed="false"
                                            autocomplete="off" title="BOLD font">
                                        <span class="glyphicon cus-text-bold" aria-hidden="true"></span>
                                    </button>

                                    <button id="decoration-font-italic-button" type="button" class="btn btn-default"
                                            style="width:50%;" data-toggle="button" aria-pressed="false"
                                            autocomplete="off" title="ITALIC font">
                                        <span class="glyphicon cus-text-italic" aria-hidden="true"></span>
                                    </button>
                                </div>
                            </div>

                            <div class="col-xs-6" style="padding: 0px;">
                                <div class="input-group" style="width:100%;" title="font SIZE">
                                    <span class="input-group-addon" style="width:20%;">
                                        <span class="glyphicon cus-font-add" aria-hidden="true"></span>
                                    </span>
                                    <input id="decoration-font-size" type="number" class="form-control" step="1" min="1"
                                           max="72" value="14">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-6" style="padding: 0px;">
                                <div class="input-group" style="width:100%;" title="FORE color">
                                <span class="input-group-addon" style="width:20%;">
                                    <span class="glyphicon cus-shape-move-forwards" aria-hidden="true"></span>
                                </span>
                                    <input id="decoration-font-forecolor" type="color" class="form-control"
                                           value="#000000">
                                </div>
                            </div>

                            <div class="col-xs-6" style="padding: 0px;">
                                <div class="input-group" style="width:100%;" title="BACK color">
                                <span class="input-group-addon" style="width:20%;">
                                    <span class="glyphicon cus-shape-move-backwards" aria-hidden="true"></span>
                                </span>
                                    <input id="decoration-font-backcolor" type="color" class="form-control"
                                           value="#FFFFFF">
                                </div>
                            </div>
                        </div>

                        <div class="row" style="padding:0px;" title="select FONT-FAMILY">
                            <select id="decoration-font-family" class="form-control" size="4">
                                <option style="font-family: 微软雅黑" selected="true">微软雅黑</option>
                                <option style="font-family: 黑体">黑体</option>
                                <option style="font-family: 宋体">宋体</option>
                                <option style="font-family: 楷体">楷体</option>
                                <option style="font-family: 新宋体">新宋体</option>
                                <option style="font-family: 仿宋">仿宋</option>
                                <option style="font-family: SimSun-ExtB">SimSun-ExtB</option>
                                <option style="font-family: Calibri">Calibri</option>
                                <option style="font-family: Consolas">Consolas</option>
                                <option style="font-family: Verdana">Verdana</option>
                                <option style="font-family: Segoe UI Symbol">Segoe UI Symbol</option>
                                <option style="font-family: Segoe UI">Segoe UI</option>
                                <option style="font-family: Segoe Print">Segoe Print</option>
                                <option style="font-family: Segoe Script">Segoe Script</option>
                                <option style="font-family: Comic Sans MS">Comic Sans MS</option>
                                <option style="font-family: Roman">Roman</option>
                                <option style="font-family: Script">Script</option>
                                <option style="font-family: Small Fonts">Small Fonts</option>
                                <option style="font-family: Sylfaen">Sylfaen</option>
                                <option style="font-family: Trebuchet MS">Trebuchet MS</option>
                                <option style="font-family: Times New Roman">Times New Roman</option>
                                <option style="font-family: Tahoma">Tahoma</option>
                                <option style="font-family: Terminal">Terminal</option>
                                <option style="font-family: System">System</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div id="decoration-container-sticker" style="float: left;">
                <button id="decoration-button-sticker" type="button" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon cus-emoticon-smile" aria-hidden="true"></span>
                </button>

                <div id="decoration-enhance-sticker" class="popover bottom" style="max-width: 350px;">
                    <div class="arrow" style="left:50%;"></div>
                    <h3 class="popover-title">选择表情</h3>
                    <div class="popover-content">
                        <table id="decoration-sticker-table" class="table table-bordered">
                            <tr>
                                <td><img src="img/mini_expression/cy.gif"></td>
                                <td><img src="img/mini_expression/dx.gif"></td>
                                <td><img src="img/mini_expression/fl.gif"></td>
                                <td><img src="img/mini_expression/aw.gif"></td>
                                <td><img src="img/mini_expression/bs.gif"></td>
                                <td><img src="img/mini_expression/tq.gif"></td>
                                <td><img src="img/mini_expression/ch.gif"></td>
                            </tr>
                            <tr>
                                <td><img src="img/mini_expression/by.gif"></td>
                                <td><img src="img/mini_expression/qz.gif"></td>
                                <td><img src="img/mini_expression/jl.gif"></td>
                                <td><img src="img/mini_expression/yq.gif"></td>
                                <td><img src="img/mini_expression/nj.gif"></td>
                                <td><img src="img/mini_expression/ns.gif"></td>
                                <td><img src="img/mini_expression/ph.gif"></td>
                            </tr>
                            <tr>
                                <td><img src="img/mini_expression/dh.gif"></td>
                                <td><img src="img/mini_expression/jx.gif"></td>
                                <td><img src="img/mini_expression/jy.gif"></td>
                                <td><img src="img/mini_expression/z.gif"></td>
                                <td><img src="img/mini_expression/mb.gif"></td>
                                <td><img src="img/mini_expression/my.gif"></td>
                                <td><img src="img/mini_expression/ts.gif"></td>
                            </tr>
                            <tr>
                                <td><img src="img/mini_expression/qs.gif"></td>
                                <td><img src="img/mini_expression/sb.gif"></td>
                                <td><img src="img/mini_expression/sh.gif"></td>
                                <td><img src="img/mini_expression/zy.gif"></td>
                                <td><img src="img/mini_expression/dy.gif"></td>
                                <td><img src="img/mini_expression/ql.gif"></td>
                                <td><img src="img/mini_expression/jj.gif"></td>
                            </tr>
                        </table>
                    </div>
                </div>

            </div>

            <div id="decoration-animation-container" style="float: left;">
                <button type="button" class="btn btn-default">
                    <span class="glyphicon cus-rainbow" aria-hidden="true"></span>
                </button>
            </div>

        </div>
        <br>

        <div id="input_container">

            <div id="progress_container_send" class="progress"
                 style="height:2px; margin-bottom: 0px; visibility: hidden;">
                <div id="progressbar_send" class="progress-bar" style="width:0%;" role="progressbar" aria-valuenow="60"
                     aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                </div>
            </div>

            <div class="input-group" style="width:100%">
                <span class="input-group-addon" style="width:10%;">Content</span>
                <div class="form-control" id="websocket_input"
                     style="height:90px;overflow-y: auto;overflow-x: hidden; word-break: break-all;"
                     contenteditable="true" tabindex="1"></div>
            </div>
            <br>
            <button class="btn btn-default" style="width:100%;display:none;" id="button_send">Send</button>
        </div>

    </div>

    <div id="reconnection-alert" class="" style="position:relative;top:50vh;transform:translateY(-50%); display: none;">
        <div id="reconnection-alert-message" class="alert alert-danger" style="text-align: center;" role="alert">
            Reconnect after 5(s)
        </div>
    </div>

    <div id="no-support-websocket-alert" class="col-xs-12" style="display:none;">
        <div class="alert alert-danger" role="alert">
            <strong>OH! Snap.</strong>
            It seems like your browser doesn't support websocket. Please update your browser or change to the below.
            <a class="alert-link" href="http://caniuse.com/#feat=websockets">Click to see more compatibility</a>
            <ul>
                <li>IE 10</li>
                <li>Edge 12</li>
                <li>Firefox 10</li>
                <li>Chrome 16</li>
                <li>Safari 7</li>
                <li>Opera 12.1</li>
                <li>iOS Safari 6.1</li>
                <li>Android Browser 4.4</li>
                <li>Blackberry Browser 10</li>
                <li>Opera Mobile 12.1</li>
                <li>Chrome for Android 59</li>
                <li>Firefox for Android 54</li>
                <li>IE Mobile 11</li>
                <li>UC Browser for Android 11.4</li>
                <li>Samsung Internet 4</li>
                <li>QQ Browser 1.2</li>
                <li>Baidu Browser 7.12</li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">

    var webSocket = {
        "url": "ws://" + window.location.host + "/" + ${baseurl} + "websocket/chat.ws",
        "ws": null,
        "timeout": 300,
        "wait": 10,
        "timeoutId": null,
        "waitId": null
    }
    var chatSession = {
        "id": null,
        "unread": 0,
        "progress": 0,
        "limited": 4096,
        "queue": ""
    };
    showMainBody("#content");
    connect();

    var output = document.getElementById("websocket_output");
    var input = document.getElementById("websocket_input");
    function connect() {
        if (window.WebSocket != null) {
            try {
                webSocket["ws"] = new WebSocket(webSocket["url"]);
            } catch (e) {
                var m = "";
                for (var i in e) {
                    m += e[i];
                }
                //console.log("new weboscket exception:" + m);
            }
        } else if (window.MozWebSocket != null) {
            webSocket["ws"] = new MozWebSocket;
        } else {
            showMainBody("#no-support-websocket-alert");
            return;
        }
        webSocket["ws"].onopen = function () {
            showMainBody("#content");
            //console.log('Websocket opened.');
        };
        webSocket["ws"].onmessage = function (event) {
            //console.log('Received:' + event.data);
            dispatchMessage(event.data);
        };
        webSocket["ws"].onclose = function (event) {
            //console.log('Websocket closed.');
            //console.log(event);
            whenCloseReconnect(event);
        };
        webSocket["ws"].onerror = function (event) {
            //console.log(event);
        };
    }

    function whenCloseReconnect(e) {
        if (e.code < 1001 && 1013 < e.code) {
            return;
        }
        showMainBody("#reconnection-alert");

        var wait = webSocket["wait"];
        webSocket["waitId"] = setInterval(
            function () {
                if (wait == 0) {
                    $("#reconnection-alert-message").html("Reconnecting");
                    clearInterval(webSocket["waitId"]);
                    webSocket["waitId"] == null;
                    if (webSocket["ws"].readyState != 1)
                        connect();
                } else {
                    $("#reconnection-alert-message").html("Reconnect after " + wait + "(s)");
                }
                wait--;
            },
            1000
        );
    }

    function showMainBody(selector) {
        var container = [
            "#content",
            "#reconnection-alert",
            "#no-support-websocket-alert"
        ];
        for (var i in container) {
            if (selector == container[i]) {
                $(container[i]).css("display", "block");
            } else {
                $(container[i]).css("display", "none");
            }

        }
    }

    function dispatchMessage(d) {
        var jsonObj = $.parseJSON(d);
        var id = jsonObj["id"];
        var type = jsonObj["type"];
        var date = jsonObj["date"];
        var msg = jsonObj["msg"];
        if (type == "chat") {
            appendSpeech(id, date, msg);
            showTitleCounter(++chatSession["unread"]);
        } else if (type == "identity") {
            chatSession["id"] = id;
        } else {
        }
    }

    function appendSpeech(id, date, msg) {
        if (id == chatSession["id"]) {
            constructSpeech(msg, true);
        } else {
            constructSpeech(msg, false);
        }
    }

    function constructSpeech(json, bool) {
        var jsonObj = $.parseJSON(json);
        var total = jsonObj["total"];
        var segment = jsonObj["segment"];
        var css = jsonObj["css"];
        var msg = jsonObj["message"];
        var backcolor = jsonObj["css"]["background-color"];

        if (segment < total) {
            chatSession["queue"] += msg;
            setProgressBar(50 + 50 * segment / total);
            return;
        } else {
            setProgressBar(100);
            chatSession["queue"] += msg;
        }
        msg = chatSession["queue"];
        chatSession["queue"] = "";

        var row = document.createElement("div");
        $(row).addClass("bubble-row");

        var bubble = document.createElement("div");
        var bubble_before;
        var bubble_after;
        if (bool == true) {
            $(bubble).addClass("bubble-right bubble-right-suffix");

            bubble_before = document.createElement("div");
            $(bubble_before).addClass("bubble-right-before");
            bubble_after = document.createElement("div");
            $(bubble_after).addClass("bubble-right-after");
            $(bubble_after).css("border-color", "transparent " + backcolor);

        } else {
            $(bubble).addClass("bubble-left bubble-left-suffix");

            bubble_before = document.createElement("div");
            $(bubble_before).addClass("bubble-left-before");
            bubble_after = document.createElement("div");
            $(bubble_after).addClass("bubble-left-after");
            $(bubble_after).css("border-color", "transparent " + backcolor);
        }
        $(bubble).css(css);
        $(bubble).html(msg);
        $(bubble).prepend(bubble_before);
        $(bubble).append(bubble_after);
        $(bubble).addClass("flipInY animated");

        var br = document.createElement("br");

        row.appendChild(bubble);
        output.appendChild(row);
        output.appendChild(br);
        output.scrollTop = output.scrollHeight;
    }

    $("#button_send").click(function () {
        var message = $(input).html();
        if (!message) {
            return;
        }
        var wrap = wrapMessage(message);
        //console.log("Send:" + wrap);
        for (var x in wrap) {
            webSocket["ws"].send(wrap[x]);
            setProgressBar(50 * (x + 1) / wrap.size);
        }
        $(input).empty();
    });

    function wrapMessage(message) {
        var font = getEnhanceFont();
        var obj = {
            css: font,
            segment: null,
            total: null,
            message: message
        };

        var limited = chatSession["limited"];
        var quotient = Math.ceil(message.length / limited);
        var json = new Array(quotient);
        obj["total"] = quotient;
        for (var i = 0; i < quotient; i++) {
            obj["segment"] = i + 1;
            obj["message"] = message.slice(i * limited, (i + 1) * limited);
            json[i] = JSON.stringify(obj);
        }

        return json;
    }

    function setProgressBar(d) {
        var c = $("#progress_container_send");
        var b = $("#progressbar_send");
        if (d >= 100) {
            b.css("width", 100 + "%");
            var d = b.css("transition-duration");
            d = parseFloat(d.replace(/[ms | s]/, "0")) * 1000;
            var now = chatSession["progress"];
            setTimeout(
                function () {
                    c.css('visibility', 'hidden')
                    b.css("width", 0 + "%");
                },
                d
            );
        } else {
            c.css("visibility", "visible");
            b.css("width", d + "%");
            chatSession["progress"] = d;
        }
    }

    input.onkeydown = function (e) {
        if (e.keyCode == 13 || e.charCode == 13) {
            $("#button_send").trigger("click");
        }
    }

    input.onkeyup = function (e) {
        if (e.keyCode == 13 || e.charCode == 13) {
            $(input).empty();
        }
    }

    input.addEventListener("paste", function (e) {
        lookForClipboard(e);
    });

    var sticker_table = $("#decoration-sticker-table");
    sticker_table.on("mouseup", "td", function () {
        $(input).append(this.innerHTML);
        this.style.backgroundColor = null;
    });
    sticker_table.on("mousedown", "td", function () {
        this.style.backgroundColor = "lightgray";
    });
    sticker_table.on("mouseout", "td", function () {
        this.style.backgroundColor = null;
    });

    var font_container = $("#decoration-container-font");
    var font_button = $("#decoration-font-button");
    var font_enhance = $("#decoration-enhance-font");
    font_container.hover(
        function (e) {
            showEnhanceFont();
        },
        function (e) {
            hideEnhanceFont();
            var font = getEnhanceFont();
            $(input).css(font);
        }
    );

    function showEnhanceFont() {
        var a = getBottomMidpoint(font_button);
        var s = getAbsoluteSize(font_enhance);
        var x = a[0] - s[0] / 2;
        var y = a[1];
        font_enhance.css("top", y);
        font_enhance.css("left", x);
        font_enhance.css("display", "block");
    };
    function hideEnhanceFont() {
        font_enhance.css("display", "none");
    };

    var sticker_container = $("#decoration-container-sticker");
    var sticker_button = $("#decoration-button-sticker");
    var sticker_enhance = $("#decoration-enhance-sticker");
    sticker_container.hover(
        function (e) {
            showEnhanceSticker();
        },
        function (e) {
            hideEnhanceSticker();
        }
    )

    function showEnhanceSticker() {
        var a = getBottomMidpoint(sticker_button);
        var s = getAbsoluteSize(sticker_enhance);
        var x = a[0] - s[0] / 2;
        var y = a[1];
        sticker_enhance.css("top", y);
        sticker_enhance.css("left", x);
        sticker_enhance.css("display", "block");
    }
    function hideEnhanceSticker() {
        sticker_enhance.css("display", "none");
    }

    function getEnhanceFont() {
        var font_bold = $("#decoration-font-bold-button");
        var font_italic = $("#decoration-font-italic-button");
        var font_size = $("#decoration-font-size");
        var font_forecolor = $("#decoration-font-forecolor");
        var font_backcolor = $("#decoration-font-backcolor");
        var font_family = $("#decoration-font-family");

        var font = {
            "font-size": font_size.val() + "px",
            "color": font_forecolor.val(),
            "background-color": font_backcolor.val(),
            "font-family": font_family.find("option:selected").text()
        }

        if (font_bold.attr("aria-pressed") == "true") {
            font["font-weight"] = "bold";
        } else {
            font["font-weight"] = "normal";
        }

        if (font_italic.attr("aria-pressed") == "true") {
            font["font-style"] = "italic";
        } else {
            font["font-style"] = "normal";
        }
        return font;
    }

    input.ondragenter = function (e) {
    }

    input.ondragover = function (e) {
        e.preventDefault();
    };

    input.ondrop = function (e) {
        e.preventDefault();
        var files = e.dataTransfer.files;
        insertInputFile(files[0]);
        return false;
    }

    function lookForClipboard(e) {
        var items = e.clipboardData.items;
        if (items) {
            // Loop through all items, looking for any kind of image
            for (var i = 0; i < 1; i++) {
                //console.log("clipboard type:" + items[i].type);
                if (items[i].type.indexOf("image") !== -1) {
                    // We need to represent the image as a file,
                    var file = items[i].getAsFile();
                    insertInputFile(file);
                }
            }
        }
    }

    function insertInputFile(file) {
        var reader = new FileReader();
        reader.onloadend = function (e) {
            var binary = '';
            var bytes = new Uint8Array(e.currentTarget.result);
            for (var i = 0; i < bytes.byteLength; i++) {
                binary += String.fromCharCode(bytes[i]);
            }

            var base64 = $.base64.encode(binary);
            var element = wrapFileType(file, base64, bytes);
            $(input).append(element);
        };
        reader.readAsArrayBuffer(file);
    }

    function wrapFileType (file, base64, bytes) {
        var mime = [
            "image/svg+xml",
            "image/png",
            "image/gif",
            "image/jpeg",
            "text/plain",
            "application/pdf"
        ];
        var type = [
            "<iframe style='border-color: transparent;' src=\"data:image/svg+xml;base64,",
            "<img style='max-width:100%;' src=\"data:image/png;base64,",
            "<img style='max-width:100%;' src=\"data:image/gif;base64,",
            "<img style='max-width:100%;' src=\"data:image/jpeg;base64,",
            "<pre>",
            "<div class='media {autoplay: true}' style='background-color: transparent; max-width:100%;'> <iframe style='width:100%;' allowfullscreen src=\"data:application/pdf;base64,"
        ];

        var element = "";
        switch (file.type) {
            case mime[0]:
                element = type[0] + base64 + "\">";
                break;
            case mime[1]:
                element = type[1] + base64 + "\">";
                break;
            case mime[2]:
                element = type[2] + base64 + "\">";
                break;
            case mime[3]:
                element = type[3] + base64 + "\">";
                break;
            case mime[4]:
                element = type[4] + Utf8ArrayToStr(bytes) + "</pre>";
                break;
            case mime[5]:
                element = type[5] + base64 + "\"></iframe></div>";
                break;
        }

        return element;
    }

    function showTitleCounter(e) {
        if (!document.hasFocus()) {
            document.title = "Chat-" + e;
        } else {
            document.title = "Chat";
        }
    }

    window.onfocus = function (e) {
        showTitleCounter(0);
    }
    window.onblur = function (e) {
        chatSession["unread"] = 0;
    }


</script>
</body>
</html>