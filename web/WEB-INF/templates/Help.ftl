<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>Help</#macro>

<#macro imports>
    <link href="./static/css/help.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(./static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">FAQ:</div>
                <div class="list">
                    <div class="spoiler disabled" id="question 1">
                        <h2 class="question-heading">Lorem ipsum?</h2>
                        <p class="question-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                            eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                    <div class="spoiler disabled" id="question 2">
                        <h2 class="question-heading">Lorem ipsum?</h2>
                        <p class="question-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                            eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                    <div class="spoiler disabled" id="question 3">
                        <h2 class="question-heading">Lorem ipsum?</h2>
                        <p class="question-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                            eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@page></@page>

