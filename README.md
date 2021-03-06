# Api Súper Héroe
Api Súper Héroe CRUD
## Requerimientos
- Maven ([Apache Maven][maven], 3.6.3 recomendada), con la configuracion para nuestro repositorio de [Artifactory][artifactory].
- Java ([OpenJDK 11 (LTS)][adoptopenjdk])
- Project Lombok instalado en el IDE ([descarga][projectlombok])
- Apache Tomcat 9 ([descarga][Apache Tomcat 9])
- Libreria H2

Como alternativa a la instalación manual de Java y Maven, se puede usar [SDKMAN!][SDKMAN!].

Dependencias utilizadas .

```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.7.0</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.7.0</version>
			<scope>compile</scope>
		</dependency>
	</dependencies>
```

## Correr build localmente
Para correr bajar las dependencias, se debe verificar localmente con el siguiente comando:

```bash
$ cd ApiSuperHeroes/
$ mvn clean install
```

## Ver api cliente con Swagger2 
Una vez levantado el proyecto abrir un navegador:
```bash
 http://localhost:8080/swagger-ui.html
```
## Levantar con docker 

Generar el jar primero:

```bash
$ cd ApiSuperHeroes/
$ mvn clean install
$ docker build -t api-super-heroes .
$ docker run --rm -it -p 8080:8080 api-super-heroes
```

