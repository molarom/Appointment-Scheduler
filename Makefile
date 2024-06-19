DB_NAME 	:= c195
DB_USER 	:= root
DB_PASSWORD := password

dev-up:
	docker-compose -f infra/compose.yml up -d

dev-down:
	docker-compose -f infra/compose.yml down

dev-sql:
	docker-compose -f infra/compose.yml exec -it db \
		mysql -h 127.0.0.1 -P 3306 -u $(DB_USER) -p$(DB_PASSWORD) $(DB_NAME)

dev-logs:
	docker-compose -f infra/compose.yml logs db 
