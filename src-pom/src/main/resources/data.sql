-- Insert two venues
INSERT INTO venue (name, city, country) VALUES
  ('LSE', 'New York', 'USA'),
  ('TURQUOISE', 'London', 'UK');

-- Insert two members
INSERT INTO member (legal_name, description, address, venue_id, lei) VALUES
  ('RevolutBank', 'Musician', '123 Main St', 1, 'LEI123'),
  ('SomeStock', 'Speaker', '456 Elm St', 2, 'LEI456'),
  ('Topac', 'Speaker', '123 Bl St', 2, 'LEI351');

-- Insert two issuers
INSERT INTO issuer (legal_name, description, lei) VALUES
  ('Music Label Inc.', 'Record Label', 'LEI789'),
  ('Conference Organizers Ltd.', 'Event Organizer', 'LEI987'),
  ('Anew nama Ltd.', 'DOnknow', 'LEI187');

-- Insert two instruments
INSERT INTO instrument (currency, type, description, effective_date, status, issuer_id, isin) VALUES
  ('USD', 'Stock', 'Company A', '2023-07-01', 'Active', 1, '1293781239'),
  ('EUR', 'Bond', 'Government', '2023-07-01', 'Inactive', 2, '2193812983'),
  ('GBP', 'Stock', 'Company B', '2022-07-01', 'Active', 1, '12093129');
