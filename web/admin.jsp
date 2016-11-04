<%@include file="/misc/header.jsp" %>
<div class="main-container">
    <div>
        Today's featured item 
        <select id="featured-select">
            <c:forEach items="${applicationScope.items}" var="item">
                <c:choose>
                    <c:when test="${applicationScope.featuredItem != null && item.getId() == featuredItem.getId()}">
                        <option selected value="${item.getId()}" style="background-image:url(${item.getImage()})">${item.getName()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${item.getId()}" >${item.getName()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            
        </select>
    </div>
    
</div>

<script>
    $("#featured-select").on("change", function(){
        var selId = $(this).val();
        $.ajax({
           url: "Admin",
            type: "POST",
            data:{
                id: selId,
            }, 
            success: function(){
                location.reload();
            }
        });
        
    });
    
</script>