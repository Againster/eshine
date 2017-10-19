<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=${lang}>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="${baseurl}"/>
    <title>Explore</title>

    <link rel="icon" href="img/${img_theme}/favicon.ico" type="image/x-icon"/>

    <link href="lib/animate.css/animate.min.css" rel="stylesheet">
    <link href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="lib/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/jquery.base64/jquery.base64.js"></script>

    <script type="text/javascript" src="js/log/tbody_log_list.js?ver=1" charset="UTF-8"></script>
    <script type="text/javascript" src="js/explorer/upload.js?ver=1" charset="UTF-8"></script>
</head>
<body>

<c:import url="/jsp/so/navigator.jsp"></c:import>

<div class="container">

    <div class="col-lg-12">
        <div class="row">
            <form id="file_upload_form" action="explorer/doUpload" enctype="multipart/form-data" method="post">
                <input id="file_upload" style="display: none;" type="file" name="file_upload" accept="*" multiple>
                <label class="btn btn-success" style="width: 100%;" for="file_upload">${lang_bundle["file_upload"]}</label>
            </form>
        </div>
        <div class="row" id = "file_upload_progress" >
        </div>
    </div>

    <div>
        <ol class="breadcrumb">
            <li><a href="#">Home</a></li>
            <li><a href="#">Library</a></li>
            <li class="active">Data</li>
        </ol>
    </div>

    <div class="col-lg-12">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>${lang_bundle["index"]}</th>
                <th>${lang_bundle["file"]}</th>
                <th>${lang_bundle["size"]}</th>
            </tr>
            </thead>
            <tbody id="tbody_log_list">
            <c:set var="index" value="1"></c:set>
            <c:forEach items="${exploreAttribute}" var="attrs">
                <tr>
                    <td style="width:10%;">${index}</td>
                    <td><a href="${attrs.url}">${attrs.name}</a></td>
                    <td style="width:10%;">${attrs.length}</td>
                </tr>
                <c:set var="index" value="${index + 1}"></c:set>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">

    var xhr_map = new Map();
    var xhr_upload_map = new Map();

    $("#file_upload").change(function (e) {
        for (var i = 0; i < this.files.length; i++) {
            var progress = $(document.createElement("div"));
            progress.attr("class", "progress");

            var bar = $(document.createElement("div"));
            bar.attr("class", "progress-bar progress-bar-striped active");
            bar.attr("role", "progressbar");
            bar.attr("aria-valuenow", "0");
            bar.attr("aria-valuemin", "0");
            bar.attr("aria-valuemax", "100");
            bar.attr("style", "width:0%; float:none;");

            var label = $(document.createElement("div"));
            label.css("float", "left");
            label.css("color", "lightgrey");
            label.text(this.files[i].name + " 0.00K/s 0.00%");

            progress.append(label);
            progress.append(bar);

            $("#file_upload_progress").append(progress);
            var form = new FormData();
            form.append("files", this.files[i]);
            var xhr = new XMLHttpRequest();
            xhr.onerror = function (e) {
                console.log(e);
            }

            xhr.onload = function (e) {
                setTimeout(
                    function (t, event) {
                        $(xhr_map.get(t)["progress"]).remove();
                        xhr_map.delete(t);
                        xhr_upload_map.delete(t.upload);
                        if (xhr_map.size == 0) {
                            window.location.reload();
                        }
                    },
                    1000,
                    this,
                    e
                )
            }

            xhr.onloadstart = function (e) {
                xhr_map.get(this)["last_time"] = new Date();
                xhr_map.get(this)["last_loaded"] = 0;
            }

            xhr.upload.onprogress = function (e) {
                var info = xhr_upload_map.get(this);

                var p = e.loaded / e.total * 100;
                var last_loaded = info["last_loaded"];
                var delt_loaded = (e.loaded - last_loaded);
                var delt_time =  (new Date()).getTime() - info["last_time"].getTime();
                var upload_speed = (delt_loaded / 1024 ) / (delt_time / 1000);

                info["last_loaded"] = e.loaded;
                info["last_time"] = (new Date());
                $(info["bar"]).css("width", p + "%");
                $(info["label"]).text(xhr_upload_map.get(this)["file"].name + " " + upload_speed.toFixed(2) + "K/s " + p.toFixed(2) + "%");
            }

            xhr.onreadystatechange = function (e) {
                if (this.readyState === 4) {
                    console.log(this.response); //Outputs a DOMString by default
                }
            }


            var info = {
                "progress": progress,
                "bar": bar,
                "form": form,
                "file": this.files[i],
                "label": label,
                "last_loaded": null,
                "last_time": null
            }

            xhr_map.set(xhr, info);
            xhr_upload_map.set(xhr.upload, info);


            xhr.open("POST", "explorer/doUpload", true);
            xhr.send(form);
        }
    });
</script>
</body>
</html>
