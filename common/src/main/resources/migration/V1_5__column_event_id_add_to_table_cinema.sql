alter table m_cinema
	add event_id bigint;

alter table m_cinema
	add constraint m_cinema_m_event_id_fk
		foreign key (event_id) references m_event
			on delete cascade;