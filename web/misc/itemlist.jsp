<div id="available-items">
    <c:forEach items="${items}" var="item">
        <div class="item background-white border-light" data-id="${item.getId()}" data-name="${item.getName()}" data-desc="${item.getDescription()}" data-image="${item.getImage()}">

            <div class="item-image-container">
                <img class="item-image" src="${item.getImage()}" alt="${item.getName()}">
            </div>
            <div class="item-name">
                <c:out value="${item.getName()}"/>
            </div>
            <div class="item-desc">
                <c:out value="${item.getDescription()}"/>
            </div>
            <div class="item-addtocart-container">
                <button class="item-add-btn add btn background-orange text-white" value="${item.id}">Add to cart</button>
            </div>

        </div>
    </c:forEach>
    
</div>