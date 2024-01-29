postgres-build:
	docker run -d --name app-postgres-test \
		-p 5432:5432 \
		-e POSTGRES_USER=admin \
		-e POSTGRES_DB=fashion-shop \
    	-e POSTGRES_PASSWORD=password \
    	-v /Users/quandoan/Documents/m2_java_projects/.postgres/data/pgdata=/var/lib/postgresql/data/pgdata \
    	-v /Users/quandoan/Documents/m2_java_projects/.postgres/data/:/var/lib/postgresql/data \
    	postgres
postgres-up: postgres-build
	docker start app-postgres
clean:
	./gradlew clean
build:
	./gradlew quarkusBuild
run:
	./gradlew quarkusDev
product-run:
	./gradlew product-service:quarkusDev
cert:
	openssl req -newkey rsa:2048 -nodes -keyout /Users/quandoan/Documents/m2_java_projects/fashion_shop/src/main/resources/.ssl/ca.key -x509 -days 365 -out /Users/quandoan/Documents/m2_java_projects/fashion_shop/src/main/resources/.ssl/ca.crt