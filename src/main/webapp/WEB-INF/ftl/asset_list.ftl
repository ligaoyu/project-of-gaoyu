<html>  
  <head>  
        <title>freemarker测试</title>  
    </head>  
    <body>  
        <#list list as l>
        	${l.id},
        	${l.name}, 
        	${l.creation?date}, 
        	${l.lastModified?date}, 
        	${l.removed?string("ok","no")}, 
        	${l.assetNo}, 
        	${l.location }
        	<br/>
        </#list>
    </body>  
</html> 