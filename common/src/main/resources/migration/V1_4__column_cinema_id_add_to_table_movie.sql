alter table m_movie
	add cinema_id bigint;

alter table m_movie
    add constraint m_movie_m_cinema_id_fk
	    foreign key (cinema_id) references m_cinema
		    on delete cascade;