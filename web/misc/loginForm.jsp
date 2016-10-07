<div class="normalized-form-popup">
    <div class="normalized-form-section">
        <h1>
            Log in
        </h1>
    </div>
    <div class="normalized-form-section">
        <div class="error-msg" style="visibility: hidden;">
        
        </div>
        <div>
            <label for="username">Username:</label><input type="text" id="username" name="username"/>
        </div>
        <div>
            <label for="password">Password:</label><input type="password" id="password" name="password"/>
        </div>
        <div>
            <button id="login-btn" class="normalized-form-button btn background-orange text-white">
                Log in
            </button>
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
                username: $("#username").val(),
                password: $("#password").val()
            },
            success: function(){
                
            },
            error: function(){
                formcontainer.find(".error-msg").text("Something wrong happened..");
                formcontainer.find(".error-msg").css({
                    visibility: "visible",
                    color: "red"
                });
            }
        });
    });
</script>