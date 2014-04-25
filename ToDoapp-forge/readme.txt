Project generated entirely with JBoss Forge 2.5

Commands executed:

project-new --named ToDoapp-forge --topLevelPackage com.wordpress.infow.todoforge --finalName todoappforge

servlet-setup ; cdi-setup ; jpa-setup --container WILDFLY ; rest-setup ; constraint-setup ; faces-setup ; ejb-setup ; scaffold-setup ;

jpa-new-entity --named User --targetPackage com.wordpress.infow.todoforge.entities

jpa-new-entity --named Task --targetPackage com.wordpress.infow.todoforge.entities

jpa-new-field --named title --type String --length 30

jpa-new-field --named description --type String --length 150

jpa-new-field --named completed --type boolean

cd ../User.java

jpa-new-field --named name --type String --length 30

jpa-new-field --named tasks --type com.wordpress.infow.todoforge.entities.Task --relationshipType One-to-Many

constraint-add --onProperty name --constraint Size --max 30

cd ../Task.java

constraint-add --onProperty title --constraint Size --max 30

constraint-add --onProperty description --constraint Size --max 150

scaffold-generate --targets com.wordpress.infow.todoforge.entities.User

scaffold-generate --targets com.wordpress.infow.todoforge.entities.Task

rest-generate-endpoints-from-entities --targets com.wordpress.infow.todoforge.entities.User --packageName com.wordpress.infow.todoforge.rest

rest-generate-endpoints-from-entities --targets com.wordpress.infow.todoforge.entities.Task --packageName com.wordpress.infow.todoforge.rest

-------------------------------------------------------------------------------
Testing results:
- mvn clean package
- Deploy on WildFly
	- http://localhost:8080/todoappforge/
	- http://localhost:8080/todoappforge/rest/users
	- http://localhost:8080/todoappforge/rest/tasks

-------------------------------------------------------------------------------
Install AngularJS

addon-git-install --url https://github.com/forge/angularjs-addon.git

-------------------------------------------------------------------------------
Next steps (Not done yet):
- scaffold-setup --provider AngularJS
- scaffold-generate --provider AngularJS --targets com.wordpress.infow.todoforge.entities.User
- scaffold-generate --provider AngularJS --targets com.wordpress.infow.todoforge.entities.Task

-------------------------------------------------------------------------------
Based on:
- https://github.com/javaee-samples/forge-samples/
- http://blog.arungupta.me/2014/04/rapid-javaee-development-forge2/