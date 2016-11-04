<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="panier">
    <div id="panier-toggle" class="border-dark">
        <img src="img/icons/cart.png">
       
        <c:set var="totalItemCount" value="${sessionScope.cart.getTotalItemCount()}"/>
        <c:if test="${totalItemCount > 0}">
            <div id="panier-counter">
                <c:choose>
                    <c:when test="${totalItemCount > 99}">
                        <span class="text-orange" style="font-weight: bold; font-size: 0.8em;">99+</span>
                    </c:when>
                    <c:otherwise>
                        <span class="text-orange" style="font-weight: bold;"><c:out value="${totalItemCount}"/></span>
                    </c:otherwise>
                </c:choose>
                
                
            </div>

        </c:if>        
    </div>
            
    <c:set var="panierOut" value=""/>
    <c:if test="${sessionScope.cartTabOut}">
        <c:set var="panierOut" value="panier-out"/>
    </c:if>
        <div id="panier-contents" class="${panierOut} border-dark">
            <center>
                <h3 style="margin-top: 0.5em;">Cart</h3>
            </center>
            <c:if test="${sessionScope.cart != null && !sessionScope.cart.getItems().isEmpty()}">

                <c:forEach items="${sessionScope.cart.getItems().keySet()}" var="cart_item_id">
                    <c:set var="item" value="${sessionScope.cart.getItems().get(cart_item_id)}"/>

                    <div class="cart-item" data-id="${item[0].getId()}" data-name="${item[0].getName()}" data-desc="${item[0].getDescription()}" data-image="${item[0].getImage()}" data-qtty="${item.size()}">
                        <img class="cart-item-img" src="${item[0].getImage()}">
                        <div class="cart-item-name">
                            <a href="#"><c:out value="${item[0].getName()}"/></a>
                        </div>
                        <div class="cart-item-quantity">
                            <c:out value="${item.size()}"/>
                        </div>
                        <button class="cart-item-remove" value="${item[0].id}">-</button>

                    </div>        
                </c:forEach>
                <div class="cart-item ignore" style="padding-right: 0.5em;">
                    <button id="order" class="normalized-form-button btn background-orange text-white">Place order</button>
                </div>
            </c:if>
        </div>
</div>
            
<script>
    $("#order").on("click", function(){
        var ids = [];
        var qtys = [];
        $("#panier-contents .cart-item:not(.ignore)").each(function(){
            ids.push($(this).data("id"));
            qtys.push($(this).data("qtty"));
        });
        $.ajax({
            url: "order",
            type: "POST",
            data:{
                ids: ids,
                qttys: qtys
            }, 
            success: function(){
                location = "order";
            }
        });
        
    });
</script>
        