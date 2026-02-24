# Veera-WizzAIrAPP-mobile-automation-framework
Executing on local or remote 
mvn clean install -Dexecution.type=local
mvn clean install -Dexecution.type=remote

Screenshot Options
mvn clean install -Dscreenshot=only.pass
mvn clean install -Dscreenshot=only.fail
mvn clean install -Dscreenshot=all

Platform Options
mvn clean install -Dplatform=android
mvn clean install -Dplatform=ios

Sauce Options
mvn clean install -Dsauce.url=sauce-url -Dsauce.username=username -Dsauce.password=pasword

Environment
mvn clean install -Denv=stg

Report Name
mvn clean install -Dreport.name="Automation Test Summary - The SDET"

Executing all the tests
All the features & it's scenarios can be executed by using below command.
mvn clean install
