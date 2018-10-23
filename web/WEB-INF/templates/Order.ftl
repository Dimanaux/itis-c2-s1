<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>Order food</#macro>
<#macro imports>
    <link href="./static/css/order.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(./static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">Links:</div>
                <div class="links">
                    <a href="#">
							<span class="link">
								<img src="./static/res/link1.jpg"/>
								<div class="link-text">
									<h3>2 Бeрега</h3>
								</div>
							</span>
                    </a>
                    <a href="#">
							<span class="link">
								<img src="./static/res/link2.jpg"/>
								<div class="link-text">
									<h3>AYATI</h3>
								</div>
							</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@page></@page>

