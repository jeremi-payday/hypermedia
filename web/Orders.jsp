<%@include file="/misc/header.jsp" %>
<div class="main-container">
    <div class="width: 500px;">
        <center style="background: white;">
            <h2>
                Your orders
            </h2>
        </center>
        <div class="order-container" style="opacity: 1!important;">
            <c:forEach items="${orders}" var="orderItem">
                <c:set var="item" value="${orderItem.getItem()}"/>
                <div class="cart-item"  data-id="${item.getId()}" data-name="${item.getName()}" data-desc="${item.getDescription()}" data-image="${item.getImage()}" data-qtty="1">
                    <img class="cart-item-img" src="img/items/${item.getImage()}">
                    <div class="cart-item-name">
                        <a href="#"><c:out value="${item.getName()}"/></a>
                    </div>

                    <button class="item-add-btn add btn background-orange text-white" value="${item.id}">Add to cart</button>

                </div>        
            </c:forEach>

        </div>
    </div>
</div>
<script>

        $(".add").on("click", function(){
            var itemid = $(this).closest(".cart-item").data("id");
            var itemname = $(this).closest(".cart-item").data("name");
            var itemdesc = $(this).closest(".cart-item").data("desc");
            var itemimage = $(this).closest(".cart-item").data("image");

            $.ajax({
                url: "cart/add",
                type: "POST",
                data:{
                    id: itemid,
                    name: itemname,
                    desc: itemdesc,
                    img: "img/items/"+itemimage
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