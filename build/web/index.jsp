
<%@include file="/misc/header.jsp" %>

<div class="main-container">
    <%@include file="/misc/productsSidebar.jsp" %>
    <%@include file="/misc/itemlist.jsp"%>
    <%@include file="/misc/loginForm.jsp"%>
</div>
    <script>

        $(".add").on("click", function(){
            var itemid = $(this).closest(".item").data("id");
            var itemname = $(this).closest(".item").data("name");
            var itemdesc = $(this).closest(".item").data("desc");
            var itemimage = $(this).closest(".item").data("image");

            $.ajax({
                url: "cart/add",
                type: "POST",
                data:{
                    id: itemid,
                    name: itemname,
                    desc: itemdesc,
                    img: itemimage
                },
                success: function(){
                    if(!$("#panier-toggle").hasClass("panier-out")){
                        $.ajax({
                            url: "cart/misc",
                            type: "POST",
                            data:{
                                actionType: "toggleTab",
                                actionVal: "true"
                            }
                        });
                    }

                    location.reload();
                }
            });

        });
        $(".cart-item-remove").on("click", function(){
            var itemid = $(this).closest(".cart-item").data("id");
            var itemname = $(this).closest(".cart-item").data("name");
            var itemdesc = $(this).closest(".cart-item").data("desc");
            var itemimage = $(this).closest(".cart-item").data("image");

            $.ajax({
                url: "cart/delete",
                type: "POST",
                data:{
                    id: itemid,
                    name: itemname,
                    desc: itemdesc,
                    img: itemimage
                },
                success: function(){

                    location.reload();
                }
            });

        });

        $("#panier-toggle").on("click", function(){
            $("#panier-contents").toggleClass("panier-out");
            $.ajax({
                url: "cart/misc",
                type: "POST",
                data:{
                    actionType: "toggleTab",
                    actionVal: ""+$("#panier-contents").hasClass("panier-out")
                }
            });

        });
    </script>
<%@include file="/misc/footer.jsp" %>