# Telegram Bot
This project contains a Telegram Bot to inform you about the price from specific stocks daily.

Bot comments:

        \start: add your Telegram chatID
        \stop: remove your Telegram chatID
        \add: add stock 
        \rm: remove stock

## Setup
### Telegram Bot
1. Create a Telegram Bot
2. Add 'config.properties' to resource folder and add your BOT_TOKEN

### AlphaVantage (stock API)
3. Generate a token from AlphaVantage and add your token to the 'config.properties' file

### Database
4. install PostgresDB
5. create 'telegram_bot_db' a database with chats and stocks table

        chats
            id Interger
            name character varying 

        stocks
            stock character varying 

6. create file 'db.properties' in resource folder and add: 

        db.url=jdbc:yourUrl
        db.username=yourUser
        db.password=yourPwd





start the db:
sudo -i -u postgres psql

start .jar
java -jar file.jar