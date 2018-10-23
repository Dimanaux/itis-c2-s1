<#-- @ftlvariable name="posts" type="java.util.List<app.db.models.Post>" -->
<#macro recipes_view>
    <div class="recipes-bar__top">
        <div class="label">Posts:</div>
        <a href="/posts/new" class="add-recipe"><span class="plus">+</span> Add a post</a>
    </div>
    <div class="list">
        <#list posts as post>
            <div>
                <a href="/posts/${post.id}">
                    <span class="item">
                        <img src="./static/res/item1.jpg" alt="item1">
                        <span class="item-text">
                            <h3 class="heading">${post.title}</h3>
                            <p>${post.text.substring(0, 65)}...</p>
                        </span>
                        <small>${post.date}</small>
                    </span>
                </a>
            </div>
        </#list>
    </div>
</#macro>