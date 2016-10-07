<div id="header-bar-container" class="text-light background-dark">
    <div>
        <h1 style="margin-top: 10px;display: inline-block; font-weight: bold;" class="text-orange">PCMR </h1>
        <h2 style="display: inline-block; margin-left: 0.5em;">Eletronics</h2>
    </div>
    
    <div>
        <ul class="menu-list">
            <li><a href="/hypermedia">Accueil</a></li>
            <li><a href="/hypermedia/list">Produits</a></li>
            <li style="cursor: pointer">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
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

<%@include file="/misc/loginForm.jsp"%>
<script>
    $("#connect-button").on("click", function(){
        $("#login-popup").css("visibility","visible");
    })
</script>