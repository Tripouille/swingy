FROM alpine
EXPOSE 80 5432
RUN apk -U upgrade && apk add postgresql-dev postgresql
RUN mkdir /run/postgresql && chown postgres:postgres /run/postgresql/

#phppgadmin
RUN apk add lighttpd php7-common php7-session php7-iconv php7-json php7-gd php7-curl \
php7-xml php7-mysqli php7-imap php7-cgi fcgi php7-pdo php7-pdo_mysql php7-soap \
php7-xmlrpc php7-posix php7-mcrypt php7-gettext php7-ldap php7-ctype php7-dom \
postgresql postgresql-client php-pgsql php-mbstring
COPY lighttpd.conf /etc/lighttpd/lighttpd.conf
RUN wget -P /var/www/localhost/htdocs https://github.com/vrana/adminer/releases/download/v4.8.0/adminer-4.8.0.php \
	&& mv /var/www/localhost/htdocs/adminer-4.8.0.php /var/www/localhost/htdocs/index.php \
	&& mkdir /var/run/lighttpd \
    && touch /var/run/lighttpd/php-fastcgi.socket \
    && chown -R lighttpd:lighttpd /var/run/lighttpd

COPY swingy.sql /
COPY db.sh /
ENTRYPOINT [ "sh", "/db.sh" ]