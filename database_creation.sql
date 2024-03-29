USE [meltemDB]
GO
/****** Object:  User [admin]    Script Date: 5/3/2020 5:29:36 AM ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [admin]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [admin]
GO
ALTER ROLE [db_datareader] ADD MEMBER [admin]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [admin]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [admin]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [admin]
GO
/****** Object:  Table [dbo].[branch_attendance]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[branch_attendance](
	[branch_id] [int] NOT NULL,
	[student_id] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[branch_courses]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[branch_courses](
	[branch_id] [int] IDENTITY(1,1) NOT NULL,
	[branch_name] [nvarchar](30) NOT NULL,
	[branch_teacher_id] [int] NOT NULL,
	[branch_capacity] [int] NOT NULL,
	[branch_student_id] [int] NULL,
 CONSTRAINT [PK_branch_courses] PRIMARY KEY CLUSTERED 
(
	[branch_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[classroom_attendance]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[classroom_attendance](
	[course_id] [int] NOT NULL,
	[student_id] [int] NOT NULL,
	[teacher_id] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[classroom_courses]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[classroom_courses](
	[classroom_id] [int] NOT NULL,
	[classroom_teacher_id] [int] NULL,
	[classroom_course_id] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[classrooms]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[classrooms](
	[classroom_id] [int] IDENTITY(1,1) NOT NULL,
	[classroom_teacher_id] [int] NOT NULL,
	[classroom_name] [nvarchar](30) NOT NULL,
	[classroom_capacity] [int] NOT NULL,
 CONSTRAINT [PK_classrooms] PRIMARY KEY CLUSTERED 
(
	[classroom_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[courses]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[courses](
	[course_classroom_id] [int] NOT NULL,
	[course_id] [int] IDENTITY(1,1) NOT NULL,
	[course_name] [nvarchar](30) NOT NULL,
	[course_teacher_id] [int] NOT NULL,
	[course_teacher_name] [nvarchar](60) NULL,
 CONSTRAINT [PK_courses] PRIMARY KEY CLUSTERED 
(
	[course_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[meetings]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[meetings](
	[meeting_id] [int] IDENTITY(1,1) NOT NULL,
	[meeting_title] [nvarchar](30) NOT NULL,
	[meeting_summary] [nvarchar](200) NOT NULL,
	[meeting_date] [datetime] NOT NULL,
 CONSTRAINT [PK_meetings] PRIMARY KEY CLUSTERED 
(
	[meeting_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[payments]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payments](
	[payment_id] [int] IDENTITY(1,1) NOT NULL,
	[payment_student_id] [int] NOT NULL,
	[payment_date] [datetime] NOT NULL,
	[payment_status] [bit] NOT NULL,
 CONSTRAINT [PK_payments] PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[students]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[students](
	[student_id] [int] IDENTITY(1,1) NOT NULL,
	[student_name] [nvarchar](30) NOT NULL,
	[student_lastname] [nvarchar](30) NOT NULL,
	[orientation_start] [datetime] NOT NULL,
	[orientation_end] [datetime] NOT NULL,
	[parent_name] [nvarchar](30) NOT NULL,
	[parent_lastname] [nvarchar](30) NOT NULL,
	[parent_phone] [nvarchar](30) NULL,
	[parent_email] [nvarchar](30) NOT NULL,
	[payment_monthly] [bigint] NOT NULL,
 CONSTRAINT [PK_students] PRIMARY KEY CLUSTERED 
(
	[student_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[teachers]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[teachers](
	[teacher_id] [int] IDENTITY(1,1) NOT NULL,
	[teacher_name] [nvarchar](30) NOT NULL,
	[teacher_lastname] [nvarchar](30) NOT NULL,
	[teacher_phone] [nvarchar](15) NULL,
	[teacher_email] [nvarchar](30) NULL,
	[teacher_auth] [nchar](10) NOT NULL,
 CONSTRAINT [PK_teachers] PRIMARY KEY CLUSTERED 
(
	[teacher_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 5/3/2020 5:29:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[user_name] [nvarchar](30) NOT NULL,
	[user_password] [nvarchar](20) NOT NULL,
	[user_auth] [int] NOT NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[branch_courses] ADD  CONSTRAINT [DF_branch_courses_branch_capacity]  DEFAULT ((25)) FOR [branch_capacity]
GO
ALTER TABLE [dbo].[classrooms] ADD  CONSTRAINT [DF_classrooms_classroom_capacity]  DEFAULT ((25)) FOR [classroom_capacity]
GO
ALTER TABLE [dbo].[courses] ADD  CONSTRAINT [DF_courses_course_classroom_id]  DEFAULT ((1)) FOR [course_classroom_id]
GO
ALTER TABLE [dbo].[payments] ADD  CONSTRAINT [DF_payments_payment_status]  DEFAULT ((0)) FOR [payment_status]
GO
