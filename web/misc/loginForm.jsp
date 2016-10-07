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
            <label for="password">Password:</label><input id="password" type="password" name="password"/>
        </div>
        <div>
            <button id="login-btn" class="normalized-form-button btn background-orange text-white">
                Log in
            </button>
        </div>
        
    </div>
</div>

<script>
    $(".normalized-form-button#login-btn").on("click",function(){
        var formcontainer = $(this).closest(".normalized-form-popup");
        $.ajax({
            url: "www.google.ca",
            type: "post",
            data:{
                username: formcontainer.find("#username"),
                password: formcontainer.find("#password")
            },
            success: function(res){
                
            },
            error: function(res){
                var error = JSON.parse(res);
                alert();
                formcontainer.find(".error-msg").text("error.message");
                formcontainer.find(".error-msg").css({
                    visibility: "visible",
                    color: "red"
                });
                
            }
        });
    });
</script>