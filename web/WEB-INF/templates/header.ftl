<#-- @ftlvariable name="user" type="app.db.models.User" -->
<#macro header>
<header>
    <div class="brand">BR4ND</div>
    <div class="search">
        <span class="search-border">
            <input type="search" id="search-input" placeholder="Search">
            <button type="submit" id="search-button">&#128269;</button>
        </span>
    </div>
    <div class="bar__right">
        <#if user??>
            <a href="/profile" class="topnav-button">Profile</a>
            <a class="topnav-button" onclick="logout();">Log out</a>
        <#else>
            <a href="/auth" class="topnav-button">Log In</a>
            <a href="/registration" class="topnav-button">Sign Up</a>
        </#if>
    </div>
</header>
</#macro>
