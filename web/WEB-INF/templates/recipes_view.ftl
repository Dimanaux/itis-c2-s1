<#macro recipes_view>
    <div class="recipes-bar__top">
        <div class="label">User recipes:</div>
        <a href="create_recipe.html" class="add-recipe"><span class="plus">+</span> Add recipe</a>
    </div>
    <div class="list">
        <a href="recipe.html">
            <span class="item">
                <img src="./static/res/item1.jpg" alt="item1">
                <span class="item-text">
                    <h3 class="heading">Classic Russian Borsch</h3>
                    <p>Ingredients:</p>
                    <ul class="ingredients">
                        <li>meat</li>
                        <li>potato</li>
                        <li>cabbage</li>
                        <li>carrot</li>
                        <li>onion</li>
                        <li>parsley</li>
                    </ul>
                </span>
            </span>
        </a>
    </div>
</#macro>