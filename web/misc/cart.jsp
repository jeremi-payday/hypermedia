<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="panier">
    <div id="panier-toggle">
        <img src="img/icons/cart.png">
       
        <c:set var="totalItemCount" value="${sessionScope.cart.getTotalItemCount()}"/>
        <c:if test="${totalItemCount > 0}">
            <div id="panier-counter">
                <c:choose>
                    <c:when test="${totalItemCount > 99}">
                        99+
                    </c:when>
                    <c:otherwise>
                        <c:out value="${totalItemCount}"/>
                    </c:otherwise>
                </c:choose>
                
                
            </div>

        </c:if>        
    </div>
            
    <c:set var="panierOut" value=""/>
    <c:if test="${sessionScope.cartTabOut}">
        <c:set var="panierOut" value="panier-out"/>
    </c:if>
    <div id="panier-contents" class="${panierOut}">
        <center>
            <h3 style="margin-top: 0.5em;">Cart</h3>
        </center>
        <c:if test="${sessionScope.cart != null && !sessionScope.cart.getItems().isEmpty()}">

            <c:forEach items="${sessionScope.cart.getItems().keySet()}" var="cart_item_id">
                <c:set var="item" value="${sessionScope.cart.getItems().get(cart_item_id)}"/>

                <div class="cart-item" data-id="${item[0].getId()}" data-name="${item[0].getName()}" data-desc="${item[0].getDescription()}" data-image="${item[0].getImage()}">
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

        </c:if>
    </div>
</div>