FROM php:8.2-cli

RUN apt-get update && apt-get install -y git unzip && \
    curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

WORKDIR /app
COPY composer.json .
RUN composer install --no-interaction --no-dev --optimize-autoloader

COPY . .

EXPOSE 80
CMD ["php", "-S", "0.0.0.0:80", "-t", "public"]
