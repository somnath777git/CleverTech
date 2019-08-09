<#import "/spring.ftl" as spring>
<html>
<h1>My Category 2</h1>
<ul>
<#list category2 as cat>
    <li>${cat}</li>
</#list>
</ul>
<br>
<a href="/keycloak/logout">Logout</a>
<br>
<br>
<p>
<a href="/" >Back to Home</a>
</p>
</html>