CREATE TABLE  SAMPLE
(
    county_fips  NUMBER ,
    county_name   VARCHAR(200),
    state_name   VARCHAR(200),
    date   VARCHAR,
    county_vmt   NUMBER ,
    baseline_jan_vmt   NUMBER,
    percent_change_from_jan   NUMBER,
    mean7_county_vmt   NUMBER,
    mean7_percent_change_from_jan   NUMBER,
    date_at_low   VARCHAR,
    mean7_county_vmt_at_low NUMBER,
    percent_change_from_low NUMBER
)as select * from CSVREAD('classpath:04_SAMPLE_vmt_county.csv');

ALTER TABLE SAMPLE ADD id bigint auto_increment;


