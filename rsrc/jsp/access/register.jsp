<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang=${lang}>
<head>

    <meta charset="UTF-8">
    <base href="${baseurl}" />
    <title>Register your access</title>
    <link rel="icon" href="img/${img_theme}/favicon.ico" type="image/x-icon"/>

    <link href="lib/animate.css/animate.css" rel="stylesheet">
    <link href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <script src="js/access/check_register.js?ver=3"></script>
</head>
<body>
    <div class = "container">
        <div class = "col-lg-12">
            <form method="get" action=${action_register_user}>
                <div class = "input-group" style="width:100%" id="input_group_user" data-trigger="manual" data-container="body" data-toggle="popover" data-placement="bottom"  data-content="default">
                    <span class="input-group-addon" style="width:20%">
                        ${lang_bundle["user"]}
                    </span>
                    <input class="form-control" id="input_user" type="text" name="user" placeholder='${lang_bundle["user_placeholder"]}' />
                </div>
                <br>

                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">
                        ${lang_bundle["password"]}
                    </span>
                    <input class="form-control" id = "input_password" type="password" name="password" placeholder='${lang_bundle["password_placeholder"]}'/>
                </div>
                <br>

                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">
                        ${lang_bundle["confirm_password"]}
                    </span>
                    <input class="form-control" id = "input_password_confirm" type="password" name="password_confirm" placeholder='${lang_bundle["confirm_password_placeholder"]}'/>
                </div>
                <br>

                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">
                        ${lang_bundle["gender"]}
                    </span>
                    <div class="btn-group" style="width:100%;" data-toggle="buttons">
                      <span class="btn btn-default active" style="width:50%;">
                        <input type="radio" name="gender" id="gender_male" autocomplete="off" value="male" checked>
                        ${lang_bundle["male"]}
                      </span>
                      <span class="btn btn-default" style="width:50%;">
                        <input type="radio" name="gender" id="gender_female" value="female" autocomplete="off">
                        ${lang_bundle["female"]}
                      </span>
                    </div>
                </div>
                <br>


                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">${lang_bundle["phone"]}</span>
                    <input class="form-control" id = "input_phone" type="text" name="phone" placeholder='${lang_bundle["phone_placeholder"]}'/>
                </div>
                <br>

                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">${lang_bundle["email"]}</span>
                    <input class="form-control" id = "input_mail" type="text" name="mail" placeholder='${lang_bundle["email_placeholder"]}'/>
                </div>
                <br>

                <div class = "input-group" style="width:100%">
                    <span class="input-group-addon" style="width:20%">${lang_bundle["address"]}</span>
                    <input class="form-control" id= "input_address" type="text" name="address" placeholder='${lang_bundle["address_placeholder"]}'/>
                </div>
                <br>
                <button class="btn btn-default" style="width:100%;" id = "button_submit" >${lang_bundle["submit"]}</button>
            </form>
        </div>
	</div>

    <script type="text/javascript">
        $(document).ready(check_register);
        $(function () {
            $("[data-toggle='popover']").popover()
        })
    </script>
</body>
</html>