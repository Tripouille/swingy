#!/bin/sh
su postgres -c "cd && mkdir data && chmod 0700 data && initdb -D data \
				&& echo 'host all all 0.0.0.0/0 trust' >> /var/lib/postgresql/data/pg_hba.conf \
				&& echo \"listen_addresses='*'\" >> /var/lib/postgresql/data/postgresql.conf \
				&& pg_ctl start -D data && echo 'create database swingy;' | psql \
				&& echo 'CREATE TABLE ahero (name VARCHAR(100) PRIMARY KEY, level INT, experience BIGINT);' | psql swingy"
/usr/sbin/lighttpd -f /etc/lighttpd/lighttpd.conf
tail -f /dev/null