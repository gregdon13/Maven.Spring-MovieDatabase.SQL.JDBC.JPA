select * from movies where genre = 'Sci-Fi';

select * from movies where imdb_score >= 6.5;

select * from movies where rating = 'G' or rating = 'PG' and runtime < 100;

select genre, avg(runtime) from movies where imdb_score < 7.5 group by genre;

update movies set rating = 'R' where title = 'Starship Troopers';

select id, imdb_score from movies where genre = 'Horror' or genre = 'Documentary';

select avg(imdb_score), max(imdb_score), min(imdb_score), rating from movies group by rating;

select avg(imdb_score), max(imdb_score), min(imdb_score), rating from movies group by rating having count(*) > 1;

delete from movies where rating = 'R';


-- HOME/PERSON
select * from person left join home on home.id=person.home_id order by home.id;

select * from home left join person on person.home_id = home.id where home.address = '11 Essex Dr. Farmingdale, NY 11735';

update person set home_id = 4 where last_name = 'Figueroa';

update home left join person on person.home_id = home.id set home.home_number = '123-4567' where person.id = 2;

select home_id from person where first_name = 'Jane' and last_name = 'Smith';