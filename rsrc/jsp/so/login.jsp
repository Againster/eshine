<div class="container-fluid">
    <div class="row">
        <form method="get" action="${action_login}">
            <div class="input-group" style="width:100%" id="input_group_user" data-trigger="manual"
                 data-container="body" data-toggle="popover" data-placement="bottom" data-content="default">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-user"></span>
                    </span>
                <input class="form-control" id="input_user" type="text" name="user" value="${input_user}"
                       placeholder="${lang_bundle["user_placeholder"]}">
            </div>
            <br>

            <div class="input-group" style="width:100%">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-lock"></span>
                    </span>
                <input class="form-control" id="input_password" type="password" name="password" value="${input_password}"
                       placeholder="${lang_bundle["password_placeholder"]}"/>
            </div>
            <br>

            <div style="-webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none;">
                <input id="auto_signin" name="auto_signin" type="checkbox" style="vertical-align: top;" ${input_auto_checked}>
                <label for="auto_signin" style="font-weight: normal;">${lang_bundle["auto_signin"]}</label>
                <a style="float: right; padding-left: 15px;" href="${href_forget_password}">${lang_bundle["forget_password"]}</a>
                <a style="float: right;" href="${href_signup}">${lang_bundle["signup"]}</a>
            </div>

            <button class="btn btn-success" style="width:100%;" id="button_signin">
                <span class="glyphicon glyphicon-log-in"></span>
                ${lang_bundle["signin"]}
            </button>
        </form>
        <br>

        <div>
            <img src="lib/simple-icons-master/icons/tencentqq.svg" alt="QQ" class="img-rounded" style="height: 20px;" draggable="false">
            <img src="lib/simple-icons-master/icons/sinaweibo.svg" alt="WeiBo" class="img-rounded" style="height: 20px;" draggable="false">
            <img src="lib/simple-icons-master/icons/wechat.svg" alt="WeChat" class="img-rounded" style="height: 20px;" draggable="false">
            <img src="lib/simple-icons-master/icons/facebook.svg" alt="Facebook" class="img-rounded" style="height: 20px;" draggable="false">
            <img src="lib/simple-icons-master/icons/twitter.svg" alt="Twitter" class="img-rounded" style="height: 20px;" draggable="false">
        </div>
    </div>
</div>
