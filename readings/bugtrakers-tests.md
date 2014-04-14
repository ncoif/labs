# install mysql
# create database for bugs
mysql -u root -p 
CREATE DATABASE bugs DEFAULT CHARACTER SET utf8;
mysql -u root -p bugs < mplayerbugzilla.sql

# migrate the database using bz2redmine script
# https://github.com/ralli/bz2redmine
# watch out for the custom settings, especially mapping of priority

# install ruby
# install rails
# install mysql2 (ruby binding)

# get the source from redmine
svn co http://svn.redmine.org/redmine/branches/2.3-stable redmine-2.3


# to reactivate the projects
mysql -u redmine -p redmine
update projects set status=1;

DROP DATABASE redmine;
CREATE DATABASE redmine CHARACTER SET utf8;
CREATE USER 'redmine'@'localhost' IDENTIFIED BY 'cnaf88redmine';
GRANT ALL PRIVILEGES ON redmine.* TO 'redmine'@'localhost';

RAILS_ENV=production rake db:migrate
RAILS_ENV=production rake redmine:load_default_data

# create the bugzilla database
- install mysql
- in my.cnf, change max_allowed_packet for something bigger (100M works for me)
- CREATE DATABASE bugs DEFAULT CHARACTER SET utf8;
- mysql -u root -p bugs < mplayerbugzilla.sql 

# create trac user in the database
- CREATE DATABASE trac DEFAULT CHARACTER SET utf8 COLLATE utf8_bin; 
- GRANT ALL ON trac.* TO tracuser@localhost IDENTIFIED BY 'password'
- connector: mysql://tracuser:password@localhost/trac

# install python 2.6
# install mysqldb (pythin26 connector)
# install trac

# systemd jail for testing
sudo systemd-nspawn -bD /home/nicolas/hacking/redmine/redmine-jail
