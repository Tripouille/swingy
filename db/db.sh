#!/bin/sh
su postgres -c "cd && mkdir data && chmod 0700 data && initdb -D data && pg_ctl start -D data && echo 'create database swingy;' | psql"
/usr/sbin/lighttpd -f /etc/lighttpd/lighttpd.conf
tail -f /dev/null