<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid" style="padding:0px; font-family: 'Open Sans Light,Helvetica,arial,sans-serif'">
    <nav class="navbar navbar-default" style="margin:0px;">
        <div class="container-fluid"
             style="font-size: 18px; font-family: 'Open Sans Light,Helvetica,arial,sans-serif';">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${baseurl}" style="padding:0px;">
                    <img alt="Brand" class="img-circle" style="width:74px;position: relative;z-index:1000;"
                         src="img/theme_live/eshine.svg"
                         type="image/svg+xml" draggable="false">
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="">
                        <a href="chat">
                            Chat
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li>
                        <a href="explorer">Explorer</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">
                            Dropdown
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>

                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" aria-describedby="basic-addon2">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-search"></span>
                        </div>
                    </div>
                </form>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <button id = "signinbutton" class="btn btn-primary" type="button" >
                            Sign in</button>
                        <div id="signinbutton_popover" style="display: none">
                            <c:import url="/jsp/so/login.jsp"></c:import>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Help<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Join in</a></li>
                            <li><a href="#">About us</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Version</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

<script>
    $('[data-toggle="popover"]').popover();
    var signbutton_popover = $('#signinbutton').popover({
        content: function () {return $("#signinbutton_popover").html()},
        trigger: 'manual',
        html: 'true',
        placement: 'bottom',
        container: 'body'
    });

    var showPopover = function () {
        $(this).popover('show');
        $("#" + $(this).attr("aria-describedby")).hover(null, function() {
            $(this).popover("hide");
        });

    };
    var hidePopover = function () {
        $(this).popover('hide');
    };

    signbutton_popover.mouseover(showPopover);
</script>