/* 
	Master SQL Table Generator for Initial Setup.
    Run everything only when setting up DB environment for the first time.
    Copy and paste portions pertaining to table you're updating into mySQL 
    for any additional DB changes.
*/
START TRANSACTION;
USE `givenkind`;
    
    /*Dropping foreign keys*/
    
	alter table tblPasswordReset 
        drop 
        foreign key FK_3xd9s5vf2oet0n9yqxrr07752;

    alter table tblProfile 
        drop 
        foreign key FK_1dj81tdwastr43ld5hkc19qs7;

    alter table tblProfileUser 
        drop 
        foreign key FK_srmmyuy1ns8jq9pe8cemx30wj;

    alter table tblProfileUser 
        drop 
        foreign key FK_3fjlfegdmexpvk28iebtsyxmh;

    alter table tblUserProfileCategory 
        drop 
        foreign key FK_ga1x8wvmacwkhmhcujjem68of;

    alter table tblUserProfileCategory 
        drop 
        foreign key FK_7od3e92bt7qwq1nq9ywe2vsia;

    alter table tblUserRole 
        drop 
        foreign key FK_d48njwjs44p14ps4usurvmd0x;

    alter table tblUserRole 
        drop 
        foreign key FK_qnac7cvbxumkyv4t5ue67tiyk;

    alter table tblWishlistItem 
        drop 
        foreign key FK_19hl1icj2rvanel3bqdsk902w;

    alter table tblWishlistItem 
        drop 
        foreign key FK_78bahvj0h03nmtbskn4wshwua;
        
	alter table tblDonorListItem
		drop
        foreign key FK_tblProfile_ProfileId;
        
	alter table tblDonorListItem
		drop
        foreign key FK_TblItemCategory_ItemCategory;

    /*Dropping tables*/

    drop table if exists tblApprovalStatus;
    
    drop table if exists tblDonorListItem;

    drop table if exists tblItemCategory;

    drop table if exists tblNonProfitCategory;

    drop table if exists tblPasswordReset;

    drop table if exists tblProfile;

    drop table if exists tblProfileUser;

    drop table if exists tblRole;

    drop table if exists tblState;

    drop table if exists tblUserLogin;

    drop table if exists tblUserProfileCategory;

    drop table if exists tblUserRole;

    drop table if exists tblWishlistItem;
    
    drop table if exists tblActiveTransactions;
    
    drop table if exists tblCompletedTransactions;
    
    drop table if exists tblStatusCategory;


	/*Creating tables*/

    create table if not exists tblApprovalStatus (
        StatusId bigint not null auto_increment,
        StatusName varchar(255),
        primary key (StatusId)
    );

	create table if not exists tblDonorListItem (
		DonorListItemId bigint not null auto_increment,
        ItemName varchar(255),
        Quantity integer,
        ItemCondition text(511),
        FairMarketValue DOUBLE(10,2),
        Description text(511),
		TblProfile_ProfileId bigint,
        TblItemCategory_ItemCategory bigint,
        primary key (DonorListItemId)
    );

    create table if not exists tblItemCategory (
        CategoryId bigint not null auto_increment,
        CategoryName varchar(255),
        CategoryDescription varchar(255),
        primary key (CategoryId)
    );

    create table if not exists tblNonProfitCategory (
        CategoryId bigint not null auto_increment,
        CategoryDescription varchar(255),
        CategoryName varchar(255),
        primary key (CategoryId)
    );

    create table if not exists tblPasswordReset (
        Id bigint not null auto_increment,
        createdAt datetime,
        uniqueResetKey varchar(255),
        tblUserLogin_UserId bigint,
        primary key (Id)
    );

    create table if not exists tblProfile (
        ProfileId bigint not null auto_increment,
        EIN varchar(255),
        ModifiedBy varchar(255),
        AddressLine1 varchar(255),
        AddressLine2 varchar(255),
        ApprovalStatus varchar(255),
        City varchar(255),
        ContactEmail varchar(255),
        Country varchar(255),
        FullName varchar(255),
        IsPickupServiceAvailable boolean,
        LogoLocation varchar(255),
        MissionStatement varchar(255),
        ModifiedDate datetime,
        OrganizationName varchar(255),
        Phone1 varchar(255),
        Phone2 varchar(255),
        Phone3 varchar(255),
        TravelDistance double precision,
        WebSiteUrl varchar(255),
        ZipCode varchar(255),
        tblState_StateId bigint,
        primary key (ProfileId)
    );

    create table if not exists tblProfileUser (
        Id bigint not null auto_increment,
        tblProfile_ProfileId bigint,
        tblUserLogin_UserId bigint,
        primary key (Id)
    );

    create table if not exists tblRole (
        RoleId bigint not null auto_increment,
        RoleDescription varchar(255),
        RoleName varchar(255),
        primary key (RoleId)
    );

    create table if not exists tblState (
        StateId bigint not null auto_increment,
        StateAbbr varchar(255),
        StateName varchar(255),
        primary key (StateId)
    );

    create table if not exists tblUserLogin (
        UserId bigint not null auto_increment,
        CreatedDate datetime,
        isActive boolean,
        LastLoggedInDate datetime,
        LoginId varchar(255),
        ModifiedBy varchar(255),
        ModifiedDate datetime,
        PasswordHash varchar(255),
        primary key (UserId)
    );

    create table if not exists tblUserProfileCategory (
        Id bigint not null auto_increment,
        CreatedDate datetime,
        ModifiedBy varchar(255),
        ModifiedDate datetime,
        tblNonProfitCategory_CategoryId bigint,
        tblUserLogin_UserId bigint,
        primary key (Id)
    );

    create table if not exists tblUserRole (
        Id bigint not null auto_increment,
        CreatedDate datetime,
        ModifiedBy varchar(255),
        ModifiedDate datetime,
        tblUserLogin_UserId bigint,
        tblRole_RoleId bigint,
        primary key (Id)
    );

    create table if not exists tblWishlistItem (
        WishlistItemId bigint not null auto_increment,
        DateExpires datetime,
        Impact varchar(255),
        ItemName varchar(255),
        Notes varchar(255),
        QuantityDesired integer,
        TblProfile_ProfileId bigint,
        TblItemCategory_ItemCategory bigint,
        primary key (WishlistItemId)
    );

	create table if not exists tblActiveTransactions (
		ActiveTransactionId bigint not null auto_increment,
        Quantity integer,
		DonorProfileId bigint,
		NpProfileId bigint,
        StatusCategory bigint,
        DonorItemId bigint,
        WishlistItemId bigint,
        primary key (ActiveTransactionId)
    );
    
	create table if not exists tblCompletedTransactions (
        CompletedTransactionId bigint not null auto_increment,
        Quantity integer,
		DonorProfileId bigint,
		NpProfileId bigint,
		DonorListItemId bigint,
        WishlistItemId bigint,
        primary key (CompletedTransactionId)
    );
    
    create table if not exists tblStatusCategory (
		StatusCategoryId bigint not null auto_increment,
        StatausCategoryName varchar(255),
        primary key (StatusCategoryId)
    );


	/*Add foreign keys*/

    alter table tblPasswordReset 
        add index FK_3xd9s5vf2oet0n9yqxrr07752 (tblUserLogin_UserId), 
        add constraint FK_3xd9s5vf2oet0n9yqxrr07752 
        foreign key (tblUserLogin_UserId) 
        references tblUserLogin (UserId);

	alter table tblDonorListItem
		add index FK_tblProfile_ProfileId (tblProfile_ProfileId),
        add constraint FK_tblProfile_ProfileId
        foreign key (TblProfile_ProfileId)
        references tblProfile (ProfileId);

    alter table tblDonorlistItem 
        add index FK_TblItemCategory_ItemCategory (tblItemCategory_ItemCategory), 
        add constraint FK_TblItemCategory_ItemCategory
        foreign key (TblItemCategory_ItemCategory) 
        references tblItemCategory (CategoryId);

    alter table tblProfile 
        add index FK_1dj81tdwastr43ld5hkc19qs7 (tblState_StateId), 
        add constraint FK_1dj81tdwastr43ld5hkc19qs7 
        foreign key (tblState_StateId) 
        references tblState (StateId);

    alter table tblProfileUser 
        add index FK_srmmyuy1ns8jq9pe8cemx30wj (tblProfile_ProfileId), 
        add constraint FK_srmmyuy1ns8jq9pe8cemx30wj 
        foreign key (tblProfile_ProfileId) 
        references tblProfile (ProfileId);

    alter table tblProfileUser 
        add index FK_3fjlfegdmexpvk28iebtsyxmh (tblUserLogin_UserId), 
        add constraint FK_3fjlfegdmexpvk28iebtsyxmh 
        foreign key (tblUserLogin_UserId) 
        references tblUserLogin (UserId);

    alter table tblUserProfileCategory 
        add index FK_ga1x8wvmacwkhmhcujjem68of (tblNonProfitCategory_CategoryId), 
        add constraint FK_ga1x8wvmacwkhmhcujjem68of 
        foreign key (tblNonProfitCategory_CategoryId) 
        references tblNonProfitCategory (CategoryId);

    alter table tblUserProfileCategory 
        add index FK_7od3e92bt7qwq1nq9ywe2vsia (tblUserLogin_UserId), 
        add constraint FK_7od3e92bt7qwq1nq9ywe2vsia 
        foreign key (tblUserLogin_UserId) 
        references tblUserLogin (UserId);

    alter table tblUserRole 
        add index FK_d48njwjs44p14ps4usurvmd0x (tblUserLogin_UserId), 
        add constraint FK_d48njwjs44p14ps4usurvmd0x 
        foreign key (tblUserLogin_UserId) 
        references tblUserLogin (UserId);

    alter table tblUserRole 
        add index FK_qnac7cvbxumkyv4t5ue67tiyk (tblRole_RoleId), 
        add constraint FK_qnac7cvbxumkyv4t5ue67tiyk 
        foreign key (tblRole_RoleId) 
        references tblRole (RoleId);

    alter table tblWishlistItem 
        add index FK_19hl1icj2rvanel3bqdsk902w (tblProfile_ProfileId), 
        add constraint FK_19hl1icj2rvanel3bqdsk902w 
        foreign key (TblProfile_ProfileId) 
        references tblProfile (ProfileId);

    alter table tblWishlistItem 
        add index FK_78bahvj0h03nmtbskn4wshwua (tblItemCategory_ItemCategory), 
        add constraint FK_78bahvj0h03nmtbskn4wshwua 
        foreign key (TblItemCategory_ItemCategory) 
        references tblItemCategory (CategoryId);

COMMIT;

