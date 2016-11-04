<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header-bar-container" class="text-light background-dark">
    <div>
        <h1 style="margin-top: 10px;display: inline-block; font-weight: bold;" class="text-orange">PCMR </h1>
        <h2 style="display: inline-block; margin-left: 0.5em;">Eletronics</h2>
    </div>
    
    <div>
        <ul class="menu-list">
            <c:if test="${sessionScope.user.IsAdmin()}">
                <li>
                    <a class="button-link text-orange" href="/hypermedia/Admin">Admin</a>
                </li>
            </c:if>
            <li><a href="/hypermedia">Accueil</a></li>
            <li><a href="/hypermedia/list">Produits</a></li>
            <li style="cursor: pointer">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <a style="margin-right: 0.5em;" href="/hypermedia/order">Your orders</a>
                        <span style="font-size: 0.7em;">Bonjour</span> <b class="text-orange" style="font-size: 1.2em;">${sessionScope.user.getUsername()}</b>
                    </c:when>
                    <c:otherwise>
                      <a id="connect-button">Connexion</a>
                    </c:otherwise>
                </c:choose>
                
            </li>
        </ul>
    </div>
</div>
<c:if test="${applicationScope.featuredItem != null}">
    <center>
        <b>
            Today's featured item is 
            <span class="text-orange">${applicationScope.featuredItem.getName()}</span>
        </b>
    </center>
</c:if>

<%@include file="/misc/loginForm.jsp"%>
<script>
    $("#connect-button").on("click", function(){
        $("#login-popup").css("visibility","visible");
    })
</script>