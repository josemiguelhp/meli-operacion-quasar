# meli-operacion-quasar

# Correr local
Asegurese que tiene instalado +java 8 y el JAVA_HOME configurado, use
`./gradlew bootRun` para correr el proyecto local

para configurar el JAVA_HOME en macOs es necesario que identifique en que lugar se instalo, luego ejecute los siguientes comando como ejemplo para asegurarse (coloque su jdk home dir):

###### `export JAVA_HOME=/Users/jose/Library/Java/JavaVirtualMachines/corretto-1.8.0_282/Contents/Home`
###### `echo $JAVA_HOME`

# Herramientas a utilizar
## Host
Decidi hostearlo en Azure porque nunca lo habia usado y queria experimentar a la par de hacer el challenge
`https://meli-operacion-quarsar.azurewebsites.net/`
## CI/CD 
Como en las consignas se pidio que contemplara las buenas practicas, decidi configurar un pipeline de *github actions*
que despliegue el proyecto en azure, me hubiera gustado usar gitlab pipelines pero en los entregables se especificaba github.
Pretendia configurar en el pipeline de desarrollo y staging que desplegara en sus respectivos ambientes en azure, pero azure me exige tener una suscripcion
estandar o premium para usar espacios de implementacion. por lo que solo se desplegara master, en los otros pipelines solo se configurara que haga el build y corra los tests.
