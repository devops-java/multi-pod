MYSQL_ROOT=root
MYSQL_PASS=password1
/etc/init.d/mysql start
echo $MYSQL_PASS;

if [ $MYSQL_PASS ]
then
  mysql -u "$MYSQL_ROOT" -p"$MYSQL_PASS" -e "SHOW DATABASES"
else
  mysql -u "$MYSQL_ROOT" -e "SHOW DATABASES"
fi

mysql -u "$MYSQL_ROOT" -p"$MYSQL_PASS" -e "CREATE DATABASE MYDB"
mysql -u "$MYSQL_ROOT" -p"$MYSQL_PASS" -e "SHOW DATABASES"
mysql -u "$MYSQL_ROOT" -p"$MYSQL_PASS" MYDB < script.sql
