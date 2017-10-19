<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang=${lang}>
<head>
    <title>WELCOME TO ESHINE</title>
    <c:import url="/jsp/so/head.jsp"></c:import>

    <script src="lib/jquery.base64/jquery.base64.js"></script>
    <script src="lib/jquery.media/jquery.media.js"></script>

    <script src="js/so/so.js?${res_arg}"></script>
</head>
<body>

<c:import url="/jsp/so/navigator.jsp"></c:import>

<div class="container-fluid" style="padding:0px; font-family: 'Open Sans Light,Helvetica,arial,sans-serif'">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="img/${img_theme}/homepage_slider_1.jpg?ver=1" type="image/jpeg" draggable="false">
                <div class="carousel-caption">
                    荒漠中的小精灵
                    酷热干旱都不怕
                </div>
            </div>
            <div class="item">
                <img src="img/${img_theme}/homepage_slider_2.jpg?ver=1" type="image/jpeg" draggable="false">
                <div class="carousel-caption">
                    强度与耐力的考验
                    突破自我 挑战极限
                </div>
            </div>
            <div class="item">
                <img src="img/${img_theme}/homepage_slider_3.jpg?ver=1" type="image/jpeg" draggable="false">
                <div class="carousel-caption">
                    满屏的粉色大长腿
                    鸟界的颜值担当
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>


</body>
</html>



