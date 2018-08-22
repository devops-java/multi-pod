FROM ubuntu:16.04
ENV MYSQL_PWD password1
RUN echo "mysql-server mysql-server/root_password password $MYSQL_PWD" | debconf-set-selections
RUN echo "mysql-server mysql-server/root_password_again password $MYSQL_PWD" | debconf-set-selections

RUN apt-get update
RUN apt-get -y install mysql-server-5.7

RUN sed -i -e "$ a [client]\n\n[mysql]\n\n[mysqld]"  /etc/mysql/my.cnf && \
	sed -i -e "s/\(\[client\]\)/\1\ndefault-character-set = utf8/g" /etc/mysql/my.cnf && \
	sed -i -e "s/\(\[mysql\]\)/\1\ndefault-character-set = utf8/g" /etc/mysql/my.cnf && \
	sed -i -e "s/\(\[mysqld\]\)/\1\ninit_connect='SET NAMES utf8'\ncharacter-set-server = utf8\ncollation-server=utf8_unicode_ci\nbind-address = 0.0.0.0/g" /etc/mysql/my.cnf


ADD script.sh script.sh
ADD script.sql script.sql
RUN chmod 777 script.sql script.sh && sleep 1 && /script.sh
#RUN /run.sh

EXPOSE 3306
CMD ["/usr/bin/mysqld_safe"]

#ENTRYPOINT /etc/init.d/mysql start && bash
#CMD service mysql start
#CMD /etc/init.d/mysql start && bash
