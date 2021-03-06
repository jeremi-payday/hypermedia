

<div id="login-popup" class="normalized-form-popup" style="visibility: hidden;">
    <div class="normalized-form-group active" id="login-form">
        <div class="normalized-form-section" id="login-from-title">
            <h1>
                Log in
            </h1>
        </div>
        <div class="normalized-form-section">
            <div class="login-error-msg" style="visibility: hidden;">

            </div>
            <div>
                <label for="username">Username:</label><input type="text" id="login-username" name="username"/>
            </div>
            <div>
                <label for="password">Password:</label><input type="password" id="login-password" name="password"/>
            </div>
            <div>
                <button id="login-btn" class="normalized-form-button btn background-orange text-white">
                    Log in
                </button>
            </div>
            <div style="margin-top: 2em;">
                <a class="button-link" id="from-login-to-register">Not registered yet?</a>
            </div>
        </div>

    </div>
    
    <div class="normalized-form-group" id="register-form">
        <div class="normalized-form-section" id="register-from-title">
            <h1>
                Register ${sessionScope.user.getUsername()}
            </h1>
        </div>
        <div class="normalized-form-section">
            <div class="register-error-msg" style="visibility: hidden;">

            </div>
            <div>
                <label for="username">Username:</label><input type="text" id="register-username" name="username"/>
            </div>
            <div>
                <label for="password">Password:</label><input type="password" id="register-password" name="password"/>
            </div>
            <div>
                <label for="firstname">First name:</label><input type="text" id="register-firstname" name="firstname"/>
            </div>
            <div>
                <label for="lastname">Last name:</label><input type="text" id="register-lastname" name="lastname"/>
            </div>
            <div>
                <label for="address">Address:</label><input type="text" id="register-address" name="address"/>
            </div>
            <div>
                <label for="age">Age:</label><input type="text" id="register-age" name="age"/>
            </div>
            <div>
                <button id="register-btn" class="normalized-form-button btn background-orange text-white">
                    Register & Sign in
                </button>
            </div>
            <div style="margin-top: 1em;">
                <a class="button-link" id="from-register-to-login">
                    Back to login
                </a>
            </div>
        </div>
    </div>
   
</div>

<script>
    
    $("#login-btn").click(function(){
        var formcontainer = $(this).closest(".normalized-form-popup");
        $.ajax({
            url: "login",
            type: "post",
            data:{
                username: $("#login-username").val(),
                password: $("#login-password").val()
            },
            success: function(){
                window.location.reload();
            },
            error: function(){
                formcontainer.find(".login-error-msg").text("Something wrong happened..");
                formcontainer.find(".login-error-msg").css({
                    visibility: "visible",
                    color: "red"
                });
            }
        });
    });
    
    $("#register-btn").click(function(){
        var formcontainer = $(this).closest(".normalized-form-popup");
        $.ajax({
            url: "register",
            type: "post",
            data:{
                username: $("#register-username").val(),
                password: $("#register-password").val(),
                firstname: $("#register-firstname").val(),
                lastname: $("#register-lastname").val(),
                address: $("#register-address").val(),
                age: $("#register-age").val()
            },
            success: function(data){
                response = $.parseJSON(data);
                var color = 'red';
                if(response['state'] === 'success'){
                    color = 'blue';
                    $("#register-form").removeClass("active");
                    $("#login-form").addClass("active");
                    formcontainer.find(".login-error-msg").text(response['message']);
                    formcontainer.find(".login-error-msg").css({
                        visibility: "visible",
                        color: color
                    });
                }
                formcontainer.find(".register-error-msg").text(response['message']);
                formcontainer.find(".register-error-msg").css({
                    visibility: "visible",
                    color: color
                });
            },
            error: function(){
                formcontainer.find(".error-msg").text("Something went wrong...");
                formcontainer.find(".error-msg").css({
                    visibility: "visible",
                    color: "red"
                });
            }
        });
    });
    
    $("#from-login-to-register").on("click", function(){
        $("#login-form").removeClass("active");
        $("#register-form").addClass("active");
    });
    $("#from-register-to-login").on("click", function(){
        $("#register-form").removeClass("active");
        $("#login-form").addClass("active");
    });
    
</script>