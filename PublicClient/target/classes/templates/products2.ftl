<#import "/spring.ftl" as spring>
<html>
<h1>My Products 2</h1>
<ul>
<#list products2 as product>
    <li>${product}</li>
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