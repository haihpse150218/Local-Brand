create DATABASE LocalBrand
USE LocalBrand
GO
CREATE TABLE MembershipTier( 
	id INT IDENTITY(1,1) NOT NULL,
	rank VARCHAR(50) DEFAULT(NULL) CHECK(rank IN (NULL,'Gold','Silver','Copper', 'Platinum','SuperVIP')) NOT NULL,
	description NVARCHAR(50),
	discount FLOAT DEFAULT(0) CHECK(discount IN (0, 0.15, 0.3, 0.4, 0.6)) NOT NULL,
	minimum_coins INT DEFAULT(15000),
)
GO
