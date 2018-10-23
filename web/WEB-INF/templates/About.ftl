<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>About</#macro>

<#macro imports>

</#macro>


<#macro content>
    <div class="wrapper" style="background-image: url(./static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">Developers: </div>
                <div class="devs">
                    <div class="dev">
                        <img src="./static/res/profile.jpg"/>
                        <div class="dev-text">
                            <h3>Viktor Barinov</h3>
                            <p>e-mail@mail.com</p>
                        </div>
                    </div>
                    <div class="dev">
                        <img src="./static/res/profile.jpg"/>
                        <div class="dev-text">
                            <h3>Viktor Barinov</h3>
                            <p>e-mail@mail.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@page></@page>
