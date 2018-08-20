FROM ubuntu:16.04
ENV MYSQL_PWD password1
RUN echo "mysql-server mysql-server/root_password password $MYSQL_PWD" | debconf-set-selections
RUN echo "mysql-server mysql-server/root_password_again password $MYSQL_PWD" | debconf-set-selections
RUN apt-get update
RUN apt-get -y install mysql-server-5.7

ADD script.sh script.sh
ADD script.sql script.sql
RUN chmod 777 script.sql script.sh && sleep 1 && /script.sh
#RUN /run.sh

EXPOSE 3306
CMD ["/usr/bin/mysqld_safe"]

#ENTRYPOINT /etc/init.d/mysql start && bash
#CMD service mysql start
#CMD /etc/init.d/mysql start && bash
