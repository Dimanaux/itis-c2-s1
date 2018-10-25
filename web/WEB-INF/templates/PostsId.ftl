<#-- @ftlvariable name="post" type="app.db.models.Post" -->
<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro imports>
    <script src="/static/js/posts_id.js" defer></script>
</#macro>

<#macro title>Post #${post.id}</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div>
                    <div>
                        <h3>${post.title}</h3>
                        <p>${post.text}</p>
                        <small>${post.date}</small>
                    </div>
                    <div>
                        <h3>Write a comment:</h3>
                        <label for="comment-text">Text:</label>
                        <textarea name="text" id="comment-text" cols="30" rows="10" required></textarea>
                        <button onclick="sendComment();">Comment!</button>
                    </div>
                    <div id="comments-list">
                        <h3>Comments:</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>


<@page></@page>
